package chaplinskiy.jdbccrud.service;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.model.Role;
import chaplinskiy.jdbccrud.model.User;
import chaplinskiy.jdbccrud.repository.PostRepository;
import chaplinskiy.jdbccrud.repository.RegionRepository;
import chaplinskiy.jdbccrud.repository.UserRepository;
import chaplinskiy.jdbccrud.repository.jdbc.PostRepositoryJDBC;
import chaplinskiy.jdbccrud.repository.jdbc.RegionRepositoryJDBC;
import chaplinskiy.jdbccrud.repository.jdbc.UserRepositoryJDBC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final PostRepository postRepository;

    public UserService() {
        userRepository = new UserRepositoryJDBC();
        regionRepository = new RegionRepositoryJDBC();
        postRepository = new PostRepositoryJDBC();
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

    public void createUser(User user, int id) {
        Region region = new Region();
        region.setId(Long.valueOf(id));
        user.setRegion(region);
        user.setRole(Role.USER);
        userRepository.create(user);
    }

    public void updateUser(User userUpdate, Long regionIdUpdate) {
        Region regionUpdate = new Region();
        regionUpdate.setId(regionIdUpdate);

        userUpdate.setRegion(regionUpdate);
        userRepository.update(userUpdate);


    }
}
