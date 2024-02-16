package app.ui.console;


import app.controller.AddNewVaccineAdministrationController;
import app.controller.NewVaccineController;
import app.controller.App;
import app.domain.model.*;
import app.domain.model.Store.VaccineTypeStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type New vaccine ui.
 */
public class NewVaccineUI implements Runnable {


    // Creating a new instance of the NewVaccineController class.
    private NewVaccineController controller;
    // A private variable that is used to create a new instance of the AddNewVaccineAdministrationController class.
    private AddNewVaccineAdministrationController controllerAdministration;
    private static NewVaccineUI instance;
    private Vaccine vaccine;
    private VaccineType vaccineType = null;
    private Company company;

    private List<Vaccine> listOfVaccines = new ArrayList<>();
    private ArrayList<AgeGroup> listOfAgeGroups = new ArrayList<>();


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static NewVaccineUI getInstance() {
        if (instance == null) {
            instance = new NewVaccineUI();
        }
        return instance;
    }

    /**
     * Instantiates a new New vaccine ui.
     */
    public NewVaccineUI() {
        company = App.getInstance().getCompany();
        controller = new NewVaccineController();
        controllerAdministration = new AddNewVaccineAdministrationController();

    }


    @Override
    public void run() {

        Scanner read = new Scanner(System.in);
        int id;
        String name;
        String answer;
        int numberOfAgeGroups = 0;
        AgeGroup administrationForAgeGroup = null;
        AdministrationProcess administrationProcess = new AdministrationProcess(listOfAgeGroups);
        List<Vaccine> getListOfVaccines = company.getListOfVaccines();
        List<VaccineType> getListOfVaccineTypes = company.getVaccineTypes();
         ArrayList<AgeGroup> listOfAgeGroupsToSet = new ArrayList<>();
        int option;


        System.out.println("[1] to add a new Vaccine \n [2] to remove a Vaccine \n [3] to view the existing vaccines \n [5] to view the existing vaccine types ");
        option = read.nextInt();

        switch (option) {
            case 1:

                System.out.println("Do you wish to add a new vaccine? YES / NO ");
                answer = read.next();
                if (answer.equalsIgnoreCase("yes")) {
                    vaccine = controller.createBlanck();

                    do {
                        System.out.print("Please insert the id of this vaccine as an identifier (numbers only and different from 0000) : ");
                        id = read.nextInt();
                    } while (!controller.CheckId(id));
                    vaccine.setId(id);

                    do {
                        System.out.print("Please insert the vaccine name : ");
                        name = read.next();
                    } while (!controller.CheckName(name));
                    vaccine.setName(name);

                    do {
                        System.out.println("Please select a vaccine type from the list below");

                        String codeToChoose;

                        System.out.println("List of vaccine types: ");
                        for (VaccineType vaccineTypeToPrint : VaccineTypeStore.vaccinetypes) {
                            System.out.println(vaccineTypeToPrint);
                        }
                        if (VaccineTypeStore.vaccinetypes.isEmpty()) {
                            System.out.println("No vaccine types found.");
                            break;
                        } else

                            System.out.println("");
                        System.out.print("Select the code of the type you wish to apply to the vaccine: ");
                        // read.nextLine();
                        codeToChoose = read.next();

                        for (VaccineType vaccineCodeToChoose : VaccineTypeStore.vaccinetypes) {
                            if (codeToChoose.equalsIgnoreCase(vaccineCodeToChoose.getCode())) {
                                vaccineType = vaccineCodeToChoose;
                            }
                        }
                        break;


                    } while (vaccineType == null || !vaccineType.validateCodeForApplying());

                    System.out.println("");
                    do {
                        System.out.println("How many different age groups will be available for the vaccine?");
                         numberOfAgeGroups = read.nextInt();
                    }while (numberOfAgeGroups <= 0);

                    for ( int i = 0; i < numberOfAgeGroups; i++ ) {
                        System.out.printf("Please insert the details for the [%d] age group: ", i);
                        if (vaccineType != null) {
                            do {
                                System.out.println("Please insert vaccine administration process data:  ");
                                System.out.println("Minimum age required: ");
                                int minAge = read.nextInt();
                                System.out.println("Maximum age required: ");
                                int maxAge = read.nextInt();
                                System.out.println("Number of doses: ");
                                int numberOfDoses = read.nextInt();
                                System.out.println("Dosage per dose: ");
                                double dosage = read.nextDouble();
                                System.out.println("Days between doses: ");
                                int daysBetweenDoses = read.nextInt();

                                 administrationForAgeGroup = controller.createAgeGroupAdministration(minAge,maxAge,numberOfDoses,dosage,daysBetweenDoses);
                                System.out.println("Admin process that will be added: \n" + administrationForAgeGroup);

                            } while (!controller.validateVaccineAdministration(administrationForAgeGroup));
                        }
                        controller.AddToList(administrationProcess, administrationForAgeGroup);
                        System.out.println("List of Age Groups " + administrationProcess.getListOfAgeGroups());
                    }


                    getListOfVaccines.add(controller.createVaccine(id, name, vaccineType, administrationProcess));



                } else if (answer.equalsIgnoreCase("no")) {
                    System.out.println("Going back...");
                }
                break;
            case 2:
                int CONST = 0000;
                int indexToChange = 99999;
                int vaccineIdToCheck;

                do {
                    System.out.println("Please insert the vaccine ID (different from 0000): ");
                    vaccineIdToCheck = read.nextInt();
                } while (vaccineIdToCheck == CONST);

                for (Vaccine vaccineCheck : getListOfVaccines) {
                    if (vaccineCheck.getId() == vaccineIdToCheck) {
                        indexToChange = getListOfVaccines.indexOf(vaccineCheck);
                    }
                }
                if (indexToChange == 99999) {
                    System.out.println("Vaccine id not found");
                } else {
                    getListOfVaccines.remove(indexToChange);
                    System.out.println("Vaccine removed.");
                }

                break;
            case 3:
                for (Vaccine vaccinePrint : getListOfVaccines) {
                    System.out.println(vaccinePrint);
                    System.out.println("");
                }
                if (getListOfVaccines.isEmpty()) {
                    System.out.println("No vaccines found.");
                }
                break;
            case 5:
                for (VaccineType vaccinePrint : getListOfVaccineTypes) {
                    System.out.println(vaccinePrint);
                    System.out.println("");
                }
                if (getListOfVaccineTypes.isEmpty()) {
                    System.out.println("No vaccine types found.");
                }
                break;
        }


    }
}



