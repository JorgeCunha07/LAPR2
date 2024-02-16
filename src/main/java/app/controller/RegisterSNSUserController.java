package app.controller;


import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.Store.SNSUserStore;
import app.domain.shared.Constants;
import java.util.Arrays;
import java.util.List;

/**
 * The type Register sns user controller.
 */
public class RegisterSNSUserController {

    private Company company ;


    /**
     * Instantiates a new Register sns user controller.
     */
    private SNSUserStore snsuserstore;

    // A constructor.
    public RegisterSNSUserController() {
        company = App.getInstance().getCompany();
        snsuserstore = company.getSnsUserStore();
    }

      /**
     * Get Gender list .
     *
     * @return the list
     */
    public List<SNSUser.Gender> getSexList() {
        SNSUser.Gender[] arr = SNSUser.Gender.values();
        return Arrays.asList(arr);
    }

    /**
     * This function creates a new SNSUser object and returns it
     *
     * @param name The name of the user
     * @param address The address of the user
     * @param gender "M" or "F"
     * @param phoneNumber The phone number of the user.
     * @param email The email address of the user.
     * @param birthDate The date of birth of the user.
     * @param snsUserNumber The number of the SNS user.
     * @param citizenCardNumber The citizen card number of the user.
     * @return A SNSUser object is being returned.
     */
    public SNSUser createSNSUser(String name, String address, String gender, String phoneNumber, String email, String birthDate, String snsUserNumber, String citizenCardNumber){
        return snsuserstore.createSNSUser(name, address, gender, phoneNumber, email, birthDate, snsUserNumber, citizenCardNumber);
    }

     /**
      * > If the user is saved in the SNS database, then add the user to the company database with the role of SNS_USER
      *
      * @return A boolean value.
      */
     public boolean saveSNSUser(SNSUser aux) {
         String pass = Company.generatePassword();
         if(company.saveSNSUser(aux) && company.addUserWithRole(aux.getName(), aux.getEmail(), pass, Constants.ROLE_SNS_USER)){
             System.out.println("User Login Information");
             System.out.println("Email: \n" + aux.getEmail() + "\nPassword: \n" + pass);
             return true;
         }
         return false;
    }




}
