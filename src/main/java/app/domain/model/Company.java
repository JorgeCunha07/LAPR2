package app.domain.model;

import app.domain.model.Store.*;
import app.domain.model.dto.EmployeeDTO;
import app.domain.shared.UtilsCheck;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.prefs.InvalidPreferencesFormatException;

import static app.domain.shared.Constants.TYPE_WITHOUT_HEADER;
import static app.domain.shared.Constants.TYPE_WITH_HEADER;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company implements Serializable {

    // A constant that defines the number of characters of the password.
    // Creating a new ArrayList of Vaccine objects.
    public List<Vaccine> listOfVaccines = new ArrayList<>();
    // A private variable of the class Company.
    private String designation;
    // Creating a new object of the class AuthFacade.
    private AuthFacade authFacade;
    // Creating a new object of the class EmployeeStore.
    private EmployeeStore employeeStore;
    private SNSUserStore snsUserStore;
    private VaccineTypeStore vaccinetypes;

    private VaccineType vaccinetype;
    private VaccinationCenterStore vaccinationCenterStore;
    private ScheduledVaccineStore scheduledVaccineStore;

    private EntryRecordStore entryRecordStore;

    private LeavingRecordStore leavingRecordStore;

    private UtilsCheck utilsCheck;

    private LegacyDataStore legacyDataStore;

    //Jorge Cunha

    // The constructor of the class Company.
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.employeeStore = new EmployeeStore(authFacade);
        this.vaccinetypes = new VaccineTypeStore();
        this.snsUserStore = new SNSUserStore(authFacade);
        this.vaccinationCenterStore = new VaccinationCenterStore();
        this.scheduledVaccineStore = new ScheduledVaccineStore();
        this.entryRecordStore = new EntryRecordStore();
        this.leavingRecordStore = new LeavingRecordStore();
        this.utilsCheck = new UtilsCheck(authFacade,employeeStore,snsUserStore,vaccinationCenterStore,scheduledVaccineStore,entryRecordStore,leavingRecordStore);
        this.legacyDataStore = new LegacyDataStore();

    }


    /**
     * It generates a random password of length 7, containing 2 lowercase letters, 3 uppercase letters, and 2 digits
     *
     * @return A random password
     */
    public static String generatePassword() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

        String alphabetBIG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String numbersDigits = "1234567890";
        int posicao;

        StringBuilder passWord = new StringBuilder();
        Random shuffle = new Random();

        for (int i = 0; i < 2; i++) {
            posicao = shuffle.nextInt(alphaNumeric.length());
            passWord.append(alphaNumeric.charAt(posicao));
        }
        for (int i = 0; i < 3; i++) {
            posicao = shuffle.nextInt(alphabetBIG.length());

            passWord.append(alphabetBIG.charAt(posicao));
        }

        for (int i = 0; i < 2; i++) {
            posicao = shuffle.nextInt(numbersDigits.length());
            passWord.append(numbersDigits.charAt(posicao));
        }

        return passWord.toString();
    }

    /**
     * Get the user roles from the facade.
     *
     * @return A list of UserRoleDTO objects.
     */

    public List<UserRoleDTO> getUserRoles() {
        return getAuthFacade().getUserRoles();
    }

    /**
     * This function returns the employeeStore variable.
     *
     * @return The employeeStore object.
     */


    public EmployeeStore getEmployeeStore() {
        return employeeStore;
    }

    /**
     * This function returns the designation of the employee
     *
     * @return The designation of the employee.
     */

    public String getDesignation() {
        return designation;
    }

    /**
     * > This function returns the AuthFacade object
     *
     * @return The AuthFacade object.
     */


    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * Create an employee from the given employee DTO and return it.
     *
     * @param employeeDTO This is the object that is passed in from the client.
     * @return Employee
     */
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        return employeeStore.createEmployee(employeeDTO);
    }

    /**
     * This function saves an employee to the employee store.
     *
     * @param employee The employee object to be saved.
     * @return A boolean value.
     */
    public boolean saveEmployee(Employee employee) {
        return employeeStore.saveEmployee(employee);
    }

    /**
     * It adds a user with a role
     *
     * @param employeeDTO This is the employee object that you want to add to the database.
     * @param pass        The password of the user
     * @param userRoleDTO This is the role of the user.
     * @return boolean
     */
    public boolean addUserWithRole(EmployeeDTO employeeDTO, String pass, UserRoleDTO userRoleDTO) {

        return authFacade.addUserWithRole(employeeDTO.getName(), employeeDTO.getEmail().getEmail(), pass, userRoleDTO.getId());
    }

    /**
     * > This function returns an Optional of UserDTO, which is the result of calling the getUser function of the
     * authFacade, which takes an email as a parameter
     *
     * @param employeeDTO This is the object that is passed to the method.
     * @return Optional<UserDTO>
     */
    public Optional<UserDTO> getUser(EmployeeDTO employeeDTO) {

        return authFacade.getUser(employeeDTO.getEmail().getEmail());
    }

    public UtilsCheck getUtilsCheck() {
        return utilsCheck;
    }

    /**
     * > This function returns a list of employees based on the user role
     *
     * @param userRole This is a DTO object that contains the user's role and the user's id.
     * @return A list of employees
     */
    public List<Employee> getEmployeesList(UserRoleDTO userRole) {
        return employeeStore.getEmployeesList(userRole);
    }
    //US14 Jorge Cunha

    /**
     * > This function checks if the Citizen card number is already in the employee list
     *
     * @param ccNumber The Citizen card number to be checked.
     * @return A boolean value.
     */
    public boolean checkCCNumberEmployeeList(String ccNumber) {
        return employeeStore.checkCCNumberEmployeeList(ccNumber);
    }


    /**
     * > This function adds a user to the database with a random password and a role
     *
     * @param aux     The user object that contains the user's name and email address.
     * @param roleDto This is a DTO that contains the role id and the role name.
     * @return A boolean value.
     */
    public boolean addUserWithRole(SNSUser aux, UserRoleDTO roleDto, String pass) {
        return authFacade.addUserWithRole(aux.getName(), aux.getEmail(), pass, roleDto.getId());
    }


    //Joao Gil

    /**
     * This function creates a new vaccine type in the database
     *
     * @param vaccinetype The VaccineType object that you want to create.
     * @return The method is returning a VaccineType object.
     */
    public VaccineType createVaccineType(VaccineType vaccinetype) {
        return vaccinetypes.createVaccineType(vaccinetype);
    }

    /**
     * It saves a vaccine type.
     *
     * @param vaccinetype The VaccineType object to be saved.
     * @return A boolean value.
     */
    public boolean saveVaccineType(VaccineType vaccinetype) {
        return vaccinetypes.saveVaccineType(vaccinetype);
    }

    /**
     * This function returns a list of all the vaccine types.
     *
     * @return A list of vaccine types.
     */
    public List<VaccineType> getVaccineTypes() {
        return VaccineTypeStore.vaccinetypes;
    }


    //US14 Jorge Cunha

    /**
     * Check if a file exists at the given location.
     *
     * @param location The location of the file you want to check.
     * @return A boolean value.
     */
    public boolean checkLocation(String location) throws IOException {
        Path path = Paths.get(location);

        return Files.exists(path);
    }

    /**
     * It reads the first line of the file and counts the number of commas and semicolons. If the number of commas is 7, it
     * returns a constant value of 1. If the number of semicolons is 7, it returns a constant value of 2. If the number of
     * commas and semicolons is not 7, it throws an exception
     *
     * @param file The file to be read.
     * @return The method is returning the type of CSV file.
     */
    public int VerifyTypeofCSV(BufferedReader file) throws IOException, InvalidPreferencesFormatException {
        String line = file.readLine();
        int totalSemiColon = 0;
        int totalComma = 0;

        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ';')
                    totalSemiColon++;
                else if (line.charAt(i) == ',')
                    totalComma++;
            }

            if ((totalSemiColon > 0 && totalComma > 0) || (totalSemiColon != 7 && totalComma != 7))
                throw new InvalidPreferencesFormatException("Invalid file structure. Check your file and try again.");

            line = file.readLine();

            if (line != null) {
                totalSemiColon = 0;
                totalComma = 0;
            }
        }

        if (totalSemiColon == 7)
            return TYPE_WITH_HEADER;
        else if (totalComma == 7)
            return TYPE_WITHOUT_HEADER;

        throw new InvalidPreferencesFormatException("Invalid file structure. Check your file and try again.");
    }


    /**
     * > This function returns the SNSUserStore object
     *
     * @return The snsUserStore object.
     */
    public SNSUserStore getSnsUserStore() {
        return snsUserStore;
    }

    /**
     * > This function saves a SNSUser object to the database
     *
     * @param snsUser The SNSUser object that you want to save.
     * @return A boolean value.
     */
    public boolean saveSNSUser(SNSUser snsUser) {

        return snsUserStore.saveSNSUser(snsUser);
    }

    /**
     * This function adds a user to the database with a role of "user" and returns true if the user was added successfully
     *
     * @param name             The name of the user
     * @param email            The email address of the user.
     * @param generatePassword The password to be generated.
     * @param snsUser          The user's SNS ID.
     * @return A boolean value.
     */
    // Pedro Conceiçao
    public boolean addUserWithRole(String name, String email, String generatePassword, String snsUser) {
        return authFacade.addUserWithRole(name, email, generatePassword, snsUser);
    }

    /**
     * This function returns the list of vaccines
     *
     * @return The list of vaccines.
     */
    public List<Vaccine> getListOfVaccines() {
        return listOfVaccines;
    }

    public VaccinationCenterStore getVaccinationCenterStore() {
        return vaccinationCenterStore;
    }

    /**
     * This function is used to save a vaccination center to the database
     *
     * @param vaccinationCenter The vaccination center object that you want to save.
     * @return A boolean value.
     */
    public boolean SaveVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return vaccinationCenterStore.saveVaccinationCenter(vaccinationCenter);
    }

    /**
     * This function removes a vaccination center from the vaccination center store
     *
     * @param vaccinationCenter The vaccination center to be removed.
     * @return A boolean value.
     */
    public boolean removeVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return vaccinationCenterStore.deleteVaccinationCenter(vaccinationCenter);
    }

    /**
     * > This function returns the ScheduledVaccineStore object
     *
     * @return The scheduledVaccineStore is being returned.
     */
    public ScheduledVaccineStore getScheduledVaccineStore() {
        return scheduledVaccineStore;
    }

    /**
     * This function saves a scheduled vaccine to the database
     *
     * @param scheduledVaccine This is the object that contains the information about the scheduled vaccine.
     * @return A boolean value.
     */
    public boolean SaveSchedule(ScheduledVaccine scheduledVaccine) {
        return scheduledVaccineStore.addToList(scheduledVaccine);
    }

    //us16


    /**
     * This function returns the entryRecordStore.
     *
     * @return The entryRecordStore object.
     */
    public EntryRecordStore getEntryRecordStore() {
        return entryRecordStore;
    }

    /**
     * This function returns the leavingRecordStore variable.
     *
     * @return The leavingRecordStore object.
     */
    public LeavingRecordStore getLeavingRecordStore() {
        return leavingRecordStore;
    }

    public LegacyDataStore getLegacyDataStore(){return  legacyDataStore; }
}
