package domain;

/**
 * Created by Costi on 11.03.2017.
 */
public class Client {
    private int id;
    private String nume;
    private int idCursa;

    public Client(String nume, int idCursa) {
        id=0;
        this.nume = nume;
        this.idCursa = idCursa;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", idCursa=" + idCursa +
                '}';
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

    public int getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(int idCursa) {
        this.idCursa = idCursa;
    }

    public Client(int id, String nume, int idCursa) {

        this.id = id;
        this.nume = nume;
        this.idCursa = idCursa;
    }
}
