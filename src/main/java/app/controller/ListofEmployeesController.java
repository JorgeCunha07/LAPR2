package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;


public class ListofEmployeesController {


    // It's a controller class that handles the registration of employees
    private Company company;
    // It's a list of user roles.
    private List<UserRoleDTO> rolesList;


    // It's a constructor that initializes the company and rolesList variables.
    public ListofEmployeesController() {
        company = App.getInstance().getCompany();
        rolesList = company.getUserRoles();
    }

    /**
     * > This function returns a list of UserRoleDTO objects
     *
     * @return A list of UserRoleDTO objects.
     */
    public List<UserRoleDTO> getRolesList() {
        return rolesList;
    }


    /**
     * > This function returns a list of employees for a given user role
     *
     * @param userRole This is the role of the user who is trying to access the list of employees.
     * @return A list of employees.
     */
    public List<Employee> getEmployeesList(UserRoleDTO userRole) {

        return company.getEmployeesList(userRole);
    }


}
