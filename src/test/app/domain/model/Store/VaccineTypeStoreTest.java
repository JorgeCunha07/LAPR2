package app.domain.model.Store;

import app.domain.model.VaccineType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeStoreTest {

    @Test
    void createVaccineType() {
    }

    @Test
    void validateVaccineType() {
    }

    @Test
    void saveVaccineType() {
    }
    @Test
    void validateCode() {
        VaccineType a= new VaccineType();
        a.setCode("ffff");
        assertFalse(a.validateCode());
    }

}