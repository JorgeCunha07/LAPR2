package app.controller;

import app.domain.model.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type Legacy data controller test.
 */
class LegacyDataControllerTest {
    /**
     * The Controller.
     */
    LegacyDataController controller = new LegacyDataController();

    /**
     * The Sns user list.
     */
    /*Creating some stuff that is needed for the tests*/
    //A list of sns users.
    ArrayList<SNSUser> snsUserList = new ArrayList<>(
            Arrays.asList(new SNSUser("Guilherme Sousa", "Rua jacinto", "Male", "912321321", "guisousa2003@gmail.com", "01-02-1990", "981234121", "15469870"),
                    new SNSUser("Antonio Jose", "Arouca", "Male", "913885321", "antonio@gmail.com", "21-06-2002", "999999999", "15214470"))
    );
    /**
     * The Vaccine types.
     */
//--------------------------------------
    //A list of vaccine types.
    ArrayList<VaccineType> vaccineTypes = new ArrayList<>(
            Arrays.asList(new VaccineType("covid", "Covid-19 vaccine", "Live-attenuated vaccine"))
    );
    //-----------------------------------------------

    /**
     * The Age group 1 for vaccine 1.
     */
//A list of vaccines
    AgeGroup ageGroup1ForVaccine1 = new AgeGroup(10, 20, 2, 10, 30);
    /**
     * The Age group 2 for vaccine 1.
     */
    AgeGroup ageGroup2ForVaccine1 = new AgeGroup(30, 40, 1, 100, 0);
    /**
     * The Listfor vaccine 1.
     */
    ArrayList<AgeGroup> listforVaccine1 = new ArrayList<>(
            Arrays.asList(ageGroup1ForVaccine1, ageGroup2ForVaccine1)
    );
    /**
     * The Vaccine type.
     */
    VaccineType vaccineType = new VaccineType("covid", "Covid-19 vaccine", "Live-attenuated vaccine");
    /**
     * The Administration process for vaccine 1.
     */
    AdministrationProcess administrationProcessForVaccine1 = new AdministrationProcess(listforVaccine1);
    /**
     * The Vaccine 1.
     */
    Vaccine vaccine1 = new Vaccine(1, "moderna", vaccineType, administrationProcessForVaccine1);

    /**
     * The Vaccine list.
     */
    ArrayList<Vaccine> vaccineList = new ArrayList<>(
            Arrays.asList(vaccine1)
    );
    //------------------------------
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    /**
     * The List of legacy data.
     */
    ArrayList<LegacyData> listOfLegacyData = new ArrayList<>(
            Arrays.asList(new LegacyData("123456789", "moderna", "vaccine for covid", "Primeira", "21C16-05", LocalDateTime.parse("05/30/2022 08:00", formatter), LocalDateTime.parse("05/30/2022 08:24", formatter),LocalDateTime.parse("05/30/2022 09:11", formatter),LocalDateTime.parse("05/30/2022 09:43", formatter)),
                    new LegacyData("123456788", "moderna", "vaccine for covid", "Primeira", "21C16-05", LocalDateTime.parse("05/30/2022 08:00", formatter), LocalDateTime.parse("05/30/2022 08:20", formatter), LocalDateTime.parse("05/30/2022 09:09", formatter), LocalDateTime.parse("05/30/2022 09:43", formatter)),
                    new LegacyData("123456784", "moderna", "vaccine for covid", "Primeira", "21C16-05", LocalDateTime.parse("05/30/2022 08:00", formatter), LocalDateTime.parse("05/30/2022 08:40", formatter), LocalDateTime.parse("05/30/2022 09:20", formatter), LocalDateTime.parse("05/30/2022 09:43", formatter))
            )
    );


    /**
     * Test check if vaccine exists true.
     */
    @Test
    void testCheckIfVaccineExistsTrue() {
        boolean status = false;
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.getName().equals("moderna")) {
                status = true;
            }
        }
        assertTrue(status);
    }

    /**
     * Test check if vaccine exists false.
     */
    @Test
    void testCheckIfVaccineExistsFalse() {
        boolean status = false;
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.getName().equals("Vaccine")) {
                status = true;
            }
        }
        assertFalse(status);
    }

    /**
     * Test check if sns user exists true.
     */
    @Test
    void testCheckIfSnsUserExistsTrue() {
        boolean status = false;
        for (SNSUser snsUser : snsUserList) {
            if (snsUser.getSnsUserNumber().equals("999999999")) {
                status = true;
            }
        }

        assertTrue(status);
    }

    /**
     * Test check if sns user exists false.
     */
    @Test
    void testCheckIfSnsUserExistsFalse() {
        boolean status = false;
        for (SNSUser snsUser : snsUserList) {
            if (snsUser.getSnsUserNumber().equals("999999990")) {
                status = true;
            }
        }

        assertFalse(status);
    }


    /**
     * Test get size of array list.
     */
    @Test
    void testGetSizeOfArrayList() {
        boolean status;
        if (listOfLegacyData.size() == 3) {
            status = true;
        } else status = false;
        assertTrue(status);
    }

    /**
     * Test bubble sort.
     */
    @Test
    void testBubbleSort() {
        boolean status = false;
        for (int i = 0; i < listOfLegacyData.size() - 1; i++) {
            for (int j = i + 1; j < listOfLegacyData.size(); j++) {
                if (listOfLegacyData.get(j).getArrivalTime().isBefore(listOfLegacyData.get(i).getArrivalTime())) {
                    Collections.swap(listOfLegacyData, i, j);
                }
            }
        }
        if (listOfLegacyData.get(0).getSNSUser().equals("123456788")) {
            if (listOfLegacyData.get(1).getSNSUser().equals("123456789")) {
                if (listOfLegacyData.get(2).getSNSUser().equals("123456784")) {
                    status = true;
                }
            }
        }
        assertTrue(status);
    }

    /**
     * Test selection sort.
     */
    @Test
    void testSelectionSort() {
        boolean status = false;
        for (int i = 0; i < listOfLegacyData.size() - 1; i++) {
            int minimum = i;
            for (int j = i + 1; j < listOfLegacyData.size(); j++) {
                if (listOfLegacyData.get(j).getArrivalTime().isBefore(listOfLegacyData.get(minimum).getArrivalTime())) {
                    minimum = j;
                }
            }
            LegacyData temp = listOfLegacyData.get(minimum);
            listOfLegacyData.set(minimum, listOfLegacyData.get(i));
            listOfLegacyData.set(i, temp);
        }

        if (listOfLegacyData.get(0).getSNSUser().equals("123456788")) {
            if (listOfLegacyData.get(1).getSNSUser().equals("123456789")) {
                if (listOfLegacyData.get(2).getSNSUser().equals("123456784")) {
                    status = true;
                }
            }
        }
        assertTrue(status);

    }

    /**
     * Test create file.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCreateFile() throws IOException {
        boolean status = false;
        File file = new File("test.csv");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new IOException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);


        for (LegacyData legacyData1 : listOfLegacyData) {
            try {
                bw.write(legacyData1.toString());
            } catch (IOException e) {
                throw new IOException(e);
            }
        }

        if (file.exists()) {
            status = true;
        }
        assertTrue(status);
    }


    /**
     * Test get dose.
     */
    @Test
    void testGetDose() {
        boolean status;
        String answer = controller.getDose(3);
        if (answer.equals("Terceira")) {
            status = true;
        } else status = false;
        assertTrue(status);
    }


    /**
     * Test check if already exists true.
     */
    @Test
    void testCheckIfAlreadyExistsTrue() {
        boolean status = false;
        if (listOfLegacyData.isEmpty()) {
            status = false;
        } else
            for (LegacyData object : listOfLegacyData) {
                //This "if" is enough because if it is the same sns user taking the same vaccine dose, it already exists. It's impossible to take the same dose more than one time.
                if (object.getSNSUser().equals("123456784") && object.getVaccineName().equals("moderna") && object.getDose().equals("Primeira")) {
                    status = true;
                }
            }
        assertTrue(status);
    }

    /**
     * Test check if already exists false.
     */
    @Test
    void testCheckIfAlreadyExistsFalse() {
        boolean status = false;
        if (listOfLegacyData.isEmpty()) {
            status = false;
        } else
            for (LegacyData object : listOfLegacyData) {
                //This "if" is enough because if it is the same sns user taking the same vaccine dose, it already exists. It's impossible to take the same dose more than one time.
                if (object.getSNSUser().equals("123416782") && object.getVaccineName().equals("moderna") && object.getDose().equals("Segunda")) {
                    status = true;
                }
            }
        assertFalse(status);
    }
}