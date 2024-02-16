package app.controller;

import app.domain.model.*;
import app.domain.model.VaccineType;


import java.util.List;
import java.util.Scanner;


/**
 * The type Add new vaccine controller.
 */
public class NewVaccineController {

    private Company company;
    private List<Vaccine> listOfVaccines;

    /**
     * Instantiates a new Add new vaccine controller.
     */
    public NewVaccineController() {
        company = App.getInstance().getCompany();
        listOfVaccines = company.getListOfVaccines();
    }

    /**
     * The Read.
     */
    Scanner read = new Scanner(System.in);

    /**
     * Create vaccine vaccine.
     *
     * @param id                    the id
     * @param name                  the name
     * @param vaccineType           the vaccine type
     * @param administrationProcess the administration process
     * @return the vaccine
     */
    public Vaccine createVaccine(int id, String name, VaccineType vaccineType,AdministrationProcess administrationProcess){
        return new Vaccine(id,name,vaccineType,administrationProcess);
    }

    /**
     * Create blanck vaccine.
     *
     * @return the vaccine
     */
    public Vaccine createBlanck(){
        return  new Vaccine();
    }


    /**
     * Vaccine validation boolean.
     *
     * @param vaccine the vaccine
     * @return the boolean
     */
   /* public boolean VaccineValidation(Vaccine vaccine){
        if(vaccine.validateVaccine() == true){
            System.out.println("Vaccine validated");
            return true;
        }else
            System.out.println("Error while validating");
        return false;
    }

    */

    /**
     * Check id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean CheckId(int id){
        boolean result = true;
        for (Vaccine vaccineToCheck : company.getListOfVaccines()){
            if (vaccineToCheck.getId() == id){
                result = false;
                System.out.println("Vaccine id already exists.");
            }
        }
        if (id == 0000){
            result = false;
        }
        return result;
    }


    /**
     * Get vaccines list.
     *
     * @return the list
     */
    public List<Vaccine> getVaccines (){
        return company.getListOfVaccines();
    }

    /**
     * Check name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean CheckName(String name){
        boolean result = true;
        for (Vaccine vaccineToCheck : company.getListOfVaccines()){
            if ( vaccineToCheck.getName().equalsIgnoreCase(name) ){
                result = false;
                System.out.println("Vaccine name already exists.");
            }
        }
        return result;
    }


    /**
     * Create age group administration age group.
     *
     * @param minAge           the min age
     * @param maxAge           the max age
     * @param numberDoses      the number doses
     * @param dosage           the dosage
     * @param daysBetweenDoses the days between doses
     * @return the age group
     */
    public AgeGroup createAgeGroupAdministration(int minAge, int maxAge, int numberDoses, double dosage, int daysBetweenDoses){
        return new AgeGroup(minAge,maxAge,numberDoses,dosage,daysBetweenDoses);
    }

    /**
     * Add to list.
     *
     * @param administrationProcess the administration process
     * @param ageGroup              the age group
     */

    public void AddToList (AdministrationProcess administrationProcess, AgeGroup ageGroup){
        administrationProcess.getListOfAgeGroups().add(ageGroup);
    }

    /**
     * Validate vaccine administration boolean.
     *
     * @param ageGroupToCheck the age group to check
     * @return the boolean
     */
    public boolean validateVaccineAdministration(AgeGroup ageGroupToCheck){
        boolean result = true;
        int minAge = ageGroupToCheck.getMinAge();
        int maxAge = ageGroupToCheck.getMaxAge();
        int numberOfDoses = ageGroupToCheck.getNumberOfDoses();
        double dosage = ageGroupToCheck.getDosage();

        int daysBetweenDoses = ageGroupToCheck.getDaysBetweenDoses();
        if ( minAge > maxAge  || minAge < 0){ //
            System.out.println("Error on the age group!");
            result = false;
        }
        if (numberOfDoses <= 0){
            System.out.println("Error on the number of doses. It can't be zero or lower");
            result = false;
        }
        if(dosage == 0){
            System.out.println("Error on the dosage. The vaccine can't be empty");
            result = false;
        }
        if ( daysBetweenDoses <= 0 && numberOfDoses > 1){
            System.out.println("Error on the days between doses. It can't be zero or lower");
            result = false;
        }
        return  result;

    }



}