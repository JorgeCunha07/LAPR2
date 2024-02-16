package app.domain.model.Store;

import app.domain.model.VaccinationCenter;
import app.controller.VaccinationCenterController;
import app.domain.model.VaccinationCenterSlot;
import app.domain.model.VaccinatonCenterDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

/**
 * The type Vaccination center store.
 */
public class VaccinationCenterStore {

    /**
     * The Controller.
     */
    public VaccinationCenterController controller ;

    /**
     * The Vaccination centers.
     */
    public ArrayList<VaccinationCenter> vaccinationCenters = new ArrayList<>();

    /**
     * Get controller vaccination center controller.
     *
     * @return the vaccination center controller
     */
    public VaccinationCenterController getController(){

        return this.controller;
    }

    /**
     * Get vaccination centers array list.
     *
     * @return the array list
     */
    public ArrayList<VaccinationCenter> getVaccinationCenters(){
        return vaccinationCenters;
    }


    /**
     * Save vaccination center boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @return the boolean
     */
    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter){
        return vaccinationCenters.add(vaccinationCenter);
    }

    /**
     * Create center vaccination center.
     *
     * @param vaccinationCenterName            the vaccination center name
     * @param vaccinationCenterAdress          the vaccination center adress
     * @param vaccinationCenterPhoneNumber     the vaccination center phone number
     * @param vaccinationCenterEmailAdress     the vaccination center email adress
     * @param vaccinationCenterFaxNumber       the vaccination center fax number
     * @param vaccinationCenterWebAdress       the vaccination center web adress
     * @param vaccinationCenterOpeningHour     the vaccination center opening hour
     * @param vaccinationCenterClosingHour     the vaccination center closing hour
     * @param vaccinationCenterSlotDuration    the vaccination center slot duration
     * @param vaccinationCenterVaccinesPerSlot the vaccination center vaccines per slot
     * @return the vaccination center
     */
    public VaccinationCenter createCenter(String coordinatorEmail, String vaccinationCenterName, String vaccinationCenterAdress, int vaccinationCenterPhoneNumber, String vaccinationCenterEmailAdress, long vaccinationCenterFaxNumber, String vaccinationCenterWebAdress, LocalTime vaccinationCenterOpeningHour, LocalTime vaccinationCenterClosingHour, int vaccinationCenterSlotDuration, int vaccinationCenterVaccinesPerSlot){
        return new VaccinationCenter(coordinatorEmail,vaccinationCenterName, vaccinationCenterAdress, vaccinationCenterPhoneNumber, vaccinationCenterEmailAdress, vaccinationCenterFaxNumber, vaccinationCenterWebAdress, vaccinationCenterOpeningHour, vaccinationCenterClosingHour,  vaccinationCenterSlotDuration, vaccinationCenterVaccinesPerSlot);
    }

    /**
     * Create center 2 vaccination center.
     *
     * @param vaccinationCenter the vaccination center
     * @param listOfDates       the list of dates
     * @param StringArray       the string array
     * @return the vaccination center
     */
    public VaccinationCenter createCenter2(VaccinationCenter vaccinationCenter, ArrayList<VaccinatonCenterDate> listOfDates,  ArrayList<String> StringArray){
        return new VaccinationCenter(vaccinationCenter,listOfDates,StringArray);
    }


    /**
     * Delete vaccination center boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @return the boolean
     */
    public boolean deleteVaccinationCenter(VaccinationCenter vaccinationCenter){
        return vaccinationCenters.remove(vaccinationCenter);
    }

    public void setController(VaccinationCenterController controller) {
        this.controller = controller;
    }

    /**
     * Check if center exists boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean CheckIfCenterExists(String name) {
        boolean result = false;
        for (VaccinationCenter vaccinationCenter :vaccinationCenters){
            if (name.equals(vaccinationCenter.getName())){
                result = true;
            }
        }
            return result;
    }

    /**
     * This function adds all the dates from 2022-06-10 to 2024-12-31 to the list of dates of a vaccination center
     *
     * @param vaccinationCenter the vaccination center that we want to add the dates to.
     */
    public void addDaysBOOTSTRAP (VaccinationCenter vaccinationCenter){
        ArrayList<VaccinatonCenterDate> listOfDates = vaccinationCenter.getListOfDates();
        LocalDate beggining = LocalDate.of(2022,06,10);
        LocalDate lastDay = LocalDate.of(2024,12,31);
        VaccinatonCenterDate date = new VaccinatonCenterDate(beggining);
        listOfDates.add(date);
        do{
            LocalDate tomorrow = beggining.plusDays(1);
            VaccinatonCenterDate date2 = new VaccinatonCenterDate(tomorrow);
            listOfDates.add(date2);
            beggining = tomorrow;
        }while (beggining.isBefore(lastDay));
    }


    /**
     * It takes a vaccination center and adds slots to it's list of dates
     *
     * @param vaccinationCenter the vaccination center object
     */
    public void addSlotsToDateBOOTSTRAP(VaccinationCenter vaccinationCenter){
        ArrayList<VaccinatonCenterDate> listOfDates = vaccinationCenter.getListOfDates();
        LocalTime openingTime = vaccinationCenter.getOpeningHour();
        LocalTime closingTime = vaccinationCenter.getClosingHour();

        do {
            for (VaccinatonCenterDate date : listOfDates) {
                date.addToList(new VaccinationCenterSlot(openingTime, vaccinationCenter.getVaccinesPerSlot(), 0));
            }
            openingTime = openingTime.plusMinutes(vaccinationCenter.getSlotDuration());
        } while (openingTime.isBefore(closingTime) );
    }

    // To find the right vaccination center where the coordinatior is working

    /**
     * > This function returns the vaccination center object that has the same email as the one passed in as a parameter
     *
     * @param email The email of the coordinator of the vaccination center
     * @return The vaccination center that has the same email as the one passed in the parameter.
     */
    public VaccinationCenter getByEmail(String email){

        for (VaccinationCenter va : vaccinationCenters) {
            if(va.getCoordinatorEmail().equals(email)){
                return va;
            }
        }
        return null;
    }
}
