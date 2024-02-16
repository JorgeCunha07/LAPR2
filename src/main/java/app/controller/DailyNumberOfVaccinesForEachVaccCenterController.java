package app.controller;

import app.domain.model.Company;
import app.domain.model.LeavingRecord;
import app.domain.model.Store.LeavingRecordStore;
import app.domain.model.Store.VaccinationCenterStore;
import app.domain.model.VaccinationCenter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyNumberOfVaccinesForEachVaccCenterController {
  // A private variable that is used to store the company.
  private Company company;
  // A private variable that is used to store the LeavingRecordStore.
  private LeavingRecordStore vaccinations;
  // A private variable that is used to store the VaccinationCenterStore.
  private VaccinationCenterStore vaccCenters;
    // A constructor.
    public DailyNumberOfVaccinesForEachVaccCenterController() {
        this.company = App.getInstance().getCompany();
        this.vaccinations=company.getLeavingRecordStore();
        this.vaccCenters= company.getVaccinationCenterStore();

    }

    public ArrayList<Integer> numberofvaccinesforvacccenterperday(){
        ArrayList<Integer> numberofvaccs= new ArrayList<Integer>(vaccCenters.getVaccinationCenters().size());
        ArrayList<LeavingRecord> vaccines = new ArrayList<LeavingRecord>();
        LocalDate today =LocalDate.now();
        for(VaccinationCenter vaccCenter : vaccCenters.getVaccinationCenters() ){
            vaccines=vaccinations.getLeavingRecordsFromVaccinationCenterByDay(vaccCenter.getName(),today);
            numberofvaccs.add(vaccines.size());
        }
        return numberofvaccs;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
   /* public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
    }*/

}

