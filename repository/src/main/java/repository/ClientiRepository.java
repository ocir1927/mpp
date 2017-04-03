package repository;

import domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Costi on 19.03.2017.
 */
public class ClientiRepository implements IRepository<Integer,Client> {
    JdbcUtils jdbcUtils;

    public ClientiRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Client findOne(Integer idClient){
        Connection connection = jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from firmatransport.clienti where idclient=?");
            preparedStatement.setInt(1, idClient);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idclient");
                String nume = resultSet.getString("nume");
                return new Client(id,nume);
            }
            else {
                System.out.println("Nu exista in baza de date");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findIdByName(String nume){
        Connection connection = jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from firmatransport.clienti where nume=?");
            preparedStatement.setString(1, nume);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idclient");
               // String nume = resultSet.getString("nume");
                return id;
            }
            else {
//                System.out.println("Nu exista in baza de date");
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void save(Client client) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into clienti values (?,?)")) {
            preStmt.setInt(1, 0);
            preStmt.setString(2, client.getNume());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer, Client entity) {

    }


    @Override
    public Iterable<Client> findAll() {
        return null;
    }
}
