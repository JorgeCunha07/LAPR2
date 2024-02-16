package app.domain.model;

import java.time.LocalDateTime;

/**
 * This class is used to create an entry record object that contains the date and time of the entry, the vaccine schedule,
 * and whether the user is waiting or not
 */

public class EntryRecord {

    /**
     * Declaring a variable of type LocalDateTime.
     */
    private LocalDateTime entryDateTime;

    /**
     * Declaring a variable of type Boolean.
     */
    private Boolean waiting;

    /**
     * Declaring a variable of type VaccineSchedule.
     */
    private ScheduledVaccine vaccineSchedule;

    /**
     * This is a constructor that takes in a LocalDateTime, a boolean, and a VaccineSchedule object. It then calls the
     * setter methods for each of the variables.
     */
    public EntryRecord(LocalDateTime entryDateTime, Boolean waiting, ScheduledVaccine vaccineSchedule) {
        setEntryDateTime(entryDateTime);
        setWaiting(waiting);
        setVaccineSchedule(vaccineSchedule);
    }
/*
    public EntryRecord() {

    }
*/



    /**
     * This function returns the value of the waiting variable.
     *
     * @return The boolean value of the variable waiting.
     */
    public boolean isWaiting() {
        return waiting;
    }

    /**
     * This function sets the value of the waiting variable to the value of the waiting parameter.
     *
     * @param waiting This is a boolean value that indicates whether the thread is waiting for a resource.
     */
    public void setWaiting(Boolean waiting) {
        if (waiting == null)
            throw new IllegalArgumentException("Waiting property cannot be null!");
        this.waiting = waiting;
    }

    /**
     * This function returns the entryDateTime of the object
     *
     * @return The entryDateTime variable is being returned.
     */
    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    /**
     * This function sets the entryDateTime variable to the value of the entryDateTime parameter
     *
     * @param entryDateTime The date and time the entry was created.
     */
    public void setEntryDateTime(LocalDateTime entryDateTime) {
        if (entryDateTime == null)
            throw new IllegalArgumentException("Entry Date Time cannot be null!");
        this.entryDateTime = entryDateTime;
    }

    /**
     * This function returns the vaccine schedule
     *
     * @return The vaccine schedule is being returned.
     */
    public ScheduledVaccine getVaccineSchedule() {
        return vaccineSchedule;
    }

    /**
     * This function sets the vaccine schedule for the patient
     *
     * @param vaccineSchedule The vaccine schedule to be added to the database.
     */
    public void setVaccineSchedule(ScheduledVaccine vaccineSchedule) {
        if (vaccineSchedule == null)
            throw new IllegalArgumentException("Vaccine Schedule cannot be null!");
        this.vaccineSchedule = vaccineSchedule;
    }
    public String getNameOfVaccinationCenter() {
        return getVaccineSchedule().getVaccinationCenterName();

            }

    /**
     * If the two objects are the same the methods are equal.
     * If the other object is null, or if the other object is not an instance of the
     * same class, then the two objects are not equal. Otherwise, the two objects are equal if their vaccine schedules,
     * entry date/times, and waiting statuses are equal
     *
     * @param otherObject The object to compare this EntryRecord to.
     * @return The hash code of the object.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;

        if (otherObject == null || otherObject.getClass() != this.getClass())
            return false;

        EntryRecord otherEntryRecord = (EntryRecord) otherObject;
        return (this.vaccineSchedule.equals(otherEntryRecord.vaccineSchedule) &&
                this.entryDateTime.equals(otherEntryRecord.entryDateTime) &&
                this.waiting == otherEntryRecord.waiting);
    }


}