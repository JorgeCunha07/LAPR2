package app.domain.model.Store;

import app.domain.model.Employee;
import app.domain.model.dto.EmployeeDTO;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeStore {

    // Creating a list of employees.
    public static ArrayList<Employee> employeeList;
    // A variable that is used to check if the employee is already registered in the system.
    private final AuthFacade authFacade;
    // Creating a new instance of the Employee class.
    private final Employee employee;
    // A variable that is used to generate a random id for the employee.
    private int id;
    // Used to check if the id generated is already in use.
    private boolean flag;

    // Creating a new instance of the AuthFacade class.
    public EmployeeStore(AuthFacade authFacade) {
        this.authFacade = authFacade;
        this.employee = new Employee();
        employeeList = new ArrayList<>();
    }

    /**
     * It returns an ArrayList of Employee objects
     *
     * @return The employeeList ArrayList is being returned.
     */
    public  ArrayList<Employee> getListOfEmployees() {
        return employeeList;
    }

    /**
     * This function sets the employeeList variable to the employeeList parameter
     *
     * @param employeeList This is the list of employees that will be stored in the EmployeeStore.
     */
    public void setListOfEmployees(ArrayList<Employee> employeeList) {
        EmployeeStore.employeeList = employeeList;
    }

    /**
     * This function checks if the ccNumber is already in the employeeList
     *
     * @param ccNumber The citizen card number of the employee.
     * @return A boolean value.
     */
    public boolean checkCCNumberEmployeeList(String ccNumber) {
        boolean flag = true;
        for (int i = 0; i < employeeList.size(); i++) {
            if (ccNumber.compareToIgnoreCase(employeeList.get(i).getCitizenCardNumber()) == 0) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * The function creates an employee object with the given parameters
     *
     * @param employeeDTO The object that contains the information of the employee to be created.
     * @return An Employee object is being returned.
     */
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Random rnd = new Random();
        do {
            id = rnd.nextInt(999999999);
            flag = Employee.checkId(id);
        } while (!flag);
        return new Employee(id, employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getPhoneNumber(), employeeDTO.getEmail(), employeeDTO.getCitizenCardNumber());
    }

    /**
     * If the employee is null, or if the employee's email is already in use, or if the employee is already in the list,
     * then return false. Otherwise, return true
     *
     * @param employee The employee object to be validated.
     * @return A boolean value.
     */
    public boolean validateEmployee(Employee employee) {
        if (employee == null) {
            return false;
        } else if (authFacade.existsUser(employee.getEmail().getEmail())) {
            return false;
        } else return !employeeList.contains(employee);
    }

    /**
     * > If the employee is valid, add it to the list
     *
     * @param employee The employee object to be saved.
     * @return A boolean value.
     */
    public boolean saveEmployee(Employee employee) {
        if (validateEmployee(employee)) {
            return employeeList.add(employee);
        } else return false;
    }


    /**
     * It takes a user role as an argument, then it gets all the users from the database, then it loops through all the
     * users and checks if the user role is the same as the one passed as an argument, if it is, then it adds the user to
     * the list of employees
     *
     * @param userRole The role of the user you want to get the list of employees for.
     * @return A list of employees.
     */
    public List<Employee> getEmployeesList(UserRoleDTO userRole) {

        if (userRole == null)
            throw new IllegalArgumentException("User Role does not exist..");

        List<UserDTO> usersList = authFacade.getUsers();
        ArrayList<Employee> employeesListFilter = new ArrayList<>();

        for (int userByRow = 0; userByRow < usersList.size(); userByRow++) {
            List<UserRoleDTO> userRolesList = usersList.get(userByRow).getRoles();
            for (int roleRow = 0; roleRow < userRolesList.size(); roleRow++) {
                if (userRolesList.get(roleRow).getId().compareTo(userRole.getId()) == 0)
                    employeesListFilter.add(getEmployeeEmail(new Email(usersList.get(userByRow).getId())));
            }
        }
        return employeesListFilter;
    }


    /**
     * This function takes in an email object and returns an employee object
     *
     * @param email The email of the employee you want to search for.
     * @return The method is returning an Employee object.
     */
    public Employee getEmployeeEmail(Email email) {
        Employee searchOfEmployee = new Employee();

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmail().getEmail().compareToIgnoreCase(email.getEmail()) == 0) {
                searchOfEmployee = employeeList.get(i);
            }

        }
        return searchOfEmployee;
    }
}
