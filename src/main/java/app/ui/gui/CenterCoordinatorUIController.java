package app.ui.gui;

import app.controller.AnalyseCenterPerformanceController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CenterCoordinatorUIController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton SelectOptionCenterCOORD;

    private Stage stage;

    private final double SCENE_WIDTH = 540.0;
    private final double SCENE_HEIGHT = 278.0;

    private String analysisPerformance = "AnalysisWindowStart.fxml";

    private String vaccinationStatistics = "CheckVaccinationStatistics.fxml";

    private String showLegacyData = "ShowLegacyDataStart.fxml";

    private AnalyseCenterPerformanceController analyseCenterPerformanceController;

    /**
     * The function loads the FXML file and sets the controller for the FXML file
     */
    public void replaceSceneContent(String fxmlString) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlString));
        Parent parent;
        stage = new Stage();
        if (Objects.equals(fxmlString, analysisPerformance)){
            loader.setController(new AnalysisWindowStartOneController(analyseCenterPerformanceController));
            parent = loader.load() ;
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Vaccination Center Performance Analysis");
        }else if (Objects.equals(fxmlString, showLegacyData)){
            parent = loader.load() ;
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Legacy Data");

        }else {
            parent = loader.load() ;
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Vaccination Statistics");

        }
        stage.setScene(new Scene(parent));
        stage.show();

    }

    /**
     * It creates an alert with the given parameters
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

        return alert;
    }

    // Creating a new instance of the AnalyseCenterPerformanceController class.
    public CenterCoordinatorUIController() {
        analyseCenterPerformanceController = new AnalyseCenterPerformanceController();
    try {
        analyseCenterPerformanceController.getVaccinationCenterForTheUser();
    } catch (Exception e) {
        createAlert(Alert.AlertType.ERROR,"Error","Error",e.getMessage()).show();
                }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MenuItem option1 = new MenuItem("Check the Vaccination Statistics");
        MenuItem option2 = new MenuItem("Analysis of the performance of the center");
        MenuItem option3 = new MenuItem("Import data from a legacy system");

        SelectOptionCenterCOORD.getItems().add(option1);
        SelectOptionCenterCOORD.getItems().add(option2);
        SelectOptionCenterCOORD.getItems().add(option3);


        option1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Check the Vaccination Statistics");
                try {
                    replaceSceneContent(vaccinationStatistics);
                } catch (Exception e) {
                    createAlert(Alert.AlertType.ERROR,"Error","Error",e.getMessage());
                }
            }
        });

        option2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Analysis of the performance of the center");
                try {
                    replaceSceneContent(analysisPerformance);
                } catch (Exception e) {
                    createAlert(Alert.AlertType.ERROR,"Error","Error",e.getMessage());
                }
            }
        });

        option3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Import data from a legacy system");
                try {
                    replaceSceneContent(showLegacyData);
                } catch (Exception e) {
                    createAlert(Alert.AlertType.ERROR,"Error","Error",e.getMessage());
                }

            }
        });


    }

}
