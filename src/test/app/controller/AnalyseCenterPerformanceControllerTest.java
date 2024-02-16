package app.controller;

import app.domain.model.*;
import app.domain.shared.UtilsCheck;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnalyseCenterPerformanceControllerTest {


    @Test
    void getTotalEntriesByVaccinationCenter() {
    }

    @Test
    void getTotalExitsByVaccinationCenter() {
    }

    @Test
    void getEntryRecordsFromVaccinationCenterByDay() {
    }

    @Test
    void getLeavingRecordsFromVaccinationCenterByDay() {
    }

    @Test
    void calculateDifferencesBetweenEntryAndLeavingRecordsOfVaccinationCenter() {
    }

    @Test
    void calculateMaximumSumContiguousSublist() {
    }

    @Test
    void calculateSumofContiguousSublist() {
    }

    @Test
    void ensureIsNotPossibleToCalculateDifferencesWithNoEntryAndLeavingRecords() {
        boolean flag;
        AnalyseCenterPerformanceController analyseCenterPerformanceController = new AnalyseCenterPerformanceController();
        VaccinationCenter vaccinationCenter = App.getInstance().getCompany().getVaccinationCenterStore().createCenter("centercoordinatorr@lei.sem2.pt","Centroo1990", "Rua do Meio", 912887321, "centro@dgs.pt", 1334531222, "www.centro.pt", LocalTime.of(8,0), LocalTime.of(10,30), 30, 2);
        VaccinationCenter vaccinationCenter1 = App.getInstance().getCompany().getVaccinationCenterStore().createCenter2(vaccinationCenter, new ArrayList<VaccinatonCenterDate>(), new ArrayList<String>());
       try{
           flag = analyseCenterPerformanceController.validateVaccinationCenter(vaccinationCenter1);
       }catch (RuntimeException e){
           flag = false;
       }
        assertFalse(flag);
    }

    @Test
    void ensureIsPossibleToCalculateDifferencesBetweenValidEntryAndLeavingRecords() {
        boolean flag;
        AnalyseCenterPerformanceController analyseCenterPerformanceController = new AnalyseCenterPerformanceController();
        VaccinationCenter vaccinationCenter = App.getInstance().getCompany().getVaccinationCenterStore().createCenter("centercoordinatorr@lei.sem2.pt","Centroo1996", "Rua do Meio", 912887321, "centro@dgs.pt", 1334531222, "www.centro.pt", LocalTime.of(8,0), LocalTime.of(10,30), 30, 2);
        VaccinationCenter vaccinationCenter2 = App.getInstance().getCompany().getVaccinationCenterStore().createCenter2(vaccinationCenter, new ArrayList<VaccinatonCenterDate>(), new ArrayList<String>());
        App.getInstance().getCompany().getVaccinationCenterStore().addDaysBOOTSTRAP(vaccinationCenter2);
        App.getInstance().getCompany().getVaccinationCenterStore().addSlotsToDateBOOTSTRAP(vaccinationCenter2);
        App.getInstance().getCompany().SaveVaccinationCenter(vaccinationCenter2);
        ScheduledVaccine scheduledVaccineForUser1 = new ScheduledVaccine("999999999","Centroo1996", LocalDate.of(2022,6,17),vaccinationCenter2.getListOfDates().get(3).getListOfSlots().get(1),LocalTime.of(8,30),"covid", LocalDateTime.of(2022,6,17,8,30));
        ScheduledVaccine scheduledVaccineForUser2 = new ScheduledVaccine("981234121","Centroo1996", LocalDate.of(2022,6,20),vaccinationCenter2.getListOfDates().get(6).getListOfSlots().get(2),LocalTime.of(9,00),"covid",LocalDateTime.of(2022,6,20,9,00));
        ScheduledVaccine scheduledVaccineForUser3 = new ScheduledVaccine("333333333","Centroo1996", LocalDate.of(2022,6,21),vaccinationCenter2.getListOfDates().get(7).getListOfSlots().get(4),LocalTime.of(10,00),"covid",LocalDateTime.of(2022,6,21,10,00));
        vaccinationCenter2.getListOfDates().get(3).getListOfSlots().get(1).addSchedule();
        vaccinationCenter2.getListOfDates().get(6).getListOfSlots().get(2).addSchedule();
        vaccinationCenter2.getListOfDates().get(7).getListOfSlots().get(4).addSchedule();
        App.getInstance().getCompany().getScheduledVaccineStore().addToList(scheduledVaccineForUser1);
        App.getInstance().getCompany().getScheduledVaccineStore().addToList(scheduledVaccineForUser2);
        App.getInstance().getCompany().getScheduledVaccineStore().addToList(scheduledVaccineForUser3);
        EntryRecord entryRecord1 = new EntryRecord(LocalDateTime.of(2022,6,17,8,0),true,scheduledVaccineForUser1);
        EntryRecord entryRecord2 = new EntryRecord(LocalDateTime.of(2022,6,20,8,30),true, scheduledVaccineForUser2);
        EntryRecord entryRecord3 = new EntryRecord(LocalDateTime.of(2022,7,20,9,45),true,scheduledVaccineForUser3);
        App.getInstance().getCompany().getEntryRecordStore().getEntryRecords().add(entryRecord1);
        App.getInstance().getCompany().getEntryRecordStore().getEntryRecords().add(entryRecord2);
        App.getInstance().getCompany().getEntryRecordStore().getEntryRecords().add(entryRecord3);

        try{
            flag = analyseCenterPerformanceController.validateVaccinationCenter(vaccinationCenter2);
        }catch (RuntimeException e){
            flag = false;
        }
        assertTrue(flag);
    }
    @Test
    void checkDate() {
    }

    @Test
    void analyzeVaccinationCenterPerformance() {
    }

    @Test
    void getUserEmail() {
    }

    @Test
    void getVaccinationCenterForTheUser() {
    }


//_____________________________________________________________


@Test
    void ensureTimeIntervalIsValid() {
        Assertions.assertTrue(UtilsCheck.checkTimeInterval("30"));
    }

    @Test
    void ensureTimeIntervalIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UtilsCheck.checkTimeInterval("11");
        });
    }

    @Test
    void ensureDayIsValid() {
        Assertions.assertTrue(UtilsCheck.checkDate("30/05/2022"));
    }

    @Test
    void ensureDayIsInvalid() {
        Assertions.assertFalse(UtilsCheck.checkDate("30//2022"));
    }
}