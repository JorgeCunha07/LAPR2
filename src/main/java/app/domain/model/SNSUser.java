package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

/**
 * The type Sns user.
 */
public class SNSUser {

    // Creating a class called SNS User.
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    private String birthDate;
    private String snsUserNumber;
    private String citizenCardNumber;
    private ArrayList<Bulletin> vaccineBulletin;
    /**
     * The enum Gender.
     */
    public enum Gender {
        /**
         * Male Gender.
         */
        Male("Male"),
        /**
         * Female Gender.
         */
        Female("Female"),
        /**
         * Other Gender.
         */
        NA("NA"),
        /**
         * Non specified, since Gender/sex is optional;
         */
        Other("Other"),
        /**
         * Male Gender.
         */
        Masculino("Masculino"),
        /**
         * Female Gender.
         */
        Feminino("Feminino"),
        /**
         * Empty Gender.
         */
        Empty("");

        private final String label;

        // Creating a constructor for the enum Gender.
        Gender(String label) {
            this.label = label;
        }

        /**
         * This function returns the label of the current node
         *
         * @return The label of the enum.
         */
        public String getLabel() {
            return this.label;
        }
    }



    /**
     * Instantiates a new Sns user.
     *
     * @param name              the name
     * @param address           the address
     * @param gender            the gender
     * @param phoneNumber       the phone number
     * @param email             the email
     * @param birthDate         the birthdate
     * @param snsUserNumber     the sns user number
     * @param citizenCardNumber the citizen card number
     */
    public SNSUser(String name, String address, String gender, String phoneNumber, String email, String birthDate, String snsUserNumber, String citizenCardNumber) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.vaccineBulletin = new ArrayList<>();
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
     * This function sets the address of the object to the address passed in as a parameter.
     *
     * @param address The address of the location you want to search for.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This function sets the gender of the SNS User
     *
     * @param gender The gender of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This function sets the phone number of the SNS User.
     *
     * @param phoneNumber The phone number of the SNS User.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This function sets the email of the SNS User.
     *
     * @param email The email address of the SNS User.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This function sets the birthDate variable to the value of the birthDate parameter.
     *
     * @param birthDate The date of birth of theSNS User.
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * This function sets the value of the variable snsUserNumber to the value of the parameter snsUserNumber
     *
     * @param snsUserNumber The SNS User's phone number.
     */
    public void setSnsUserNumber(String snsUserNumber) {
        this.snsUserNumber = snsUserNumber;
    }

    /**
     * This function sets the citizen card number of SNS User.
     *
     * @param citizenCardNumber The citizen card number of the citizen.
     */
    public void setCitizenCardNumber(String citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Address.
     *
     * @return the Address
     */

    public String getAddress() {
        return address;
    }

    /**
     * Gets Gender
     *
     * @return the Gender
     */

    public String getGender() {
        return gender;
    }

    /**
     * Gets BirthDate
     *
     * @return the Birthdate
     */

    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Gets sns user number.
     *
     * @return the sns user number
     */
    public String getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * Gets citizen card number.
     *
     * @return the citizen card number
     */
    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * This function returns the vaccine bulletin
     *
     * @return An ArrayList of Bulletin objects.
     */
    public ArrayList<Bulletin> getVaccineBulletin() {
        return vaccineBulletin;
    }

    /**
     * This function sets the vaccine bulletin
     *
     * @param vaccineBulletin This is the ArrayList that will hold the Bulletin objects.
     */
    public void setVaccineBulletin(ArrayList<Bulletin> vaccineBulletin) {
        this.vaccineBulletin = vaccineBulletin;
    }

    /**
     * This function adds a vaccine administration to the bulletin
     *
     * @param auxBulletin The bulletin to be added to the list.
     * @return Boolean
     */
    public Boolean addVaccineAdministrationToTheBulletin(Bulletin auxBulletin) {
        return vaccineBulletin.add(auxBulletin);
    }



    /**
     * It checks if the parameters are valid
     *
     * @param name The name of the person.
     * @param address The address of the person.
     * @param gender The gender of the person.
     * @param phoneNumber The phone number of the person.
     * @param email The email of the person.
     * @param birthDate The birthdate of the person.
     * @param snsUserNumber The number of the SNS user.
     * @param citizenCardNumber The citizen card number of the person.
     * @return The method is returning a boolean value.
     */
    public static boolean checkSNSUserParam(String name, String address, String gender, String phoneNumber, String email, String birthDate, String snsUserNumber, String citizenCardNumber) {
        boolean check = true;

        // Checking the name of the person to see if it is valid.
        try {
            checkName(name);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // The above code is checking the gender of the person.
        try {
            checkGender(gender);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // Validating the birthdate of a person.
        try {
            checkBirthDate(birthDate);
        } catch (RuntimeException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // Checking the address of the person.
        try {
            checkAddress(address);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // Validating the phone number.
        try {
            checkPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // The above code is checking if the email is valid.
        try {
            Email mail = new Email(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Validating the SNS user number.
        try {
            checkSNSUserNumber(snsUserNumber);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }

        // Validating the citizen card number.
        try {
            checkCitizenCardNumber(citizenCardNumber);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }
        return check;
    }


    /**
     * It checks if the phone number is 9 digits long, if the first digit is 9, and if the second digit is either 2, 3, 4,
     * or 5
     *
     * @param phoneNumber The phone number to be validated.
     * @return A boolean value.
     */
    static public boolean checkPhoneNumber(String phoneNumber) {

        if (phoneNumber.length() == Constants.PHONE_NUMBER_DIGITS_LIMIT && Double.parseDouble(phoneNumber) % 1 == 0) {
            int ch1 = Integer.parseInt(String.valueOf(phoneNumber.charAt(0)));
            if (ch1 != Constants.PHONE_NUMBER_FIRST_DIGIT_PT)
                return false;

            int ch2 = Integer.parseInt(String.valueOf(phoneNumber.charAt(1)));
            if (ch2 != Constants.PHONE_NUMBER_SECOND_DIGIT_VERIFICATION1 && ch2 != Constants.PHONE_NUMBER_SECOND_DIGIT_VERIFICATION2 && ch2 != Constants.PHONE_NUMBER_SECOND_DIGIT_VERIFICATION3 && ch2 != Constants.PHONE_NUMBER_SECOND_DIGIT_VERIFICATION4 && ch2 != Constants.PHONE_NUMBER_SECOND_DIGIT_VERIFICATION5) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * It takes a string as input and returns true if the string is a valid date in the format dd/MM/yyyy
     *
     * @param strBirthDate The date of birth in the format dd/MM/yyyy
     * @return A boolean value.
     */
    public static boolean checkBirthDate(String strBirthDate) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(strBirthDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    /**
     * If the citizen card number is blank, contains non-numeric characters, or is not 8 digits long, throw an exception.
     *
     * @param citizenCardNumber The citizen card number to be checked.
     * @return A boolean value.
     */
    public static boolean checkCitizenCardNumber(String citizenCardNumber) throws IllegalArgumentException
    {
        int citizenCardNumberInt;

        if(citizenCardNumber.equals(""))
            throw new IllegalArgumentException("Citizen card number is blank.");

        for (int i = 0; i < citizenCardNumber.length(); i++) {
            if (!Character.isDigit(citizenCardNumber.charAt(i)))
                throw new IllegalArgumentException("Citizen card can only contain numbers.");
        }

        try {
            citizenCardNumberInt = Integer.parseInt(citizenCardNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Citizen card number must have 8 digits.");
        }

        if (citizenCardNumberInt > 99999999 || citizenCardNumberInt < 10000000)
            throw new IllegalArgumentException("Citizen card number must have 8 digits.");

        return true;
    }

    /**
     * If the name is not blank and the length of the name is less than or equal to 35, return true, otherwise throw an
     * IllegalArgumentException.
     *
     * @param name The name of the user.
     * @return A boolean value.
     */
    public static boolean checkName(String name) {

        if (!StringUtils.isBlank(name) ) {
            return true;
        } else throw new IllegalArgumentException("Name is invalid.");
    }

    /**
     * Check if the address is not null, not blank, and not longer than 30 characters.
     *
     * @param address The address to check.
     * @return A boolean value.
     */
    public static boolean checkAddress(String address) {
        if (address != null && !StringUtils.isBlank(address) ) {
            return true;
        } else throw new IllegalArgumentException("Address is invalid.");
    }

    /**
     * If the gender is valid, return true, otherwise throw an exception.
     *
     * @param gender The gender to check.
     * @return A boolean value.
     */
    public static boolean checkGender(String gender) {
        for (Gender t : Gender.values()) {
            if (t.getLabel().equalsIgnoreCase(gender))
                return true;
        }

        throw new IllegalArgumentException("Gender is invalid.");
    }

    /**
     * It checks if the SNS number is blank, if it contains only numbers, if it has 9 digits and if it's not too big or too
     * small
     *
     * @param snsNumber The SNS number to be validated.
     * @return A boolean value.
     */

    public static boolean checkSNSUserNumber(String snsNumber) throws IllegalArgumentException {
        int snsNumberInt;

        if (snsNumber.equals(""))
            throw new IllegalArgumentException("SNS number is blank.");
        try {
            for (int i = 0; i < snsNumber.length(); i++) {
                if (!Character.isDigit(snsNumber.charAt(i)))
                    throw new IllegalArgumentException("SNS number can only contain numbers.");
            }
            snsNumberInt = Integer.parseInt(snsNumber);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("SNS number must have 9 digits.");
        }
        if (snsNumberInt > 999999999 || snsNumberInt < 100000000)
            throw new IllegalArgumentException("SNS number must have 9 digits.");

        return true;
    }

    /**
     * It checks if the attributes of a person are valid
     *
     * @param lineAttributes The attributes of the line that is being read.
     * @param lineNumber     The line number of the file that is being read.
     * @return The method is returning a boolean value.
     */
    public static boolean checkSNSUsersFileAttributesPerLine(String[] lineAttributes, int lineNumber) {
        boolean check = true;

        // Checking the name of the person to see if it is valid.
        try {
            checkName(lineAttributes[0]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // The above code is checking the gender of the person.
        try {
            checkGender(lineAttributes[1]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // Validating the birthdate of a person.
        try {
            checkBirthDate(lineAttributes[2]);
        } catch (RuntimeException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // Checking the address of the person.
        try {
            checkAddress(lineAttributes[3]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // Validating the phone number.
        try {
            checkPhoneNumber(lineAttributes[4]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // The above code is checking if the email is valid.
        try {
            Email email = new Email(lineAttributes[5]);
        } catch (IllegalArgumentException e) {
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // Validating the SNS user number.
        try {
            checkSNSUserNumber(lineAttributes[6]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }

        // Validating the citizen card number.
        try {
            checkCitizenCardNumber(lineAttributes[7]);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println("Line " + lineNumber + ": " + e.getMessage());
        }
        return check;
    }

    /**
     * If the phone number, email, snsUserNumber or citizenCardNumber of the object passed as a parameter is equal to the
     * phone number, email, snsUserNumber or citizenCardNumber of the object that called the function, then return true
     *
     * @param o The object to be compared.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SNSUser snsUser = (SNSUser) o;
        if (getPhoneNumber().equals(snsUser.getPhoneNumber()))
            return true;
        if (getEmail().equals(snsUser.getEmail()))
            return true;
        if (getSnsUserNumber().equals(snsUser.getSnsUserNumber()))
            return true;
        return (getCitizenCardNumber().equals(snsUser.getCitizenCardNumber()));
    }

    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return "SNSUser Information:" + "\n" +
                "Name= " + name + "\n" +
                "Address= " + address + "\n" +
                "Gender= " + gender + "\n" +
                "Phone Number= " + phoneNumber + "\n" +
                "Email= " + email + "\n" +
                "Birth Date= " + birthDate + "\n" +
                "SNS User Number= " + snsUserNumber + "\n" +
                "Citizen Card Number= " + citizenCardNumber + "\n";
    }


    /**
     * It converts a java.util.Date to a java.time.LocalDate
     *
     * @param dateToConvert The date to convert to a LocalDate
     * @return A LocalDate object
     */
    public static LocalDate convertToLocalDate(Date dateToConvert){
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    /**
     * It takes a date in the format of dd/MM/yyyy and returns the age of the user in years
     *
     * @param birthDate The date of birth of the user.
     * @return The age of the user.
     */
    public static int getSNSUserAge(String birthDate) throws ParseException {
        LocalDate localDate = LocalDate.now();
        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);

        LocalDate localBirthDate = convertToLocalDate(birthdate);

        if(localBirthDate != null){
            return Period.between(localBirthDate, localDate).getYears();
        }
        else{
            return 0;
        }
    }
}


