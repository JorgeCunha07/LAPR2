package app.ui.console;

import app.controller.VaccineAdministrationController;
import app.domain.model.ScheduledVaccine;
import app.domain.model.TestTimer;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;

public class VaccineAdministrationUI implements Runnable {

    // Creating a new instance of the VaccineAdministrationController class.
    private VaccineAdministrationController controller;
    // Creating a new instance of the Scanner class.
    private Scanner scan = new Scanner(System.in);

    // Creating a new instance of the VaccineAdministrationController class.
    public VaccineAdministrationUI() {
        controller = new VaccineAdministrationController();
    }

    @Override
    // This function is the main function of the VaccineAdministrationUI class. It is called when the user clicks on the
    // "Add Bulletin" button.
    public void run() {

        // Select a center
        int vaccinationCenter = selectCenterUI(controller.vaccinationCenterName());
        setNurseCenter(vaccinationCenter);

        // Select a User
        int user = selectUserUI(controller.waitingRoomUsersSnsNumber());
        this.controller.setSnsUser(user);
        setUserVaccineType(user);

        // Select a Vaccine (if he never took a dose), validate age group
        int vaccine = selectVaccineUI(controller.vaccinesAvailable());
        setVaccine(vaccine);

        // store LocalDateTime.now()
        controller.setLocalDateTime();

        // lotNumber : A1BcD-12
        lotNumber();

        // Remove User From Waiting Room List
        removeUserFromWaitingList(user);

        //add bulletin
        addBulletinVaccineUI();

        controller.notifyAfterXMinutes(1);

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
     * It shows a list of vaccination centers and asks the user to select one.
     *
     * @param vaccinationCenterNameList A list of the names of the vaccination centers.
     * @return The index of the selected vaccination center.
     */
    private int selectCenterUI(ArrayList<String> vaccinationCenterNameList) {
        return Utils.showSelectCenterUserVaccine(vaccinationCenterNameList, "Select a Vaccination Center:");
    }

    /**
     * This function is used to select a user from the waiting room list
     *
     * @param waitingRoomListUsers ArrayList<String>
     * @return The index of the selected user.
     */
    private int selectUserUI(ArrayList<ScheduledVaccine> waitingRoomListUsers) {
        return Utils.showSelectCenterUserVaccine(waitingRoomListUsers, "Select an User");
    }

    /**
     * This function is used to select a vaccine from a list of vaccines
     *
     * @param vaccineNameList This is a list of vaccine names that are available for the child.
     * @return The index of the vaccine selected by the user.
     */
    private int selectVaccineUI(ArrayList<String> vaccineNameList) {
        if (controller.numberOfDosesForVaccineType() == Constants.FIRST_DOSE) {
            return Utils.showSelectCenterUserVaccine(vaccineNameList, "Select a Vaccine");
        }
        return Constants.NOT_FIRST_DOSE;
    }

    /**
     * This function asks the user to input a lot number, and then validates it
     */
    private void lotNumber() {
        String lotNumber;
        do {
            System.out.print("Introduce Lot Number: ");
            lotNumber = scan.nextLine();
        } while (!controller.validateLotNumber(lotNumber));
        controller.setLotNumber(lotNumber);
    }

    /**
     * This function sets the vaccine type for a user.
     *
     * @param user The user's ID
     */
    private void setUserVaccineType(int user) {
        try {
            controller.setVaccineType(user);
        } catch (NoSuchObjectException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function sets the vaccination center for the nurse
     *
     * @param vaccinationCenter The ID of the vaccination center.
     */
    private void setNurseCenter(int vaccinationCenter) {
        controller.setVaccinationCenter(vaccinationCenter);
    }

    /**
     * This function sets the vaccine to the value passed in
     *
     * @param vaccine The number of vaccines the player has.
     */
    private void setVaccine(int vaccine) {
        controller.setVaccine(vaccine);
    }

    /**
     * > Removes a user from the waiting list
     *
     * @param user The user's ID
     */
    private void removeUserFromWaitingList(int user) {

        controller.removeUserWaitingList(user);
    }

    /**
     * The function `addBulletinVaccineUI()` is called when the user clicks on the "Add Bulletin" button. It calls the
     * function `addBulletinVaccine()` in the controller
     */
    private void addBulletinVaccineUI() {
         controller.addBulletinVaccine();
    }
}
