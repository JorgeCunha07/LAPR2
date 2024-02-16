package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.Store.SNSUserStore;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.InvalidPreferencesFormatException;

public class LoadSNSUserController {

    // Creating an ArrayList of SNSUser objects to print.
    ArrayList<SNSUser> user = new ArrayList<>();
    // Used to store the password of the SNSUser to print.
    ArrayList<String> password = new ArrayList<>();
    // A variable that is used to store the Company object.
    private Company company;
    // A variable that is used to store the SNSUserStore.
    private SNSUserStore storeSNSUser;
    // Creating an ArrayList of SNSUser objects.
    private ArrayList<SNSUser> snsUserAuxList = new ArrayList<>();


    // The constructor of the class.
    public LoadSNSUserController() {
        company = App.getInstance().getCompany();
        this.storeSNSUser = company.getSnsUserStore();
    }

    public void printInfoUser() {
        for (int i = 0; i < user.size(); i++) {
            System.out.println(user.get(i));
            System.out.println("User Info:");
            System.out.println("Id: " + user.get(i).getEmail());
            System.out.println("Password: " + password.get(i));
            System.out.println();
        }
    }

    /**
     * It checks if the location of the file is valid, then it verifies the type of the file, and then it converts the file
     * to an ArrayList of SNSUser
     *
     * @param location The path to the CSV file.
     * @return An ArrayList of SNSUser objects.
     */
    public ArrayList<SNSUser> createSNSUserFromCSV(String location) throws IOException, InvalidPreferencesFormatException {
        int type;

        boolean flag = company.checkLocation(location);

        if (flag) {
            File br = new File(location);
            BufferedReader file = new BufferedReader(new FileReader(br));

            try {
                type = company.VerifyTypeofCSV(file);
            } catch (InvalidPreferencesFormatException e) {
                throw new InvalidPreferencesFormatException(e.getMessage());
            }
            file = new BufferedReader(new FileReader(br));

            try {
                snsUserAuxList = storeSNSUser.convertFileSNSUserToArrayList(file, type);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else throw (new IOException("File Path is invalid!"));
        return snsUserAuxList;
    }

    /**
     * This function is used to save the SNS users list from a CSV file
     *
     * @param snsUserAuxArrayList ArrayList of SNSUser objects
     * @return A boolean value.
     */
    public boolean saveSNSUsersListFromCSVFile(ArrayList<SNSUser> snsUserAuxArrayList) {
        boolean flag = false, tag = false;

        UserRoleDTO roleDto = new UserRoleDTO(Constants.ROLE_SNS_USER, Constants.ROLE_SNS_USER);

        for (SNSUser aux : snsUserAuxArrayList) {

            if (storeSNSUser.checkSNSUser(aux)) {
                flag = storeSNSUser.addSNSUser(aux);
                String pass = Company.generatePassword();
                tag = company.addUserWithRole(aux, roleDto, pass);
                if (flag && tag) {
                    user.add(aux);
                    password.add(pass);
                }
            }
        }
        return flag && tag;
    }


}
                 