package app.controller;

import app.domain.model.AdministrationProcess;
import app.domain.model.AgeGroup;


import java.util.ArrayList;

/**
 * The type Add new vaccine administration controller.
 */
public class AddNewVaccineAdministrationController {
    /**
     * The constant listOfAdministrationProcess.
     */

    /**
     * > This function creates an AdministrationProcess object and returns it
     *
     * @param listOfAgeGroups An ArrayList of AgeGroup objects.
     * @return An instance of the AdministrationProcess class.
     */
    public AdministrationProcess createAdministrationProcess(ArrayList<AgeGroup> listOfAgeGroups){
        return new AdministrationProcess(listOfAgeGroups);
    }





}