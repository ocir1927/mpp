package domain;

/**
 * Created by Costi on 17.03.2017.
 */
public class Operator {
    private String username;
    private String password;
    private String email;
    private int oficiu;

    @Override
    public String toString() {
        return "Operator{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", oficiu=" + oficiu +
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

    public int getOficiu() {
        return oficiu;
    }

    public void setOficiu(int oficiu) {
        this.oficiu = oficiu;
    }

    public Operator(String username, String password, String email, int oficiu) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.oficiu = oficiu;
    }
}
