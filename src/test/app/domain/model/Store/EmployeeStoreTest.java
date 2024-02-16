package app.domain.model.Store;

import app.controller.App;
import app.domain.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;
import java.util.List;

class EmployeeStoreTest {

    @Test
    void checkInvalidEmployeeIsNotAccepted() {
        EmployeeStore store = App.getInstance().getCompany().getEmployeeStore();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            store.validateEmployee(new Employee(0, null, null, 0, new Email(null), null));
        });
    }

    @Test
    void checkEmployeeNotSavedIfInvalid() {
        EmployeeStore store = App.getInstance().getCompany().getEmployeeStore();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            store.saveEmployee(new Employee(0, null, null, 0, new Email(null), null));
        });
    }

    @Test
    void checkEmployeeSavedIfValid() {
        EmployeeStore store = App.getInstance().getCompany().getEmployeeStore();

        Assertions.assertTrue(store.saveEmployee(new Employee(0, "Tiago", "Corcena do Libano", 912638424, new Email("tiago912@gmail.com"), "12345678")));
    }


    @Test
    public void ensureNullUserRoleIsNotAllowed() {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            EmployeeStore employeeStore = App.getInstance().getCompany().getEmployeeStore();
            UserRoleDTO userRole = null;
            List<Employee> list = employeeStore.getEmployeesList(userRole);
        });

        String errorMessageExpected = "User Role does not exist..";

        Assertions.assertEquals(errorMessageExpected, exception.getMessage());
    }
}