package app.domain.model.Store;

import app.domain.model.ScheduledVaccine;

import java.util.ArrayList;

/**
 * The type Scheduled vaccine store.
 */
public class ScheduledVaccineStore {

    /**
     * The List of scheduled vaccines.
     */
    public ArrayList<ScheduledVaccine> listOfScheduledVaccines = new ArrayList<>();


    // A constructor.
    public ScheduledVaccineStore() {
        
    }

    /**
     * Gets list of scheduled vaccines.
     *
     * @return the list of scheduled vaccines
     */
    public ArrayList<ScheduledVaccine> getListOfScheduledVaccines() {
        return listOfScheduledVaccines;
    }

    /**
     * Sets list of scheduled vaccines.
     *
     * @param listOfScheduledVaccines the list of scheduled vaccines
     */
    public void setListOfScheduledVaccines(ArrayList<ScheduledVaccine> listOfScheduledVaccines) {
        this.listOfScheduledVaccines = listOfScheduledVaccines;
    }

    /**
     * Add to list boolean.
     *
     * @param scheduledVaccine the scheduled vaccine
     * @return the boolean
     */
    public boolean addToList (ScheduledVaccine scheduledVaccine){
       return this.listOfScheduledVaccines.add(scheduledVaccine);
    }




}
