package app.ui.console;

import app.controller.AnalyseCenterPerformanceController;
import app.domain.model.Analysis;
import app.domain.model.VaccinationCenter;
import app.domain.shared.UtilsCheck;
import app.ui.console.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class AnalyseCenterPerformanceUI implements Runnable {

    private final AnalyseCenterPerformanceController controller; // instanciar o Controller;
    Scanner scanner = new Scanner(System.in);

    public AnalyseCenterPerformanceUI() {
        this.controller = new AnalyseCenterPerformanceController();
    }

    public void run() {
        int opcao;
        boolean flag;
        Analysis analysis = null;

        try{
            getVaccinationCenterForTheUser();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return ;
        }

        // It asks the user to enter a date and a time interval and then it checks if the date and the time interval are
        // valid
        do {
            try {
                analysis = requestedData();
                flag = true;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        } while (!flag);

        System.out.println(analysis);

    }


    /**
     * This function returns the vaccination center that the user chose.
     *
     * @param opcao the option chosen by the user
     * @return The vaccination center that was chosen by the user.
     */
    public VaccinationCenter chooseVaccinationCenter(int opcao) {
        return controller.getVaccinationCenters().get(opcao);
    }

    /**
     * > This function returns an ArrayList of all the vaccination centers in the system
     *
     * @return An ArrayList of VaccinationCenters
     */
    public ArrayList<VaccinationCenter> VaccinationCenters() {
        return controller.getVaccinationCenters();
    }

    /**
     * The function receives a vaccination center and checks if it is valid. If it is valid, it sets the vaccination center
     *
     * @param vaccinationCenter The vaccination center to be set.
     * @return The method returns a boolean value.
     */
    public boolean setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        boolean flag;
        try {
            flag = controller.validateVaccinationCenter(vaccinationCenter);
        } catch (IllegalArgumentException e) {
            flag = false;
            System.out.println(e.getMessage());
        }
        if (flag) {
            controller.setVaccinationCenter(vaccinationCenter);
        }
        return flag;

    }

    /**
     * It asks the user to enter a date and a time interval and then it checks if the date and the time interval are valid
     */
    public Analysis requestedData() throws ParseException {
        boolean flag;
        String date, timeInterval;
        Date formatDate = new Date();

        // Asking the user to enter a date and then it checks if the date is valid.
        System.out.println("\nEnter the date (dd/mm/yy): ");
        do {
            date = scanner.nextLine();
            if (!controller.checkDate(date)) {
                System.out.println("That is not a valid date! Please enter again (dd/mm/yy): ");
                flag = false;
            } else {
                flag = true;
                String dateFormat = "dd/MM/yyyy";
                DateFormat sdf = new SimpleDateFormat(dateFormat);
                formatDate = sdf.parse(date);
            }
        } while (!flag);

        // Converting the date to a LocalDate.
        LocalDate dateLocal = formatDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        // Asking the user to choose a time interval,and then it checks if the time interval is valid.
        do {
            timeInterval = Utils.readLineFromConsole("Choose the time Interval");
            try {
                UtilsCheck.checkTimeInterval(timeInterval);
                flag = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                flag = false;
            }

        } while (!flag);


        return analyzeVaccinationCenterPerformance(dateLocal, Integer.parseInt(timeInterval));
    }

    /**
     * > This function analyzes the performance of the vaccination center on a given day, and returns an object of type
     * Analysis
     *
     * @param day the day to analyze
     * @param timeInterval the time interval in minutes
     * @return An object of type Analysis.
     */
    public Analysis analyzeVaccinationCenterPerformance(LocalDate day, int timeInterval) {
        return controller.analyzeVaccinationCenterPerformance(day, timeInterval);
    }

    /**
     * This function returns the vaccination center for the user
     *
     * @return The vaccination center for the user.
     */
    public void getVaccinationCenterForTheUser(){
        controller.getVaccinationCenterForTheUser();

    }


}

  /*      // Asking the user to choose a vaccination center and then it checks if the vaccination center is valid.
        do {
            //Choose the Vaccination Center
            opcao = Utils.selectsIndex(VaccinationCenters());

            //Set the VaccinationCenter on the Controller
            flag = setVaccinationCenter(chooseVaccinationCenter(opcao));

        } while (!flag);
*/
