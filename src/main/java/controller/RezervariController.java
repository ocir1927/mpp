package controller;

import domain.Cursa;
import domain.Rezervare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.CurseRepository;
import services.CurseServices;
import services.RezervariServices;
import utils.Observable;
import utils.Observer;

import java.security.Timestamp;

/**
 * Created by Costi on 18.03.2017.
 */
public class RezervariController implements Observer<Rezervare> {

    RezervariServices rezervariServices;
    CurseServices curseServices;
    private ObservableList<Rezervare> modelRezervari;
    private ObservableList<Cursa> modelCurse;
    ////////////////////////////////////////////////////////////

    @FXML
    public TableView<Cursa> twCurse;
    @FXML
    public TableColumn tcId;

    public TableColumn tcDestinatie;
    public TableColumn tcDataOra;
    public TableColumn tcLocuri;

    public TableView<Rezervare> twRezervari;
    public TableColumn tcNrLoc;
    public TableColumn tcClient;


    public RezervariController() {
    }

    public void setService(RezervariServices rezervariServices, CurseServices curseServices){
        this.rezervariServices = rezervariServices;
        this.curseServices = curseServices;
        modelRezervari= FXCollections.observableArrayList(rezervariServices.getAllRezervari());
        modelCurse= FXCollections.observableArrayList(curseServices.getAll());
        initTableCurse();
    }

    public RezervariController(RezervariServices rezervariServices, CurseServices curseServices) {
        this.rezervariServices = rezervariServices;
        this.curseServices = curseServices;
        modelRezervari= FXCollections.observableArrayList(rezervariServices.getAllRezervari());
    }

    @Override
    public void update(Observable<Rezervare> observable) {
        RezervariServices services=(RezervariServices) observable;
        modelRezervari.setAll(rezervariServices.getAllRezervari());
    }

    private void initTableCurse(){
        twCurse.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        twCurse.setItems(modelCurse);
        tcId.setCellValueFactory(new PropertyValueFactory<Cursa,Integer>("id"));
        tcDestinatie.setCellValueFactory(new PropertyValueFactory<Cursa,String>("destinatie"));
        tcDataOra.setCellValueFactory(new PropertyValueFactory<Cursa,Timestamp>("date_time"));
        tcLocuri.setCellValueFactory(new PropertyValueFactory<Cursa,Integer>("locuriDisponibile"));
    }

    private void initTableClienti(){
        twRezervari.setItems(modelRezervari);
    }

}

