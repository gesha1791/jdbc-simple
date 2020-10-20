package chaplinskiy.jdbccrud.service;

import chaplinskiy.jdbccrud.model.User;
import chaplinskiy.jdbccrud.repository.UserRepository;
import chaplinskiy.jdbccrud.repository.jdbc.UserRepositoryJDBC;

import java.util.List;


public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryJDBC();
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
}
