package app.domain.model.Store;

import app.domain.model.EntryRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntryRecordStore {

    // Creating an array list of EntryRecord objects.
    public ArrayList<EntryRecord> entryRecords;
    // A private variable that is used to store the current entry record.
    private EntryRecord entryRecord;

    // Creating a new array list of EntryRecord objects.
    public EntryRecordStore() {
        entryRecords = new ArrayList<>();

    }

    public  boolean registerArrival(EntryRecord entryRecordAux) {
        return entryRecords.add(entryRecordAux);
    }

    /**
     * > This function returns the entryRecords ArrayList
     *
     * @return An ArrayList of EntryRecord objects.
     */
    public ArrayList<EntryRecord> getEntryRecords() {
        return entryRecords;
    }

    /**
     * This function sets the entryRecords variable to the value of the parameter entryRecords
     *
     * @param entryRecords This is the list of EntryRecords that will be displayed in the list.
     */
    public void setEntryRecords(ArrayList<EntryRecord> entryRecords) {
        this.entryRecords = entryRecords;
    }

    /**
     * This function returns the entry record.
     *
     * @return The entry record.
     */
    public EntryRecord getEntryRecord() {
        return entryRecord;
    }

    /**
     * > This function sets the entry record of the current object to the entry record passed in as a parameter
     *
     * @param entryRecord The entry record that is being edited.
     */
    public void setEntryRecord(EntryRecord entryRecord) {
        this.entryRecord = entryRecord;
    }


 /**
  * This function returns an ArrayList of EntryRecords from a given Vaccination Center and a given day.
  *
  * @param vaccinationCenter String
  * @param day LocalDateTime
  * @return It is being returned an ArrayList of EntryRecords.
  */
 public ArrayList<EntryRecord> getEntryRecordsFromVaccinationCenterByDay(String vaccinationCenter, LocalDate day) {
    ArrayList<EntryRecord> aux = new ArrayList<>();

     for (EntryRecord auxER:entryRecords) {
         if (auxER.getVaccineSchedule().getVaccinationCenterName().equalsIgnoreCase(vaccinationCenter) && auxER.getVaccineSchedule().getDate().equals(day))
            aux.add(auxER);
     }

     return aux;
 }


    /**
     * > This function returns an ArrayList of EntryRecords that have the same VaccinationCenterName as the one passed as a
     * parameter
     *
     * @param vaccinationCenter the name of the vaccination center
     * @return An ArrayList of EntryRecords
     */
    public ArrayList<EntryRecord> getTotalEntriesByVaccinationCenter(String vaccinationCenter) {
        ArrayList<EntryRecord> aux = new ArrayList<>();
        for (EntryRecord auxER:entryRecords) {
            if (auxER.getVaccineSchedule().getVaccinationCenterName().equalsIgnoreCase(vaccinationCenter) )
                aux.add(auxER);
        }
        return aux;
    }








}
