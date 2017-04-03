package controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ClientiServices;
import services.CurseServices;
import services.OperatoriServices;
import services.RezervariServices;

/**
 * Created by Costi on 20.03.2017.
 */
public class LoginController {
    OperatoriServices operatoriServices;
    CurseServices curseServices;
    ClientiServices clientiServices;
    RezervariServices rezervariServices;

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

/*    public void login() {
        String username = tfUsername.getText().toString();
        Operator operator = operatoriServices.findByUsername(username);

        if(operator==null){
            MessageAlert.showErrorMessage(null,"Nu exista operatorul in baza de date");
        }
        else if(!tfPassword.getText().toString().equals(operator.getPassword())){
            MessageAlert.showErrorMessage(null,"Parola este gresita");
        }
        else
        if (tfPassword.getText().toString().equals(operator.getPassword())) {
            try {
               // Parent parent= FXMLLoader.load(getClass().getResource("/mainView.fxml"));

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/mainView.fxml"));
                Parent parent=fxmlLoader.load();
                RezervariController rezervariController=fxmlLoader.getController();
                rezervariController.setService(rezervariServices,curseServices,clientiServices);

                Stage stage=new Stage();
                Scene scene=new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


}
