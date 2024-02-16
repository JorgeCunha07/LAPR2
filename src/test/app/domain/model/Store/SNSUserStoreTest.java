package app.domain.model.Store;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.model.SNSUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static app.domain.shared.Constants.TYPE_WITHOUT_HEADER;
import static app.domain.shared.Constants.TYPE_WITH_HEADER;
import static org.junit.jupiter.api.Assertions.*;

class SNSUserStoreTest {

    @Test
    void createSNSUser() {
    }

    @Test
    void convertFileSNSUserToArrayList() {
    }

    @Test
    void checkSNSUser() {
    }

    @Test
    void testConvertFileSNSUserToArrayList() {
    }


    @Test
    void checkSNSUserIsNotAccepted() {
        SNSUserStore store = App.getInstance().getCompany().getSnsUserStore();

        Assertions.assertThrows(NullPointerException.class, () -> {
            store.checkSNSUser(new SNSUser(null,null,null,null,null,null,null,null));
        });
    }

    @Test
    void checkSNSUserNotSavedIfInvalid() {
        SNSUserStore store = App.getInstance().getCompany().getSnsUserStore();

        Assertions.assertThrows(NullPointerException.class, () -> {
            store.checkSNSUser(new SNSUser(null,null,null,null,null,null,null,null));
        });
    }

    //String name, String address, String gender, String phoneNumber, String email, String birthDate, String snsUserNumber, String citizenCardNumber

    @Test
    void testConvertFileSNSUserToArrayListWithValidCSVHeader() throws IOException {
        SNSUserStore store = App.getInstance().getCompany().getSnsUserStore();
        ArrayList<SNSUser> test = new ArrayList<>();
        test.add(new SNSUser("Jorge","Rua Jose","Male","912638424","cunha7@me.com","27/11/2000","123456789","12345678"));

        File br = new File("Exemplo1.csv");
        BufferedReader file = new BufferedReader(new FileReader(br));;

        ArrayList<SNSUser> test2 =store.convertFileSNSUserToArrayList(file, TYPE_WITH_HEADER);
        Assertions.assertEquals(test,test2);
    }
    @Test
    void testConvertFileSNSUserToArrayListWithValidCSVWithoutHeader() throws IOException {
        SNSUserStore store = App.getInstance().getCompany().getSnsUserStore();
        ArrayList<SNSUser> test = new ArrayList<>();
        test.add(new SNSUser("ruben","rua da bela","Male","912861639","ruben@gmail.com","01/05/2003","987654321","87654321"));

        File br = new File("Exemplo2.csv");
        BufferedReader file = new BufferedReader(new FileReader(br));;

        ArrayList<SNSUser> test2 =store.convertFileSNSUserToArrayList(file, TYPE_WITHOUT_HEADER);
        Assertions.assertEquals(test,test2);
    }



}