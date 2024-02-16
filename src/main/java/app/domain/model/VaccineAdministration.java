package app.domain.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class VaccineAdministration {

    private String snsNumber;
    private Vaccine vaccine;
    private int dose;
    private String lotNumber;
    private String vacinationName;
    private LocalDateTime localDateTime;

    public VaccineAdministration(){

    }
    // A constructor.
    public VaccineAdministration (String snsNumber, Vaccine vaccine, int dose, String lotNumber, String vacinationName, LocalDateTime localDateTime){
        this.snsNumber = snsNumber;
        this.vaccine = vaccine;
        this.dose = dose;
        this.lotNumber = lotNumber;
        this.vacinationName = vacinationName;
        this.localDateTime = localDateTime;
    }



    /**
     * This function returns the snsNumber of the user
     *
     * @return The snsNumber is being returned.
     */
    public String getSnsNumber() {
        return snsNumber;
    }

    /**
     * This function sets the value of the variable snsNumber to the value of the parameter snsNumber
     *
     * @param snsNumber The phone number of the user you want to send the message to.
     */
    public void setSnsNumber(String snsNumber) {
        this.snsNumber = snsNumber;
    }

   /**
     * This function returns the vaccine object
     *
     * @return The vaccine object is being returned.
     */
     public Vaccine getVaccine() {
        return vaccine;
    }


    /**
     * This function sets the vaccine of the patient
     *
     * @param vaccine The vaccine that the user is currently viewing.
     */
    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    /**
     * This function returns the dose of the drug.
     *
     * @return The dose of the medicine.
     */
    /**
     * This function returns the dose of the drug.
     *
     * @return The dose of the medicine.
     */
    public int getDose() {
        return dose;
    }

    /**
     * This function sets the dose of the drug.
     *
     * @param dose The amount of medicine to be taken.
     */
    public void setDose(int dose) {
        this.dose = dose;
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
     * This function returns the name of the vacination
     *
     * @return The name of the vacination.
     */
    public String getVacinationName() {
        return vacinationName;
    }

    /**
     * This function sets the name of the vacination
     *
     * @param vacinationName The name of the vacination.
     */
    public void setVacinationName(String vacinationName) {
        this.vacinationName = vacinationName;
    }

    /**
     * > This function returns the local date time
     *
     * @return A LocalDateTime object
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * > This function sets the localDateTime variable to the value of the localDateTime parameter
     *
     * @param localDateTime The date and time to be formatted.
     */
    public void setLocalDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }




}
