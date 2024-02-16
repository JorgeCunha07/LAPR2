package app.domain.model.Store;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static app.domain.shared.Constants.*;


public class SNSUserStore {
    // Creating a new ArrayList of SNSUser objects.
    public ArrayList<SNSUser> snsUserList = new ArrayList<>();
    // It's a private variable that is used to store the AuthFacade object.
    private AuthFacade authFacade;
       // It's a private variable that is used to store a SNSUser object.
    private SNSUser snsUser;

    // It's a constructor that receives an AuthFacade object and stores it in the private variable authFacade.
    public SNSUserStore(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    /**
     * This function returns the ArrayList of SNSUser objects
     *
     * @return An ArrayList of SNSUser objects.
     */
    public ArrayList<SNSUser> getSaveSNSUser() {
        return snsUserList;
    }


    /**
     * > This function saves a SNSUser object to the database
     *
     * @param snsUser The SNSUser object that you want to save.
     * @return A boolean value.
     */
    public boolean saveSNSUser(SNSUser snsUser) {
        return checkSNSUser(snsUser) && addSNSUser(snsUser) ;
    }

    /**
     * This function creates a new SNSUser object and returns it.
     *
     * @param name The name of the user.
     * @param address The address of the user.
     * @param gender "M" or "F"
     * @param phoneNumber The phone number of the user.
     * @param email The email address of the user.
     * @param birthDate The date of birth of the user.
     * @param snsUserNumber The number of the SNS user.
     * @param citizenCardNumber The citizen card number of the user.
     * @return A new SNSUser object.
     */
    public SNSUser createSNSUser(String name, String address, String gender, String phoneNumber, String email, String birthDate, String snsUserNumber, String citizenCardNumber) {
        return new SNSUser(name, address, gender, phoneNumber, email, birthDate, snsUserNumber, citizenCardNumber);
    }

    /**
     * It reads a file and converts it to an ArrayList of SNSUser objects
     *
     * @param file The file to be read.
     * @param type
     * @return An ArrayList of SNSUser objects.
     */
    public ArrayList<SNSUser> convertFileSNSUserToArrayList(BufferedReader file, int type) throws IOException {
        ArrayList<SNSUser> snsUserAuxList = new ArrayList<>();
        String aux ;
        boolean check = true;
        int lineNumber = 0;
        if (type == TYPE_WITH_HEADER) {
            file.readLine();
            lineNumber++;
        }

        aux = file.readLine();
        lineNumber++;

        while ((aux != null)) {
            aux = aux.replaceAll("\"", "");
            String[] auxList = aux.split(";|,");

            if (SNSUser.checkSNSUsersFileAttributesPerLine(auxList, lineNumber) && check) {
                SNSUser auxSNS = new SNSUser(auxList[0], auxList[3], auxList[1], auxList[4], auxList[5], auxList[2], auxList[6], auxList[7]);
                snsUserAuxList.add(auxSNS);
            } else
                check = false;
            lineNumber++;
            aux = file.readLine();
        }

        if (check)
            return snsUserAuxList;
        else
            throw new RuntimeException("The file has errors.");
    }

    /**
     * This function checks if the SNSUser is valid and if it's not already in the list
     *
     * @param aux SNSUser
     * @return a boolean value.
     */
    public boolean checkSNSUser(SNSUser aux) {
        boolean flag1 ,flag2;
        if (aux == null) {
            return false;
        }
        if (SNSUser.checkSNSUserParam(aux.getName(), aux.getAddress(), aux.getGender(), aux.getPhoneNumber(), aux.getEmail(), aux.getBirthDate(), aux.getSnsUserNumber(), aux.getCitizenCardNumber())) {
            try{
                checkSNSNumberList(aux.getSnsUserNumber());
                flag1 = true;
            }catch (IllegalArgumentException e){
                flag1 = false;
                System.out.println(e.getMessage());
            }

            try{
                checkCitizenCardNumberList(aux.getCitizenCardNumber());
                flag2 = true;
            }catch (IllegalArgumentException e){
                flag2 = false;
                System.out.println(e.getMessage());
            }

            return !snsUserList.contains(aux) && flag1 && flag2  ;
        } else
            return false;
    }


    /**
     * This function checks if the citizen card number already exists in the list of users
     *
     * @param citizenCardNumber The citizen card number of the user.
     * @return A boolean value.
     */
    private boolean checkCitizenCardNumberList(String citizenCardNumber) {
        for (SNSUser aux: snsUserList) {
            if (aux.getCitizenCardNumber().trim().equals(citizenCardNumber.trim())){
                throw new IllegalArgumentException("Citizen Card Number already exists ! ->" + aux.getCitizenCardNumber() + "\n");
            }
        }
       return true;
    }

    /**
     * It checks if the SNS number is already in the list
     *
     * @param snsUserNumber The number of the SNS user.
     */
    public void checkSNSNumberList(String snsUserNumber) {
        for (SNSUser aux: snsUserList) {
            if (aux.getSnsUserNumber().trim().equals(snsUserNumber.trim())){
                throw new IllegalArgumentException("SNS User Number already exists! ->" + aux.getSnsUserNumber());
            }
        }
    }

    /**
     * If the user is not a duplicate, add the user to the list
     * @param aux The SNSUser object to be added to the list.
     * @return A boolean value.
     */
    public boolean addSNSUser(SNSUser aux) {
        if (checkSNSUser(aux)) {
            return snsUserList.add(aux);
        } else {
            System.out.println(aux + "\n Already exists.");
            return false;
        }
    }


}
