package app.domain.model;

import app.controller.App;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.prefs.InvalidPreferencesFormatException;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void verifyTypeofCSV() {
    }

    @Test
    void verifyTypeofCSVTrue() throws FileNotFoundException {
        boolean flag ;
      Company company =  App.getInstance().getCompany();

        File br = new File("Exemplo1.csv");
        BufferedReader file = new BufferedReader(new FileReader(br));

        try {
            company.VerifyTypeofCSV(file);
            flag = true;
        } catch (InvalidPreferencesFormatException | IOException e) {
            flag = false;
        }
        assertTrue(flag);
    }

    @Test
    void verifyTypeofCSVFalse() throws FileNotFoundException {
        boolean flag ;
        Company company =  App.getInstance().getCompany();

        File br = new File("ExemploInvalido1.csv");
        BufferedReader file = new BufferedReader(new FileReader(br));
        try {
            company.VerifyTypeofCSV(file);
            flag = true;
        } catch (InvalidPreferencesFormatException | IOException e) {
            flag = false;
        }
        assertFalse(flag);
    }

    @Test
    void checkLocationTrue() throws IOException {
        Company company =  App.getInstance().getCompany();
        boolean flag = company.checkLocation("Exemplo1.csv") ;

        assertTrue(flag);
    }

    @Test
    void checkLocationFalse() throws IOException {
        Company company =  App.getInstance().getCompany();
        boolean flag = company.checkLocation("Exemplofafafa.csv") ;

        assertFalse(flag);
    }
}