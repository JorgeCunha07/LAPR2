package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccineControler;
import app.controller.VaccinationCenterController;
import app.domain.model.*;
import app.domain.model.Store.VaccinationCenterStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Vaccination center ui.
 */
public class VaccinationCenterUI implements Runnable {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private VaccinationCenterController controller;
    private ScheduleVaccineControler scheduledController;
    private VaccinationCenterStore store;
    private static VaccinationCenterUI instance;
    private VaccinationCenter vaccinationCenter;
    private VaccinationCenter vaccinationCenter2;
    private Company company;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static VaccinationCenterUI getInstance() {
        if (instance == null) {
            instance = new VaccinationCenterUI();
        }
        return instance;
    }

    /**
     * Instantiates a new Vaccination center ui.
     */
    public VaccinationCenterUI() {
        controller = new VaccinationCenterController();
        scheduledController = new ScheduleVaccineControler();
        company = App.getInstance().getCompany();
    }

    @Override
    public void run() {
        Scanner read = new Scanner(System.in);
        int UiOption;
        System.out.println("[1] to add a new Vaccination Center \n [2] to remove a Vaccination Center \n [3] to manage a Vaccination Center \n [4] to check all vaccination centers \n [5] to check scheduled vaccines");
        UiOption = read.nextInt();
        String vaccinationCenterName;
        String vaccinationCenterAdress;
        int vaccinationCenterPhoneNumber;
        String vaccinationCenterEmailAdress;
        long vaccinationCenterFaxNumber;
        String vaccinationCenterWebAdress;
        String coordinatorEmail;


        switch (UiOption) {
            case 1: {
                do {
                    System.out.printf("Please insert the coordinator");
                    coordinatorEmail = read.next();
                    System.out.print("Please insert the vaccination center name: ");
                    vaccinationCenterName = read.next();
                    System.out.print("Please insert the vaccination Center adress: ");
                    read.nextLine();
                    vaccinationCenterAdress = read.nextLine();
                    System.out.print("Please insert the vaccination Center phone number: ");
                    vaccinationCenterPhoneNumber = read.nextInt();
                    System.out.print("Please insert the vaccination center email adress: ");
                    vaccinationCenterEmailAdress = read.next();
                    System.out.print("Please insert the vaccination center fax number: ");
                    vaccinationCenterFaxNumber = read.nextLong();
                    System.out.print("Please insert the vaccination center web adress: ");
                    vaccinationCenterWebAdress = read.next();
                } while (!controller.checkAtributes(vaccinationCenterName, vaccinationCenterAdress, vaccinationCenterPhoneNumber, vaccinationCenterEmailAdress, vaccinationCenterFaxNumber, vaccinationCenterWebAdress));

                System.out.println("Please insert the vaccination center opening hour: ");
                System.out.print("Hour: ");
                int vaccinationCenterOpeningHourInt = read.nextInt();
                System.out.print("Minute : ");
                int vaccinationCenterOpeningMinuteInt = read.nextInt();
                LocalTime openingTime = LocalTime.of(vaccinationCenterOpeningHourInt,vaccinationCenterOpeningMinuteInt);



                System.out.println("Please insert the vaccination center closing hour: ");
                System.out.print("Hour: ");
                int vaccinationCenterClosingHourInt = read.nextInt();
                System.out.print("Minute  : ");
                int vaccinationCenterClosingMinuteInt = read.nextInt();
                LocalTime closingTime = LocalTime.of(vaccinationCenterClosingHourInt,vaccinationCenterClosingMinuteInt);




                System.out.print("Please insert the vaccination center slot duration in minutes [5/10/15/20/30/60]: ");
                int vaccinationCenterSlotDuration = read.nextInt();
                System.out.print("Please specify how many vaccines can be given per slot: ");
                int vaccinationCenterVaccinesPerSlot = read.nextInt();

                vaccinationCenter = controller.createVaccinationCenter(coordinatorEmail,vaccinationCenterName, vaccinationCenterAdress, vaccinationCenterPhoneNumber, vaccinationCenterEmailAdress, vaccinationCenterFaxNumber, vaccinationCenterWebAdress, openingTime, closingTime,  vaccinationCenterSlotDuration, vaccinationCenterVaccinesPerSlot);
                ArrayList<VaccinatonCenterDate> listOfDates = new ArrayList<>();

                vaccinationCenter2 = controller.createVaccinationCenter2(vaccinationCenter, listOfDates, new ArrayList<>());
                controller.addDays(vaccinationCenter2);
                controller.addSlotsToDate(vaccinationCenter2);


                if (vaccinationCenter2.validateVaccinationCenter(vaccinationCenter2) && vaccinationCenter2.validateVaccinationCenterTime(vaccinationCenter2)) {
                    System.out.println("Vaccination center: " + vaccinationCenter2.getName());
                    System.out.println("Are you sure you want to register this Vaccination Center : Yes/No ");
                    String registerOption = read.next();
                    if (registerOption.equalsIgnoreCase("yes")) {
                        controller.saveVaccinationCenter(vaccinationCenter2);
                    } else if (registerOption.equalsIgnoreCase("no")) {
                        break;
                    } else System.out.println("Inexistent option");
                } else System.out.println("Error on data.");


                break;
            }
            case 2: {
                boolean status = false;
                System.out.println("Please insert the name of the vaccination center you wish to remove: ");
                String nameToRemove = read.next();
                int removeIndex = 0;

                for (VaccinationCenter vaccinationCenterCheck : controller.getVaccinationCenters()) {
                    if (nameToRemove.equalsIgnoreCase(vaccinationCenterCheck.getName())) {
                        removeIndex = controller.getVaccinationCenters().indexOf(vaccinationCenterCheck);
                        status = true;
                    }
                }
                if (status) {
                    System.out.println(removeIndex);
                    controller.getVaccinationCenters().remove(removeIndex);
                    System.out.println("Vaccination Center Removed.");

                } else {
                    System.out.println("Vaccination Center not found.");
                }
                break;
            }
            case 3: {
                int manageOption;
                boolean status = false;
                System.out.println("Please, insert the name of the vaccination center you wish to manage: ");
                String nameToManage = read.next();
                for (VaccinationCenter vaccinationCenterCheck : controller.getVaccinationCenters()) {
                    if (nameToManage.equalsIgnoreCase(vaccinationCenterCheck.getName())) {
                        do {
                            System.out.println("What would you like to modify the vaccination center " + vaccinationCenterCheck.getName());
                            System.out.println("Name [1]");
                            System.out.println("Adress [2]");
                            System.out.println("Phone Number [3]");
                            System.out.println("Email adress [4]");
                            System.out.println("Fax Number [5]");
                            System.out.println("Website Adress [6]");
                            System.out.println("Cancel [11]");
                            manageOption = read.nextInt();

                            switch (manageOption) {
                                case 1: {
                                    System.out.println("Please insert the new name for the vaccination center: ");
                                    String newName = read.next();
                                    vaccinationCenterCheck.setName(newName);
                                }
                                break;
                                case 2: {
                                    System.out.println("Please insert the new adress for the vaccination center: ");
                                    String newAdress = read.nextLine();
                                    vaccinationCenterCheck.setAddress(newAdress);
                                }
                                break;
                                case 3: {
                                    int length = 0;
                                    int phoneNumber = 0;
                                    do {
                                        System.out.println("Please insert the new phone number for the vaccination center: ");
                                        phoneNumber = read.nextInt();
                                        length = String.valueOf(phoneNumber).length();
                                        if (length != 9) {
                                            System.out.println("A phone number consists of 9 digits. Try again");
                                        }
                                    } while (length != 9);
                                    vaccinationCenterCheck.setPhoneNumber(phoneNumber);
                                }
                                break;
                                case 4: {
                                    System.out.println("Please insert the new email address for the vaccination center: ");
                                    String emailAdress = read.next();
                                    vaccinationCenterCheck.setEmailAdress(emailAdress);
                                }
                                break;
                                case 5: {
                                    int length = 0;
                                    System.out.println("Please insert the new Fax Number for the vaccination center: ");
                                    int faxNumber = read.nextInt();
                                    do {
                                        System.out.println("Please insert the new phone number for the vaccination center: ");
                                        faxNumber = read.nextInt();
                                        length = String.valueOf(faxNumber).length();
                                        if (length != 7) {
                                            System.out.println("A phone number consists of 7 digits. Try again");
                                        }
                                    } while (length != 9);
                                    vaccinationCenterCheck.setPhoneNumber(faxNumber);
                                }
                                break;
                                case 6: {
                                    System.out.println("Please insert the new website address for the vaccination center: ");
                                    String websiteAdress = read.next();
                                    vaccinationCenterCheck.setWebsiteAdress(websiteAdress);
                                }
                                break;
                                case 11: {
                                    System.out.println("Going back... ");
                                }
                                break;

                            }

                        } while (manageOption != 11);
                        status = true;
                    }
                    if (!status){
                        System.out.println("Vaccine center not found");
                    }
                }

                break;
            }
            case 4: {
                for (VaccinationCenter vaccinationCenterToPrint : controller.getVaccinationCenters()) {
                    System.out.println(vaccinationCenterToPrint);
                    System.out.println("");
                }
                if (controller.getVaccinationCenters().isEmpty()) {
                    System.out.println("No vaccination centers found.");
                }
            }
            break;
            case 5:
                for (ScheduledVaccine scheduledVaccineToPrint : scheduledController.getScheduledVaccines()) {
                    System.out.println(scheduledVaccineToPrint);
                    System.out.println("");
                }
                if (scheduledController.getScheduledVaccines().isEmpty()) {
                    System.out.println("No scheduled vaccines.");
                }
                break;

        }


    }
}
