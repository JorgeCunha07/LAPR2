package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Scheduled vaccine.
 */
public class ScheduledVaccine {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String SNSNumber;
    private String vaccinationCenterName;

    private LocalDate date;
    private LocalTime hours;
    private VaccinationCenterSlot slot;

    private AgeGroup administrationDetail;
    private String vaccineType;
    private String vaccineName;
    private LocalDateTime dayAndHoursLOCALDATETIME;

    /**
     * Instantiates a new Scheduled vaccine.
     *
     * @param SNSNumber             the sns number
     * @param vaccinationCenterName the vaccination center name
     * @param date                  the date
     * @param slot                  the slot
     * @param hours                 the hours
     * @param vaccineType           the vaccine type
     * @param dayAndHours           the day and hours
     */
    public ScheduledVaccine(String SNSNumber, String vaccinationCenterName, LocalDate date, VaccinationCenterSlot slot, LocalTime hours, String vaccineType, LocalDateTime dayAndHours) {
        this.SNSNumber = SNSNumber;
        this.vaccinationCenterName = vaccinationCenterName;
        this.date = date;
        this.slot = slot;
        this.hours = hours;
        this.vaccineType = vaccineType;
        this.dayAndHoursLOCALDATETIME = dayAndHours;
    }

    /**
     * Gets sns number.
     *
     * @return the sns number
     */
    public String getSNSNumber() {
        return SNSNumber;
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public LocalTime getHours() {
        return hours;
    }

    /**
     * Sets hours.
     *
     * @param hours the hours
     */
    public void setHours(LocalTime hours) {
        this.hours = hours;
    }

    /**
     * Gets administration detail.
     *
     * @return the administration detail
     */
    public AgeGroup getAdministrationDetail() {
        return administrationDetail;
    }

    /**
     * Sets administration detail.
     *
     * @param administrationDetail the administration detail
     */
    public void setAdministrationDetail(AgeGroup administrationDetail) {
        this.administrationDetail = administrationDetail;
    }

    /**
     * Gets day and hours localdatetime.
     *
     * @return the day and hours localdatetime
     */
    public LocalDateTime getDayAndHoursLOCALDATETIME() {
        return dayAndHoursLOCALDATETIME;
    }

    /**
     * Sets day and hours localdatetime.
     *
     * @param dayAndHoursLOCALDATETIME the day and hours localdatetime
     */
    public void setDayAndHoursLOCALDATETIME(LocalDateTime dayAndHoursLOCALDATETIME) {
        this.dayAndHoursLOCALDATETIME = dayAndHoursLOCALDATETIME;
    }

    /**
     * Sets sns number.
     *
     * @param SNSNumber the sns number
     */
    public void setSNSNumber(String SNSNumber) {
        this.SNSNumber = SNSNumber;
    }

    /**
     * Gets vaccination center name.
     *
     * @return the vaccination center name
     */
    public String getVaccinationCenterName() {
        return vaccinationCenterName;
    }

    /**
     * Sets vaccination center name.
     *
     * @param vaccinationCenterName the vaccination center name
     */
    public void setVaccinationCenterName(String vaccinationCenterName) {
        this.vaccinationCenterName = vaccinationCenterName;
    }

    /**
     * Gets slot.
     *
     * @return the slot
     */
    public VaccinationCenterSlot getSlot() {
        return slot;
    }

    /**
     * Sets slot.
     *
     * @param slot the slot
     */
    public void setSlot(VaccinationCenterSlot slot) {
        this.slot = slot;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets vaccine type.
     *
     * @return the vaccine type
     */
    public String getVaccineType() {
        return vaccineType;
    }


    /**
     * Sets vaccine type.
     *
     * @param vaccineType the vaccine type
     */
    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     * Gets vaccine name.
     *
     * @return the vaccine name
     */
    /*public String getVaccineName() {
        return vaccineName;
    }

     */

    /*public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

     */

    @Override
    public String toString() {

        return String.format(" Scheduled vaccine: \n SNS Number: %s \n Vaccination Center: %s \n Date: %s \n %s \n Vaccine Type: %s ", SNSNumber, vaccinationCenterName, date.format(formatter), slot/*, hours*/, vaccineType/* vaccineName, administrationDetail*/);
    }
}
