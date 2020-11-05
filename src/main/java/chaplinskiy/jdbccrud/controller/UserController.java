package chaplinskiy.jdbccrud.controller;


import chaplinskiy.jdbccrud.model.User;
import chaplinskiy.jdbccrud.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    public User getUserById(Long id) {
       return userService.getUserBuId(id);
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    public void createUser(User user) {
        userService.createUser(user);
    }

    public void updateUser(User userUpdate) {
        userService.updateUser(userUpdate);
    }
}
