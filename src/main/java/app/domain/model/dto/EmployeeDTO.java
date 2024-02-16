package app.domain.model.dto;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

    // A constant that is used to initialize the id of the employee.
    private static final int ID_BY_OMISSION = 0;
    // A constant that is used to initialize the name of the employee.
    private static final String NAME_BY_OMISSION = "Name OMITTED";
    // A constant that is used to initialize the address of the employee.
    private static final String ADDRESS_BY_OMISSION = "Address OMITTED";
    // A constant that is used to initialize the phone number of the employee.
    private static final int PHONENUMBER_BY_OMISSION = 0;
    // A constant that is used to initialize the citizen card number of the employee.
    private static final String CCNUMBER_BY_OMISSION = "Citizen Card Number OMITTED";
    // Creating a class called Client.
    // Declaring a variable called id of type int.
    private int id;
    // Declaring a variable called name of type String.
    private String name;
    // Declaring a variable called address of type String.
    private String address;
    // Declaring a variable called phoneNumber of type int.
    private int phoneNumber;
    // Declaring a variable called email of type Email.
    private Email email;
    // A variable that is used to store the citizen card number of the employee.
    private String citizenCardNumber;

    /**
     * This function is a constructor that creates an employee with the default values
     */
    public void Employee() {

        this.name = NAME_BY_OMISSION;
        this.address = ADDRESS_BY_OMISSION;
        this.phoneNumber = PHONENUMBER_BY_OMISSION;
        this.email = null;
        this.citizenCardNumber = CCNUMBER_BY_OMISSION;

    }

    /**
     * The function Employee is a constructor that takes in a String name, a String address, an int phoneNumber, an
     * Email email, and a String citizenCardNumber and assigns the value of the parameter to the instance variable
     *
     * @param name              The name of the employee.
     * @param address           The address of the employee.
     * @param phoneNumber       The phone number of the employee.
     * @param email             Email
     * @param citizenCardNumber The citizen card number of the employee.
     */
    public void Employee(String name, String address, int phoneNumber, Email email, String citizenCardNumber) {
        // Assigning the value of the parameter to the instance variable.
        this.id = ID_BY_OMISSION;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
    }


    /**
     * This function returns the id of the current object
     *
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     *
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the name of the person
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the name of the person to the name passed in as a parameter
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function returns the address of the person
     *
     * @return The address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * This function sets the address of the person
     *
     * @param address The address of the node to connect to.
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
     * This function sets the phone number of the person to the phone number passed in as a parameter
     *
     * @param phoneNumber The phone number of the contact.
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * > This function returns the email address of the user
     *
     * @return The email address.
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
     * > This function returns the citizen card number
     *
     * @return The citizenCardNumber
     */
    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * This function sets the citizen card number of the person
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
                "\n citizenCardNumber: " + citizenCardNumber + "\n" ;
    }
}


