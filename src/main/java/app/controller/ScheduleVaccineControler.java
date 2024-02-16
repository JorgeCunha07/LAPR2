package app.controller;

import app.domain.model.*;
import app.domain.model.Store.ScheduledVaccineStore;
import app.domain.shared.UtilsCheck;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The type Schedule vaccine controler.
 */
public class ScheduleVaccineControler {


    private VaccinationCenter vaccinationCenter;
    private ScheduledVaccine scheduledVaccine;
    private ScheduledVaccineStore scheduledVaccineStore;
    private Company company ;
    private UtilsCheck utilsCheck;

    /**
     * Instantiates a new Vaccination center controller.
     */
    public ScheduleVaccineControler() {
        company = App.getInstance().getCompany();
        scheduledVaccineStore = company.getScheduledVaccineStore();
        utilsCheck = company.getUtilsCheck();
    }

    /**
     * Gets vaccination center.
     *
     * @return the vaccination center
     */
    public ScheduledVaccine getScheduledVaccine() {
        return scheduledVaccine;
    }

    /**
     * Save vaccination center boolean.
     *
     * @param scheduledVaccine the scheduled vaccine
     * @return the boolean
     */
    public boolean saveScheduledVaccine(ScheduledVaccine scheduledVaccine) {
        return company.SaveSchedule(scheduledVaccine);
    }

    /**
     * This function returns a list of all the scheduled vaccines in the company
     *
     * @return The list of scheduled vaccines.
     */
    public ArrayList<ScheduledVaccine> getScheduledVaccines(){
        return company.getScheduledVaccineStore().getListOfScheduledVaccines();
    }


    /**
     * This function creates a scheduled vaccine
     *
     * @param SNSNumber             The SNS number of the patient
     * @param vaccinationCenterName The name of the vaccination center where the vaccine will be administered.
     * @param date                  the date of the vaccination
     * @param slot                  the slot of the day that the vaccine is scheduled for
     * @param hours                 the time of the appointment
     * @param vaccineType           "Vacina" or "Vacina de Reforço"
     * @param dayAndHours           the date and time of the vaccination
     * @return The scheduledVaccine object is being returned.
     */
    public ScheduledVaccine createScheduledVaccine(String SNSNumber, String vaccinationCenterName, LocalDate date, VaccinationCenterSlot slot, LocalTime hours, String vaccineType,LocalDateTime dayAndHours) {
        this.scheduledVaccine = new ScheduledVaccine(SNSNumber,vaccinationCenterName,date,slot,hours,vaccineType,dayAndHours);
        return scheduledVaccine;
    }

    /**
     * Check if center exists boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean checkIfCenterExists(String name){
        boolean result = false;
        for(VaccinationCenter vacinationCenterToCheck :company.getVaccinationCenterStore().getVaccinationCenters()){
            if (vacinationCenterToCheck.getName().equals(name)){
                result = true;
            }
        }
        return  result;
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

    /**
     * Validate scheduled vaccine boolean.
     *
     * @param scheduledVaccine the scheduled vaccine
     * @return the boolean
     */
    public boolean validateScheduledVaccine(ScheduledVaccine scheduledVaccine){
        boolean result = false;
        if (checkIfCenterExists(scheduledVaccine.getVaccinationCenterName())){
            VaccinationCenter vaccinationCenter1 = setVaccinationCenterWithName(scheduledVaccine.getVaccinationCenterName()); //You were getting the vaccine name instead of the vaccination center name
            for (SNSUser SNSUserToCheck: company.getSnsUserStore().getSaveSNSUser()){
                if (SNSUserToCheck.getSnsUserNumber().equals(scheduledVaccine.getSNSNumber())){
                    for (VaccinatonCenterDate dateToCheck : vaccinationCenter1.getListOfDates()){
                        if (dateToCheck.getDate().equals(scheduledVaccine.getDate())){
                            /* you were not comparing two dates, but a VaccinationCenterDate object with a VaccinationCenterSlot object
                            /* It is not possible to schedule vaccines in the next years, besides 2022 (which is supposed to be possible)
                             */
                            /* do more tests for the vaccine and vaccine type */
                            result = true;
                        }
                    }
                }
            }
        }
            return result;
        }


    /**
     * This function checks if a user exists in the database
     *
     * @param snsUserNumber The user's phone number.
     * @return A boolean value.
     */
    public boolean checkIfSnsUserExists(String snsUserNumber){
        return utilsCheck.checkIfSnsUserExists(snsUserNumber);
        }


    /**
     * It returns the SNSUser object with the given snsUserNumber.
     *
     * @param snsUserNumber The user's SNS number.
     * @return A SNSUser object.
     */
    public SNSUser getSnsUser (String snsUserNumber){
        return utilsCheck.getSnsUser(snsUserNumber);
        }


    /**
     * Check if still has doses to administer boolean.
     *
     * @param snsUser the sns user
     * @return the boolean
     */
    public boolean checkIfIsFullyVaccinated(SNSUser snsUser, VaccineType vaccineType){
        ArrayList<Bulletin> bulletinList = new ArrayList<>();
        ArrayList<Bulletin> bulletinListSameType = new ArrayList<>();
        boolean status;
        int lastVaccine = 0;
        for (SNSUser snsUserCheck : company.getSnsUserStore().snsUserList){
            if (snsUserCheck.getSnsUserNumber().equals(snsUser.getSnsUserNumber())){
                bulletinList = snsUserCheck.getVaccineBulletin();
            }
        }

        if (bulletinList.isEmpty()){
            //If it is empty, then it has no doses, therefore the user is not fully vaccinated
            return false;

        }else {
            for (Bulletin bulletinPage : bulletinList){
                if( bulletinPage.getVaccine().getVaccineType().equals(vaccineType)){
                bulletinListSameType.add(bulletinPage); //Checks the sns User bulletin and adds the bulletins that have the same vaccine type.
                }
            }
            //If it is not empty it gets the size of the list of bulletin pages with the same type
            int lastVaccineFromSameType = bulletinListSameType.size();
            int lastDoseFromSameType = bulletinListSameType.get(lastVaccineFromSameType - 1).getDose();

            //with the number of dose, call's the isLastDose method.
            status = bulletinListSameType.get(lastVaccineFromSameType - 1).isLastDose(lastDoseFromSameType);
        }
        return status;
        }

    }

