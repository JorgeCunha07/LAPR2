package app.controller;

import app.domain.model.*;
import app.domain.model.Store.EntryRecordStore;
import app.domain.model.Store.SNSUserStore;
import app.domain.model.Store.ScheduledVaccineStore;
import app.domain.model.Store.VaccinationCenterStore;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegisterSNSUserArrivalController {

    // A variable that is being used to store the company object.
    private Company company;
    // A variable that is being used to store the SNS user store.
    private SNSUserStore snsStore;
    // A variable that is being used to store the vaccination center store.
    private VaccinationCenterStore vaccinationCenterStore;
    private ScheduledVaccineStore scheduledVaccineStore;
    // A variable that is being used to store the appointment that is being checked.
    private ScheduledVaccine appointment;
    // A variable that is being used to store the vaccination center that the user is currently logged in to.
    private VaccinationCenter vaccinationCenter;
    // A variable that is being used to store the entry records of the patients.
    private EntryRecordStore entryRecordStore;
    // A list of scheduled vaccines.
    private ArrayList<ScheduledVaccine> listScheduledVaccines ;

    // This is the constructor of the class. It is initializing the variables of the class.
    public RegisterSNSUserArrivalController() {
        company = App.getInstance().getCompany();
        snsStore = company.getSnsUserStore();
        vaccinationCenterStore = company.getVaccinationCenterStore();
        scheduledVaccineStore = company.getScheduledVaccineStore();
        listScheduledVaccines = company.getScheduledVaccineStore().listOfScheduledVaccines;
        entryRecordStore =company.getEntryRecordStore();
    }

    /**
     * If the day of the month and the month of the appointment day are not equal to the day of the month and the month of
     * the scheduled vaccine's date, return false. Otherwise, return true.
     *
     * @param appointmentDay The date of the appointment
     * @param scheduledVaccine The vaccine that is being scheduled
     * @return A boolean value.
     */
    public boolean validateDate(LocalDateTime appointmentDay, ScheduledVaccine scheduledVaccine){
        if(appointmentDay.getDayOfMonth() != scheduledVaccine.getDate().getDayOfMonth()){
            return false;
        }
        if(appointmentDay.getMonth() != scheduledVaccine.getDate().getMonth()){
            return false;
        }
        return appointmentDay.getYear() == scheduledVaccine.getDate().getYear();
    }

    /**
     * If the vaccination center is not valid, return false, otherwise return the result of the date validation.
     *
     * @param vaccinationCenterName The name of the vaccination center that the user is trying to schedule the vaccine at.
     * @param dateTime The date and time of the appointment
     * @param scheduledVaccine The vaccine that is being scheduled.
     * @return A boolean value.
     */
    public boolean majorChecks(String vaccinationCenterName, LocalDateTime dateTime, ScheduledVaccine scheduledVaccine){
        if(!validateVaccinationCenter(vaccinationCenterName, scheduledVaccine)) return false;
        return validateDate(dateTime, scheduledVaccine);
    }

    /**
     * This function returns an ArrayList of VaccinationCenter objects
     *
     * @return The method returns an ArrayList of VaccinationCenter objects.
     */
    public ArrayList<VaccinationCenter> getVacinationCenters(){

        return  vaccinationCenterStore.vaccinationCenters;
    }

    /**
     * > This function returns the list of arrivals to the vaccination center
     *
     * @return The list of arrivals.
     */
    public ArrayList<EntryRecord> getArrivals (){
        return vaccinationCenter.getArrivalsList();
    }

    /**
     * > This function returns the list of arrivals of a specific vaccination center
     * @param vaccCenter name of a vaccination center
     * @return The list of arrivals
     */
    public ArrayList<EntryRecord> getVaccinationCenterArrivals(String vaccCenter) {
        if (CheckIfCenterExists(vaccCenter)) {
            return setVaccinationCenterWithName(vaccCenter).getArrivalsList();
        }
        return null;
    }


    /**
     * This function returns the scheduled vaccine at the given index
     *
     * @param index The index of the scheduled vaccine you want to get.
     * @return The scheduled vaccine at the index.
     */
    public ScheduledVaccine getScheduleVaccine(int index){
        return company.getScheduledVaccineStore().getListOfScheduledVaccines().get(index);
    }

    /**
     * This function checks if a center exists in the database
     *
     * @param name The name of the center
     * @return A boolean value.
     */
    public boolean CheckIfCenterExists(String name){
        return vaccinationCenterStore.CheckIfCenterExists(name);
    }

    /**
     * > Check if the SNS number is valid and not used
     *
     * @param snsNumber The number of the SNS account.
     */
    public boolean checkSNSNumber(String snsNumber){
        boolean flag1,flag2;
        flag1 = SNSUser.checkSNSUserNumber(snsNumber);
        try {
            snsStore.checkSNSNumberList(snsNumber);
            flag2 = false;
        }catch ( IllegalArgumentException e){
            flag2 = true;
        }
        return  flag1 && flag2;
    }

    /**
     * > This function validates that the vaccination center name is the same as the vaccination center name of the
     * scheduled vaccine
     *
     * @param vaccinationCenterName The name of the vaccination center that the user is currently logged in to.
     * @param scheduledVaccine The scheduled vaccine object that is being validated.
     * @return A boolean value.
     */
    public boolean validateVaccinationCenter(String vaccinationCenterName, ScheduledVaccine scheduledVaccine){
        return vaccinationCenterName.equals(scheduledVaccine.getVaccinationCenterName());
    }

    /**
     * This function checks if the appointment exists.
     *
     * @param snsUserNumber The SNS number of the user.
     * @return The appointment that is being returned is the appointment that is being checked.
     */
    public ScheduledVaccine checkAppointment(String snsUserNumber){
        for (int scheduledVaccine = 0; scheduledVaccine < listScheduledVaccines.size(); scheduledVaccine++) {
            if(listScheduledVaccines.get(scheduledVaccine).getSNSNumber().equals(snsUserNumber)){
                return getScheduleVaccine(scheduledVaccine);
            }
        }
        throw new IllegalArgumentException("The appointment does not exist.");
    }

    /**
     * > This function registers the arrival of a patient at the vaccination center
     *
     * @param exScheduledVaccine The scheduled vaccine that is being registered.
     * @param arrivalDate the date and time of the arrival of the patient
     * @param vaccinationCenter The vaccination center where the vaccine is being registered.
     * @return A boolean value.
     */
    public boolean registerArrival(ScheduledVaccine exScheduledVaccine, LocalDateTime arrivalDate, VaccinationCenter vaccinationCenter){
        EntryRecord entryRecordAux = new EntryRecord(arrivalDate,true,exScheduledVaccine);
        return entryRecordStore.registerArrival(entryRecordAux);
    }

    /**
     * Set vaccination center with name vaccination center.
     *
     * @param name the name
     * @return the vaccination center
     */
    public VaccinationCenter setVaccinationCenterWithName (String name){
        for(VaccinationCenter vacinationCenterToCheck : company.getVaccinationCenterStore().getVaccinationCenters()){
            if (vacinationCenterToCheck.getName().equals(name)){
                vaccinationCenter = vacinationCenterToCheck;
            }
        }
        return  vaccinationCenter;
    }
}
