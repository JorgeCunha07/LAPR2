package app.controller;

import app.domain.model.*;
import app.domain.model.Store.VaccinationCenterStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalTime;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;


/**
 * The type Vaccination center controller.
 */
public class VaccinationCenterController {

    // A reference to the VaccinationCenterStore object.
    private VaccinationCenterStore vaccinationCenterStore;
    // A reference to the company object.
    private Company company;

    /**
     * Instantiates a new Vaccination center controller.
     */
    public VaccinationCenterController() {
        company = App.getInstance().getCompany();
        vaccinationCenterStore = company.getVaccinationCenterStore();
    }


    /**
     * Create vaccination center vaccination center.
     *
     * @param vaccinationCenterName            the vaccination center name
     * @param vaccinationCenterAdress          the vaccination center adress
     * @param vaccinationCenterPhoneNumber     the vaccination center phone number
     * @param vaccinationCenterEmailAdress     the vaccination center email adress
     * @param vaccinationCenterFaxNumber       the vaccination center fax number
     * @param vaccinationCenterWebAdress       the vaccination center web adress
     * @param openingHout                      the opening hout
     * @param closingHour                      the closing hour
     * @param slotduration                     the slotduration
     * @param vaccinationCenterVaccinesPerSlot the vaccination center vaccines per slot
     * @return the vaccination center
     */
    public VaccinationCenter createVaccinationCenter (String coordinatorEmail,String vaccinationCenterName, String vaccinationCenterAdress, int vaccinationCenterPhoneNumber, String vaccinationCenterEmailAdress, long vaccinationCenterFaxNumber, String vaccinationCenterWebAdress, LocalTime openingHout, LocalTime closingHour,int slotduration, int vaccinationCenterVaccinesPerSlot){
        return new VaccinationCenter(coordinatorEmail, vaccinationCenterName, vaccinationCenterAdress, vaccinationCenterPhoneNumber, vaccinationCenterEmailAdress, vaccinationCenterFaxNumber, vaccinationCenterWebAdress, openingHout,closingHour,slotduration, vaccinationCenterVaccinesPerSlot);
    }

    /**
     * Create vaccination center 2 vaccination center.
     *
     * @param vaccinationCenter the vaccination center
     * @param listOfDates       the list of dates
     * @param StringArray       the string array
     * @return the vaccination center
     */
    public VaccinationCenter createVaccinationCenter2(VaccinationCenter vaccinationCenter, ArrayList<VaccinatonCenterDate> listOfDates,  ArrayList<String> StringArray){
     return new VaccinationCenter(vaccinationCenter,listOfDates,StringArray);
    }

    /**
     * Save vaccination center boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @return the boolean
     */
    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return company.SaveVaccinationCenter(vaccinationCenter);
    }


    /**
     * This function returns an ArrayList of all the vaccination centers in the company
     *
     * @return The method returns an ArrayList of VaccinationCenter objects.
     */
    public ArrayList<VaccinationCenter> getVaccinationCenters (){
        return company.getVaccinationCenterStore().getVaccinationCenters();
    }

    /**
     * This function returns a list of all the scheduled vaccines in the company
     *
     * @return The list of scheduled vaccines.
     */
    public ArrayList<ScheduledVaccine> getScheduledVaccines(){
        return  company.getScheduledVaccineStore().getListOfScheduledVaccines();
    }


    /**
     * Check atributes boolean.
     *
     * @param vaccinationCenterName        the vaccination center name
     * @param vaccinationCenterAdress      the vaccination center adress
     * @param vaccinationCenterPhoneNumber the vaccination center phone number
     * @param vaccinationCenterEmailAdress the vaccination center email adress
     * @param vaccinationCenterFaxNumber   the vaccination center fax number
     * @param vaccinationCenterWebAdress   the vaccination center web adress
     * @return the boolean
     */
    public boolean checkAtributes (String vaccinationCenterName,String vaccinationCenterAdress,int vaccinationCenterPhoneNumber,String vaccinationCenterEmailAdress,long vaccinationCenterFaxNumber, String vaccinationCenterWebAdress){
        boolean result = true;
        for (VaccinationCenter vaccinationCenterCheck : vaccinationCenterStore.getVaccinationCenters()){
            if (vaccinationCenterCheck.getName().equals(vaccinationCenterName)){
                result = false;
                System.out.println("Name already exists.");
            }
            if (vaccinationCenterCheck.getAddress().equals(vaccinationCenterAdress)){
                result = false;
                System.out.println("A vaccination center is already registered at that adress");
            }
            if (vaccinationCenterCheck.getPhoneNumber() == vaccinationCenterPhoneNumber){
                result = false;
                System.out.println("Phone number already used by another registered center.");
            }
            if (vaccinationCenterCheck.getEmailAdress().equals(vaccinationCenterEmailAdress)){
                result = false;
                System.out.println("Email adress already used by another vaccination center.");
            }
            if (vaccinationCenterCheck.getFaxNumber() == vaccinationCenterFaxNumber){
                result = false;
                System.out.println("Fax Number already in use by another vaccination center.");
            }
            if (vaccinationCenterCheck.getName().equals(vaccinationCenterName)){
                result = false;
                System.out.println("Name already exists.");
            }
            if (vaccinationCenterCheck.getWebsiteAdress().equals(vaccinationCenterWebAdress)){
                result = false;
                System.out.println("Web adress already in use by another center.");
            }
        }
        return result;
    }


    /**
     * Add days.
     *
     * @param vaccinationCenter the vaccination center
     */
    public void addDays (VaccinationCenter vaccinationCenter){
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
     * Add slots to date.
     *
     * @param vaccinationCenter the vaccination center
     */
    public void addSlotsToDate(VaccinationCenter vaccinationCenter){
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








}
