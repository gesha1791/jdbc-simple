package chaplinskiy.jdbccrud.repository.hibernate;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.repository.PostRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static chaplinskiy.jdbccrud.util.HibernateSessionFactory.getSessionFactory;

public class PostRepositiryHibernate implements PostRepository {

    @Override
    public Post create(Post post) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public Post update(Post post) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Post getPost = session.get(Post.class, post.getId());
        getPost.setUpdated(post.getUpdated());
        getPost.setContent(post.getContent());

        session.update(getPost);
        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public Post getById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Post post = (Post) session.get(Post.class, id);
        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public List<Post> getAll() {
        Session session = getSessionFactory().openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List posts = session.createQuery("from Post").getResultList();

        transaction.commit();
        session.close();
        return posts;
    }

    @Override
    public void deleteById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Post post = (Post) session.get(Post.class, id);

        session.delete(post);
        transaction.commit();
        session.close();
    }
}
