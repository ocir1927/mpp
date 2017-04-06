import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Costi on 03.04.2017.
 */
public class StartClient extends Application {
    AnchorPane rootLayout;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
/*        CurseServices curseServices = new CurseServices();
        RezervariServices rezervariServices = new RezervariServices();
        ClientiServices clientiServices = new ClientiServices();
        OperatoriServices operatoriServices = new OperatoriServices();*/


        stage.setTitle("Firma Transport");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(StartClient.class.getResource("logIn.fxml"));

        rootLayout = fxmlLoader.load();
       //* RezervariController rezervariController=fxmlLoader.getController();
       // rezervariController.setService(rezervariServices,curseServices,clientiServices);*//*
        LoginController loginController = fxmlLoader.getController();
        //loginController.setServices(operatoriServices, rezervariServices, curseServices, clientiServices);

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
