package app.ui.console;

import app.controller.LoadSNSUserController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.InvalidPreferencesFormatException;

import static app.ui.console.utils.Utils.readIntegerFromConsole;

public class LoadSNSUserUI implements Runnable {

    // A static variable that is used to create a single instance of the class.
    private static LoadSNSUserUI instance;
    // A private variable that is used to create a single instance of the class.
    private LoadSNSUserController controller;

    // A constructor that creates a new instance of the class LoadSNSUserController.
    public LoadSNSUserUI() {
        this.controller = new LoadSNSUserController();
    }

    /**
     * If the instance variable is null, create a new instance of the class and assign it to the instance variable. Then
     * return the instance variable.
     *
     * @return The instance of the class.
     */
    public static LoadSNSUserUI getInstance() {
        if (instance == null) {
            instance = new LoadSNSUserUI();
        }
        return instance;
    }

    /**
     * This function takes in a location of a CSV file and returns an ArrayList of SNSUser objects
     *
     * @param location The location of the CSV file.
     * @return An ArrayList of SNSUser objects.
     */
    private ArrayList<SNSUser> createSNSUserFromCSV(String location) throws IOException, InvalidPreferencesFormatException {
        return controller.createSNSUserFromCSV(location);
    }

    /**
     * This function saves the SNSUsersList from a CSV file
     *
     * @param snsUserAuxArrayList ArrayList of SNSUser objects
     * @return A boolean value.
     */
    private boolean saveSNSUsersListFromCSVFile(ArrayList<SNSUser> snsUserAuxArrayList) {
        return controller.saveSNSUsersListFromCSVFile(snsUserAuxArrayList);
    }

    /**
     * This function prints the contents of an ArrayList of SNSUser objects
     *
     * @param snsUserAuxArrayList ArrayList of SNSUser objects
     */
    public void printSNSUsers(ArrayList<SNSUser> snsUserAuxArrayList) {
        for (SNSUser aux : snsUserAuxArrayList) {
            System.out.println(aux);
        }
    }


    /**
     * This function is responsible for reading a CSV file and creating a list of SNSUsers
     */
    @Override
    public void run() {
        boolean flag;
        int respond;
        do {
            ArrayList<SNSUser> snsUserAuxArrayList = new ArrayList<>();
            do {
                try {
                    String location = Utils.readLineFromConsole("Insert the location of the CSV File:");
                    snsUserAuxArrayList = createSNSUserFromCSV(location);
                    flag = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    flag = false;
                }

            } while (!flag);

            printSNSUsers(snsUserAuxArrayList);

            do {
                respond = readIntegerFromConsole("If you agree write 1 :\nIf you don´t agree write 0 :");

            } while (respond < 0 || respond > 1);

            if (respond == 1) {
                flag = saveSNSUsersListFromCSVFile(snsUserAuxArrayList);
                if (flag) {
                    System.out.println();
                    System.out.println("Saved with Success!");
                    controller.printInfoUser();
                } else {
                    System.out.println("Error trying to upload SNS Users!");
                }
            }

        } while (!flag || respond == 0);
    }


}
