package app.controller;

import app.domain.model.*;
import app.domain.model.Store.EntryRecordStore;
import app.domain.model.Store.LeavingRecordStore;
import app.domain.model.Store.VaccinationCenterStore;
import app.domain.shared.UtilsCheck;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.time.LocalDate;
import java.util.ArrayList;

public class AnalyseCenterPerformanceController {

    // A private variable that is not used anywhere in the class.
    private ArrayList<EntryRecord> entryRecords = new ArrayList<>();
    // A private variable that is not used anywhere in the class.
    private ArrayList<LeavingRecord> leavingRecords = new ArrayList<>();

    private VaccinationCenterStore vaccinationCenterStore;
    private ArrayList<VaccinationCenter> vaccinationCenters;
    private EntryRecordStore entryRecordStore;
    private LeavingRecordStore leavingRecordStore;
    private Company company;
    private PerformanceCenter performanceCenter;

    private VaccinationCenter vaccinationCenter;

    private AuthFacade authFacade;



    public AnalyseCenterPerformanceController() {
        company = App.getInstance().getCompany();
        vaccinationCenterStore = company.getVaccinationCenterStore();
        vaccinationCenters = vaccinationCenterStore.getVaccinationCenters();
        entryRecordStore = company.getEntryRecordStore();
        leavingRecordStore = company.getLeavingRecordStore();
        performanceCenter = new PerformanceCenter();
        authFacade = company.getAuthFacade();
    }

    public VaccinationCenterStore getVaccinationCenterStore() {
        return vaccinationCenterStore;
    }

    public void setVaccinationCenterStore(VaccinationCenterStore vaccinationCenterStore) {
        this.vaccinationCenterStore = vaccinationCenterStore;
    }

    public ArrayList<VaccinationCenter> getVaccinationCenters() {
        return vaccinationCenters;
    }

    public void setVaccinationCenters(ArrayList<VaccinationCenter> vaccinationCenters) {
        this.vaccinationCenters = vaccinationCenters;
    }

    /**
     * This function returns the entryRecordStore.
     *
     * @return The entryRecordStore object.
     */
    public EntryRecordStore getEntryRecordStore() {
        return entryRecordStore;
    }

    /**
     * This function sets the entryRecordStore variable to the value of the entryRecordStore parameter.
     *
     * @param entryRecordStore This is the name of the EntryRecordStore that you want to use.
     */
    public void setEntryRecordStore(EntryRecordStore entryRecordStore) {
        this.entryRecordStore = entryRecordStore;
    }

    /**
     * This function returns the leavingRecordStore variable.
     *
     * @return The leavingRecordStore object.
     */
    public LeavingRecordStore getLeavingRecordStore() {
        return leavingRecordStore;
    }

    /**
     * This function sets the leavingRecordStore variable to the value of the leavingRecordStore parameter.
     *
     * @param leavingRecordStore This is the name of the store that will be used to store the leaving records.
     */
    public void setLeavingRecordStore(LeavingRecordStore leavingRecordStore) {
        this.leavingRecordStore = leavingRecordStore;
    }

    /**
     * This function returns the company of the current user
     *
     * @return The company object.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * This function sets the company of the employee.
     *
     * @param company The company that the user is associated with.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * This function returns the performance center.
     *
     * @return The performanceCenter object.
     */
    public PerformanceCenter getPerformanceCenter() {
        return performanceCenter;
    }

    /**
     * > This function sets the performance center of the current object to the performance center passed in as a parameter
     *
     * @param performanceCenter The Performance Center server to connect to.
    /**
     * This function returns the vaccination center
     *
     * @return The vaccination center.
     */

    public void setPerformanceCenter(PerformanceCenter performanceCenter) {
        this.performanceCenter = performanceCenter;
    }

    /**
     * This function returns the vaccination center
     *
     * @return The vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * This function sets the vaccination center of the patient
     *
     * @param vaccinationCenter The vaccination center that the user is currently viewing.
     */
    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }
//______________________________________________________________________________________________________________________
    /**
     * This function returns an ArrayList of EntryRecord objects that are associated with a specific vaccination center
     *
     * @param vaccinationCenter The name of the vaccination center
     * @return An ArrayList of EntryRecord objects.
     */
    public ArrayList<EntryRecord> getTotalEntriesByVaccinationCenter(String vaccinationCenter) {

        return entryRecordStore.getTotalEntriesByVaccinationCenter(vaccinationCenter);
    }

    /**
     * > This function returns the total number of exits from a vaccination center
     *
     * @param vaccinationCenter The name of the vaccination center
     * @return An ArrayList of LeavingRecord objects.
     */
    public ArrayList<LeavingRecord> getTotalExitsByVaccinationCenter(String vaccinationCenter) {

        return leavingRecordStore.getTotalExitsByVaccinationCenter(vaccinationCenter);
    }

    //___________________________________________________________________________


    /**
     * > This function returns an ArrayList of EntryRecord objects from a given vaccination center on a given day
     *
     * @param vaccinationCenter The name of the vaccination center
     * @param day               the day of the entry records you want to get
     * @return An ArrayList of EntryRecord objects.
     */
    public ArrayList<EntryRecord> getEntryRecordsFromVaccinationCenterByDay(String vaccinationCenter, LocalDate day) {
        return entryRecordStore.getEntryRecordsFromVaccinationCenterByDay(vaccinationCenter, day);
    }

    /**
     * > Get all the leaving records from a vaccination center on a specific day
     *
     * @param vaccinationCenter the name of the vaccination center
     * @param day               the day of the leaving records you want to get
     * @return An ArrayList of LeavingRecord objects.
     */
    public ArrayList<LeavingRecord> getLeavingRecordsFromVaccinationCenterByDay(String vaccinationCenter, LocalDate day) {
        return leavingRecordStore.getLeavingRecordsFromVaccinationCenterByDay(vaccinationCenter, day);
    }

//___________________________________________________________________________________________________________________________

    /**
     * This function calculates the differences between entry and leaving records of vaccination center
     *
     * @param entryRecords   an ArrayList of EntryRecord objects
     * @param leavingRecords ArrayList of LeavingRecords
     * @param timeInterval   the time interval in minutes that you want to calculate the differences between entry and
     *                       leaving records.
     * @return The method returns an array of integers.
     */
    public int[] calculateDifferencesBetweenEntryAndLeavingRecordsOfVaccinationCenter(ArrayList<EntryRecord> entryRecords, ArrayList<LeavingRecord> leavingRecords, int timeInterval) {

        return performanceCenter.calculateDifferencesBetweenEntryAndLeavingRecordsOfVaccinationCenter(entryRecords, leavingRecords, timeInterval);
    }

    /**
     * Given an array of integers, return the start and end indices of the contiguous sublist with the maximum sum
     *
     * @param differences an array of integers
     * @return The maximum sum contiguous sublist.
     */
    public int[] calculateMaximumSumContiguousSublist(int[] differences) {

        return performanceCenter.calculateMaximumSumContiguousSublist(differences);

    }

    /**
     * It takes an array of integers and returns the sum of the contiguous sublist of the array which has the largest sum
     *
     * @param maximumSum The array of integers to be used for the calculation.
     * @return The sum of the contiguous sublist of the array that has the largest sum.
     */
    public long calculateSumofContiguousSublist(int[] maximumSum) {

        return performanceCenter.calculateSumofContiguousSublist(maximumSum);

    }


//___________________________________________________________________________________________________________________________________

    /**
     * This function checks if there are entries and exits in the vaccination center
     *
     * @param vaccinationCenter The vaccination center that we want to validate.
     * @return A boolean value.
     */
    public boolean validateVaccinationCenter(VaccinationCenter vaccinationCenter) {

        if (getTotalExitsByVaccinationCenter(vaccinationCenter.getName()).isEmpty() && getTotalEntriesByVaccinationCenter(vaccinationCenter.getName()).isEmpty()) {
            throw new RuntimeException("There is no exits and no entries.");
        }
        return true;
    }
    /**
     * > Checks if the given date is a valid birth date
     *
     * @param date The date to be checked.
     * @return A boolean value.
     */
    public boolean checkDate(String date) {
        return UtilsCheck.checkDate(date);
    }

    /**
     * It takes a day and a time interval as input, and returns an array of integers that represent the number of people
     * who entered the vaccination center at each time interval
     *
     * @param day          the day we want to analyze
     * @param timeInterval the time interval in minutes between each time slot.
     * @return The differences between the entry and leaving records of the vaccination center.
     */
    public Analysis analyzeVaccinationCenterPerformance(LocalDate day, int timeInterval) {

        entryRecords = getEntryRecordsFromVaccinationCenterByDay(vaccinationCenter.getName(), day);

        leavingRecords = getLeavingRecordsFromVaccinationCenterByDay(vaccinationCenter.getName(), day);

        int[] differences = calculateDifferencesBetweenEntryAndLeavingRecordsOfVaccinationCenter(entryRecords, leavingRecords, timeInterval);

        int[] maximumSum = calculateMaximumSumContiguousSublist(differences);

        long sum = calculateSumofContiguousSublist(maximumSum);

        String[] hoursPeriod= getHoursPeriodOfMaximumSumSublist(differences, timeInterval);

        Analysis analysis = new Analysis(vaccinationCenter.getName(), differences, maximumSum,hoursPeriod,sum);


        return analysis;
    }

    private String[] getHoursPeriodOfMaximumSumSublist(int[] differences, int timeInterval) {
    return  performanceCenter.getHoursPeriodOfMaximumSumSublist(differences, timeInterval);
    }
//______________________________________________________________________________________________________________________
    /**
     * Get the email address of the currently logged in user.
     *
     * @return The email of the current user.
     */
    public String getUserEmail() {
        UserSession info = authFacade.getCurrentUserSession();
        return info.getUserId().toString();
    }

    /**
     * This function gets the vaccination center for the user
     */
    public void getVaccinationCenterForTheUser(){
        boolean flag = false;
        String email = getUserEmail();
        for (VaccinationCenter vaccinationCenterAux:vaccinationCenters) {
            if (email.equalsIgnoreCase(vaccinationCenterAux.getCoordinatorEmail())){
                setVaccinationCenter(vaccinationCenterAux);
                 flag = true;
            }
        }
        if(!flag)
            throw new RuntimeException("There is no Vaccination Center for this center Coordinator.");
        validateVaccinationCenter(vaccinationCenter);
    }

    public void getVaccinationCenterForTheUserJavaFX(String email){
        boolean flag = false;
        for (VaccinationCenter vaccinationCenterAux:vaccinationCenters) {
            if (email.equalsIgnoreCase(vaccinationCenterAux.getCoordinatorEmail())){
                setVaccinationCenter(vaccinationCenterAux);
                flag = true;
            }
        }
        if(!flag)
            throw new RuntimeException("There is no Vaccination Center for this center Coordinator.");
        validateVaccinationCenter(vaccinationCenter);
    }

}
