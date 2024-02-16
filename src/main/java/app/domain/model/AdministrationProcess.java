package app.domain.model;

import java.util.ArrayList;


/**
 * The type Administration process.
 */
public class AdministrationProcess {

    // Declaring a variable called listOfAgeGroups of type ArrayList<AgeGroup> and making it private.
    private ArrayList<AgeGroup> listOfAgeGroups;


    // This is the constructor for the AdministrationProcess class. It is used to create an instance of the class.
    public AdministrationProcess(ArrayList<AgeGroup> listOfAgeGroups){
        this.listOfAgeGroups = listOfAgeGroups;

    }

    /**
     * This function returns the list of age groups
     *
     * @return The list of age groups.
     */
    public ArrayList<AgeGroup> getListOfAgeGroups() {
        return listOfAgeGroups;
    }

    /**
     * This function sets the listOfAgeGroups variable to the listOfAgeGroups parameter
     *
     * @param listOfAgeGroups This is the list of age groups that will be displayed in the dropdown.
     */
    public void setListOfAgeGroups(ArrayList<AgeGroup> listOfAgeGroups) {
        this.listOfAgeGroups = listOfAgeGroups;
    }

    /**
     * This function adds an AgeGroup object to the listOfAgeGroups ArrayList
     *
     * @param ageGroup The age group to be added to the list.
     */
    public void addToList(AgeGroup ageGroup){
        this.listOfAgeGroups.add(ageGroup);
    }


    /**
     * The toString() method returns a string representation of the object
     *
     * @return The list of age groups and the administration process.
     */
    @Override
    public String toString() {
        return "Available Age Groups and administration process: \n" +
                 listOfAgeGroups;
    }

    public AgeGroup get(int ageGroupIndex) {
        return listOfAgeGroups.get(ageGroupIndex);
    }
}
