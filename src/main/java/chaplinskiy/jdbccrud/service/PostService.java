package chaplinskiy.jdbccrud.service;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.repository.PostRepository;
import chaplinskiy.jdbccrud.repository.jdbc.PostRepositoryJDBC;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(){
        postRepository = new PostRepositoryJDBC();
    }


    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public void createPost(String value) {
        Post post = new Post();
        post.setContent(value);
        LocalDateTime now = LocalDateTime.now();
        post.setCreate(now);
        post.setUpdated(now);

        postRepository.create(post);
    }

    public void deletePostById(Long idDelete) {
        postRepository.deleteById(idDelete);
    }

    public Post getPostById(Long id) {
        return postRepository.getById(id);
    }

    public void updatePost(Post post) {
        postRepository.update(post);
    }
}
