import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rpcprotocol.ServerRpcProxy;
import services.IServer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by grigo on 2/25/16.
 */
public class StartRpcClient extends Application {
    AnchorPane rootLayout;
    static IServer server;
    private static int defaultChatPort = 55555;
    private static String defaultServer = "localhost";

    public static void main(String[] args) {
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartRpcClient.class.getResourceAsStream("/firmatransportclient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatclient.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("firmatransport.server.host", defaultServer);
        int serverPort = defaultChatPort;
        try {
            serverPort = Integer.parseInt(clientProps.getProperty("firmatransport.server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);
        server = new ServerRpcProxy(serverIP, serverPort);
        launch(args);
//        LoginController ctrl=new LoginController(server);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Firma Transport");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(StartClient.class.getResource("logIn.fxml"));

        rootLayout = fxmlLoader.load();
        //* RezervariController rezervariController=fxmlLoader.getController();
        // rezervariController.setService(rezervariServices,curseServices,clientiServices);*//*
        LoginController loginController = fxmlLoader.getController();
        loginController.setServer(server);
        //loginController.setServices(operatoriServices, rezervariServices, curseServices, clientiServices);

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
