package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * The type Vaccination center.
 */
public class VaccinationCenter {
    /**
     * The Formatter.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private String coordinatorEmail;
    private String name;
    private String address;
    private int phoneNumber;
    private String emailAdress;
    private long faxNumber;
    private String websiteAdress;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private int slotDuration;
    private int vaccinesPerSlot;
    private  ArrayList<VaccinatonCenterDate> listOfDates;
    private ArrayList<String> SNSUsersOnVaccinationCenter;

    private FullyVaccinatedUsersPerDay fullyVaccinatedUsersPerDay;

    private ArrayList<EntryRecord> arrivalsList = new ArrayList<>();

    // Setting the default values for the attributes of the class.
    private static final String NAME = "no name";
    private static final String ADRESS = "no adress";
    private static final int PHONE_NUMBER = 000000000;
    private static final String EMAIL_ADRESS = "no email";
    private static final long FAX_NUMBER = 0000000000;
    private static final String WEBSITE_ADRESS = "no website adress";
    private static final LocalTime OPENING_HOUR = null;
    private static final LocalTime CLOSING_HOUR = null;
    private static final int SLOT_DURATION = 0;
    private static final int VACCINES_PER_SLOT = 0;

    /**
     * Instantiates a new Vaccination center.
     */
    public  VaccinationCenter() {

    }


    // This is a constructor that receives all the attributes of the class and sets them.
    public VaccinationCenter(String coordinatorEmail, String name, String adress, int phoneNumber, String emailAdress, long faxNumber, String websiteAdress, LocalTime openingHour, LocalTime closingHour, int slotDuration, int vaccinesPerSlot){
        this.coordinatorEmail = coordinatorEmail;
        this.name = name;
        this.address = adress;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.faxNumber = faxNumber;
        this.websiteAdress = websiteAdress;
         this.openingHour = openingHour;
         this.closingHour = closingHour;
        this.slotDuration = slotDuration;
        this.vaccinesPerSlot = vaccinesPerSlot;
    }



    // A constructor that receives all the attributes of the class and sets them.
    public VaccinationCenter(VaccinationCenter vaccinationCenter, ArrayList<VaccinatonCenterDate> listOfDates, ArrayList<String> listOfSNSNumbers){
        this.coordinatorEmail = vaccinationCenter.coordinatorEmail;
        this.name = vaccinationCenter.name;
        this.address = vaccinationCenter.address;
        this.phoneNumber = vaccinationCenter.phoneNumber;
        this.emailAdress = vaccinationCenter.emailAdress;
        this.faxNumber = vaccinationCenter.faxNumber;
        this.websiteAdress = vaccinationCenter.websiteAdress;
        this.openingHour = vaccinationCenter.openingHour;
        this.closingHour = vaccinationCenter.closingHour;
        this.slotDuration = vaccinationCenter.slotDuration;
        this.vaccinesPerSlot = vaccinationCenter.vaccinesPerSlot;
        this.listOfDates = listOfDates;
        this.SNSUsersOnVaccinationCenter = listOfSNSNumbers;
    }




    /**
     * This function returns the email of the coordinator
     *
     * @return The coordinatorEmail is being returned.
     */
    public String getCoordinatorEmail() {
        return coordinatorEmail;
    }

    /**
     * This function sets the coordinatorEmail variable to the value of the parameter coordinatorEmail
     *
     * @param coordinatorEmail The email address of the coordinator.
     */
    public void setCoordinatorEmail(String coordinatorEmail) {
        this.coordinatorEmail = coordinatorEmail;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email adress.
     *
     * @return the email adress
     */
    public String getEmailAdress() {
        return emailAdress;
    }

    /**
     * Sets email adress.
     *
     * @param emailAdress the email adress
     */
    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    /**
     * Gets fax number.
     *
     * @return the fax number
     */
    public long getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets fax number.
     *
     * @param faxNumber the fax number
     */
    public void setFaxNumber(long faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * Gets website adress.
     *
     * @return the website adress
     */
    public String getWebsiteAdress() {
        return websiteAdress;
    }

    /**
     * Sets website adress.
     *
     * @param websiteAdress the website adress
     */
    public void setWebsiteAdress(String websiteAdress) {
        this.websiteAdress = websiteAdress;
    }


    /**
     * Gets opening hour.
     *
     * @return the opening hour
     */
    public LocalTime getOpeningHour() {
        return openingHour;
    }

    /**
     * Sets opening hour.
     *
     * @param openingHour the opening hour
     */
    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    /**
     * Gets closing hour.
     *
     * @return the closing hour
     */
    public LocalTime getClosingHour() {
        return closingHour;
    }

    /**
     * Sets closing hour.
     *
     * @param closingHour the closing hour
     */
    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }

    /**
     * Gets slot duration.
     *
     * @return the slot duration
     */
    public int getSlotDuration() {
        return slotDuration;
    }

    /**
     * Sets slot duration.
     *
     * @param slotDuration the slot duration
     */
    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    /**
     * Gets vaccines per slot.
     *
     * @return the vaccines per slot
     */
    public int getVaccinesPerSlot() {
        return vaccinesPerSlot;
    }

    /**
     * Sets vaccines per slot.
     *
     * @param vaccinesPerSlot the vaccines per slot
     */
    public void setVaccinesPerSlot(int vaccinesPerSlot) {
        this.vaccinesPerSlot = vaccinesPerSlot;
    }


    /**
     * Gets sns users on vaccination center.
     *
     * @return the sns users on vaccination center
     */
    public ArrayList<String> getSNSUsersOnVaccinationCenter() {
        return SNSUsersOnVaccinationCenter;
    }

    /**
     * Sets sns users on vaccination center.
     *
     * @param SNSUsersOnVaccinationCenter the sns users on vaccination center
     */
    public void setSNSUsersOnVaccinationCenter(ArrayList<String> SNSUsersOnVaccinationCenter) {
        this.SNSUsersOnVaccinationCenter = SNSUsersOnVaccinationCenter;
    }


    /**
     * Sns user arrival.
     *
     * @param SnsUserNumber the sns user number
     */
    public void SNSUserArrival(String SnsUserNumber){
        this.getSNSUsersOnVaccinationCenter().add(SnsUserNumber);
    }


    /**
     * Validate vaccination center boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @return the boolean
     */
    public boolean validateVaccinationCenter(VaccinationCenter vaccinationCenter){
        boolean result = true;
        int phoneNumberLength = String.valueOf(vaccinationCenter.getPhoneNumber()).length();
        int faxNumberLength = String.valueOf(vaccinationCenter.getFaxNumber()).length();
        if (vaccinationCenter.name == null || vaccinationCenter.address == null || vaccinationCenter.emailAdress == null || vaccinationCenter.websiteAdress == null ){
            result = false;
            throw new IllegalArgumentException("Arguments can't be null");
        }
        else if (phoneNumberLength != 9){
            System.out.println("Phone number digits incorrect. A phone number must have 9 digits.");
            result = false;
        }
        else if (faxNumberLength != 10){
            System.out.println("Fax number incorrect. A fax number must have 10 digits.");
            result = false;
        }
        return result;
   }


    /**
     * Validate vaccination center time boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @return the boolean
     */
    public boolean validateVaccinationCenterTime(VaccinationCenter vaccinationCenter){
        if (vaccinationCenter.getOpeningHour().isBefore(vaccinationCenter.getClosingHour())){
            return true;
        }else return false;
    }


    /**
     * > This function returns the arrivalsList
     *
     * @return The arrivalsList is being returned.
     */
    public ArrayList<EntryRecord> getArrivalsList() {
        return arrivalsList;
    }


    /**
     * > This function adds an entry record to the arrivals list
     *
     * @param exEntryRecord The EntryRecord object that is to be added to the arrivals list.
     */
    public void registerArrival(EntryRecord exEntryRecord){
        getArrivalsList().add(exEntryRecord);
    }

    /**
     * Gets list of dates.
     *
     * @return the list of dates
     */
    public ArrayList<VaccinatonCenterDate> getListOfDates() {
        return listOfDates;
    }

    /**
     * Sets list of dates.
     *
     * @param listOfDates the list of dates
     */
    public void setListOfDates(ArrayList<VaccinatonCenterDate> listOfDates) {
        this.listOfDates = listOfDates;
    }



    @Override
    public String toString() {
        return String.format("Vaccination Center %s data: " +
                "\n Adress: %s | Phone Number: %d " +
                "\n Email: %s | Fax number : %d " +
                "\n Website adress: %s" +
                "\n Opening hour: %s | Closing hour: %s " +
                "\n Slot duration: %d | Vaccines per slot: %d", name, address,phoneNumber,emailAdress,faxNumber,websiteAdress,
                openingHour.format(formatter),closingHour.format(formatter),slotDuration,vaccinesPerSlot);
    }


    /**
     * Gets dates.
     *
     * @return the dates
     */
    public String getDates() {
        return "VaccinationCenter:" + "\n" +
                "ListOfDates= " + listOfDates + "\n";
    }

    // Goes trought the days of the chosen time gap/interval
    
    public ArrayList<String> checkTimeStatistics(String time0, String time1){
        ArrayList<String> statistics = new ArrayList<>();
        for (LocalDate date = LocalDate.parse(time0, DateTimeFormatter.ofPattern("dd/MM/yyyy")); date.isBefore(LocalDate.parse(time1,  DateTimeFormatter.ofPattern("dd/MM/yyyy"))); date = date.plusDays(1)){
            
        }
        return  null;
    }

    // Method to run trought the SNS User List and trought the list of vaccines of each SNS User, need to run check whick was the last vaccine

    /*
    public int findTotalVaccinatedPerDay(LocalDate date1){
        for (SNSUser :
             ) {
            
        }
    } */


}
