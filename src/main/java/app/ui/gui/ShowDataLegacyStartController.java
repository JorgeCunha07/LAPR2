package app.ui.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.controller.LegacyDataController;
import app.domain.model.EntryRecord;
import app.domain.model.LeavingRecord;
import app.domain.model.LegacyData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowDataLegacyStartController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnShowData;

    @FXML
    private ComboBox<?> cbCriteria;

    @FXML
    private ComboBox<?> cbIncDec;

    @FXML
    private ComboBox<?> cbMethod;

    @FXML
    private TextField txtFileName;

    @FXML
    private TextField txtMessage;

    private LegacyDataController controller;
    private ShowLegacyDataUIController showLegacyDataController;

    private File selectedFile;
    private Stage stage;

    public void replaceSceneContentToLegacyShow(ArrayList<LegacyData> legacyData) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImportLegacyData.fxml"));
        loader.setController(new ShowLegacyDataUIController(legacyData));
        Parent parent = loader.load() ;
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Legacy Data");
        stage.setScene(new Scene(parent));
        stage.show();

    }


    public ShowDataLegacyStartController() {
        controller = new LegacyDataController();

    }

    @FXML
    void initialize() {

        btnBrowse.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();

                selectedFile = fileChooser.showOpenDialog(stage);
                if(selectedFile!=null) {
                    txtFileName.setText(selectedFile.getAbsolutePath());
                }
            }
        });


        btnShowData.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(selectedFile!=null) {
                    ArrayList<LegacyData> legacyData = controller.getLegacyData();
                    ArrayList<EntryRecord> entryData = controller.getEntryRecords();
                    ArrayList<LeavingRecord> leavingData = controller.getLeavingRecords();
                    if (entryData.isEmpty() || leavingData.isEmpty()) {
                        System.out.println("There's no system data available to import.");
                    } else{
                        System.out.println("Exporting this system data...");
                        controller.exportToLegacyData(entryData, leavingData);
                    }
                    boolean status = controller.addToArrayListForGui(selectedFile.getAbsolutePath());
                    String message = "";
                    if(status) {
                        message = "File read";
                    }
                    else {
                        message = "Not read";
                    }
                    int method = cbMethod.getSelectionModel().getSelectedIndex()+1;
                    int criteria = cbCriteria.getSelectionModel().getSelectedIndex()+1;
                    int incDec = cbIncDec.getSelectionModel().getSelectedIndex()+1;
                    int dataSize = controller.getSizeOfArrayList();
                    if(dataSize>0) {
                        if(method == 1) {//bubble sort
                            controller.bubbleSort(dataSize, criteria, incDec);
                            message = message + " ,Bubble Sorting done, Criteria: " + cbCriteria.getSelectionModel().getSelectedItem() +
                                    " ,IncDec: " + cbIncDec.getSelectionModel().getSelectedItem();
                            try {
                                replaceSceneContentToLegacyShow(legacyData);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        else {//selection sort
                            controller.selectionSort(criteria, incDec);
                            message = message + " ,Selection Sorting done, Criteria: " + cbCriteria.getSelectionModel().getSelectedItem() +
                                    " ,IncDec: " + cbIncDec.getSelectionModel().getSelectedItem();
                            try {
                                replaceSceneContentToLegacyShow(legacyData);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }


                    }
                    else {
                        message = message + "Some Error Occurred";
                    }

                    txtMessage.setText(message);


                }
            }
        });


    }
    private Alert createAlert(Alert.AlertType alertType, String title, String header, String message){
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }

}
