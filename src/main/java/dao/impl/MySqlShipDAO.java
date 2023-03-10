package dao.impl;

import connection.DataSource;
import dao.CityDao;
import dao.ShipDao;
import dao.StaffDao;
import dao.constants.*;

import exceptions.DbException;
import model.City;
import model.Ship;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlShipDAO implements ShipDao {
    @Override
    public void add(Ship ship) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_SHIP);
            int index = 0;
            preparedStatement.setString(++index, ship.getName());
            preparedStatement.setInt(++index, ship.getCapacity());
            preparedStatement.setInt(++index, ship.getNumberOfPorts());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot add Ship", e);
        }
    }

    @Override
    public void addRoute(long shipId, List<City> route) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            for (City city : route) {
                CityDao cityDao = new MySqlCityDAO();
                if(cityDao.getById(city.getId()).isEmpty()){
                    cityDao.add(city);
                }
                PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_ROUTE);
                int index = 0;
                preparedStatement.setLong(++index, shipId);
                preparedStatement.setLong(++index, city.getId());
                preparedStatement.execute();
            }
        }catch (SQLException e){
            throw new DbException("Cannot add Route to the ship", e);
        }
    }

    @Override
    public void addStaff(long shipId, List<Staff> staff) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            for (Staff person : staff) {
                StaffDao staffDao = new MySqlStaffDAO();
                if(staffDao.getById(person.getId()).isEmpty()){
                    staffDao.add(person);
                }
                PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.ADD_STAFF);
                int index = 0;
                preparedStatement.setLong(++index, person.getId());
                preparedStatement.setLong(++index, shipId);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            throw new DbException("Cannot add Staff to the ship", e);
        }
    }

    @Override
    public Optional<Ship> getById(long id) throws DbException {
        Ship ship;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_BY_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            ship = new Ship();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    List<City> city = (new MySqlCityDAO()).getAllCitiesByShipId(id);
                    List<Staff> staff = (new MySqlStaffDAO()).getAllStaffByShipId(id);
                    ship.setId(resultSet.getLong("id"));
                    ship.setName(resultSet.getString("name"));
                    ship.setCapacity(resultSet.getInt("capacity"));
                    ship.setNumberOfPorts(resultSet.getInt("number_of_ports"));
                    ship.setRoute(city);
                    ship.setStaff(staff);
                }
            }catch (SQLException e){
                throw new DbException("Cannot get All Cities", e);
            }
        }catch (SQLException e){
            throw new DbException("Cannot get Shup by id", e);
        }
        return Optional.of(ship);
    }

    @Override
    public List<Ship> getAll() throws DbException {
        List<Ship> shipList;
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_ALL);
            shipList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Ship ship = setShipValues(resultSet);
                    shipList.add(ship);
                }
            }
        }catch (SQLException e){
            throw new DbException("Cannot get all Ships", e);
        }
        return shipList;
    }

    private Ship setShipValues(ResultSet resultSet) throws SQLException, DbException {
        Ship ship = new Ship();
        ship.setId(resultSet.getLong("id"));
        ship.setName(resultSet.getString("name"));
        ship.setNumberOfPorts(resultSet.getInt("number_of_ports"));
        List<City> route = (new MySqlCityDAO()).getAllCitiesByShipId(ship.getId());
        List<Staff> staff = (new MySqlStaffDAO()).getAllStaffByShipId(ship.getId());
        ship.setRoute(route);
        ship.setStaff(staff);
        ship.setCapacity(resultSet.getInt("capacity"));
        return ship;
    }

    @Override
    public void update(Ship ship) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.UPDATE);
            int index = 0;
            preparedStatement.setString(++index, ship.getName());
            preparedStatement.setInt(++index, ship.getCapacity());
            preparedStatement.setInt(++index, ship.getNumberOfPorts());
            preparedStatement.setLong(++index, ship.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot update Ship", e);
        }
    }

    @Override
    public void delete(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.DELETE);
            int index = 0;
            preparedStatement.setLong(++index, id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DbException("Cannot delete Ship", e);
        }
    }

    @Override
    public int getCapacityByShipId(long id) throws DbException {
        try(Connection connection = DataSource.getConnection()) {
            int capacity = 0;
            PreparedStatement preparedStatement = connection.prepareStatement(ShipMysqlQuery.GET_CAPACITY_BY_SHIP_ID);
            int index = 0;
            preparedStatement.setLong(++index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                capacity = resultSet.getInt(1);
            }
            return capacity;
        }catch (SQLException e){
            throw new DbException("Cannot get Ship Capacity by Id", e);
        }
    }
}
