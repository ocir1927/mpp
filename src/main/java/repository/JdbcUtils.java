package repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by grigo on 3/4/17.
 */
public class JdbcUtils {
    private Properties jdbcProps;

    public JdbcUtils(Properties props){
        jdbcProps=props;
    }
    private  Connection instance=null;
    private Connection getNewConnection(){
        String driver=jdbcProps.getProperty("db_oficii.jdbc.driver");
        String url=jdbcProps.getProperty("db_oficii.jdbc.url");
        String user=jdbcProps.getProperty("db_oficii.jdbc.user");
        String pass=jdbcProps.getProperty("db_oficii.jdbc.pass");
        Connection con=null;
        try {
            System.out.println(driver);
            Class.forName(driver);
            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }

    public static Properties loadProps(){
        Properties properties=new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
