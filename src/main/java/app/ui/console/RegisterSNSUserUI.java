package app.ui.console;

import app.controller.RegisterSNSUserController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

// * The type Register sns user UI.

public class RegisterSNSUserUI implements Runnable {

    private final RegisterSNSUserController controller; // instanciar o Controller;

    //  * Instantiates a new Register sns user ui.
    public RegisterSNSUserUI() {
        this.controller = new RegisterSNSUserController();
    }

    public void run() {
        // A variable that is used to check if the user input is valid.
        boolean checks;
        // Declaring the variables that will be used in the program.
        String name, address, phoneNumber, email, citizenCardNumber, snsUserNumber, date;
        // Declaring a variable of type Email and initializing it to null.
        Email mail = null;

        // Printing the message "Register new SNS User" on the console.
        System.out.println("Register new SNS User");

        // Asking the user to insert a name and validating it.
        do {
            name = Utils.readLineFromConsole("Insert name:");
            try {
                checks = SNSUser.checkName(name);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);


        // Asking the user to insert an address and validating it.
        do {
            address = Utils.readLineFromConsole("Insert address:");
            try {
                checks = SNSUser.checkAddress(address);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);


        // Asking the user to insert a phone number and validating it.
        do {
            phoneNumber = Utils.readLineFromConsole("Insert phone number:");
            try {
                checks = SNSUser.checkPhoneNumber(phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);


        // Asking the user to insert a email and validating it.
        do {
            email = Utils.readLineFromConsole("Insert email:");
            try {
                mail = new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n Insert a valid email adress.");
                checks = false;
            }
        } while (!checks);


        // Asking the user to insert a Citizen Card Number and validating it.
        do {
            citizenCardNumber = Utils.readLineFromConsole("Insert Citizen Card Number:");
            try {
                checks = SNSUser.checkCitizenCardNumber(citizenCardNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);

        // Asking the user to insert a SNS User Number and validating it.
        do {
            snsUserNumber = Utils.readLineFromConsole("Insert SNS User Number:");
            try {
                checks = SNSUser.checkSNSUserNumber(snsUserNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);


        // Asking the user to insert a birth date and validating it.
        do {

            date = Utils.readLineFromConsole("Insert birth date (Format dd/MM/yyyy):");
            try {
                checks = SNSUser.checkBirthDate(date);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                checks = false;
            }
        } while (!checks);


        // Showing the list of genders and asking the user to select one.
        String gender = Utils.showAndSelectOne(controller.getSexList(), "Insert Gender or select 'Other' to skip:").toString();

        // Calling the createSNSUser method from the controller.
        SNSUser aux = controller.createSNSUser(name, address, gender, phoneNumber, mail.getEmail(), date, snsUserNumber, citizenCardNumber);

        // Printing the information of the SNSUser object that is returned by the controller.
        printSNSUser(aux);

        // Asking the user if he wants to save the user.
        if (Utils.confirmCreation()) {
            if (controller.saveSNSUser(aux)) {
                System.out.printf("%nUser has been registered!");
            }
        }

    }

    /**
     * This function prints the information of a SNSUser object
     *
     * @param newSNSUser The SNSUser object that you want to print.
     */
    public void printSNSUser(SNSUser newSNSUser) {
        System.out.printf("%nName: %s %nAddress: %s &nGender: %s %nPhone Number: %s %nEmail: %s %nBirthdate: %s %nSNS User Number: %s %nCitizen Card Number: %s %n%n", newSNSUser.getName(), newSNSUser.getAddress(), newSNSUser.getGender(), newSNSUser.getPhoneNumber(), newSNSUser.getEmail(), newSNSUser.getBirthDate(), newSNSUser.getSnsUserNumber(), newSNSUser.getCitizenCardNumber());
    }
}


