package services;

import domain.Client;
import domain.Cursa;
import domain.Rezervare;

import java.util.ArrayList;

/**
 * Created by Costi on 01.04.2017.
 */
public interface IServer{
    void login(String username,String password) throws ClientException;
    ArrayList<Cursa> getAllCurse() throws ClientException;
    ArrayList<Rezervare> getAllByCursa(int idCursa) throws ClientException;
    Client findClient(int id) throws ClientException;
    void addRezervare(Rezervare rezervare) throws ClientException;
    Client findClient(String nume) throws ClientException;
    void addClient(Client client) throws ClientException;
    void updateCursa(Cursa cursa) throws ClientException;
}
