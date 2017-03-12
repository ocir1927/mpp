package repository;

import domain.Client;
import domain.Cursa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Costi on 11.03.2017.
 */
public class ClientiCurseRepository {
    JdbcUtils jdbcUtils;
    public ClientiCurseRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }

    public void add(String nume,int cursa){
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into firmatransport.clienti_curse values (?,?,?)")) {
            preStmt.setInt(1, 0);
            preStmt.setString(2, nume);
            preStmt.setInt(3, cursa);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }
    public List<Client> getAll(){
        Connection con=jdbcUtils.getConnection();
        List<Client> clienti=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from firmatransport.clienti_curse")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String numeClient = result.getString("nume_client");
                    int idCursa = result.getInt("id_cursa");
                    clienti.add(new Client(id,numeClient,idCursa));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return clienti;
    }

    public List<Client> getAllByCursa(int cursa){
        Connection con=jdbcUtils.getConnection();
        List<Client> clienti=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from firmatransport.clienti_curse where id_cursa=?")) {
            preStmt.setInt(1,cursa);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String numeClient = result.getString("nume_client");
                    int idCursa = result.getInt("id_cursa");
                    clienti.add(new Client(id,numeClient,idCursa));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return clienti;
    }


}
