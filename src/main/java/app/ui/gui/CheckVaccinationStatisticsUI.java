package app.ui.gui;

import app.controller.VaccinationStatisticsController;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckVaccinationStatisticsUI implements Initializable {

    private VaccinationStatisticsController controller;

    @FXML
    private TextField pathid;
    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    void backPressed() { // Return back

    }

    @FXML
    void btnExportPressed() {
        if(fromDate.getValue() == null || toDate.getValue() == null){
            Utils.createAlert(Alert.AlertType.ERROR, "Error", "Invalid Date", "Date is empty");
        } else if(fromDate.getValue().isAfter(toDate.getValue())){
            Utils.createAlert(Alert.AlertType.ERROR, "Error", "Invalid Date", "The first date must be before the second date");
        }else{
            controller.getFullyVaccinatedCount(fromDate.getValue(), toDate.getValue());
            controller.validatePath(pathid.getText().trim());
            controller.exportToCsv(pathid.getText().trim());
        }
    }

    /* @FXML
    void btnSearchPressed(ActionEvent event) {
        if(fromDate.getValue() == null || toDate.getValue() == null){
            Utils.createAlert(Alert.AlertType.ERROR, "Error", "Invalid Date", "Date is empty");
        } else if(fromDate.getValue().isAfter(toDate.getValue())){
            Utils.createAlert(Alert.AlertType.ERROR, "Error", "Invalid Date", "The first date must be before the second date");
        }else{
            controller.getFullyVaccinatedCount(fromDate.getValue(), toDate.getValue());
        }
    } */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new VaccinationStatisticsController();


    }
}

