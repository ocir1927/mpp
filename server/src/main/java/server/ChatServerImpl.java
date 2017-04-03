package server;

import domain.Operator;
import services.IServer;
import services.OperatoriServices;
import services.ServerException;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Mar 18, 2009
 * Time: 1:39:47 PM
 */
public class ChatServerImpl implements IServer {

    private OperatoriServices operatoriServices;
   // private MessageRepository messageRepository;
    //private Map<String, IChatClient> loggedClients;


    @Override
    public Operator login(String username, String password) throws ServerException {
        Operator operator=operatoriServices.login(username, password);
        if(operator != null){
            System.out.println("Logare corecta");
        }
        else throw new ServerException("Authentication failed");
        return operator;
    }

    public ChatServerImpl(OperatoriServices operatoriServices) {
        this.operatoriServices = operatoriServices;
    }

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
