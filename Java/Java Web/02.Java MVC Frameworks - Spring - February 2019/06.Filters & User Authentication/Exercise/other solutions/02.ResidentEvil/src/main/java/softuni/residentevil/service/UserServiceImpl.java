package softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.residentevil.domain.entities.Role;
import softuni.residentevil.domain.entities.User;
import softuni.residentevil.domain.models.service.UserServiceModel;
import softuni.residentevil.repository.RoleRepository;
import softuni.residentevil.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private static final String USER_NOT_FOUND_ERR = "Username not found.";
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
    this.encoder = bCryptPasswordEncoder;
  }


  @Override
  public boolean register(UserServiceModel serviceModel) {
    this.seedRolesInDb();

    User user = this.modelMapper.map(serviceModel, User.class);
    user.setPassword(this.encoder.encode(serviceModel.getPassword()));
    this.assignRolesToUser(user);
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCredentialsNonExpired(true);
    user.setEnabled(true);

    try {
      this.userRepository.saveAndFlush(user);
      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public UserServiceModel findById(String id) {
    User user = this.userRepository.findById(id).orElse(null);
    if (user != null){
      return this.modelMapper.map(user, UserServiceModel.class);
    }
    return null;
  }

  @Override
  public List<UserServiceModel> listAllUsers(String username) {
    return this.userRepository.listAllUsersExceptAdmin(username)
        .stream()
        .map(u->this.modelMapper.map(u, UserServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public boolean update(UserServiceModel model) {
    User user = this.userRepository.findById(model.getId()).orElse(null);
    try{
      assert user != null;
      user.setRoles(model.getRoles());
      this.userRepository.saveAndFlush(user);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Role> listAllRoles() {
    return this.roleRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERR));
  }

  private void seedRolesInDb() {
    if (this.roleRepository.count() == 0){
      Role admin = new Role();
      admin.setAuthority("ROLE_ADMIN");
      this.roleRepository.saveAndFlush(admin);
      Role moderator = new Role();
      moderator.setAuthority("ROLE_MODERATOR");
      this.roleRepository.saveAndFlush(moderator);
      Role user = new Role();
      user.setAuthority("ROLE_USER");
      this.roleRepository.saveAndFlush(user);
    }
  }

  private void assignRolesToUser(User user){
    if (this.userRepository.count() == 0){
      user.getRoles().add(this.roleRepository.findByAuthority("ROLE_ADMIN"));
      user.getRoles().add(this.roleRepository.findByAuthority("ROLE_MODERATOR"));
      user.getRoles().add(this.roleRepository.findByAuthority("ROLE_USER"));
    } else {
      user.getRoles().add(this.roleRepository.findByAuthority("ROLE_USER"));
    }
  }
}
