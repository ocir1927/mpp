package domain;

/**
 * Created by Costi on 17.03.2017.
 */
public class Rezervare {
    private int id;
    private int id_cursa;
    private int idClient;
    private int nrLocuri;

    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id +
                ", id_cursa=" + id_cursa +
                ", idClient=" + idClient +
                ", nrLocuri=" + nrLocuri +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cursa() {
        return id_cursa;
    }

    public void setId_cursa(int id_cursa) {
        this.id_cursa = id_cursa;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Rezervare(int id_cursa, int idClient, int nrLocuri) {
        this.id=0;

        this.id_cursa = id_cursa;
        this.idClient = idClient;
        this.nrLocuri = nrLocuri;
    }

    public Rezervare(int id, int id_cursa, int idClient, int nrLocuri) {

        this.id = id;
        this.id_cursa = id_cursa;
        this.idClient = idClient;
        this.nrLocuri = nrLocuri;
    }
}
