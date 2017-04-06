package domain;

import java.io.Serializable;

/**
 * Created by Costi on 11.03.2017.
 */
public class Client implements Serializable {
    private int id;
    private String nume;

    public Client(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Client(String nume) {
        this.id=0;
        this.nume = nume;
    }
}
