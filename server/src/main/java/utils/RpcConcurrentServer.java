package utils;

import rpcprotocol.ClientRpcWorker;
import services.IServer;

import java.net.Socket;

/**
 * Created by grigo on 2/25/16.
 */
public class RpcConcurrentServer extends AbsConcurrentServer {
    private IServer chatServer;
    public RpcConcurrentServer(int port, IServer chatServer) {
        super(port);
        this.chatServer = chatServer;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
       // ChatClientRpcWorker worker=new ChatClientRpcWorker(chatServer, client);
        ClientRpcWorker worker=new ClientRpcWorker(chatServer, client);
        Thread tw=new Thread(worker);
        return tw;
    }
}
