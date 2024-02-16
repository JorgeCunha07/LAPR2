package app.domain.model.Store;

import app.controller.App;
import app.domain.model.ScheduledVaccine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledVaccineStoreTest {

    @Test
    void getListOfScheduledVaccines() {
    }

    @Test
    void setListOfScheduledVaccines() {
    }

    @Test
    void addToList() {
    }

    /*
    void checkNullScheduledVaccine() {
        ScheduledVaccineController controller = App.getInstance().getCompany().getScheduledVaccineController;

        assertThrows(NullPointerException.class, () -> {
            controller.checkScheduledVaccine(new ScheduledVaccine(null,null,null,null,null,null));
        });
    }



    public void checkIfScheduleExists(ScheduledVaccine scheduledVaccine) {
        ArrayList<ScheduledVaccine> list = App.getInstance().getCompany().getScheduledVaccineStore.getListOfScheduledVaccines;
       // (expected = AlreadyExistsException.class);
        for (ScheduledVaccine scheduled : list){
            if (scheduled.getVaccineType.equals(scheduledVaccine.getVaccineType));
            throw Exception;
        }

    }*/

}