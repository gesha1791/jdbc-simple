package chaplinskiy.jdbccrud.view;

import chaplinskiy.jdbccrud.controller.UserController;
import chaplinskiy.jdbccrud.model.User;

import java.time.LocalDateTime;
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
                    int regionId = scanner.nextInt();

                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);

                    userController.createUser(user, regionId);

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
                    Long regionIdUpdate = Long.valueOf(scanner.nextInt());

                    User userUpdate = new User();
                    userUpdate.setId(idUpdate);
                    userUpdate.setFirstName(firstNameUpdate);
                    userUpdate.setLastName(lastNameUpdate);

                    userController.updateUser(userUpdate, regionIdUpdate);

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
