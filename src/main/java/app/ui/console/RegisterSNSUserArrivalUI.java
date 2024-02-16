package app.ui.console;

import app.controller.RegisterSNSUserArrivalController;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegisterSNSUserArrivalUI implements Runnable {

    // Creating a new instance of the RegisterSNSUserArrivalController class.
    private final RegisterSNSUserArrivalController controller;
    // A variable that is used to store the vaccination centers.
    public ArrayList<VaccinationCenter> vaccinationCenters;

    // The constructor of the class.
    public RegisterSNSUserArrivalUI() {
        this.controller = new RegisterSNSUserArrivalController();
        vaccinationCenters = controller.getVacinationCenters();
    }

    /**
     * This function is responsible for registering the arrival of a SNS user to a vaccination center
     */
    public void run() {
        String snsUserNumber,vaccinationCenterName;
        boolean checkSNSNumber,flag,flag2 = false;
        ScheduledVaccine exScheduledVaccine = null;

        do {
            System.out.println("Register an SNS User Arrival");
            try {
                flag = true;

                for (VaccinationCenter vaccinationCenter : vaccinationCenters) {
                    System.out.println(vaccinationCenter.getName());
                }
                vaccinationCenterName = Utils.readLineFromConsole("Insert the Vaccination Center");

                if (controller.CheckIfCenterExists(vaccinationCenterName)) {
                    VaccinationCenter vaccinationCenter = this.controller.setVaccinationCenterWithName(vaccinationCenterName);
                    System.out.println("Insert the necessary information: ");
                    do {
                        snsUserNumber = Utils.readLineFromConsole("Insert SNS User Number:");
                        checkSNSNumber = controller.checkSNSNumber(snsUserNumber);

                        if (!checkSNSNumber) {
                            System.out.println("SNS user number is not correct!");
                        }
                    }
                    while (!checkSNSNumber);

                    LocalDateTime arrivalDate = LocalDateTime.now();

                    try {
                       exScheduledVaccine = controller.checkAppointment(snsUserNumber);
                       flag2 = true;
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        flag2 = false;
                    }
                    if(flag2){

                        if(controller.majorChecks(vaccinationCenterName, arrivalDate, exScheduledVaccine)){
                            if(controller.registerArrival(exScheduledVaccine, arrivalDate, vaccinationCenter)){
                                System.out.println("Arrival registered.");
                            }
                        }else {
                            System.out.println("Arrival not registered.");
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        } while (!flag2 && !flag);
    }
}
