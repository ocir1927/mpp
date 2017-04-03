package controller;

import domain.Client;
import domain.Cursa;
import domain.Rezervare;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ClientiServices;
import services.CurseServices;
import services.RezervariServices;
import observer.Observable;
import observer.Observer;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Costi on 18.03.2017.
 */
public class RezervariController implements Observer<Cursa> {

    RezervariServices rezervariServices;
    CurseServices curseServices;
    ClientiServices clientiServices;
    private ObservableList<Rezervare> modelRezervari;
    private ObservableList<Cursa> modelCurse;
    private ObservableList<Client> modelClienti;
    private FilteredList<Cursa> filteredList;
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public TableView<Cursa> twCurse;
    @FXML
    public TableColumn tcId;

    public TableColumn tcDestinatie;
    public TableColumn tcDataOra;
    public TableColumn tcLocuri;

    public TableView<Client> twRezervari;
    public TableColumn tcNrLoc;
    public TableColumn tcClient;

    public TextField tfCautaDestinatie;
    public TextField tfCautaData;

    public TextField tfNumeClient;
    public TextField tfNrLocuri;

   /////////////////////////////////////////////////////////////////////////////////////////////////////


    public RezervariController() {
    }

    public void setService(RezervariServices rezervariServices, CurseServices curseServices, ClientiServices clientiServices) {
        this.rezervariServices = rezervariServices;
        this.curseServices = curseServices;
        this.clientiServices = clientiServices;
        modelClienti = FXCollections.observableArrayList(new ArrayList<Client>());
        modelCurse = FXCollections.observableArrayList(curseServices.getAll());
        twCurse.getSelectionModel().selectedItemProperty().addListener(changeCursaTableItemListener());
        filteredList=new FilteredList<Cursa>(modelCurse,e->true);
        curseServices.addObserver(this);
        initTableCurse();
        initTableClienti();
    }

    public void handleRezervare(){
        String numeClient=tfNumeClient.getText().toString();
        int nrLocuri= Integer.parseInt(tfNrLocuri.getText().toString());
        int idCursa = 0;
        int idClient;
        Cursa cursa = null;
        if(twCurse.getSelectionModel().getSelectedItem()!=null){
            cursa=twCurse.getSelectionModel().getSelectedItem();
            idCursa=cursa.getId();
        }
        if(clientiServices.findByName(numeClient)==0){
            clientiServices.addClient(new Client(numeClient));
        }

        idClient=clientiServices.findByName(numeClient);
        Rezervare rez = new Rezervare(idCursa, idClient, nrLocuri);
        rezervariServices.addRezervare(rez);
        cursa.setLocuriDisponibile(cursa.getLocuriDisponibile() - nrLocuri);
        curseServices.updateCursa(idCursa,cursa);
        showClienti(twCurse.getSelectionModel().getSelectedItem());

    }

    public void handleReset(){
        tfCautaData.setText("");
        tfCautaDestinatie.setText("");
        twCurse.setItems(modelCurse);
    }

    public void handleCauta(){
        String destinatie=tfCautaDestinatie.getText().toString().toLowerCase();
        String dateTime=tfCautaData.getText().toString().toLowerCase();
        List<Cursa> curseFiltrate = modelCurse.stream()
                .filter(p -> p.getDestinatie().toLowerCase().contains(destinatie) &&
                        p.getDate_time().toString().contains(dateTime)
                ).collect(Collectors.toList());
        twCurse.setItems(FXCollections.observableArrayList(curseFiltrate));

    }

    public void handleCautaDestinatie(){
        tfCautaDestinatie.textProperty().addListener((observableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Cursa>) cursa->{
                if(newValue==null ||newValue.isEmpty())
                    return true;
                String lowerCaseFilter=newValue.toLowerCase();
                if (cursa.getDestinatie().toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;

            } );
        });
        twCurse.setItems(filteredList);
    }
    public void handleCautaDataOra(){
        tfCautaData.textProperty().addListener((observableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Cursa>) cursa->{
                if(newValue==null ||newValue.isEmpty())
                    return true;
                String lowerCaseFilter=newValue.toLowerCase();
                if (cursa.getDate_time().toString().toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;

            } );
        });
        twCurse.setItems(filteredList);
    }


    private void initTableCurse() {
        twCurse.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        twCurse.setItems(modelCurse);
        tcId.setCellValueFactory(new PropertyValueFactory<Cursa, Integer>("id"));
        tcDestinatie.setCellValueFactory(new PropertyValueFactory<Cursa, String>("destinatie"));
        tcDataOra.setCellValueFactory(new PropertyValueFactory<Cursa, Timestamp>("date_time"));
        tcLocuri.setCellValueFactory(new PropertyValueFactory<Cursa, Integer>("locuriDisponibile"));
    }

    private void initTableClienti() {
//        twRezervari.setItems(modelRezervari);
        tcClient.setCellValueFactory(new PropertyValueFactory<Rezervare, Integer>("nume"));
        //tcNrLoc.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(twRezervari.getItems().indexOf(column)));
        tcNrLoc.setCellFactory(col -> new TableCell<Client, String>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index+1));
                }
            }
        });
    }

    private void showClienti(Cursa cursa) {
        if (cursa != null) {
            modelClienti = FXCollections.observableArrayList(new ArrayList<Client>());
            modelRezervari = FXCollections.observableArrayList(rezervariServices.getAllByCursa(cursa.getId()));
            for (Rezervare rez : modelRezervari) {
                for (int i=0;i<rez.getNrLocuri();i++){
                    modelClienti.add(clientiServices.findOne(rez.getIdClient()));
                }
            }
            for (int i = modelClienti.size(); i < 18; i++) {
                modelClienti.add(new Client("- - -"));
            }
            twRezervari.setItems(modelClienti);
        }
    }


    public ChangeListener<Cursa> changeCursaTableItemListener() {
        ChangeListener<Cursa> changeListener = (observable, oldValue, newValue) -> {
            showClienti(newValue);
        };
        return changeListener;
    }

    @Override
    public void update(Observable<Cursa> observable) {
        CurseServices services = (CurseServices) observable;
        modelCurse.setAll(curseServices.getAll());
    }

}

