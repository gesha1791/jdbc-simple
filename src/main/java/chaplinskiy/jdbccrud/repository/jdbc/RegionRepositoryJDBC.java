package chaplinskiy.jdbccrud.repository.jdbc;

import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.repository.RegionRepository;
import chaplinskiy.jdbccrud.util.Constants;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

import static chaplinskiy.jdbccrud.util.Constants.*;
import static chaplinskiy.jdbccrud.util.Constants.createRegionSQL;
import static chaplinskiy.jdbccrud.util.DbConnection.getConnection;

public class RegionRepositoryJDBC implements RegionRepository {
    private final Connection connection;

    public RegionRepositoryJDBC() {
        connection = getConnection();
    }

    @Override
    public Region create(Region region) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createRegionSQL);
            preparedStatement.setString(1, region.getName());
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        try {
            String name = region.getName();
            Long id = region.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(updateRegionSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return region;

    }

    @Override
    public Region getById(Long id) {
        Region region = new Region();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRegionSQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Long idRegion = resultSet.getLong("id");
                String name = resultSet.getString("name");

                region.setId(idRegion);
                region.setName(name);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return region;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllRegionSQL);

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                Region region = new Region(id, name);

                regions.add(region);
            }

            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return regions;
    }

    @Override
    public void deleteById(Long id) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRegionSQL);
            preparedStatement.setLong(1, id);

            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
