package app.domain.model;

import app.domain.model.Store.EmployeeStore;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

public class Employee {

    // A constant.
    private static final int ID_BY_OMISSION = 0;
    // A constant.
    private static final String NAME_BY_OMISSION = "Name OMITTED";
    // A constant.
    private static final String ADDRESS_BY_OMISSION = "Address OMITTED";
    // A constant.
    private static final int PHONENUMBER_BY_OMISSION = 0;
    // A constant.
    private static final Email EMAIL_BY_OMISSION = null;
    // A constant.
    private static final String CCNUMBER_BY_OMISSION = "Citizen Card Number OMITTED";
    private int id;
    private String name;
    private String address;
    private int phoneNumber;
    // Declaring a variable.
    private Email email;
    private String citizenCardNumber;
    // A variable.
    private EmployeeStore employeeStore;

    // A constructor.
    public Employee() {
        this.id = ID_BY_OMISSION;
        this.name = NAME_BY_OMISSION;
        this.address = ADDRESS_BY_OMISSION;
        this.phoneNumber = PHONENUMBER_BY_OMISSION;
        this.email = EMAIL_BY_OMISSION;
        this.citizenCardNumber = CCNUMBER_BY_OMISSION;
    }

    // This is a constructor.
    public Employee(int id, String name, String address, int phoneNumber, Email email, String citizenCardNumber) {
        // Assigning the value of the parameter to the instance variable.
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * This function checks if the id of the employee is already in the list
     *
     * @param id The id of the employee to be checked.
     * @return The method is returning a boolean value.
     */
    public static boolean checkId(int id) {
        for (Employee employee : EmployeeStore.employeeList) {
            if (employee.getId() == id) {
                return false;
            }
        }
        return true;
    }

    /**
     * If the name is not blank and is less than or equal to 35 characters, return true.
     *
     * @param name The name of the player.
     * @return A boolean value.
     */
    public static boolean checkName(String name) {
        int nameChars = 35;
        return !StringUtils.isBlank(name) && name.length() <= nameChars;
    }

    /**
     * Check if the address is not null, not blank, and not longer than 30 characters.
     *
     * @param address The address to check.
     * @return A boolean value.
     */
    public static boolean checkAddress(String address) {
        int ADDRESS_MAX_CHARS = 30;
        return address != null && !StringUtils.isBlank(address) && address.length() <= ADDRESS_MAX_CHARS;
    }

    /**
     * It checks if the phone number is valid.
     *
     * @param phoneNumber the phone number to check
     * @return boolean
     */
    public static boolean checkPhoneNumber(int phoneNumber) {
        int digitFirst = 100000000, digitSecond = 10000000;

        if ((phoneNumber / digitFirst) == 9) {
            return phoneNumber / digitSecond == 91 || phoneNumber / digitSecond == 92 || phoneNumber / digitSecond == 93 || phoneNumber / digitSecond == 96 || phoneNumber / digitSecond == 21 || phoneNumber / digitSecond == 22;
        }
        return false;
    }

    /**
     * If the number is not divisible by 10000000, then it's not a valid citizen card number.
     *
     * @param ccNumber The citizen card number to be checked.
     * @return The return value is a boolean.
     */
    public static boolean checkCitizenCardNumber(String ccNumber) {
        boolean flag = false;
        int ccNumberLength = 10000000;
        if (Integer.parseInt(ccNumber) / ccNumberLength > 0 && Integer.parseInt(ccNumber) / ccNumberLength < 10) {
            flag = true;
        }
        return flag;
    }

    public int getId() {
        return id;
        /**
         * This function returns the id of the object
         *
         * @return The id of the object.
         */
    }

    /**
     * This function sets the id of the object to the value of the parameter id.
     *
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function returns the address of the person.
     *
     * @return The address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * This function sets the address of the object to the address passed in as a parameter.
     *
     * @param address The address of the location you want to search for.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This function returns the phone number of the person
     *
     * @return The phone number of the person.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This function sets the phone number of the person to the phone number passed in as a parameter.
     *
     * @param phoneNumber The phone number of the user.
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This function returns the email of the user
     *
     * @return The email address of the user.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * This function sets the email of the user
     *
     * @param email The email address of the user.
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * This function returns the citizen card number.
     *
     * @return The citizenCardNumber
     */
    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * This function sets the citizen card number.
     *
     * @param citizenCardNumber The citizen card number of the citizen.
     */
    public void setCitizenCardNumber(String citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "Employee:" +
                "\n Id: " + id + "\n" +
                "\n Name: " + name + "\n" +
                "\n Adress: " + address + "\n" +
                "\n PhoneNumber: " + phoneNumber + "\n" +
                "\n Email: " + email + "\n" +
                "\n CitizenCardNumber: " + citizenCardNumber + "\n";
    }
}
