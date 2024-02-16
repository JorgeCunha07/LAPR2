package app.domain.model.Store;

import app.domain.model.EntryRecord;
import app.domain.model.LeavingRecord;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LeavingRecordStore {

    // Creating an array list of LeavingRecord objects.
    public ArrayList<LeavingRecord> leavingRecords;
    // A private variable that is used to store a single LeavingRecord object.
    private LeavingRecord leavingRecord;

    // Creating an array list of LeavingRecord objects.
    public LeavingRecordStore() {
        leavingRecords = new ArrayList<>();
    }

    /**
     * This function returns the leavingRecords array list.
     *
     * @return An ArrayList of LeavingRecord objects.
     */
    public ArrayList<LeavingRecord> getLeavingRecords() {
        return leavingRecords;
    }

    /**
     * This function sets the value of the leavingRecords variable to the value of the leavingRecords parameter
     *
     * @param leavingRecords The list of leaving records.
     */
    public void setLeavingRecords(ArrayList<LeavingRecord> leavingRecords) {
        this.leavingRecords = leavingRecords;
    }

    /**
     * This function returns the leavingRecord variable.
     *
     * @return The leavingRecord object is being returned.
     */
    public LeavingRecord getLeavingRecord() {
        return leavingRecord;
    }

    /**
     * > This function sets the leaving record of the employee
     *
     * @param leavingRecord The leaving record to be added to the database.
     */
    public void setLeavingRecord(LeavingRecord leavingRecord) {
        this.leavingRecord = leavingRecord;
    }


    /**
     * Returns an ArrayList of LeavingRecords from a given VaccinationCenter on a given day.
     *
     * @param vaccinationCenter the name of the vaccination center
     * @param day the day of the leaving records you want to get
     * @return An ArrayList of LeavingRecords
     */
    public ArrayList<LeavingRecord> getLeavingRecordsFromVaccinationCenterByDay(String vaccinationCenter, LocalDate day) {
        ArrayList<LeavingRecord> aux = new ArrayList<>();

        for (LeavingRecord auxER:leavingRecords) {
            if (auxER.getVaccineSchedule().getVaccinationCenterName().equalsIgnoreCase(vaccinationCenter) && auxER.getVaccineSchedule().getDate().equals(day))
                aux.add(auxER);
        }

        return aux;
    }

    /**
     * > This function returns an ArrayList of LeavingRecords that have the same VaccinationCenterName as the one passed as
     * a parameter
     *
     * @param vaccinationCenter The name of the vaccination center
     * @return An ArrayList of LeavingRecords
     */
    public ArrayList<LeavingRecord> getTotalExitsByVaccinationCenter(String vaccinationCenter) {
        ArrayList<LeavingRecord> aux = new ArrayList<>();
        for (LeavingRecord auxER:leavingRecords) {
            if (auxER.getVaccineSchedule().getVaccinationCenterName().equalsIgnoreCase(vaccinationCenter) )
                aux.add(auxER);
        }
        return aux;
    }



}
