package app.ui.console;

import app.controller.RegisterEmployeeController;
import app.domain.model.Employee;
import app.domain.model.dto.EmployeeDTO;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.store.UserRoleStore;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static app.ui.console.utils.Utils.readIntegerFromConsole;

public class RegisterEmployeeUI implements Runnable {

    // Declaring the variables that will be used in the class.
    private static RegisterEmployeeUI instance;
    // Creating a new Scanner object that will read from the console.
    Scanner read = new Scanner(System.in);
    private RegisterEmployeeController controller;
    private EmployeeDTO employeeCreation;
    private Employee employee;
    private UserRoleStore userRoleStore;


    // The constructor of the class.
    public RegisterEmployeeUI() {
        controller = new RegisterEmployeeController();
        userRoleStore = new UserRoleStore();
    }

    /**
     * If the instance variable is null, create a new instance of the class and assign it to the instance variable. If the
     * instance variable is not null, return the instance variable
     *
     * @return The instance of the RegisterEmployeeUI class.
     */
    public static RegisterEmployeeUI getInstance() {
        if (instance == null) {
            instance = new RegisterEmployeeUI();
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

    /**
     * It prints the id of each role in the list
     *
     * @param rolesList List of UserRoleDTO objects
     */
    public void showUserRoles(List<UserRoleDTO> rolesList) {
        for (int i = 1; i < rolesList.size(); i++) {
            System.out.println("Opção - " + i + ":\n" + rolesList.get(i).getId());
        }
    }

    /**
     * It returns the role chosen by the user
     *
     * @param rolesList List of roles that the user has.
     * @param opcao     the option chosen by the user
     * @return The role chosen by the user.
     */
    public UserRoleDTO chooseUserRole(List<UserRoleDTO> rolesList, int opcao) {

        return rolesList.get(opcao);
    }


    @Override
    public void run() {
        // Declaring three boolean variables.
        boolean response, create, checks,create2 = false;
        // A variable that is used to store the user's choice.
        int agree;
        String password = null;

        // Creating a new employee.
        do {
            // Creating a new employee and printing a message.
            employeeCreation = new EmployeeDTO();
            System.out.println("Create a new employee:");
            do {
                // Reading the name of the employee from the console.
                String name = Utils.readLineFromConsole("Insert Name of the Employee");
                // Checking if the name is valid.
                try {
                    employeeCreation.setName(name);
                    checks = Employee.checkName(name);
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage() + "\n Name again the Employee:");
                    checks = false;
                }
            } while (!checks);

            // Checking if the address is valid.
            do {
                // Reading the address of the employee from the console.
                String address = Utils.readLineFromConsole("Insert address of the Employee");
                try {
                    employeeCreation.setAddress(address);
                    checks = Employee.checkAddress(address);
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage() + "\n Repeat address of the Employee:");
                    checks = false;
                }
            } while (!checks);

            // Checking if the phone number is valid.
            do {
                // Reading the phone number of the employee from the console.
                int phoneNumber = readIntegerFromConsole("Insert Phone Number of the Employee");
                try {
                    employeeCreation.setPhoneNumber(phoneNumber);
                    checks = Employee.checkPhoneNumber(phoneNumber);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + "\n Repeat Phone Number of the Employee:");
                    checks = false;
                }
            } while (!checks);

            do {
                // Reading the email from the console.
                String email = Utils.readLineFromConsole("Insert email of the Employee:");
                try {
                    Email mail = new Email(email);
                    employeeCreation.setEmail(mail);
                    checks = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n Insert a valid email adress.");
                    checks = false;
                }
            } while (!checks);


            // Reading the Citizen Card Number of the Employee from the console.
            do {
                // Reading the Citizen Card Number of the Employee from the console.
                String ccNumber = Utils.readLineFromConsole("Insert Citizen Card Number of the Employee");
                try {
                    employeeCreation.setCitizenCardNumber(ccNumber);
                    checks = Employee.checkCitizenCardNumber(ccNumber) && controller.checkCCNumberEmployeeList(ccNumber);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + "\n Repeat Citizen Card Number of the Employee:");
                    checks = false;
                }
            } while (!checks);

            //create employee
            employee = controller.createEmployee(employeeCreation);

            // Getting the list of roles from the database.
            List<UserRoleDTO> rolesList = getRolesList();

            // It prints the id of each role in the list
            showUserRoles(rolesList);

            // Asking the user to chose a role for the employee.
            int opcao;
            do {
                opcao = readIntegerFromConsole("Chose the Role of the Employee:");

            } while (opcao < 1 || opcao > rolesList.size());

            UserRoleDTO userRole = chooseUserRole(rolesList, opcao);

            //Print the Information of the Employee
            System.out.println(employee);

            //Print The Information of the User Role
            System.out.println(userRole.getId());

            // Asking the user if he agrees with the information that was inserted.
            System.out.println("Do you agree with the information:");
            System.out.println("1 - Agree");
            System.out.println("2 - Disagree");
            agree = read.nextInt();

            if (agree == 1) {
                response = false;
                //save employee
                create = (controller.saveEmployee(employee));

                        try{
                            password = controller.addUserWithRole(employeeCreation, userRole);
                            create2 = true;
                        }catch (Exception e){
                            create2 = false;
                        }

                //Print the User
                System.out.println();
                System.out.println("Email: " + employee.getEmail());
                System.out.println("Password: " + password);

            } else {
                response = true;
                create = true;
                System.out.println("Exited.");
            }


        } while (response || !create || !create2);
    }


}
