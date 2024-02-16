package app.controller;

import app.domain.model.*;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    //Don´t touch
    private  Company company;
    //Don´t touch
    private  AuthFacade authFacade;

    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        // Adding the roles to the system.
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST,Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_NURSE,Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_CENTER_COORDINATOR,Constants.ROLE_CENTER_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SNS_USER,Constants.ROLE_SNS_USER);

        // Creating 4 employees and saving them in the employee store.
        this.company.getEmployeeStore().saveEmployee(new Employee(1313131, "Main Administrator", "Rua 1", 911111111, new Email("admin@lei.sem2.pt"), "11111111"));
        this.company.getEmployeeStore().saveEmployee(new Employee(22313131, "Receptionist", "Rua 2", 922222222, new Email("receptionist@lei.sem2.pt"), "11111112"));
        this.company.getEmployeeStore().saveEmployee(new Employee(3313131, "Nurse", "Rua 3", 933333333, new Email("nurse@lei.sem2.pt"), "11111113"));
        this.company.getEmployeeStore().saveEmployee(new Employee(313131314, "Center Coordinator", "Rua 4", 966666666, new Email("centercoordinator@lei.sem2.pt"), "11111114"));

        //adding a sns user
        SNSUser user2 = new SNSUser("Guilherme Sousa","Rua jacinto","Male","912321321","guisousa2003@gmail.com","01-02-1990","981234121","15469870");
        SNSUser user1 = new SNSUser("Fabio Jose","Porto","Male","913885321","antonio@gmail.com","18-03-2003","999999999","15214470");
        SNSUser user3 = new SNSUser("Ana Lopes","Gaia","Female","913772123","analopes@mail.com","10-01-1970","333333333","52144743");
        SNSUser user4 = new SNSUser("Jorjao","Rua das fafas","Female","917123654","tiagofefe@famfa.com","27/01/1999","444444444","15235678");
        this.company.getSnsUserStore().addSNSUser(user1);
        this.company.getSnsUserStore().addSNSUser(user2);
        this.company.getSnsUserStore().addSNSUser(user3);
        this.company.getSnsUserStore().addSNSUser(user4);


        // Adding users to the system.
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("User", "user@lei.sem2.pt", "123456",Constants.ROLE_SNS_USER);
        this.authFacade.addUserWithRole("Receptionist", "receptionist@lei.sem2.pt", "123456",Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Nurse", "nurse@lei.sem2.pt", "123456",Constants.ROLE_NURSE);
        this.authFacade.addUserWithRole("Center Coordinator", "centercoordinator@lei.sem2.pt", "123456",Constants.ROLE_CENTER_COORDINATOR);
        this.authFacade.addUserWithRole("Guilherme Sousa", "guisousa2003@gmail.com", "123456", Constants.ROLE_SNS_USER);
        this.authFacade.addUserWithRole("Antonio Jose","antonio@gmail.com","123456",Constants.ROLE_SNS_USER);
        this.authFacade.addUserWithRole("Jorjao","tiagofefe@famfa.com","123456",Constants.ROLE_SNS_USER);

        //Adding a vaccine type
        VaccineType vaccineType = new VaccineType("covid","Covid-19 vaccine","Live-attenuated vaccine");
        this.company.getVaccineTypes().add(vaccineType);


        //Adding a vaccine
        AgeGroup ageGroup1ForVaccine1 = new AgeGroup(10,20,2,10,30);
        AgeGroup ageGroup2ForVaccine1 = new AgeGroup(30,40,1,100,0);
        ArrayList<AgeGroup> listforVaccine1 = new ArrayList<>();
        listforVaccine1.add(ageGroup1ForVaccine1); listforVaccine1.add(ageGroup2ForVaccine1);
        AdministrationProcess administrationProcessForVaccine1 = new AdministrationProcess(listforVaccine1);
        Vaccine vaccine1 = new Vaccine(1,"Moderna",vaccineType,administrationProcessForVaccine1);
        this.company.getListOfVaccines().add(vaccine1);


        //Adding another vaccine
        AgeGroup ageGroup1ForVaccine2 = new AgeGroup(50,80,1,20,0);
        ArrayList<AgeGroup> listforVaccine2 = new ArrayList<>();
        listforVaccine2.add(ageGroup1ForVaccine2);
        AdministrationProcess administrationProcessForVaccine2 = new AdministrationProcess(listforVaccine2);
        Vaccine vaccine2 = new Vaccine(2,"Pfizer",vaccineType,administrationProcessForVaccine2);
        this.company.getListOfVaccines().add(vaccine2);

        //Adding another vaccine
       AgeGroup ageGroup1ForVaccine3 = new AgeGroup(15,25,1,50,0);
        AgeGroup ageGroup2ForVaccine3 = new AgeGroup(35,40,2,20,30);
        ArrayList<AgeGroup> listforVaccine3 = new ArrayList<>();
        listforVaccine3.add(ageGroup1ForVaccine3); listforVaccine3.add(ageGroup2ForVaccine3);
        AdministrationProcess administrationProcessForVaccine3 = new AdministrationProcess(listforVaccine3);
        Vaccine vaccine3 = new Vaccine(3,"Astra",vaccineType,administrationProcessForVaccine3);
        this.company.getListOfVaccines().add(vaccine3);

        //Adding US17 vaccine
        AgeGroup ageGroup1ForVaccine4 = new AgeGroup(10,49,1,50,0);
        AgeGroup ageGroup2ForVaccine4 = new AgeGroup(55,80,2,20,30);
        ArrayList<AgeGroup> listforVaccine4 = new ArrayList<>();
        listforVaccine4.add(ageGroup1ForVaccine4); listforVaccine4.add(ageGroup2ForVaccine4);
        AdministrationProcess administrationProcessForVaccine4 = new AdministrationProcess(listforVaccine4);
        Vaccine vaccine4 = new Vaccine(4,"Spikevax",vaccineType,administrationProcessForVaccine4);
        this.company.getListOfVaccines().add(vaccine4);

        //Adding a vaccination center
        VaccinationCenter vaccinationCenter = this.company.getVaccinationCenterStore().createCenter("centercoordinator@lei.sem2.pt","Centro", "Rua do Meio", 912887321, "centro@dgs.pt", 1334531222, "www.centro.pt", LocalTime.of(8,0), LocalTime.of(10,30), 30, 2);
        VaccinationCenter vaccinationCenter1 = this.company.getVaccinationCenterStore().createCenter2(vaccinationCenter, new ArrayList<VaccinatonCenterDate>(), new ArrayList<String>());
        this.company.getVaccinationCenterStore().addDaysBOOTSTRAP(vaccinationCenter1);
        this.company.getVaccinationCenterStore().addSlotsToDateBOOTSTRAP(vaccinationCenter1);
        this.company.SaveVaccinationCenter(vaccinationCenter1);

       /* Data in order to simulate a vaccination process. Scheduling - Arrival - Administering - Leaving */
        //Adding scheduled vaccines
        ScheduledVaccine scheduledVaccineForUser1 = new ScheduledVaccine("999999999","Centro", LocalDate.of(2022,6,17),vaccinationCenter1.getListOfDates().get(3).getListOfSlots().get(1),LocalTime.of(8,30),"covid",LocalDateTime.of(2022,6,17,8,30));
        ScheduledVaccine scheduledVaccineForUser2 = new ScheduledVaccine("981234121","Centro", LocalDate.of(2022,6,20),vaccinationCenter1.getListOfDates().get(6).getListOfSlots().get(2),LocalTime.of(9,00),"covid",LocalDateTime.of(2022,6,20,9,00));
        ScheduledVaccine scheduledVaccineForUser3 = new ScheduledVaccine("333333333","Centro", LocalDate.of(2022,6,21),vaccinationCenter1.getListOfDates().get(7).getListOfSlots().get(4),LocalTime.of(10,00),"covid",LocalDateTime.of(2022,6,21,10,00));
        ScheduledVaccine scheduledVaccineForUser4 = new ScheduledVaccine("444444444","Centro", LocalDate.of(2022,6,17),vaccinationCenter1.getListOfDates().get(8).getListOfSlots().get(3),LocalTime.of(10,00),"covid",LocalDateTime.of(2022,6,17,10,00));
        vaccinationCenter1.getListOfDates().get(7).getListOfSlots().get(1).addSchedule();
        vaccinationCenter1.getListOfDates().get(10).getListOfSlots().get(2).addSchedule();
        vaccinationCenter1.getListOfDates().get(11).getListOfSlots().get(4).addSchedule();
        vaccinationCenter1.getListOfDates().get(7).getListOfSlots().get(3).addSchedule();
        this.company.getScheduledVaccineStore().addToList(scheduledVaccineForUser1);
        this.company.getScheduledVaccineStore().addToList(scheduledVaccineForUser2);
        this.company.getScheduledVaccineStore().addToList(scheduledVaccineForUser3);
        this.company.getScheduledVaccineStore().addToList(scheduledVaccineForUser4);

        //Adding entry records
        EntryRecord entryRecord1 = new EntryRecord(LocalDateTime.of(2022,6,17,8,0),false,scheduledVaccineForUser1);
        EntryRecord entryRecord2 = new EntryRecord(LocalDateTime.of(2022,6,20,8,30),false, scheduledVaccineForUser2);
        EntryRecord entryRecord3 = new EntryRecord(LocalDateTime.of(2022,7,20,9,45),false,scheduledVaccineForUser3);
        EntryRecord entryRecord4 = new EntryRecord(LocalDateTime.of(2022,7,17,9,55),true,scheduledVaccineForUser4);
        this.company.getEntryRecordStore().getEntryRecords().add(entryRecord1);
        this.company.getEntryRecordStore().getEntryRecords().add(entryRecord2);
        this.company.getEntryRecordStore().getEntryRecords().add(entryRecord3);
        this.company.getEntryRecordStore().getEntryRecords().add(entryRecord4);


        //Adding bulletins
        Bulletin bulletinForUser1 = new Bulletin(vaccine1, LocalDateTime.of(2022,6,17,8,45),1,"21C16-06");


        Bulletin bulletinForUser2 = new Bulletin(vaccine1,LocalDateTime.of(2022,6,20,9,27),1,"21C16-08");

        Bulletin bulletinForUser3 = new Bulletin(vaccine2, LocalDateTime.of(2022,6,21,10,23),1,"21C16-08");

        user1.getVaccineBulletin().add(bulletinForUser1);
        user2.getVaccineBulletin().add(bulletinForUser2);
        user3.getVaccineBulletin().add(bulletinForUser3);

        //Adding leaving records
        LeavingRecord leavingRecord1 = new LeavingRecord(LocalDateTime.of(2022,6,17,9,0),scheduledVaccineForUser1);
        LeavingRecord leavingRecord2 = new LeavingRecord(LocalDateTime.of(2022,6,20,9,50),scheduledVaccineForUser2);
        LeavingRecord leavingRecord3 = new LeavingRecord(LocalDateTime.of(2022,6,21,10,44),scheduledVaccineForUser3);
        this.company.getLeavingRecordStore().getLeavingRecords().add(leavingRecord1);
        this.company.getLeavingRecordStore().getLeavingRecords().add(leavingRecord2);
        this.company.getLeavingRecordStore().getLeavingRecords().add(leavingRecord3);







        //this.company.getScheduledVaccineStore().addToList(new ScheduledVaccine("981234121","Centro",04/06/2022,"","",""));

    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
