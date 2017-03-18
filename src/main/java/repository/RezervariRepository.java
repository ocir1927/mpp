package repository;

import domain.Client;
import domain.Cursa;
import domain.Rezervare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Costi on 11.03.2017.
 */
public class RezervariRepository {
    JdbcUtils jdbcUtils;
    public RezervariRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }

    public void add(Rezervare rezervare){
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into firmatransport.rezervari values (?,?,?,?)")) {
            preStmt.setInt(1, 0);
            preStmt.setInt(2, rezervare.getId_cursa());
            preStmt.setInt(3, rezervare.getIdClient());
            preStmt.setInt(4, rezervare.getNrLocuri());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    public void delete(int idRezervare){
        Connection conn=jdbcUtils.getConnection();
        try (PreparedStatement preStmt = conn.prepareStatement("DELETE FROM firmatransport.rezervari WHERE id=?")) {
            preStmt.setInt(1, idRezervare);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    public List<Rezervare> getAll(){
        Connection con=jdbcUtils.getConnection();
        List<Rezervare> rezervari=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from firmatransport.rezervari")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int idClient = result.getInt("client");
                    int idCursa = result.getInt("id_cursa");
                    int nrLocuri = result.getInt("nr_locuri");
                    rezervari.add(new Rezervare(id,idCursa,idClient,nrLocuri));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return rezervari;
    }

    public List<Rezervare> getAllByCursa(int cursa){
        Connection con=jdbcUtils.getConnection();
        List<Rezervare> rezervari=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from firmatransport.rezervari where id_cursa=?")) {
            preStmt.setInt(1,cursa);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int idClient= result.getInt("client");
                    int idCursa = result.getInt("id_cursa");
                    int nrLocuri= result.getInt("nr_locuri");
                    rezervari.add(new Rezervare(id,idCursa,idClient,nrLocuri));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return rezervari;
    }


}
