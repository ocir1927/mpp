package server;

import domain.Client;
import domain.Cursa;
import domain.Operator;
import domain.Rezervare;
import services.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Mar 18, 2009
 * Time: 1:39:47 PM
 */
public class ChatServerImpl implements IServer {

    private OperatoriServices operatoriServices;
    private CurseServices curseServices;
    private RezervariServices rezervariServices;
    private ClientiServices clientiServices;
    // private MessageRepository messageRepository;
    //private Map<String, IChatClient> loggedClients;


    @Override
    public void login(String username, String password) throws ClientException {
        Operator operator = operatoriServices.login(username, password);
        if (operator != null) {
            System.out.println("Logare corecta");
        } else throw new ClientException("Authentication failed");
    }

    @Override
    public ArrayList<Cursa> getAllCurse() throws ClientException {
        ArrayList<Cursa> curse = curseServices.getAll();
        if (curse.size() != 0) {
            System.out.println("Returnare curse");
        } else throw new ClientException("Nu exista curse");
//        curse.removeIf(cursa -> cursa.getId()>1);
        return curse;
    }

    @Override
    public ArrayList<Rezervare> getAllByCursa(int idCursa) throws ClientException {
        ArrayList<Rezervare> rezervari = rezervariServices.getAllByCursa(idCursa);
        if (rezervari.size() != 0) {
            System.out.println("Returnare curse");
        } else throw new ClientException("Nu exista curse");
//        curse.removeIf(cursa -> cursa.getId()>1);
        return rezervari;
    }

    @Override
    public Client findClient(int id) throws ClientException {
        Client client = clientiServices.findOne(id);
        if (client != null) {
            System.out.println("Returnare client");
        } else throw new ClientException("Nu exista acest client");
        return client;
    }

    public ChatServerImpl(OperatoriServices operatoriServices, CurseServices curseServices, RezervariServices rezervariServices, ClientiServices clientiServices) {
        this.operatoriServices = operatoriServices;
        this.curseServices = curseServices;
        this.rezervariServices = rezervariServices;
        this.clientiServices = clientiServices;
    }

/*    public ChatServerImpl(OperatoriServices operatoriServices, CurseServices curseServices) {
        this.operatoriServices = operatoriServices;
        this.curseServices = curseServices;
    }*/

   /* public synchronized void login(User user, IChatClient client) throws ChatException {
        User userR=userRepository.findBy(user.getId(),user.getPasswd());
        if (userR!=null){
            if(loggedClients.get(user.getId())!=null)
                throw new ChatException("User already logged in.");
            loggedClients.put(user.getId(), client);
            notifyFriendsLoggedIn(user);
        }else
            throw new ChatException("Authentication failed.");
    }*/

  /*  private boolean isLogged(User u){
        return loggedClients.get(u.getId())!=null;
    }*/

}
