package repository;

import domain.Cursa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Costi on 11.03.2017.
 */
public class CurseRepository implements IRepository<Integer, Cursa> {
    JdbcUtils jdbcUtils;

    public CurseRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public int size() {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select count(*) as SIZE from curse")) {
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    return result.getInt("SIZE");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return 0;
    }

    @Override
    public void save(Cursa entity) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into curse values (?,?,?,?,?)")) {
            preStmt.setInt(1, entity.getId());
            preStmt.setString(2, entity.getDestinatie());
            preStmt.setTimestamp(3, entity.getDate_time());
            preStmt.setInt(4, entity.getLocuriDisponibile());
            preStmt.setInt(5, entity.getOficiu());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }

    }

    @Override
    public void delete(Integer integer) {
        Connection con = jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM curse where curse.idcursa=?");
            {
                preparedStatement.setInt(1, integer);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer integer, Cursa entity) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement(
                "UPDATE firmatransport.curse SET destinatie=?,data_ora=?,locuri_disponibile=?,oficiu=?" +
                        " where idcursa=? ")) {
            preStmt.setInt(5, integer);
            preStmt.setString(1, entity.getDestinatie());
            preStmt.setTimestamp(2, entity.getDate_time());
            preStmt.setInt(3, entity.getLocuriDisponibile());
            preStmt.setInt(4, entity.getOficiu());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Cursa findOne(Integer integer) {
        Connection connection = jdbcUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from firmatransport.curse where idcursa=?");
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idcursa");
                String destinatie = resultSet.getString("destinatie");
                Timestamp data_ora = resultSet.getTimestamp("data_ora");
                int locuriDisponibile = resultSet.getInt("locuri_disponibile");
                int oficiu = resultSet.getInt("oficiu");
                return new Cursa(id,destinatie,data_ora,locuriDisponibile,oficiu);
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
    public Iterable<Cursa> findAll() {
        Connection con=jdbcUtils.getConnection();
        List<Cursa> curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from firmatransport.curse")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("idcursa");
                    String destinatie = result.getString("destinatie");
                    Timestamp data_ora = result.getTimestamp("data_ora");
                    int locuriDisponibile = result.getInt("locuri_disponibile");
                    int oficiu = result.getInt("oficiu");
                    curse.add(new Cursa(id,destinatie,data_ora,locuriDisponibile,oficiu));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return curse;
    }
}
