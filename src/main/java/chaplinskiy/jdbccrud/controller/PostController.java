package chaplinskiy.jdbccrud.controller;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController() {
       postService = new PostService();
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public void createPost(String value) {
        postService.createPost(value);
    }

    public void deletePostById(Long idDelete) {
        postService.deletePostById(idDelete);
    }

    public Post getPostById(Long id) {
        return postService.getPostById(id);
    }

    public void updatePost(Post post) {
        postService.updatePost(post);
    }
}
