package domain;

import java.io.Serializable;

/**
 * Created by Costi on 17.03.2017.
 */
public class Operator implements Serializable{
    private String username;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "Operator{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Operator(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
