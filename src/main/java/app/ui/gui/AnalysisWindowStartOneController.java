package app.ui.gui;

import app.controller.AnalyseCenterPerformanceController;
import app.domain.model.Analysis;
import app.domain.shared.UtilsCheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisWindowStartOneController implements Initializable {

    // A variable that is used to store the date picker that the user selects.
    @FXML
    private DatePicker DataPickerAnalysis;

    // A variable that is used to store the button that the user clicks on.
    @FXML
    private Button StartAnalysisButton;

    // A variable that is used to store the text that the user enters in the text field.
    @FXML
    private TextField TimeInterval_Text;

    private AnalyseCenterPerformanceController analyseCenterPerformanceController;

    // A constructor that receives an object of type AnalyseCenterPerformanceController and assigns it to the variable
    // analyseCenterPerformanceController.
    public AnalysisWindowStartOneController(AnalyseCenterPerformanceController analyseCenterPerformanceController) {
        this.analyseCenterPerformanceController = analyseCenterPerformanceController;
    }


    /**
     * The function checks if the time interval is valid, if it is, it creates an analysis object and sends it to the next
     * scene
     *
     * @param event the event that triggered the method.
     */
    @FXML
    void HandleStartAnalysisButton(ActionEvent event) {

        try {
            UtilsCheck.checkTimeInterval(TimeInterval_Text.getText());
            Analysis analysis = this.analyseCenterPerformanceController.analyzeVaccinationCenterPerformance(DataPickerAnalysis.getValue(), Integer.parseInt(TimeInterval_Text.getText()));
            replaceSceneContent(analysis);

        } catch (Exception e){
            createAlert(Alert.AlertType.ERROR,"Error","Error", e.getMessage()).show();
        }


    }

    /**
     * It creates an alert object with the given parameters
     *
     * @param alertType The type of alert. This can be one of the following:
     * @param title The title of the alert.
     * @param header The text to be displayed in the header area of the dialog.
     * @param message The message to be displayed in the alert.
     * @return An alert object.
     */
    private Alert createAlert(Alert.AlertType alertType, String title, String header, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

   /**
     * It loads the FXML file, sets the controller, loads the parent, creates a new stage, sets the modality, sets the
     * title, sets the scene, and shows the stage
     *
     * @param analysis the object that contains the data that will be displayed in the new window.
     */
         return alert;
    }

    /**
     * The initialize function is called when the FXML file is loaded
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle This is a resource bundle that contains the resources for the controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * It loads the FXML file, sets the controller, loads the parent, creates a new stage, sets the modality, sets the
     * title, sets the scene, and shows the stage
     *
     * @param analysis the object that contains the data that will be displayed in the new window.
     */
    public void replaceSceneContent(Analysis analysis) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ANalysisWindowEnd.fxml"));
        loader.setController(new ANalysisWindowEndController(analysis));
        Parent parent = loader.load() ;
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Vaccination Center Performance Analysis Information");
        stage.setScene(new Scene(parent));
        stage.show();

    }
}
