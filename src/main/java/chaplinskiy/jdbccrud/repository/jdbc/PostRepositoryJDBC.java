package chaplinskiy.jdbccrud.repository.jdbc;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.repository.PostRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.Converters.convertToLocalDateViaSqlDate;
import static chaplinskiy.jdbccrud.util.DbConnection.getConnection;

public class PostRepositoryJDBC implements PostRepository {
    private final Connection connection;

    public PostRepositoryJDBC() {
        connection = getConnection();
    }


    @Override
    public Post create(Post post) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createPostSQL);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(post.getCreate()));
            preparedStatement.setTimestamp(3,  Timestamp.valueOf(post.getUpdated()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updatePostSQL);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(post.getUpdated()));
            preparedStatement.setLong(3,  post.getId());
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public Post getById(Long id) {
        Post post = new Post();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPostSQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Long idRegion = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Date created = resultSet.getDate("created");
                Date updated = resultSet.getDate("updated");

                post.setId(id);
                post.setContent(content);
                post.setCreate(convertToLocalDateViaSqlDate(created));
                post.setUpdated(convertToLocalDateViaSqlDate(updated));
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllPostSQL);

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String content = resultSet.getString("content");
                Date dateCreated = resultSet.getDate("created");
                Date dateUpdated = resultSet.getDate("updated");

                LocalDateTime created = convertToLocalDateViaSqlDate(dateCreated);
                LocalDateTime updated = convertToLocalDateViaSqlDate(dateUpdated);


                Post post = new Post(id, content, created, updated);
                posts.add(post);
            }

            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return posts;
    }

    @Override
    public void deleteById(Long id) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deletePostSQL);
            preparedStatement.setLong(1, id);

            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
