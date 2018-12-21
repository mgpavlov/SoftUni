package usersystemapp.service;

import usersystemapp.domain.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(Date date);

    void save(User user);

    long getUsersCount();

    /*void deleteAllUsersDeletedTrue();*/


    /*List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound);*/

}
