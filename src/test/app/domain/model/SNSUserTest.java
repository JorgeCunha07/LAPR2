package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserTest {

    @Test
    void getEmail() {
    }

    @Test
    void getName() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void getGender() {
    }

    @Test
    void getBirthDate() {
    }

    @Test
    void getPhoneNumber() {
    }

    @Test
    void getSnsUserNumber() {
    }

    @Test
    void getCitizenCardNumber() {
    }

    @Test
    void checkSNSUserParam() {
    }

    @Test
    void checkPhoneNumber() {
    }

    @Test
    void checkBirthDate() {
    }

    @Test
    void checkCitizenCardNumber() {
    }

    @Test
    void checkName() {
    }

    @Test
    void checkAddress() {
    }

    @Test
    void checkGender() {
    }

    @Test
    void checkSNSUserNumber() {
    }

    @Test
    void checkSNSUsersFileAttributesPerLine() {
    }

    //

    @Test
    void checkSNSUserParamTrue() {
        boolean flag = SNSUser.checkSNSUserParam("Jorge","Rua José","Male","912638424","cunha7@me.com","27/11/2000","123456789","12345678");
        assertTrue(flag);

    }
    @Test
    void checkSNSUserParamFalse() {
        boolean flag = SNSUser.checkSNSUserParam("Jorge","Rua José","Male","912638424","cunha7@me.com","27/11/2000","123","12378");
        assertFalse(flag);
    }

    @Test
    void checkPhoneNumberTrue() {
        boolean flag =SNSUser.checkPhoneNumber("912333123");
        assertTrue(flag);
    }
    @Test
    void checkPhoneNumberFalse() {
        boolean flag =SNSUser.checkPhoneNumber("9123323");
        assertFalse(flag);
    }

    @Test
    void checkBirthDateTrue() {
        boolean flag =SNSUser.checkBirthDate("27/11/2000");
        assertTrue(flag);
    }
    @Test
    void checkBirthDateFalse() {
        boolean flag =SNSUser.checkBirthDate("33/14/2000");
        assertFalse(flag);
    }

    @Test
    void checkCitizenCardNumberTrue() {
        boolean flag =SNSUser.checkCitizenCardNumber("12345678");
        assertTrue(flag);
    }

    @Test
    void checkCitizenCardNumberFalse() {
        boolean flag = true;
        try {
            SNSUser.checkCitizenCardNumber("5125366");
        }catch (IllegalArgumentException e){
            flag = false;
        }
        assertFalse(flag);
    }
    @Test
    void checkNameTrue() {
        boolean flag = true;
        try {
            SNSUser.checkName("Jorge");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    void checkNameFalse() {
        boolean flag = true;
        try {
            SNSUser.checkName("       ");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertFalse(flag);
    }

    @Test
    void checkAddressTrue() {
        boolean flag = true;
        try {
            SNSUser.checkAddress("Rua José");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    void checkAddressFalse() {
        boolean flag = true;
        try {
             SNSUser.checkAddress("       ");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertFalse(flag);
    }

    @Test
    void checkGenderTrue() {
        boolean flag = true;
        try {
            SNSUser.checkGender("male");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    void checkGenderPortugueseTrue() {
        boolean flag = true;
        try {
            SNSUser.checkGender("masculino");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }


    @Test
    void checkGenderFalse() {
        boolean flag = true;
        try {
            SNSUser.checkGender("NAA");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertFalse(flag);
    }

    @Test
    void checkSNSUserNumberTrue() {
        boolean flag = true;
        try {
            SNSUser.checkSNSUserNumber("123456789");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }
    @Test
    void checkSNSUserNumberFalse() {
        boolean flag = true;
        try {
            SNSUser.checkSNSUserNumber("789");
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertFalse(flag);
    }

    @Test
    void checkSNSUsersFileAttributesPerLineTrue() {
        String[] lineAttributes = new String[8];
        lineAttributes[0] = "Jorge";
        lineAttributes[1] = "Male";
        lineAttributes[2] = "27/11/2000";
        lineAttributes[3] = "Rua Josa";
        lineAttributes[4] = "912638444";
        lineAttributes[5] = "kfafa@eam.com";
        lineAttributes[6] = "123456777";
        lineAttributes[7] = "12344321";

        int lineNumber = 0;
        boolean flag = true;
        try {
            SNSUser.checkSNSUsersFileAttributesPerLine(lineAttributes,lineNumber);
        }catch (IllegalArgumentException e){
            flag = false;
        }

        assertTrue(flag);
    }


}