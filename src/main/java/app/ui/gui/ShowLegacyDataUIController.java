package app.ui.gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.domain.model.LegacyData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowLegacyDataUIController  implements Initializable{
    @FXML
    private TableView<LegacyData> tableview = new TableView<>();

    @FXML
    private TableColumn<LegacyData, String> snsUser = new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, String> vacName= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, String> dose= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, String> lotNum= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, LocalDateTime> schedule= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, LocalDateTime> arrival= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, LocalDateTime> administered= new TableColumn<>();

    @FXML
    private TableColumn<LegacyData, LocalDateTime> leaving= new TableColumn<>();

    private ObservableList<LegacyData> data  ;


    public ShowLegacyDataUIController(ArrayList<LegacyData> legacyData) {
         data = FXCollections.observableArrayList(legacyData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        administered.setCellValueFactory(new PropertyValueFactory<>("administered"));
        leaving.setCellValueFactory(new PropertyValueFactory<>("leftTime"));
        snsUser.setCellValueFactory(new PropertyValueFactory<>("SNSUser"));
        vacName.setCellValueFactory(new PropertyValueFactory<>("vaccineName"));
        dose.setCellValueFactory(new PropertyValueFactory<>("dose"));
        lotNum.setCellValueFactory(new PropertyValueFactory<>("loteNumber"));
        schedule.setCellValueFactory(new PropertyValueFactory<>("scheduled"));
        tableview.setItems(data);

    }




}
