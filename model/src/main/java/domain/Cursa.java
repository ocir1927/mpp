package domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Costi on 10.03.2017.
 */
public class Cursa implements Serializable {
    private int id;

    private String destinatie;

    private Timestamp date_time;

    private int locuriDisponibile;

    private int oficiu;

    public Cursa(String destinatie, Timestamp date_time, int locuriDisponibile, int oficiu) {
        this.id = 0;
        this.destinatie = destinatie;
        this.date_time = date_time;
        this.locuriDisponibile = locuriDisponibile;
        this.oficiu = oficiu;
    }

    public Cursa(int id, String destinatie, Timestamp date_time, int locuriDisponibile, int oficiu) {
        this.id = id;
        this.destinatie = destinatie;
        this.date_time = date_time;
        this.locuriDisponibile = locuriDisponibile;
        this.oficiu = oficiu;
    }

    public int getOficiu() {
        return oficiu;
    }

    public void setOficiu(int oficiu) {
        this.oficiu = oficiu;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Cursa{" +
                "id=" + id +
                ", destinatie='" + destinatie + '\'' +
                ", date_time=" + date_time +
                ", locuriDisponibile=" + locuriDisponibile +
                ", oficiu=" + oficiu +
                '}';
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public int getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(int locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

}

