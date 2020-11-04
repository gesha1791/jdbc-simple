package chaplinskiy.jdbccrud.view;

import chaplinskiy.jdbccrud.controller.PostController;
import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.PrintUtils.printMessage;
import static chaplinskiy.jdbccrud.util.ScannerSingleton.getScanner;

public class PostView {
    private final Scanner scanner;
    private final PostController postController;


    public PostView() {
        scanner = getScanner();
        postController = new PostController();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(postViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    List<Post> allPosts = postController.getAllPosts();
                    allPosts.stream().forEach(System.out::println);
                    break;
                case 2:
                    printMessage(createUpdatePostValueMessage);
                    String value = scanner.next();
                    postController.createPost(value);
                    break;
                case 3:
                    printMessage(idPostMessage);
                    Long idDelete = Long.valueOf(scanner.nextInt());
                    postController.deletePostById(idDelete);
                    break;
                case 4:
                    printMessage(idPostMessage);
                    Long idUpdate = Long.valueOf(scanner.nextInt());
                    printMessage(createUpdatePostValueMessage);
                    String content = scanner.next();
                    Post post = new Post();
                    post.setId(idUpdate);
                    post.setContent(content);
                    post.setUpdated(LocalDateTime.now());
                    postController.updatePost(post);

                    break;
                case 5:
                    printMessage(idPostMessage);
                    Long id = Long.valueOf(scanner.nextInt());
                    Post postById = postController.getPostById(id);

                    System.out.println(postById.toString());

                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongPostMessage);
            }
        }
    }
}
