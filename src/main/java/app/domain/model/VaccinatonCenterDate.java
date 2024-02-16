package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The type Vaccinaton center date.
 */
public class VaccinatonCenterDate {

    private LocalDate date;
    private ArrayList<VaccinationCenterSlot> listOfSlots = new ArrayList<>();

    /**
     * Instantiates a new Vaccinaton center date.
     *
     * @param date the date
     */
    public VaccinatonCenterDate(LocalDate date){
        this.date = date;
    }

    /**
     * Instantiates a new Vaccinaton center date.
     *
     * @param listOfSlotsForTheDay the list of slots for the day
     */
    public VaccinatonCenterDate(ArrayList<VaccinationCenterSlot> listOfSlotsForTheDay){
        this.listOfSlots  = listOfSlotsForTheDay;
    }

    /**
     * Instantiates a new Vaccinaton center date.
     *
     * @param date                 the date
     * @param listOfSlotsForTheDay the list of slots for the day
     */
    public VaccinatonCenterDate(LocalDate date, ArrayList<VaccinationCenterSlot> listOfSlotsForTheDay){
        this.date = date;
        this.listOfSlots  = listOfSlotsForTheDay;
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
     * Gets list of slots.
     *
     * @return the list of slots
     */
    public ArrayList<VaccinationCenterSlot> getListOfSlots() {
        return listOfSlots;
    }

    /**
     * Sets list of slots.
     *
     * @param listOfSlots the list of slots
     */
    public void setListOfSlots(ArrayList<VaccinationCenterSlot> listOfSlots) {
        this.listOfSlots = listOfSlots;
    }

    /**
     * Add to list.
     *
     * @param slot the slot
     */
    public void addToList(VaccinationCenterSlot slot){
        this.getListOfSlots().add(slot);
    }


    /**
     * The toString() function returns a string representation of the object
     *
     * @return The date and the list of slots.
     */
    @Override
    public String toString() {
        return  "Day: " + date + "\n" + "Available Slots: " + listOfSlots;
    }
}
