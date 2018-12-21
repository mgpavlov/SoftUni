package usersystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystemapp.domain.entities.User;
import usersystemapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsersByEmailProvider(final String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public void deactivateInactiveUsers(final Date date) {
        this.userRepository
                .findAllByLastTimeLoggedInBefore(date)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public void save(final User user) {
        this.userRepository.save(user);
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

   /* @Override
    public void deleteAllUsersDeletedTrue() {
        this.userRepository.deleteAllByIsDeleted();
    }*/

    /*@Override
    public List<String> getUserNamesAndAgeByAgeRange(final int lowBound, final int highBound) {
        return this.userRepository.findAllByAgeBetween(lowBound, highBound)
                .stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .map(user -> String.format("%s %s - %d years old",
                        user.getFirstName(), user.getLastName(), user.getAge()))
                .collect(Collectors.toList());
    }*/
}
