package chaplinskiy.jdbccrud.repository.jdbc;

import chaplinskiy.jdbccrud.model.Post;
import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.model.Role;
import chaplinskiy.jdbccrud.model.User;
import chaplinskiy.jdbccrud.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.DbConnection.getConnection;

public class UserRepositoryJDBC implements UserRepository {
    private final Connection connection;

    public UserRepositoryJDBC() {
        connection = getConnection();
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createUserSQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getRegion().getId());
            if (user.getRole().name().equals("USER")) {
                preparedStatement.setLong(4, 3);
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


    @Override
    public User update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getRegion().getId());
            preparedStatement.setLong(4, user.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementPost = null;

        User user = new User();

        try {

            preparedStatementUser = connection.prepareStatement(getUserById);
            preparedStatementPost = connection.prepareStatement(getAllPostUserByID);

            preparedStatementUser.setLong(1, id);
            preparedStatementPost.setLong(1, id);

            ResultSet resultSetUser = preparedStatementUser.executeQuery();

            while (resultSetUser.next()){
                Long idUser = resultSetUser.getLong("id");
                String firstName = resultSetUser.getString("firstName");
                String lastName = resultSetUser.getString("lastName");
                String role = resultSetUser.getString("role");
                String region = resultSetUser.getString("name");

                user.setId(idUser);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setLastName(lastName);
                user.setRegion(new Region(region));

                if (role.equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }
                if (role.equals(Role.MODERATOR.toString())){
                    user.setRole(Role.MODERATOR);
                }
                if (role.equals(Role.USER.toString())){
                    user.setRole(Role.USER);
                }


                ResultSet resultSetPost = preparedStatementPost.executeQuery();
                List<Post> posts = new ArrayList<>();
                while (resultSetPost.next()){
                    Post post = new Post();
                    post.setId(resultSetPost.getLong("id"));
                    post.setContent(resultSetPost.getString("content"));
                    posts.add(post);
                }

                user.setPosts(posts);
            }

            preparedStatementPost.close();
            preparedStatementUser.close();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllUserSQL);
            preparedStatement = connection.prepareStatement(getAllPostUserByID);

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String role = resultSet.getString("role");
                String region = resultSet.getString("name");

                User user = new User();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setLastName(lastName);
                user.setRegion(new Region(region));

                if (role.equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }
                if (role.equals(Role.MODERATOR.toString())){
                    user.setRole(Role.MODERATOR);
                }
                if (role.equals(Role.USER.toString())){
                    user.setRole(Role.USER);
                }

                preparedStatement.setLong(1,user.getId());

                ResultSet resultSetPost = preparedStatement.executeQuery();
                List<Post> posts = new ArrayList<>();
                while (resultSetPost.next()){
                    Post post = new Post();
                    post.setId(resultSetPost.getLong("id"));
                    post.setContent(resultSetPost.getString("content"));
                    posts.add(post);
                }

                user.setPosts(posts);

                users.add(user);
            }

            statement.close();
            preparedStatement.close();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL);
            preparedStatement.setLong(1, id);

            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
