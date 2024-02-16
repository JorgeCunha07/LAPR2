package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VaccineAdministrationController {

    private final Company company;

    private VaccinationCenter vaccinationCenter;

    private VaccineType vaccineType;

    private SNSUser snsUser;

    private LocalDateTime localDateTime;

    private Vaccine vaccine;

    private String lotNumber;

    private ScheduledVaccine scheduledVaccine;

    private Timer timer;

    private ArrayList<ScheduledVaccine> waitingListForthisDay = new ArrayList<>();

    // Creating a new instance of the VaccineAdministrationController class.
    public VaccineAdministrationController() {
        company = App.getInstance().getCompany();
        timer = new Timer();
    }


    /**
     * This function returns the scheduledVaccine object
     *
     * @return The scheduledVaccine object is being returned.
     */
    public ScheduledVaccine getScheduledVaccine() {
        return scheduledVaccine;
    }

    /**
     * This function sets the scheduled vaccine for the patient
     *
     * @param scheduledVaccine The scheduled vaccine that the user is trying to add to the database.
     */
    public void setScheduledVaccine(ScheduledVaccine scheduledVaccine) {
        this.scheduledVaccine = scheduledVaccine;
    }

    public Timer getTimer(){
        return timer;
    }


    /**
     * This function returns the lot number of the car
     *
     * @return The lotNumber variable is being returned.
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * This function sets the lot number of the item
     *
     * @param lotNumber The lot number of the product.
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * This function returns an ArrayList of Strings with the names of the vaccination centers
     *
     * @return The name of the vaccination centers.
     */
    public ArrayList<String> vaccinationCenterName() {

        ArrayList<String> auxCenterName = new ArrayList<>();
        ArrayList<VaccinationCenter> aux = company.getVaccinationCenterStore().getVaccinationCenters();

        for (VaccinationCenter vacAUX : aux) {
            auxCenterName.add(vacAUX.getName());
        }
        return auxCenterName;
    }

    /**
     * This function sets the vaccination center of the employee to the vaccination center at the given index in the list
     * of vaccination centers.
     *
     * @param centerIndexInList The index of the vaccination center in the list of vaccination centers.
     */
    public void setVaccinationCenter(int centerIndexInList) {
        vaccinationCenter = company.getVaccinationCenterStore().getVaccinationCenters().get(centerIndexInList);
    }

    public void setVaccinationCenterJavaFX(String centerName) {

        for (VaccinationCenter vaccinationCenterax :company.getVaccinationCenterStore().getVaccinationCenters()) {
            if (centerName.equals(vaccinationCenterax.getName())){
                vaccinationCenter = vaccinationCenterax;
            }
        }
        if (vaccinationCenter == null){
            throw new RuntimeException("There are no vaccination Centers");
        }

    }

    /**
     * This function is used to set the vaccine type of the user
     *
     * @param indexUser The index of the user in the list of users who have entered the system.
     */
    public void setVaccineType(int indexUser) throws NoSuchObjectException {
        SNSUser snsUser = company.getUtilsCheck().getSnsUser(waitingListForthisDay.get(indexUser).getSNSNumber());
        String vaccineTypeName = null;
        for (int index = 0; index < company.getEntryRecordStore().getEntryRecords().size(); index++) {
            if (snsUser.getSnsUserNumber().equals(company.getEntryRecordStore().getEntryRecords().get(index).getVaccineSchedule().getSNSNumber()) && company.getEntryRecordStore().getEntryRecords().get(index).getEntryDateTime().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                vaccineTypeName = company.getEntryRecordStore().getEntryRecords().get(index).getVaccineSchedule().getVaccineType();
        }
        try {
            vaccineType = getVaccineTypeByName(vaccineTypeName);
        } catch (NoSuchObjectException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setVaccineTypeJavaFX(String snsNumber)  {

        String vaccineTypeName = null;
        for (int index = 0; index < company.getEntryRecordStore().getEntryRecords().size(); index++) {
            if (snsUser.getSnsUserNumber().equals(company.getEntryRecordStore().getEntryRecords().get(index).getVaccineSchedule().getSNSNumber()) && company.getEntryRecordStore().getEntryRecords().get(index).getEntryDateTime().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                vaccineTypeName = company.getEntryRecordStore().getEntryRecords().get(index).getVaccineSchedule().getVaccineType();
        }
        try {
            vaccineType = getVaccineTypeByName(vaccineTypeName);
        } catch (NoSuchObjectException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * > This function sets the snsUser variable to the user at the indexUser position in the snsUserNumberList
     *
     * @param indexUser The index of the user in the list of users.
     */
    public void setSnsUser(int indexUser) {
        snsUser = company.getUtilsCheck().getSnsUser(waitingListForthisDay.get(indexUser).getSNSNumber());
    }

    public void setSnsUserJavaFx(String snsNumber) {
        snsUser = company.getUtilsCheck().getSnsUser(snsNumber);
    }

    /**
     * Set the localDateTime variable to the current time.
     */
    public void setLocalDateTime() {
        localDateTime = LocalDateTime.now();
    }

    /**
     * If the number of doses for the vaccine type is 1, then set the vaccine to the vaccine at the given index in the list
     * of vaccines available. Otherwise, set the vaccine to the not first dose for the vaccine type
     *
     * @param vaccineIndex The index of the vaccine in the list of vaccines available for the child.
     */
    public void setVaccine(int vaccineIndex) {
        if (numberOfDosesForVaccineType() == Constants.FIRST_DOSE)
            vaccine = getVaccineByName(vaccinesAvailable().get(vaccineIndex));
        else
            vaccine = notFirstDoseForVaccineType();
    }

    public void setVaccineJavaFx(String vaccineJavaFx) {
        if (numberOfDosesForVaccineType() == Constants.FIRST_DOSE)
            for (String vaccineaux: vaccinesAvailable()) {
                if (vaccineJavaFx.equalsIgnoreCase(vaccineaux)){
                    vaccine = getVaccineByName(vaccineaux);
                }

            }
        else
            vaccine = notFirstDoseForVaccineType();
    }
    /**
     * This function returns the vaccine object that has the same name as the vaccineName parameter
     *
     * @param vaccineName The name of the vaccine to be searched for.
     * @return The vaccine object that has the same name as the vaccineName parameter.
     */
    private Vaccine getVaccineByName(String vaccineName) {
        for (Vaccine indexVaccine : company.getListOfVaccines()) {
            if (vaccineName.equals(indexVaccine.getName()))
                return indexVaccine;
        }
        return null;
    }

    /**
     * This function returns a vaccine type object by its name
     *
     * @param vaccineTypeName The name of the vaccine type.
     * @return The vaccine type with the name passed as parameter.
     */
    private VaccineType getVaccineTypeByName(String vaccineTypeName) throws NoSuchObjectException {
        for (VaccineType indexVaccineType : company.getVaccineTypes()) {
            if (vaccineTypeName.equals(indexVaccineType.getCode()))
                return indexVaccineType;
        }
        throw new NoSuchObjectException("Doesn´t exist the Vaccine type.");
    }


    /**
     * This function returns an ArrayList of Strings with the SNS numbers of the users that are waiting in the waiting room
     * of the vaccination center
     *
     * @return The method returns an ArrayList of Strings.
     */

    public ArrayList<ScheduledVaccine> waitingRoomUsersSnsNumber() {
        for (int index = 0; index < company.getEntryRecordStore().getEntryRecords().size(); index++) {
            if (company.getEntryRecordStore().getEntryRecords().get(index).getEntryDateTime().getDayOfMonth() == LocalDate.now().getDayOfMonth()
                    && company.getEntryRecordStore().getEntryRecords().get(index).getNameOfVaccinationCenter().equals(vaccinationCenter.getName())
                    && company.getEntryRecordStore().getEntryRecords().get(index).isWaiting()) {
                waitingListForthisDay.add(company.getEntryRecordStore().getEntryRecords().get(index).getVaccineSchedule());
            }
        }
        return waitingListForthisDay;
    }

    /**
     * The function validates the lot number by checking if the lot number is of length 10 and if the first 5 characters
     * are alphanumeric and the 6th character is a hyphen and the last 4 characters are numeric
     *
     * @param lotNumber The lot number to be validated.
     * @return A boolean value.
     */
    public boolean validateLotNumber(String lotNumber) {
        String numbers = "(.*\\d.*)";
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        int counter = 0;
        if (lotNumber.length() == Constants.LOT_NUMBER_LENGTH) {
            for (int index = 0; index < lotNumber.length(); index++) {
                if (index <= 4 && (lotNumber.matches(numbers) || lotNumber.matches(upperCaseChars) || lotNumber.matches(lowerCaseChars)))
                    counter++;
                else if (index == 5 && lotNumber.charAt(index) == '-')
                    counter++;
                else if (index >= 6 && (lotNumber.matches(numbers)))
                    counter++;
            }
        }
        return counter == Constants.LOT_NUMBER_LENGTH;
    }

    /**
     * "Return the number of doses for the vaccine type."
     * <p>
     * The function is a bit more complicated than that, but the summary is still accurate
     *
     * @return The number of doses for the vaccine type.
     */
    public int numberOfDosesForVaccineType() {
        for (int index = snsUser.getVaccineBulletin().size() - 1; index >= 0; index--) {
            if (snsUser.getVaccineBulletin().get(index).getVaccine().getVaccineType().equals(vaccineType))
                return snsUser.getVaccineBulletin().get(index).getDose();
        }
        return Constants.FIRST_DOSE;
    }

    /**
     * It takes the user's birthdate, splits it into an array of strings, converts the strings into integers, and then uses
     * the integers to create a LocalDate object. It then creates another LocalDate object for the current date, and uses
     * the Period class to calculate the difference between the two dates
     *
     * @return The age of the user.
     */
    private int getUserAge() {
        String[] birthdateSplit = snsUser.getBirthDate().split("-|/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * This function returns an ArrayList of Strings that contains the names of all the vaccines that are available for the
     * user to take
     *
     * @return An ArrayList of Strings containing the names of the vaccines that are available for the user.
     */
    public ArrayList<String> vaccinesAvailable() {
        ArrayList<String> vaccinesAvailable = new ArrayList<>();
        for (int rows = 0; rows < company.listOfVaccines.size(); rows++) {
            for (int columns = 0; columns < company.listOfVaccines.get(rows).getAdministrationProcess().getListOfAgeGroups().size(); columns++) {
                if (company.listOfVaccines.get(rows).getAdministrationProcess().getListOfAgeGroups().get(columns).checkIfBelongsInAgeGroup(getUserAge()))
                    vaccinesAvailable.add(company.listOfVaccines.get(rows).getName());
            }
        }
        return vaccinesAvailable;
    }

    /**
     * "If the user has already received a vaccine of the same type, return the vaccine that was not the first dose."
     * <p>
     * The function is a bit more complicated than that, but that's the gist of it
     *
     * @return The vaccine that is not the first dose for the vaccine type.
     */
    private Vaccine notFirstDoseForVaccineType() {
        for (int index = snsUser.getVaccineBulletin().size() - 1; index >= 0; index--) {
            if (snsUser.getVaccineBulletin().get(index).getVaccine().getVaccineType().equals(vaccineType))
                return snsUser.getVaccineBulletin().get(index).getVaccine();
        }
        return null;
    }

    /**
     * This function removes a user from the waiting list
     *
     * @param user The user ID of the user who is waiting for the SNS.
     */
    public void removeUserWaitingList(int user) {
        for (EntryRecord auxEntryRecord: company.getEntryRecordStore().getEntryRecords() ) {
            if (auxEntryRecord.getVaccineSchedule() == waitingListForthisDay.get(user)){
                auxEntryRecord.setWaiting(false);
            }
        }
        setScheduledVaccine(waitingListForthisDay.get(user));
        waitingListForthisDay.remove(user);
    }

    public void removeUserWaitingListJavaFx() {
        int contador = 0,finalRemove = 0;
        for (EntryRecord auxEntryRecord: company.getEntryRecordStore().getEntryRecords() ) {
            if (auxEntryRecord.getVaccineSchedule().getSNSNumber().equals(snsUser.getSnsUserNumber()) && auxEntryRecord.getVaccineSchedule().getDate().equals(LocalDate.now()) ){
                auxEntryRecord.setWaiting(false);
                setScheduledVaccine(auxEntryRecord.getVaccineSchedule());
            }
        }

        for (ScheduledVaccine auxSchedule: waitingListForthisDay) {
            if (auxSchedule.equals(scheduledVaccine)){
                finalRemove = contador;
            }
        contador++;
        }
        waitingListForthisDay.remove(finalRemove);

    }

    /**
     * This function adds a new bulletin to the user's vaccine bulletin list
     */
    public void addBulletinVaccine() {
        snsUser.addVaccineAdministrationToTheBulletin(new Bulletin(vaccine, localDateTime, numberOfDosesForVaccineType(), lotNumber));
    }

    //leaving
    public void leavingRecordSave(LocalDateTime timer) {
        company.getLeavingRecordStore().getLeavingRecords().add(new LeavingRecord(timer,scheduledVaccine));

    }

    public void notifyAfterXMinutes(int minutes) {
        TimerTask notifyAfter30Min = new TimerTask() {
            @Override
            public void run() {
                System.out.println("30 mins has passed! You can leave the center!");
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("SMS_Recovery.txt", "UTF-8");
                } catch (FileNotFoundException | UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
                writer.println("30 mins has passed! You can leave the center!");
                writer.close();
                leavingRecordSave(LocalDateTime.now());
                timer.cancel();
            }
        };
        timer.schedule(notifyAfter30Min, minutes*60*1000);
    }


}

