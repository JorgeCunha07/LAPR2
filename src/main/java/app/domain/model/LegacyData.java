package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Legacy data.
 */
public class LegacyData implements Serializable {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private String SNSUser;
    private String vaccineName;

    private String typeShortDescription;
    private String dose;
    private String loteNumber;
    private LocalDateTime scheduled;
    private LocalDateTime arrivalTime;
    private LocalDateTime administered;
    private LocalDateTime leftTime;

    /**
     * Instantiates a new Legacy data.
     *
     * @param SNSUser              the sns user
     * @param vaccineName          the vaccine name
     * @param typeShortDescription the type short description
     * @param dose                 the dose
     * @param loteNumber           the lote number
     * @param scheduled            the scheduled
     * @param arrivalTime          the arrival time
     * @param administered         the administered
     * @param leftTime             the left time
     */
    public LegacyData(String SNSUser, String vaccineName, String typeShortDescription,String dose, String loteNumber, LocalDateTime scheduled, LocalDateTime arrivalTime, LocalDateTime administered, LocalDateTime leftTime){
        this.SNSUser = SNSUser;
        this.vaccineName = vaccineName;
        this.typeShortDescription = typeShortDescription;
        this.dose = dose;
        this.loteNumber = loteNumber;
        this.scheduled = scheduled;
        this.arrivalTime = arrivalTime;
        this.administered = administered;
        this.leftTime = leftTime;
    }

    /**
     * Gets sns user.
     *
     * @return the sns user
     */
    public String getSNSUser() {
        return SNSUser;
    }

    /**
     * Sets sns user.
     *
     * @param SNSUser the sns user
     */
    public void setSNSUser(String SNSUser) {
        this.SNSUser = SNSUser;
    }

    /**
     * Gets vaccine name.
     *
     * @return the vaccine name
     */
    public String getVaccineName() {
        return vaccineName;
    }

    /**
     * Sets vaccine name.
     *
     * @param vaccineName the vaccine name
     */
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    /**
     * Gets type short description.
     *
     * @return the type short description
     */
    public String getTypeShortDescription() {return typeShortDescription;}

    /**
     * Sets type short description.
     *
     * @param typeShortDescription the type short description
     */
    public void setTypeShortDescription(String typeShortDescription) { this.typeShortDescription = typeShortDescription;}

    /**
     * Gets dose.
     *
     * @return the dose
     */
    public String getDose() {
        return dose;
    }

    /**
     * Sets dose.
     *
     * @param dose the dose
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     * Gets lote number.
     *
     * @return the lote number
     */
    public String getLoteNumber() {
        return loteNumber;
    }

    /**
     * Sets lote number.
     *
     * @param loteNumber the lote number
     */
    public void setLoteNumber(String loteNumber) {
        this.loteNumber = loteNumber;
    }

    /**
     * Gets scheduled.
     *
     * @return the scheduled
     */
    public LocalDateTime getScheduled() {
        return scheduled;
    }

    /**
     * Sets scheduled.
     *
     * @param scheduled the scheduled
     */
    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * Gets arrival time.
     *
     * @return the arrival time
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets arrival time.
     *
     * @param arrivalTime the arrival time
     */
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets administered.
     *
     * @return the administered
     */
    public LocalDateTime getAdministered() {
        return administered;
    }

    /**
     * Sets administered.
     *
     * @param administered the administered
     */
    public void setAdministered(LocalDateTime administered) {
        this.administered = administered;
    }

    /**
     * Gets left time.
     *
     * @return the left time
     */
    public LocalDateTime getLeftTime() {
        return leftTime;
    }

    /**
     * Sets left time.
     *
     * @param leftTime the left time
     */
    public void setLeftTime(LocalDateTime leftTime) {
        this.leftTime = leftTime;
    }

    public String toString(){
        return String.format("SNS number: %s |Vaccine name: %s |Type Description: %s |Dose: %s |Lote: %s |Scheduled Time: %s |Arrival Time: %s |Administered time: %s | Leaving time: %s\n",SNSUser,vaccineName,typeShortDescription,dose,loteNumber,scheduled.format(formatter),arrivalTime.format(formatter),administered.format(formatter),leftTime.format(formatter));
    }
}
