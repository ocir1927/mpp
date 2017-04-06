package server;

import services.*;
import utils.AbstractServer;
import utils.RpcConcurrentServer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by grigo on 2/25/16.
 */
public class StartRpcServer {
    private static int defaultPort=55555;
    public static void main(String[] args) {
        // UserRepository userRepo=new UserRepositoryMock();
        Properties serverProps=new Properties();
        try {
            serverProps.load(StartRpcServer.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatserver.properties "+e);
            return;
        }
      /*  UserRepository userRepo=new UserRepositoryJdbc(serverProps);
        MessageRepository messRepo=new MessageRepositoryJdbc(serverProps);*/
        OperatoriServices operatoriServices=new OperatoriServices();
        CurseServices curseServices=new CurseServices();
        RezervariServices rezervariServices=new RezervariServices();
        ClientiServices clientiServices=new ClientiServices();

        IServer chatServerImpl=new ChatServerImpl(operatoriServices,curseServices,rezervariServices,clientiServices);
        int chatServerPort=defaultPort;
        try {
            chatServerPort = Integer.parseInt(serverProps.getProperty("firmatransport.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+chatServerPort);
        AbstractServer server = new RpcConcurrentServer(chatServerPort, chatServerImpl);
        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        }
    }
}
