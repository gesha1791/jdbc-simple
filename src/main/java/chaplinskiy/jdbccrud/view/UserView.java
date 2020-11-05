package chaplinskiy.jdbccrud.view;

import chaplinskiy.jdbccrud.controller.UserController;
import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.PrintUtils.printMessage;
import static chaplinskiy.jdbccrud.util.ScannerSingleton.getScanner;

public class UserView {
    private final Scanner scanner;
    private final UserController userController;


    public UserView() {
        scanner = getScanner();
        userController = new UserController();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(userViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    List<User> allUsers = userController.getAllUsers();
                    allUsers.stream().forEach(System.out::println);
                    break;
                case 2:
                    printMessage(createFirstNameUser);
                    String firstName = scanner.next();
                    printMessage(createSecondNameUser);
                    String lastName = scanner.next();
                    printMessage(createRegionNameUser);
                    String regionName = scanner.next();
                    Region region = new Region();
                    region.setName(regionName);

                    printMessage(countUserPostMessage);
                    int countPost = scanner.nextInt();


                    List<Post> posts = new ArrayList<>();
                    for (int i = 0; i < countPost; i++) {
                        Post post = new Post();
                        printMessage(createUpdatePostValueMessage);
                        String value = scanner.next();
                        post.setContent(value);
                        LocalDateTime now = LocalDateTime.now();
                        post.setUpdated(now);
                        post.setCreate(now);
                        posts.add(post);
                    }

                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setRegion(region);
                    user.setPosts(posts);

                    userController.createUser(user);

                    break;
                case 3:
                    printMessage(idUserMessage);
                    Long id = Long.valueOf(scanner.nextInt());

                    userController.deleteById(id);
                    break;
                case 4:
                    printMessage(idUserMessage);
                    Long idUpdate = Long.valueOf(scanner.nextInt());

                    printMessage(createFirstNameUser);
                    String firstNameUpdate = scanner.next();
                    printMessage(createSecondNameUser);
                    String lastNameUpdate = scanner.next();
                    printMessage(createRegionNameUser);
                    String regionNameUpdate = scanner.next();
                    Region regionUpdate = new Region();
                    regionUpdate.setName(regionNameUpdate);

                    printMessage(countUserPostMessage);
                    int countPostUpdate = scanner.nextInt();


                    List<Post> postsUdate = new ArrayList<>();
                    for (int i = 0; i < countPostUpdate; i++) {
                        Post post = new Post();
                        printMessage(createUpdatePostValueMessage);
                        String value = scanner.next();
                        post.setContent(value);
                        LocalDateTime now = LocalDateTime.now();
                        post.setUpdated(now);
                        post.setCreate(now);
                        postsUdate.add(post);
                    }

                    User userUpdate = new User();
                    userUpdate.setId(idUpdate);
                    userUpdate.setFirstName(firstNameUpdate);
                    userUpdate.setLastName(lastNameUpdate);
                    userUpdate.setRegion(regionUpdate);
                    userUpdate.setPosts(postsUdate);

                    userController.updateUser(userUpdate);

                    break;
                case 5:
                    printMessage(idUserMessage);
                    Long idUser = Long.valueOf(scanner.nextInt());

                    User userById = userController.getUserById(idUser);

                    System.out.println(userById.toString());

                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongUserMessage);
            }
        }
    }
}
