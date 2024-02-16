package app.controller;

import app.domain.model.*;
import app.domain.model.Store.LegacyDataStore;
import jdk.jshell.Snippet;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The type Legacy data controller.
 */
public class LegacyDataController {

    private Company company;
    private List<LegacyData> legacyDataList;
    private LegacyDataStore store;

    /**
     * Instantiates a new Legacy data controller.
     */
    public LegacyDataController() {
        company = App.getInstance().getCompany();
        store = company.getLegacyDataStore();
    }

    /**
     * Get legacy data array list.
     *
     * @return the array list
     */
    public ArrayList<LegacyData> getLegacyData() {
        return store.getLegacyDataList();
    }

    /**
     * Add's a "csv line" to the arraylist
     *
     * @param legacyData the legacy data
     * @return the boolean
     */
    public boolean addToList(LegacyData legacyData) {
        return store.getLegacyDataList().add(legacyData);
    }

    /**
     * Check if sns user exists boolean, in order to validate the data
     *
     * @param snsUserNumber the sns user number
     * @return the boolean
     */
    public boolean checkIfSnsUserExists(String snsUserNumber) {
        boolean status = false;
        ArrayList<SNSUser> listOfSnsUsers = company.getSnsUserStore().snsUserList;
        for (SNSUser snsUser : listOfSnsUsers) {
            if (snsUser.getSnsUserNumber().equals(snsUserNumber)) {
                status = true;
            }
        }

        return status;
    }

    /**
     * Check if vaccine exists boolean, in order to validate the data
     *
     * @param vaccineName the vaccine name
     * @return the boolean
     */
    public boolean checkIfVaccineExists(String vaccineName) {
        boolean status = false;
        for (Vaccine vaccine : company.getListOfVaccines()) {
            if (vaccine.getName().equals(vaccineName)) {
                status = true;
            }
        }
        return status;
    }


    /**
     * Gets vaccine type description.
     *
     * @param vaccineName the vaccine name
     * @return the vaccine type description
     */
    public String getVaccineTypeDescription(String vaccineName) {
        String shortDescription = "not found";
        Vaccine vaccine;
        String typeCode = null;
        /*For to get the vaccine type code from a given vaccine*/
        if(!company.getVaccineTypes().isEmpty()) {
            for (Vaccine vaccineObject : company.getListOfVaccines()) {
                if (vaccineObject.getName().equals(vaccineName)) {
                    vaccine = vaccineObject;
                    typeCode = vaccine.getVaccineType().getCode();
                }
            }
            /*For to get the description of the vaccine type, given the vaccine type code*/
            for (VaccineType vaccineTypeObject : company.getVaccineTypes()) {
                if (typeCode.equals(vaccineTypeObject.getCode())) {
                    shortDescription = vaccineTypeObject.getDescription();
                }
            }
        }
        return shortDescription;
    }

    /**
     * Checks if the header order has changed and corrects it if needed.
     * Splits the line and gets all the data from that line to add it to the LegacyDataStore, if the data is valid.
     * Add to array list boolean
     *
     * @return the boolean
     */
    public boolean addToArrayList() {
        BufferedReader bufferedReader;
        boolean status;
        FileReader fileReader = null;
        String line = null;
        String fileName;
        int nLinhas = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy H:mm");
        Scanner read = new Scanner(System.in);

        do {
            try {
                System.out.println("Please insert the file name: ");
                fileName = read.next();
                status = true;
                fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
                String headerLine = bufferedReader.readLine();
                LegacyData legacyData;

                /*Splits the header, in order to get the correct index for each parameter*/
                int snsUserNumberIndex = 0, vaccineIndex = 0, doseIndex = 0, loteNumberIndex = 0, scheduledTimeIndex = 0, arrivalTimeIndex = 0, administeredTimeIndex = 0, leavingTimeIndex = 0;
                String[] header = headerLine.split(";");
                for (int i = 0; i <= 7; i++) {
                    if (header[i].equals("SNSUSerNumber")) {
                        snsUserNumberIndex = i;
                    } else if (header[i].equals("VaccineName")) {
                        vaccineIndex = i;
                    } else if (header[i].equals("Dose")) {
                        doseIndex = i;
                    } else if (header[i].equals("LotNumber")) {
                        loteNumberIndex = i;
                    } else if (header[i].equals("ScheduledDateTime")) {
                        scheduledTimeIndex = i;
                    } else if (header[i].equals("ArrivalDateTime")) {
                        arrivalTimeIndex = i;
                    } else if (header[i].equals("NurseAdministrationDateTime")) {
                        administeredTimeIndex = i;
                    } else if (header[i].equals("LeavingDateTime")) {
                        leavingTimeIndex = i;
                    }
                }

                /*----------------*/

                /*While cicle that's going to read every line from the file and add it to the class*/
                while ((line = bufferedReader.readLine()) != null) {
                    nLinhas++;
                    String[] temp = line.split(";");
                    String snsUserNumber = temp[snsUserNumberIndex];
                    String vaccineName = temp[vaccineIndex];
                    String dose = temp[doseIndex];
                    String loteNumber = temp[loteNumberIndex];
                    LocalDateTime scheduled = LocalDateTime.parse(temp[scheduledTimeIndex], formatter);
                    LocalDateTime arrivalTime = LocalDateTime.parse(temp[arrivalTimeIndex], formatter);
                    LocalDateTime administeredTime = LocalDateTime.parse(temp[administeredTimeIndex], formatter);
                    LocalDateTime leftTime = LocalDateTime.parse(temp[leavingTimeIndex], formatter);

                    /* Method to check if sns user and vaccine exists */
                    /*It will only add to the list of legacy data if the sns user and vaccine exists*/
                    if (checkIfSnsUserExists(snsUserNumber) && checkIfVaccineExists(vaccineName)) {
                        String description = getVaccineTypeDescription(vaccineName);
                        legacyData = new LegacyData(snsUserNumber, vaccineName,description, dose, loteNumber, scheduled, arrivalTime, administeredTime, leftTime);
                        /*If the data already exists, it won't add it. Avoiding duplicates*/
                        if (!checkIfAlreadyExists(legacyData)) {
                            getLegacyData().add(legacyData);
                            addToEntryRecordAndLeavingRecordStore();
                        }
                    }
                }
                bufferedReader.close();

            } catch (Exception e) {
                System.out.println("Invalid data in the file, please select another file");
                status = false;
            }
        } while (!status);
        return status;
    }


    public boolean addToArrayListForGui(String fileName) {
        BufferedReader bufferedReader;
        boolean status;
        FileReader fileReader = null;
        String line = null;
        int nLinhas = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy H:mm");
        Scanner read = new Scanner(System.in);

        do {
            try {
                status = true;
                fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
                String headerLine = bufferedReader.readLine();
                LegacyData legacyData;

                /*Splits the header, in order to get the correct index for each parameter*/
                int snsUserNumberIndex = 0, vaccineIndex = 0, doseIndex = 0, loteNumberIndex = 0, scheduledTimeIndex = 0, arrivalTimeIndex = 0, administeredTimeIndex = 0, leavingTimeIndex = 0;
                String[] header = headerLine.split(";");
                for (int i = 0; i <= 7; i++) {
                    if (header[i].equals("SNSUSerNumber")) {
                        snsUserNumberIndex = i;
                    } else if (header[i].equals("VaccineName")) {
                        vaccineIndex = i;
                    } else if (header[i].equals("Dose")) {
                        doseIndex = i;
                    } else if (header[i].equals("LotNumber")) {
                        loteNumberIndex = i;
                    } else if (header[i].equals("ScheduledDateTime")) {
                        scheduledTimeIndex = i;
                    } else if (header[i].equals("ArrivalDateTime")) {
                        arrivalTimeIndex = i;
                    } else if (header[i].equals("NurseAdministrationDateTime")) {
                        administeredTimeIndex = i;
                    } else if (header[i].equals("LeavingDateTime")) {
                        leavingTimeIndex = i;
                    }
                }

                /*----------------*/

                /*While cicle that's going to read every line from the file and add it to the class*/
                while ((line = bufferedReader.readLine()) != null) {
                    nLinhas++;
                    String[] temp = line.split(";");
                    String snsUserNumber = temp[snsUserNumberIndex];
                    String vaccineName = temp[vaccineIndex];
                    String dose = temp[doseIndex];
                    String loteNumber = temp[loteNumberIndex];
                    LocalDateTime scheduled = LocalDateTime.parse(temp[scheduledTimeIndex], formatter);
                    LocalDateTime arrivalTime = LocalDateTime.parse(temp[arrivalTimeIndex], formatter);
                    LocalDateTime administeredTime = LocalDateTime.parse(temp[administeredTimeIndex], formatter);
                    LocalDateTime leftTime = LocalDateTime.parse(temp[leavingTimeIndex], formatter);

                    /* Method to check if sns user and vaccine exists */
                    /*It will only add to the list of legacy data if the sns user and vaccine exists*/
                    if (checkIfSnsUserExists(snsUserNumber) && checkIfVaccineExists(vaccineName)) {
                        String description = getVaccineTypeDescription(vaccineName);
                        legacyData = new LegacyData(snsUserNumber, vaccineName,description, dose, loteNumber, scheduled, arrivalTime, administeredTime, leftTime);
                        /*If the data already exists, it won't add it. Avoiding duplicates*/
                        if (!checkIfAlreadyExists(legacyData)) {
                            getLegacyData().add(legacyData);
                            addToEntryRecordAndLeavingRecordStore();
                        }
                    }
                }
                bufferedReader.close();

            } catch (Exception e) {
                System.out.println("Invalid data in the file, please select another file");
                status = false;
            }
        } while (!status);
        return status;
    }
    /**
     * Check if already exists entry leaving record boolean.
     *
     * @param entryRecord   the entry record
     * @param leavingRecord the leaving record
     * @return the boolean
     */
    public boolean checkIfAlreadyExistsEntryLeavingRecord(EntryRecord entryRecord, LeavingRecord leavingRecord){
        boolean status = false;
        for (EntryRecord objectEntry : company.getEntryRecordStore().getEntryRecords()){
            if(objectEntry.getVaccineSchedule().equals(entryRecord.getVaccineSchedule()) && objectEntry.getEntryDateTime().equals(entryRecord.getEntryDateTime())) {
                status = true;
            }
        }
        for (LeavingRecord objectLeaving : company.getLeavingRecordStore().getLeavingRecords()){
            if(objectLeaving.getVaccineSchedule().equals(leavingRecord.getVaccineSchedule()) && objectLeaving.getLeavingDateTime().equals(leavingRecord.getLeavingDateTime())) {
                status = true;
            }
        }
        return status;
    }


    /**
     * Add to entry record and leaving record store.
     */
    public void addToEntryRecordAndLeavingRecordStore(){
        for (ScheduledVaccine scheduledVaccine : company.getScheduledVaccineStore().getListOfScheduledVaccines()){
            for (LegacyData legacyData : getLegacyData()){
                if (scheduledVaccine.getSNSNumber().equals(legacyData.getSNSUser())){
                    if(scheduledVaccine.getDayAndHoursLOCALDATETIME().equals(legacyData.getScheduled())){
                        EntryRecord entryRecord = new EntryRecord(legacyData.getArrivalTime(),false,scheduledVaccine);
                        LeavingRecord leavingRecord = new LeavingRecord(legacyData.getLeftTime(),scheduledVaccine);
                        if(!checkIfAlreadyExistsEntryLeavingRecord(entryRecord, leavingRecord)) {
                            company.getEntryRecordStore().getEntryRecords().add(entryRecord);
                            company.getLeavingRecordStore().getLeavingRecords().add(leavingRecord);
                        }
                    }
                }
            }
        }
    }


    /**
     * Check if already exists boolean.
     *
     * @param legacyData the legacy data
     * @return the boolean
     */
    public boolean checkIfAlreadyExists(LegacyData legacyData) {
        boolean status = false;
        ArrayList<LegacyData> listOfLegacyData = getLegacyData();
        if (listOfLegacyData.isEmpty()) {
            status = false;
        } else
            for (LegacyData object : listOfLegacyData) {
                //This "if" is enough because if it is the same sns user taking the same vaccine dose, it already exists. It's impossible to take the same dose more than one time.
                if (object.getSNSUser().equals(legacyData.getSNSUser()) && object.getVaccineName().equals(legacyData.getVaccineName()) && object.getDose().equals(legacyData.getDose())) {
                    status = true;
                }
            }
        return status;
    }

    /**
     * Get size of array list int.
     *
     * @return the int
     */
    public int getSizeOfArrayList() {
        return getLegacyData().size();
    }

    /**
     * Bubble sort.
     * Bubble sorting method. It works by swapping adjacent elements if they are in the wrong order.
     *
     * @param nLinhas      the n linhas
     * @param option       the option
     * @param sortingOrder the sorting order
     */
    public void bubbleSort(int nLinhas, int option, int sortingOrder) {
        ArrayList<LegacyData> legacyData = getLegacyData();

        if (option == 1) {
            if (sortingOrder == 1) {
                for (int i = 0; i < nLinhas - 1; i++) {
                    for (int j = i + 1; j < nLinhas; j++) {
                        if (legacyData.get(j).getArrivalTime().isBefore(legacyData.get(i).getArrivalTime())) {
                            Collections.swap(legacyData, i, j);
                        }
                    }
                }
            } else if (sortingOrder == 2) {
                for (int i = 0; i < nLinhas - 1; i++) {
                    for (int j = i + 1; j < nLinhas; j++) {
                        if (legacyData.get(j).getArrivalTime().isAfter(legacyData.get(i).getArrivalTime())) {
                            Collections.swap(legacyData, i, j);
                        }
                    }
                }
            }
        } else if (option == 2) {
            if (sortingOrder == 1) {
                for (int i = 0; i < nLinhas - 1; i++) {
                    for (int j = i + 1; j < nLinhas; j++) {
                        if (legacyData.get(j).getLeftTime().isBefore(legacyData.get(i).getLeftTime())) {
                            Collections.swap(legacyData, i, j);
                        }
                    }
                }
            } else if (sortingOrder == 2) {
                for (int i = 0; i < nLinhas - 1; i++) {
                    for (int j = i + 1; j < nLinhas; j++) {
                        if (legacyData.get(j).getLeftTime().isAfter(legacyData.get(i).getLeftTime())) {
                            Collections.swap(legacyData, i, j);
                        }
                    }
                }
            }
        }

    }

    /**
     * Selection sort.
     * Selection sorting method. It works by repeatedly finding the minimum element in the list and placing it in the beggining.
     *
     * @param option the arrival/leaving time option
     * @param order  the ascending/descending order
     */
    public void selectionSort(int option, int order) {
        ArrayList<LegacyData> legacyData = getLegacyData();
        int size = legacyData.size();
        switch (option) {
            case 1:
                switch (order) {
                    case 1:
                        for (int i = 0; i < size - 1; i++) {
                            int minimum = i;
                            for (int j = i + 1; j < size; j++) {
                                if (legacyData.get(j).getArrivalTime().isBefore(legacyData.get(minimum).getArrivalTime())) {
                                    minimum = j;
                                }
                            }
                            LegacyData temp = legacyData.get(minimum);
                            legacyData.set(minimum, legacyData.get(i));
                            legacyData.set(i, temp);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < size - 1; i++) {
                            int maximum = i;
                            for (int j = i + 1; j < size; j++) {
                                if (legacyData.get(j).getArrivalTime().isAfter(legacyData.get(maximum).getArrivalTime())) {
                                    maximum = j;
                                }
                            }
                            LegacyData temp = legacyData.get(maximum);
                            legacyData.set(maximum, legacyData.get(i));
                            legacyData.set(i, temp);
                        }
                        break;

                }
                break;
            case 2:
                switch (order) {
                    case 1:
                        for (int i = 0; i < size - 1; i++) {
                            int minimum = i;
                            for (int j = i + 1; j < size; j++) {
                                if (legacyData.get(j).getLeftTime().isBefore(legacyData.get(minimum).getLeftTime())) {
                                    minimum = j;
                                }
                            }
                            LegacyData temp = legacyData.get(minimum);
                            legacyData.set(minimum, legacyData.get(i));
                            legacyData.set(i, temp);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < size - 1; i++) {
                            int maximum = i;
                            for (int j = i + 1; j < size; j++) {
                                if (legacyData.get(j).getLeftTime().isAfter(legacyData.get(maximum).getLeftTime())) {
                                    maximum = j;
                                }
                            }
                            LegacyData temp = legacyData.get(maximum);
                            legacyData.set(maximum, legacyData.get(i));
                            legacyData.set(i, temp);
                        }
                        break;

                }
                break;

        }
    }


    /**
     * If the user wishes to store the saved data on a csv file, this creates and writes the file.
     *
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public void createFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new IOException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);


        for (LegacyData legacyData1 : getLegacyData()) {
            try {
                bw.write(legacyData1.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Gets entry records.
     *
     * @return the entry records
     */
    public ArrayList<EntryRecord> getEntryRecords() {
        return company.getEntryRecordStore().getEntryRecords();
    }

    /**
     * Gets leaving records.
     *
     * @return the leaving records
     */
    public ArrayList<LeavingRecord> getLeavingRecords() {
        return company.getLeavingRecordStore().getLeavingRecords();
    }

    /**
     * Gets a string telling the number of dose.
     *
     * @param dose the dose
     * @return the dose
     */
    public String getDose(int dose) {
        String doseText = null;

        if (dose == 1) {
            doseText = "Primeira";
        }
        if (dose == 2) {
            doseText = "Segunda";
        }
        if (dose == 3) {
            doseText = "Terceira";
        }
        if (dose == 4) {
            doseText = "Quarta";
        }
        if (dose == 5) {
            doseText = "Quinta";
        }
        return doseText;
    }

    /**
     * Exports the system data to the legacyData store, so that both the old and the new data can be sorted.
     *
     * @param entryData   the entry data
     * @param leavingData the leaving data
     */
    public void exportToLegacyData(ArrayList<EntryRecord> entryData, ArrayList<LeavingRecord> leavingData) {
        SNSUser snsUser = null;
        EntryRecord entryRecord = null;
        LeavingRecord leavingRecord = null;
        String snsUserNumber;
        String vaccineName = null;
        int dose = 0;
        String loteNumber = null;
        String loteNumberToData;
        LocalDate date;
        LocalDateTime scheduled;
        LocalDateTime arrivalTime;
        LocalDateTime administered = null;
        LocalDateTime administeredToData;
        LocalDateTime leavingTime;
        int count = 0;


        for (EntryRecord entryRecordObject : entryData) {
            for (LeavingRecord leavingRecordObject : leavingData) {
                if (entryRecordObject.getVaccineSchedule().getSNSNumber().equals(leavingRecordObject.getVaccineSchedule().getSNSNumber())) {
                    entryRecord = entryRecordObject;
                    leavingRecord = leavingRecordObject;
                    date = entryRecord.getVaccineSchedule().getDate();
                    for (SNSUser object : company.getSnsUserStore().snsUserList) {
                        if (entryRecord.getVaccineSchedule().getSNSNumber().equals(object.getSnsUserNumber())) {
                            snsUser = object;
                            for (Bulletin bulletinPage : snsUser.getVaccineBulletin()) {
                                if (bulletinPage.getDateTimeOfLastDose().toLocalDate().equals(date)) {
                                    vaccineName = bulletinPage.getVaccine().getName();
                                    loteNumber = bulletinPage.getLotNumber();
                                    dose = bulletinPage.getDose();
                                    administered = bulletinPage.getDateTimeOfLastDose();
                                    count++;

                                    snsUserNumber = entryRecord.getVaccineSchedule().getSNSNumber();
                                    String description = getVaccineTypeDescription(vaccineName);
                                    String doseText = getDose(dose);
                                    loteNumberToData = loteNumber;
                                    scheduled = entryRecord.getVaccineSchedule().getDayAndHoursLOCALDATETIME();
                                    arrivalTime = entryRecord.getEntryDateTime();
                                    administeredToData = administered;
                                    leavingTime = leavingRecord.getLeavingDateTime();

                                    LegacyData legacyDataToAdd = new LegacyData(snsUserNumber, vaccineName, description,doseText, loteNumberToData, scheduled, arrivalTime, administeredToData, leavingTime);
                                    if (!checkIfAlreadyExists(legacyDataToAdd)) {
                                        getLegacyData().add(legacyDataToAdd);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}
