import domain.Cursa;
import repository.RezervariRepository;
import repository.CurseRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by Costi on 10.03.2017.
 */
public class Main {
    public static void main(String[] args) {
       /* System.out.println("plm");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("bd.config"));
            prop.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JdbcUtils jdbcUtils = new JdbcUtils(prop);
        Connection connection = jdbcUtils.getConnection();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2017, 2, 19, 20, 1, 1);


        Timestamp data_ora = new Timestamp(cal.getTime().getTime());
        Cursa cursa = new Cursa("Turda", data_ora, 15,1);

        System.out.println(data_ora);

        String query="INSERT INTO curse VALUES (0," +
                "'"+cursa.getDestinatie()+"'" + "," +"'"+ data_ora +"'"+ ","
                + cursa.getLocuriDisponibile() +","+cursa.getOficiu() + ")";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
          *//*  ResultSet resultSet = statement.executeQuery("SELECT * FROM curse");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("idcursa") + " "
                        + resultSet.getString("destinatie") + " " + resultSet.getTimestamp("data_ora"));
            }*//*
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Properties properties=new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2016, 8, 22, 11, 11, 1);


        Timestamp data_ora = new Timestamp(cal.getTime().getTime());
        Cursa cursa = new Cursa("Blabla", data_ora, 18,4);

        CurseRepository repo = new CurseRepository(properties);
        RezervariRepository rezervariRepository =new RezervariRepository(properties);
//        System.out.println(repo.size());
       // repo.save(cursa);
       // repo.delete(17);
//        repo.findAll().forEach(e-> System.out.println(e));
//        repo.update(16,cursa);
//        repo.delete(16);
//        rezervariRepository.add("Beligan Sergiu",15);
        rezervariRepository.getAll().forEach(e-> System.out.println(e));
//        rezervariRepository.getAllByCursa(2).forEach(e-> System.out.println(e));

    }
}
