package repository;
import domain.Operator;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Costi on 20.03.2017.
 */
public class OperatoriRepository implements IOperatorRepository<Integer, Operator> {
    JdbcUtils jdbcUtils;

    public OperatoriRepository(Properties properties) {
        jdbcUtils=new JdbcUtils(properties);
    }

    public Operator findBy(String username, String password) {
        Connection conn=jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from firmatransport.operatori where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                return new Operator(username,password,email);
            }
            else {
                System.out.println("Nu exista in baza de date");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Operator findByUsername(String username){
        Connection conn=jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from firmatransport.operatori where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new Operator(username,password,email);
            }
            else {
                System.out.println("Nu exista in baza de date");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void save(Operator entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer, Operator entity) {

    }

    @Override
    public Operator findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Operator> findAll() {
        return null;
    }
}
