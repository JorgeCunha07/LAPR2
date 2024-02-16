package app.domain.model;

import java.time.LocalDateTime;


public class LeavingRecord {

    // A private variable that is used to store the leaving date time.
    private LocalDateTime leavingDateTime;

    // A private variable that is used to store the vaccine schedule.
    private ScheduledVaccine vaccineSchedule;


    // A constructor that takes in two parameters, leavingDateTime and vaccineSchedule.
    public LeavingRecord(LocalDateTime leavingDateTime, ScheduledVaccine vaccineSchedule) {
        setLeavingDateTime(leavingDateTime);
        setVaccineSchedule(vaccineSchedule);
    }

    /**
     * > This function returns the leaving date and time of the car
     *
     * @return The leavingDateTime is being returned.
     */
    public LocalDateTime getLeavingDateTime() {
        return leavingDateTime;
    }

    /**
     * If the leavingDateTime is null, throw an IllegalArgumentException with the message "Entry Date Time cannot be null!"
     * Otherwise, set the leavingDateTime to the value of the parameter.
     *
     * @param leavingDateTime The time the vehicle left the parking lot.
     */
    public void setLeavingDateTime(LocalDateTime leavingDateTime) {
        if (leavingDateTime == null)
            throw new IllegalArgumentException("Entry Date Time cannot be null!");
        this.leavingDateTime = leavingDateTime;
    }

    /**
     * This function returns the vaccine schedule for the patient
     *
     * @return The vaccineSchedule is being returned.
     */
    public ScheduledVaccine getVaccineSchedule() {
        return vaccineSchedule;
    }

    /**
     * If the vaccineSchedule is null, throw an IllegalArgumentException with the message "Vaccine Schedule cannot be
     * null!" Otherwise, set the vaccineSchedule to the given vaccineSchedule.
     *
     * @param vaccineSchedule The vaccine schedule that the user is currently viewing.
     */
    public void setVaccineSchedule(ScheduledVaccine vaccineSchedule) {
        if (vaccineSchedule == null)
            throw new IllegalArgumentException("Vaccine Schedule cannot be null!");
        this.vaccineSchedule = vaccineSchedule;
    }

    /**
     * If the two objects are the same, or if the other object is null or not the same class, return false. Otherwise, return
     * true if the two objects have the same vaccine schedule and leaving date/time
     *
     * @param otherObject The object to compare this LeavingRecord to.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;

        if (otherObject == null || otherObject.getClass() != this.getClass())
            return false;

        LeavingRecord otherEntryRecord = (LeavingRecord) otherObject;
        return (this.vaccineSchedule.equals(otherEntryRecord.vaccineSchedule) &&
                this.leavingDateTime.equals(otherEntryRecord.leavingDateTime));

    }


}