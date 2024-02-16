package app.domain.shared;

import app.domain.model.SNSUser;
import app.domain.model.Store.*;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UtilsCheck {

    // Creating a list of SNSUser objects.
    public ArrayList<SNSUser> snsUserList;
    // A variable that is used to store the authentication facade.
    // Creating a variable that is used to store the employee store.
    private AuthFacade authFacade;
    // Creating a variable that is used to store the employee store.
    private EmployeeStore employeeStore;
    // Creating a variable that is used to store the SNSUserStore.
    private SNSUserStore snsUserStore;
    // Creating a variable that is used to store the vaccination center store.
    private VaccinationCenterStore vaccinationCenterStore;
    // Creating a variable that is used to store the scheduled vaccine store.
    private ScheduledVaccineStore scheduledVaccineStore;
    private EntryRecordStore entryRecordStore;
    // A variable that is used to store the leaving record store.
    private LeavingRecordStore leavingRecordStore;

    // This is a constructor. It is called when an object of the class is created.
    public UtilsCheck(AuthFacade authFacade, EmployeeStore employeeStore, SNSUserStore snsUserStore, VaccinationCenterStore vaccinationCenterStore, ScheduledVaccineStore scheduledVaccineStore, EntryRecordStore entryRecordStore, LeavingRecordStore leavingRecordStore) {

        this.authFacade = authFacade;
        this.employeeStore = employeeStore;
        this.snsUserStore = snsUserStore;
        this.vaccinationCenterStore = vaccinationCenterStore;
        this.scheduledVaccineStore = scheduledVaccineStore;
        this.entryRecordStore = entryRecordStore;
        this.leavingRecordStore = leavingRecordStore;
        this.snsUserList = snsUserStore.getSaveSNSUser();
    }

    /**
     * It checks if the time interval is valid
     *
     * @param timeInterval The time interval in minutes.
     * @return A boolean value.
     */
    public static boolean checkTimeInterval(String timeInterval) {
        int timeInterval_ = 0;

        if (timeInterval == null)
            throw new IllegalArgumentException("Time interval cannot be null!");

        if (timeInterval.equals(""))
            throw new IllegalArgumentException("Time interval is blank.");

        try {
            for (int i = 0; i < timeInterval.length(); i++) {
                if (!Character.isDigit(timeInterval.charAt(i)))
                    throw new IllegalArgumentException("Time interval can only contain numbers.");
            }

            timeInterval_ = Integer.parseInt(timeInterval);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The time interval cannot be higher then 720.");
        }

        if (720 % timeInterval_ != 0)
            throw new IllegalArgumentException("Invalid time interval. Try another (i.e: 1, 15, 30, 60...).");

        return true;
    }

    /**
     * It checks if the date is valid by parsing it using the format "dd/MM/yyyy" and if it fails to parse, it returns
     * false
     *
     * @param strBirthDate The date to be checked.
     * @return A boolean value.
     */
    public static boolean checkDate(String strBirthDate) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(strBirthDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * This function checks if a user exists in the list of users
     *
     * @param snsUserNumber The number of the user who is trying to login.
     * @return A boolean value.
     */
    public boolean checkIfSnsUserExists(String snsUserNumber) {
        boolean status = false;
        for (SNSUser snsUser : snsUserList) {
            if (snsUser.getSnsUserNumber().equals(snsUserNumber)) {
                status = true;
            }

        }
        return status;
    }

    /**
     * This function takes a string as an argument and returns a SNSUser object
     *
     * @param snsUserNumber The user number of the user you want to get.
     * @return The SNSUser object that matches the snsUserNumber passed in as a parameter.
     */
    public SNSUser getSnsUser(String snsUserNumber) {
        SNSUser userToReturn = null;
        for (SNSUser snsUser : snsUserList) {
            if (snsUser.getSnsUserNumber().equals(snsUserNumber)) {
                userToReturn = snsUser;
            }
        }
        return userToReturn;

    }


  /*  public boolean checkIfStillHasDosesToAdminister(SNSUser snsUser, int numberOfDoses) {
        boolean status = false;
        if (snsUser.getNumber_Doses() < numberOfDoses) {
            status = true;
        }
        return status;
    }
*/
    //Corrigir antonio

}
