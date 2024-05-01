package ro.mpp2024.Repository;

import ro.mpp2024.Domain.Order;
import ro.mpp2024.Domain.validators.Validator;
import ro.mpp2024.Domain.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class OrderDBRepository implements IOrderRepository{

    private JdbcUtils jdbcUtils;
    private Validator<Order> validator;

    public OrderDBRepository(Properties properties, Validator<Order> validator) {
        this.validator = validator;
        jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public void save(Order elem) {
        validator.validate(elem);
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement insertStmt = connection
                    .prepareStatement("insert into orders(id_exemplar, status, userid) values (?,?,?)")) {
            insertStmt.setInt(1, elem.getId_exemplar());
            insertStmt.setString(2, elem.getStatus().toString());
//            insertStmt.setInt(3, elem.getIdSection());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer ID) {
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement deleteStmt = connection
                    .prepareStatement("delete from orders where id=?")) {
            deleteStmt.setInt(1, ID);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order elem) {
        validator.validate(elem);
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement updateStmt = connection
                    .prepareStatement("update from orders set id_exemplar=?, status=?, userid=?" +
                            "where id=?")) {
            updateStmt.setInt(1, elem.getId_exemplar());
            updateStmt.setString(2, elem.getStatus().toString());
//            updateStmt.setInt(3, elem.getIdSection());
            updateStmt.setInt(4, elem.getID());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order find(Integer ID) {
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement findStmt = connection
                    .prepareStatement("select * from orders where id=?")) {
            findStmt.setInt(1, ID);
            ResultSet resultSet = findStmt.executeQuery();
            if(resultSet.next() == false)
                return  null;
            Integer id = resultSet.getInt("id");
            Integer id_exemplar = resultSet.getInt("id_exemplar");
            Status status = Status.valueOf( resultSet.getString("status") );
            Integer userid = resultSet.getInt("userid");
//            Order order = new Order(id, quantity, userid, status);
//            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Order> findAll() {
        Collection<Order> orders = new ArrayList<>();
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement findStmt = connection
                    .prepareStatement("select * from orders ")) {
            ResultSet resultSet = findStmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer id_exemplar = resultSet.getInt("id_exemplar");
                Status status = Status.valueOf(resultSet.getString("status"));
                Integer userid = resultSet.getInt("userid");
//                Order order = new Order(id, quantity, userid, status);
//                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
