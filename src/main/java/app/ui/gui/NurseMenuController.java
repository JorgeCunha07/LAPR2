package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NurseMenuController implements Initializable {



    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;
    private Stage stage ;
    private Scene scene;
    private Parent root ;
    /**
     * It loads the fxml file and displays it in a new stage
     *
     * @param fxmlString The name of the fxml file to be loaded.
     */
    public void replaceSceneContent(String fxmlString) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlString));
        Parent parent = loader.load();
        stage = new Stage();
       // stage.hide();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Record Vaccine Administration");
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.show();

    }

    @FXML
   // A method that is called when the button is clicked.
   public void button3(ActionEvent event) throws Exception {
        replaceSceneContent("RecordTheAdmn.fxml");

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
}
