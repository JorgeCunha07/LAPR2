package app.controller;

import app.domain.model.*;
import app.domain.model.Store.SNSUserStore;
import app.domain.shared.UtilsCheck;
import app.ui.console.utils.Utils;
import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class VaccinationStatisticsController implements Serializable {

    // A private variable that is used to store the company.
    private Company company;
    // A variable that is used to store the SNSUserStore object.
    private SNSUserStore snsUserStore;
    // A list of all the SNS users.
    private ArrayList<SNSUser> snsUserList;

    private int[] fullyVaccinatedPerDay;

    private LocalDate startDate;


    // A constructor.
    public VaccinationStatisticsController() {
        company = App.getInstance().getCompany();
        snsUserStore = company.getSnsUserStore();
        snsUserList = snsUserStore.getSaveSNSUser();
    }

    private int getUserAge(SNSUser snsUser) {
        String[] birthdateSplit = snsUser.getBirthDate().split("-|/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    public boolean checkDate(String date) {
        return UtilsCheck.checkDate(date);
    }

    // Need to check the Vaccination Center part as we need to check this to a specific Vaccination Center
    public void getFullyVaccinatedCount(LocalDate fromDate, LocalDate toDate) {
        int count;
        long intervalOfDays = fromDate.until(toDate, ChronoUnit.DAYS) + 1;
        int[] fullyVaccinatedPerDay = new int[(int) intervalOfDays];
        for (int i = 0; i < intervalOfDays; i++) {
            count = 0;
            for (SNSUser user : snsUserList) {
                for (Bulletin bulletin : user.getVaccineBulletin()) {
                    if ((getLastDoseDay(bulletin).equals(fromDate) || getLastDoseDay(bulletin).isAfter(fromDate)) && (getLastDoseDay(bulletin).equals(toDate) || getLastDoseDay(bulletin).isBefore(toDate))) {
                        int age = getUserAge(user);
                        for (int j = 0; j < bulletin.getVaccine().getAdministrationProcess().getListOfAgeGroups().size(); j++) {
                            if (bulletin.getVaccine().getAdministrationProcess().getListOfAgeGroups().get(j).checkIfBelongsInAgeGroup(age)) {
                                if (bulletin.isLastDose(j)) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            fullyVaccinatedPerDay[i] = count;
        }

        this.fullyVaccinatedPerDay = fullyVaccinatedPerDay;
        this.startDate = fromDate;
    }

    public LocalDate getLastDoseDay(Bulletin bulletin) {
        return bulletin.getDateTimeOfLastDose().toLocalDate();
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void exportToCsv(String path) {
        String[] message = new String[fullyVaccinatedPerDay.length];
        for (int i = 0; i < fullyVaccinatedPerDay.length; i++) {
            message[i] = String.format("%s; %d", startDate.plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), fullyVaccinatedPerDay[i]);
        }
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < message.length; i++) {
                bf.write(message[i] + "\n");
                bf.flush();
            }
            bf.close();
        } catch (IOException e) {
            Utils.createAlert(Alert.AlertType.ERROR, "Error", "Error", "Something went wrong");
        }

    }

    public boolean validatePath(String path) {
        if (Objects.requireNonNull(path).isBlank()) {
            return false;
        }
        if (!path.matches(".*\\.csv")) {
            return false;
        }
        return true;
    }
}

    /* public ArrayList<String> vaccineIsCompleted(Bulletin bulletin) {
         Vaccine vaccine = bulletin.getVaccine();
         int numberOFDoses = 0;

         vaccine.getAdministrationProcess().getListOfAgeGroups();
        for (int rows = 0; rows < company.listOfVaccines.size(); rows++) {
            for (int columns = 0; columns < company.listOfVaccines.get(rows).getAdministrationProcess().getListOfAgeGroups().size(); columns++) {
                if (company.listOfVaccines.get(rows).getAdministrationProcess().getListOfAgeGroups().get(columns).checkIfBelongsInAgeGroup(getUserAge())){
                    if (company.listOfVaccines.get(rows) == vaccine){
                        numberOFDoses = company.listOfVaccines.get(rows).getAdministrationProcess().getListOfAgeGroups().get(columns).getNumberOfDoses();
                    }

                }
            }
        }
        return vaccinesAvailable;
    } */


     /* public int dayTobeAnalyse(LocalDate dateLocal) {
        for (SNSUser snsUserAux: snsUserList) {
            setSnsUser(snsUserAux);
            for (Bulletin bulletinAux: snsUserAux.getVaccineBulletin()) {


            }
        }
     } */