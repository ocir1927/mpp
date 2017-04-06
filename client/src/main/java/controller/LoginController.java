package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.*;

import java.io.IOException;

/**
 * Created by Costi on 20.03.2017.
 */
public class LoginController {
    OperatoriServices operatoriServices;
    CurseServices curseServices;
    ClientiServices clientiServices;
    RezervariServices rezervariServices;

    IServer server;

    public TextField tfUsername;
    public TextField tfPassword;
    public Button btnLogin;


    public LoginController() {

    }

    public void setServices(OperatoriServices operatoriServices, RezervariServices rezervariServices, CurseServices curseServices, ClientiServices clientiServices) {
        this.operatoriServices = operatoriServices;
        this.clientiServices = clientiServices;
        this.curseServices = curseServices;
        this.rezervariServices = rezervariServices;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    public void login() {
        String username = tfUsername.getText().toString();
        //  Operator operator = operatoriServices.findByUsername(username);
        String password = tfPassword.getText().toString();

        try {
            server.login(username, password);
        } catch (ClientException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }


        try {
            // Parent parent= FXMLLoader.load(getClass().getResource("/mainView.fxml"));

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mainView.fxml"));
            Parent parent = fxmlLoader.load();
            RezervariController rezervariController = fxmlLoader.getController();
//            rezervariController.setService(rezervariServices, curseServices, clientiServices);
            rezervariController.setServer(server);
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



