package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void checkId() {
    }

    @Test
    void checkName() {
    }

    @Test
    void checkAddress() {
    }

    @Test
    void checkPhoneNumber() {
    }

    @Test
    void checkCitizenCardNumber() {
    }

    @Test
    void setId() {
    }

    @Test
    void setName() {
    }

    @Test
    void setAddress() {
    }

    @Test
    void setPhoneNumber() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void setCitizenCardNumber() {
    }

    @Test
    void ensureNameIsValid() {
        boolean flag = Employee.checkName("joao");
        assertTrue(flag);
    }


    @Test
    void ensureAddressIsValid() {
        boolean flag = Employee.checkAddress("Rua Tio Manel");
        assertTrue(flag);
    }

    @Test
    void ensurePhoneNumberIsValid() {
        boolean flag = Employee.checkPhoneNumber(912638424);
        assertTrue(flag);
    }


    @Test
    void ensureEmailIsValid() {
        boolean flag;
        try {
            Email mail = new Email("cunha7@me.com");
            flag = true;
        } catch (IllegalArgumentException e) {
            flag = false;
        }
        assertTrue(flag);
    }

    @Test
    void ensureCitizenCardNumberIsValid() {
        boolean flag = Employee.checkCitizenCardNumber("12345678");
        assertTrue(flag);
    }

    @Test
    void ensureNameIsNotValid() {
        boolean flag = Employee.checkName("");
        assertFalse(flag);
    }


    @Test
    void ensureAddressIsNotValid() {
        boolean flag = Employee.checkAddress("");
        assertFalse(flag);
    }

    @Test
    void ensurePhoneNumberIsNotValid() {
        boolean flag = Employee.checkPhoneNumber(912424);
        assertFalse(flag);
    }


    @Test
    void ensureEmailIsNotValid() {
        boolean flag;
        try {
            Email mail = new Email("cunha7me.com");
            flag = true;
        } catch (IllegalArgumentException e) {
            flag = false;
        }
        assertFalse(flag);
    }

    @Test
    void ensureCitizenCNumberIsNotValid() {
        boolean flag = Employee.checkCitizenCardNumber("1234567899");
        assertFalse(flag);
    }


}