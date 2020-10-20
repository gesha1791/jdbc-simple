package chaplinskiy.jdbccrud.repository.jdbc;

import chaplinskiy.jdbccrud.controller.UserController;
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
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User getById(Long id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement preparedStatementRole = connection.prepareStatement(getRoleById);
            PreparedStatement preparedStatementRegion = connection.prepareStatement(getRegionSQL);

            if(resultSet.next()){
                Long idUser = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                long region_id = resultSet.getLong("region_id");
                long role_id = resultSet.getLong("role_id");

                preparedStatementRegion.setLong(1, region_id);

                ResultSet resultSetRegion = preparedStatementRegion.executeQuery();
                Region region = new Region();
                while (resultSetRegion.next()){
                    long idRegion = resultSetRegion.getLong("id");
                    String name = resultSetRegion.getString("name");

                    region = new Region(idRegion, name);
                }



                preparedStatementRole.setLong(1, role_id);
                ResultSet resultSetRole = preparedStatementRole.executeQuery();
                String role = "";
                while (resultSetRole.next()){
                    role = resultSetRole.getString("role");
                }


                // @ToDo dont get list post
                user.setId(idUser);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setLastName(lastName);
                user.setRegion(region);

                if (role.equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }
                if (role.equals(Role.MODERATOR.toString())){
                    user.setRole(Role.MODERATOR);
                }
                if (role.equals(Role.USER.toString())){
                    user.setRole(Role.USER);
                }
            }

            preparedStatement.close();
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

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllUserSQL);

            PreparedStatement preparedStatementRole = connection.prepareStatement(getRoleById);
            PreparedStatement preparedStatementRegion = connection.prepareStatement(getRegionSQL);

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                long region_id = resultSet.getLong("region_id");
                long role_id = resultSet.getLong("role_id");

                preparedStatementRegion.setLong(1, region_id);

                ResultSet resultSetRegion = preparedStatementRegion.executeQuery();
                Region region = new Region();
                while (resultSetRegion.next()){
                    long idRegion = resultSetRegion.getLong("id");
                    String name = resultSetRegion.getString("name");

                    region = new Region(idRegion, name);
                }



                preparedStatementRole.setLong(1, role_id);
                ResultSet resultSetRole = preparedStatementRole.executeQuery();
                String role = "";
                while (resultSetRole.next()){
                    role = resultSetRole.getString("role");
                }


                // @ToDo dont get list post
                User user = new User();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setLastName(lastName);
                user.setRegion(region);

                if (role.equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }
                if (role.equals(Role.MODERATOR.toString())){
                    user.setRole(Role.MODERATOR);
                }
                if (role.equals(Role.USER.toString())){
                    user.setRole(Role.USER);
                }

                users.add(user);

            }

            statement.close();
            preparedStatementRegion.close();
            preparedStatementRole.close();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteById(Long id) {
        Statement statement = null;
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
