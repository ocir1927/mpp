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

/**
 * Created by grigo on 12/15/15.
 */
public class ClientRpcWorker implements Runnable, IClient {
    private IServer server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcWorker(IServer server, Socket connection) {
        System.out.println("S-a creat un client rpc worker");
        this.server = server;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    //  private static Response errorResponse=new Response.Builder().type(ResponseType.ERROR).build();
    private Response handleRequest(Request request) {
        Response response = null;
        if (request.type() == RequestType.LOGIN) {
            System.out.println("Login request ..." + request.type());
            // UserDTO udto=(UserDTO)request.data();
            Operator operator = (Operator) request.data();
            try {
                server.login(operator.getUsername(), operator.getPassword());
                return okResponse;
            } catch (ClientException e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type() == RequestType.GET_CURSE) {
            System.out.println("Get curse request");
            ArrayList<Cursa> curse = new ArrayList<>();
            try {
                curse = server.getAllCurse();
                connected = false;
                return new Response.Builder().type(ResponseType.CURSE).data(curse).build();

            } catch (ClientException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type() == RequestType.GET_REZERVARI_BY_CURSA) {
            System.out.println("Get rezervari by cursa request ...");
//            MessageDTO mdto=(MessageDTO)request.data();
//            Message message=DTOUtils.getFromDTO(mdto);
            ArrayList<Rezervare> rezervari = new ArrayList<>();
            try {
                rezervari = server.getAllByCursa((Integer) request.data());
                connected = false;
                return new Response.Builder().type(ResponseType.REZERVARI_BY_CURSA).data(rezervari).build();
            } catch (ClientException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type() == RequestType.FIND_CLIENT) {
            System.out.println("Find client Request ...");
            int idClient = (int) request.data();
//            User user=DTOUtils.getFromDTO(udto);
            try {
                Client client = server.findClient(idClient);
//                UserDTO[] frDTO=DTOUtils.getDTO(friends);
                return new Response.Builder().type(ResponseType.CLIENT).data(client).build();
            } catch (ClientException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.ADD_REZERVARE) {
            System.out.println("Add rezervare Request ...");
            Rezervare rezervare= (Rezervare) request.data();
//            User user=DTOUtils.getFromDTO(udto);
            try {
               // Client client = server.findClient(idClient);
//                UserDTO[] frDTO=DTOUtils.getDTO(friends);
                server.addRezervare(rezervare);
                return okResponse;
            } catch (ClientException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        output.writeObject(response);
        output.flush();


    }
}
