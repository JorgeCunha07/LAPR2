package app.ui.gui;

import app.controller.VaccineAdministrationController;
import app.domain.model.ScheduledVaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class RecordTheAmnController implements Initializable {


    @FXML
    private TextField LOTnum;

    @FXML
    private ComboBox<String> Users;

    @FXML
    private ComboBox<String> VaccinationCenter;

    @FXML
    private ComboBox<String> Vaccine;

    @FXML
    private Button RecordLeaving;

    private VaccineAdministrationController vaccineAdministrationController;

    private Properties properties;

    public RecordTheAmnController() {
        vaccineAdministrationController = new VaccineAdministrationController();
    }

    /**
     * This function reads the config.properties file and returns the value of the Recovery_Time property
     *
     * @return The recovery time in seconds.
     */
    private int readConfigProperties(){
        Properties prop = new Properties();
        String fileName = "config.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Integer.parseInt(prop.getProperty("Recovery_Time"));
    }


    /**
     * The function is called when the user clicks on the "Record Leaving" button. It removes the user from the waiting
     * list and adds a bulletin to the bulletin board
     *
     * @param event The event that triggered the action.
     */
    @FXML
    void HandleRecordLeaving(ActionEvent event) {

        // Remove User From Waiting Room List
        vaccineAdministrationController.removeUserWaitingListJavaFx();
        //add bulletin
        vaccineAdministrationController.addBulletinVaccine();

       vaccineAdministrationController.notifyAfterXMinutes(readConfigProperties());

    }




    // A function that is called when the GUI is initialized. It is used to fill the combo box with the vaccination
    // centers.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> vacCenters =vaccineAdministrationController.vaccinationCenterName();
        for (String vacCenterAux:vacCenters) {
            VaccinationCenter.getItems().add(vacCenterAux);
        }


    }
    /**
     * It gets the value of the selected item in the combo box and sends it to the controller, then it gets the users that
     * are waiting in the waiting room and adds them to the combo box
     *
     * @param event the event that triggered the method
     */
    @FXML
    void set(ActionEvent event) {
        vaccineAdministrationController.setVaccinationCenterJavaFX(VaccinationCenter.getValue());
        ArrayList<ScheduledVaccine> users =vaccineAdministrationController.waitingRoomUsersSnsNumber();
        for (ScheduledVaccine  usersAux: users) {
            Users.getItems().add(usersAux.getSNSNumber());
        }
    }

    /**
     * It gets the selected user from the combo box, and then it gets the available vaccines for that user and adds them to
     * the combo box
     *
     * @param event The event that triggered the action.
     */
    @FXML
    void set2(ActionEvent event) {
        vaccineAdministrationController.setSnsUserJavaFx(Users.getValue());
        ArrayList<String> listVaccines =vaccineAdministrationController.vaccinesAvailable();
        for (String vaccinesAux: listVaccines) {
            Vaccine.getItems().add(vaccinesAux);
        }

    }

    @FXML
    // Setting the vaccine that the user has selected.
    void set3(ActionEvent event) {
        vaccineAdministrationController.setVaccineJavaFx(Vaccine.getValue());
    }

    @FXML
    // Setting the local date time and validating the lot number.
    void set4(ActionEvent event) {
        vaccineAdministrationController.setLocalDateTime();
        vaccineAdministrationController.validateLotNumber(LOTnum.getText());

    }

}
