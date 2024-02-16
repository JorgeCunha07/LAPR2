package app.ui.console;

import app.controller.App;
import app.controller.NewVaccineController;
import app.controller.ScheduleVaccineControler;
import app.controller.VaccinationCenterController;
import app.domain.model.*;
import app.domain.model.Store.VaccineTypeStore;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The type Schedule vaccine ui.
 */
public class ScheduleVaccineUI implements Runnable {
    private Scanner read = new Scanner(System.in);


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ScheduleVaccineControler controller;
    private VaccinationCenterController centerController;
    private NewVaccineController vaccineController;
    private static ScheduleVaccineUI instance;

    private Company company;
    private boolean status;


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ScheduleVaccineUI getInstance() {
        if (instance == null) {
            instance = new ScheduleVaccineUI();
        }
        return instance;
    }

    /**
     * Instantiates a new Schedule vaccine ui.
     */
    public ScheduleVaccineUI() {
        controller = new ScheduleVaccineControler();
        centerController = new VaccinationCenterController();
        vaccineController = new NewVaccineController();
        company = App.getInstance().getCompany();
    }

    // The above code is a menu that allows the user to schedule a vaccine.
    @Override
    public void run() {
        int i = -1;
        int slot = 0;
        int choosenSlotIndex = 0;
        LocalDate birthdate ;
        VaccinationCenterSlot choosenSlot = null;
        LocalDate dateToSchedule = null;
        VaccinatonCenterDate choosenDate = null;
        String snsNumber, typeCode = null;
        VaccineType vaccineType = null;
        Vaccine vaccine = null;
        SNSUser snsUser = null;
        ArrayList<VaccinatonCenterDate> listOfDays = new ArrayList<>();
        ArrayList<Vaccine> listOfVacines = new ArrayList<>();
        ArrayList<Vaccine> listOfVaccinesWithOkAgeGroups = new ArrayList<>();
        VaccinationCenter vaccinationCenterAux = new VaccinationCenter();
        VaccinationCenter vaccinationCenter = new VaccinationCenter(vaccinationCenterAux, listOfDays, new ArrayList<String>());
        int numberOfDosesToApply = 0;

        do {
            System.out.println("Please insert your SNS number: ");
            System.out.println("To go back, select 0 ");
            snsNumber = read.next();
            if(snsNumber.equals("0")){
                System.out.println("Going back...");
                break;
            }
        } while (!SNSUser.checkSNSUserNumber(snsNumber));

        if (controller.checkIfSnsUserExists(snsNumber)) {

            snsUser = controller.getSnsUser(snsNumber);
            try{
                birthdate = LocalDate.parse(snsUser.getBirthDate(), formatter2);
            }catch (Exception e){
                birthdate = LocalDate.parse(snsUser.getBirthDate(), formatter);
            }
            LocalDate today = LocalDate.now();
            int SnsUserAge = Period.between(birthdate,today).getYears();
            System.out.println("Sns User Found! ");
            System.out.println("Name: " + snsUser.getName());
            System.out.println("Age: " + SnsUserAge);
            System.out.println("");



            System.out.println("Do you want to schedulle a vaccine? yes/no");
            String answer = read.next();
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println("Please, select a vaccination center");
                for (VaccinationCenter vaccinationCenterToPrint : centerController.getVaccinationCenters()) {
                    System.out.println(vaccinationCenterToPrint);
                }
                if (centerController.getVaccinationCenters().isEmpty()) {
                    System.out.println("No vaccination centers found.");
                }
                //read.next();
                String name = read.next();
                for (VaccinationCenter vaccinationCenterCheck : centerController.getVaccinationCenters()) {
                    if (name.equalsIgnoreCase(vaccinationCenterCheck.getName())) {
                        vaccinationCenter = vaccinationCenterCheck;
                        System.out.println("Please insert the date where you want to get vacinated in the following format [ dd/MM/yyyy ]");
                        read.nextLine();
                        String dateToGetVaccinated = read.nextLine();
                        System.out.println(dateToGetVaccinated);
                        dateToSchedule = LocalDate.parse(dateToGetVaccinated,formatter);
                        if(dateToSchedule.isBefore(today)){
                            System.out.println("Can't schedule a vaccine on a day before today. Non existente");
                        }else
                        /*for to select the date from the list of dates*/
                        for (VaccinatonCenterDate date : vaccinationCenter.getListOfDates()) {
                            if (date.getDate().equals(dateToSchedule)) {
                                choosenDate = date;
                                int choosenDateIndex = vaccinationCenter.getListOfDates().indexOf(date);
                                System.out.println("Please insert the [number] of the slot where you want to be vaccinated: ");

                                for (VaccinationCenterSlot slotToCheck : choosenDate.getListOfSlots()) {
                                    i++;
                                    System.out.println("Slot [" + i + "] :");
                                    System.out.println(slotToCheck);
                                }
                                choosenSlotIndex = read.nextInt();
                                choosenSlot = vaccinationCenter.getListOfDates().get(choosenDateIndex).getListOfSlots().get(choosenSlotIndex);
                                if (choosenSlot.checkFull(choosenSlot)) {
                                    System.out.println("Can't schedule. Slot full");
                                    status = false;
                                } else {
                                    //choosenSlot.addSchedule();

                                    read.reset();
                                    System.out.println("Please insert the vaccine type code :");
                                    for (VaccineType vaccineTypeCheck : VaccineTypeStore.vaccinetypes) {
                                        System.out.println(vaccineTypeCheck);
                                    }
                                    if (VaccineTypeStore.vaccinetypes.isEmpty()) {
                                        System.out.println("No vaccine types found");
                                    }

                                    typeCode = read.next();

                                    for (VaccineType vaccineTypeCheck : VaccineTypeStore.vaccinetypes) {
                                        if (typeCode.equals(vaccineTypeCheck.getCode())) {
                                            vaccineType = vaccineTypeCheck;
                                            for (Vaccine vaccineToCheck : vaccineController.getVaccines()) {
                                                if (vaccineToCheck.getVaccineType().getCode().equals(typeCode)) {
                                                    listOfVacines.add(vaccineToCheck);

                                                }

                                            }
                                        }


                                        for (Vaccine checkVaccine : listOfVacines) {
                                            for (AgeGroup ageGroupToCheck : checkVaccine.getAdministrationProcess().getListOfAgeGroups()) {
                                                if (SnsUserAge >= ageGroupToCheck.getMinAge() && SnsUserAge <= ageGroupToCheck.getMaxAge()) {
                                                    listOfVaccinesWithOkAgeGroups.add(checkVaccine);
                                                }
                                            }
                                        }
                                        if (listOfVaccinesWithOkAgeGroups.isEmpty()) {
                                            status = false;
                                            System.out.println("There are no available vaccines for your age.");
                                        } else
                                            Collections.shuffle(listOfVaccinesWithOkAgeGroups);
                                        if (!listOfVaccinesWithOkAgeGroups.isEmpty()) {
                                            vaccine = listOfVaccinesWithOkAgeGroups.get(0);

                                            //System.out.println("Vaccines with an ok age group" + listOfVaccinesWithOkAgeGroups);
                                            status = true;
                                        }
                                    }


                                    for (AgeGroup ageGroup : vaccine.getAdministrationProcess().getListOfAgeGroups()) {
                                        if (SnsUserAge >= ageGroup.getMinAge() && SnsUserAge <= ageGroup.getMaxAge()) {
                                            numberOfDosesToApply = ageGroup.getNumberOfDoses();
                                        }
                                    }

                                    /*Fixed.*/

                                    if(!controller.checkIfIsFullyVaccinated(snsUser, vaccineType)){
                                        status = true;
                                    }else {
                                        System.out.println("Can't schedule, user is already fully vacinated");
                                        status = false;
                                    }




                                    for (ScheduledVaccine scheduledVaccineToCheck : centerController.getScheduledVaccines()) {
                                        if (scheduledVaccineToCheck.getSNSNumber().equals(snsNumber)) {
                                            if (scheduledVaccineToCheck.getVaccineType().equals(typeCode)) {
                                                status = false;
                                                System.out.println("You can't schedule the same vaccine time more than once.");
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                if (status) {
                    choosenSlot.addSchedule();
                    AgeGroup administrationDetails = new AgeGroup();
                    for (AgeGroup ageGroup : vaccine.getAdministrationProcess().getListOfAgeGroups()) {
                        if (SnsUserAge >= ageGroup.getMinAge() && SnsUserAge <= ageGroup.getMaxAge()) {
                            administrationDetails = ageGroup;
                        }
                    }
                    System.out.println("Vaccine scheduled.");
                    System.out.println("SNS Number: " + snsNumber);
                    System.out.println("Vaccination center: " + vaccinationCenter.getName());
                    System.out.println("Day of vacination: " + dateToSchedule);
                    System.out.println("" + choosenDate.getListOfSlots().get(choosenSlotIndex));
                    System.out.println("Vaccine type" + vaccineType.getCode());
                   /* System.out.println("Vaccine: " + vaccine.getName());
                    System.out.println("Administration details: " + administrationDetails);

                    */

                    LocalDateTime timeInLocalDateTime = LocalDateTime.of(dateToSchedule, choosenSlot.getTime());
                    ScheduledVaccine scheduledVaccine = controller.createScheduledVaccine(snsNumber, vaccinationCenter.getName(), dateToSchedule, choosenSlot, choosenSlot.getTime(), vaccineType.getCode(),timeInLocalDateTime);
                    controller.saveScheduledVaccine(scheduledVaccine);

                    System.out.println("Do you wish to receive a text message) [1] YES | [2] NO");
                    int textmessage = read.nextInt();
                    if (textmessage == 1) {
                        PrintWriter writer = null;
                        try {
                            writer = new PrintWriter("SMS.txt", "UTF-8");
                        } catch (FileNotFoundException | UnsupportedEncodingException e) {
                            System.out.println(e.getMessage());
                        }
                        writer.println(scheduledVaccine);
                        writer.close();

                    } else if (textmessage == 2) {
                        System.out.println("You won't be notified by sms.");
                    }

                    if (controller.validateScheduledVaccine(scheduledVaccine)) {
                        System.out.println("Vaccine sucessfully scheduled.");
                        System.out.println("");
                        System.out.println("Please, logout from this menu before scheduling any other vaccine.");
                    }

                }

            } else System.out.println("Going back.");

        }else System.out.println("SNS User doesn't exist.");
    }
}