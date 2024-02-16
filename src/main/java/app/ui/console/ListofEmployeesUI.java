package app.ui.console;

import app.controller.ListofEmployeesController;
import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.domain.store.UserRoleStore;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

import static app.ui.console.utils.Utils.readIntegerFromConsole;


public class ListofEmployeesUI implements Runnable {
    // Creating a new instance of the ListofEmployeesUI class.
    private static ListofEmployeesUI instance;
    // Creating a new instance of the ListofEmployeesController class.
    private ListofEmployeesController controller;
    // Creating a new instance of the UserRoleStore class.
    private UserRoleStore userRoleStore;


    // Creating a new instance of the ListofEmployeesController and UserRoleStore classes.
    public ListofEmployeesUI() {
        controller = new ListofEmployeesController();
        userRoleStore = new UserRoleStore();
    }

    /**
     * If the instance of the class is null, create a new instance of the class
     *
     * @return The instance of the ListofEmployeesUI class.
     */
    public static ListofEmployeesUI getInstance() {
        if (instance == null) {
            instance = new ListofEmployeesUI();
        }
        return instance;
    }


    /**
     * > This function returns a list of all the roles in the database
     *
     * @return A list of UserRoleDTO objects.
     */
    public List<UserRoleDTO> getRolesList() {
        return this.controller.getRolesList();
    }

    public void showUserRoles(List<UserRoleDTO> rolesList) {
        for (int i = 1; i < rolesList.size(); i++) {
            System.out.println("Opção - " + i + ":\n" + rolesList.get(i).getId());
        }
    }

    /**
     * This function returns the user role that the user chose from the list of roles.
     *
     * @param rolesList List of UserRoleDTO objects
     * @param opcao     the option chosen by the user
     * @return The role chosen by the user.
     */
    public UserRoleDTO choseUserRole(List<UserRoleDTO> rolesList, int opcao) {
        return rolesList.get(opcao);
    }


    @Override
    public void run() {

        // Getting the list of roles from the controller.
        List<UserRoleDTO> rolesList = getRolesList();
        // Showing the list of roles.
        showUserRoles(rolesList);

        // Declaring a variable called opcao.
        int opcao;
        // Asking the user to choose an option from the list of roles.
        do {
            opcao = readIntegerFromConsole("Identify the list of employees that you want:");

        } while (opcao < 1 || opcao > rolesList.size());

        // Getting the role that the user chose from the list of roles.
        UserRoleDTO userRole = choseUserRole(rolesList, opcao);

        // Getting the list of employees from the controller.
        List<Employee> employeeList = controller.getEmployeesList(userRole);

        // Printing the list of employees.
        for (Employee ix : employeeList) {
            System.out.println(ix);
        }
    }
}
