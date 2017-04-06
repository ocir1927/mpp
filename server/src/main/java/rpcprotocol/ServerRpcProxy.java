package rpcprotocol;

import domain.Client;
import domain.Cursa;
import domain.Operator;
import domain.Rezervare;
import services.ClientException;
import services.IClient;
import services.IServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by grigo on 12/15/15.
 */
public class ServerRpcProxy implements IServer {
    private String host;
    private int port;

    private IClient client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;
    public ServerRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();
    }

 /*   public void login(String username, String password) throws ClientException {
        initializeConnection();
        UserDTO udto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ChatException(err);
        }
    }*/


    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request)throws ClientException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new ClientException("Error sending object "+e);
        }

    }

    private Response readResponse() throws ClientException {
        Response response=null;
        try{
            /*synchronized (responses){
                responses.wait();
            }
            response = responses.remove(0);    */
            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void login(String username, String password) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        UserDTO udto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(new Operator(username,password,"")).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return ;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
    }

    @Override
    public ArrayList<Cursa> getAllCurse() throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request req=new Request.Builder().type(RequestType.GET_CURSE).data(null).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
        return (ArrayList<Cursa>) response.data();
    }

    @Override
    public ArrayList<Rezervare> getAllByCursa(int idCursa) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request req=new Request.Builder().type(RequestType.GET_REZERVARI_BY_CURSA).data(idCursa).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
        return (ArrayList<Rezervare>) response.data();
    }

    @Override
    public Client findClient(int id) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request req=new Request.Builder().type(RequestType.FIND_CLIENT).data(id).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
        return (Client) response.data();
    }

    @Override
    public void addRezervare(Rezervare rezervare) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        UserDTO udto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.ADD_REZERVARE).data(rezervare).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return ;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
    }

    @Override
    public Client findClient(String nume) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request req=new Request.Builder().type(RequestType.FIND_CLIENT_NAME).data(nume).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
        return (Client) response.data();
    }

    @Override
    public void addClient(Client client) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        UserDTO udto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.ADD_CLIENT).data(client).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            //this.client=client;
            return ;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
    }

    @Override
    public void updateCursa(Cursa cursa) throws ClientException {
        try {
            initializeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        UserDTO udto= DTOUtils.getDTO(user);
        Request req=new Request.Builder().type(RequestType.UPDATE_CURSA).data(cursa).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            //this.client=client;
            return ;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ClientException(err);
        }
    }

    private void initializeConnection() throws Exception {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }


    private void handleUpdate(Response response){
      /*  if (response.type()== ResponseType.FRIEND_LOGGED_IN){

            User friend=DTOUtils.getFromDTO((UserDTO) response.data());
            System.out.println("Friend logged in "+friend);
            try {
                client.friendLoggedIn(friend);
            } catch (ChatException e) {
                e.printStackTrace();
            }
        }
        if (response.type()== ResponseType.FRIEND_LOGGED_OUT){
            User friend=DTOUtils.getFromDTO((UserDTO)response.data());
            System.out.println("Friend logged out "+friend);
            try {
                client.friendLoggedOut(friend);
            } catch (ChatException e) {
                e.printStackTrace();
            }
        }

        if (response.type()== ResponseType.NEW_MESSAGE){
            Message message=DTOUtils.getFromDTO((MessageDTO)response.data());
            try {
                client.messageReceived(message);
            } catch (ChatException e) {
                e.printStackTrace();
            }
        }*/
    }

    private boolean isUpdate(Response response){
        return response.type()== ResponseType.FRIEND_LOGGED_OUT || response.type()== ResponseType.FRIEND_LOGGED_IN || response.type()== ResponseType.NEW_MESSAGE;
    }
    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{

                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                    finished=true;
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
