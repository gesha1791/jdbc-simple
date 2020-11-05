package chaplinskiy.jdbccrud.service;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.model.Role;
import chaplinskiy.jdbccrud.model.User;
import chaplinskiy.jdbccrud.repository.PostRepository;
import chaplinskiy.jdbccrud.repository.RegionRepository;
import chaplinskiy.jdbccrud.repository.UserRepository;
import chaplinskiy.jdbccrud.repository.hibernate.UserRepositoryHibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryHibernate();
    }

    public List<User> getAllUser() {
        return userRepository.getAll();
    }

    public User getUserBuId(Long id) {
        return userRepository.getById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void createUser(User user) {
        user.setRole(Role.USER);
        userRepository.create(user);
    }

    public void updateUser(User userUpdate) {
        userRepository.update(userUpdate);
    }
}
