package app.domain.model;

import app.controller.App;
import org.apache.commons.lang3.StringUtils;

import static app.domain.model.Store.VaccineTypeStore.vaccinetypes;

public class VaccineType {
    // A constant that is used to set the default value of the variable code.
    private static final String CODE_BY_OMISSION = "0000a";
    // A constant that is used to set the default value of the variable description.
    private static final String DESCRIPTION_BY_OMISSION = "Description ommited";
    // A constant that is used to set the default value of the variable vaccinetechnology.
    private static final String TECHNOLOGY_BY_OMISSION = " Live-attenuated vaccine";
    // An array of strings that contains the different technologies that can be used to create a vaccine.
    // An array of strings that contains the different technologies that can be used to create a vaccine.
    public String[] vaccinetechnologies =
           {" Live-attenuated vaccine ",
            " Inactivated vaccine ",
            " Subunit vaccine",
            " Toxoid vaccine",
            " Viral vector vaccine",
            " Messenger RNA (mRNA) vaccine"};
    // Declaring a variable called code of type String and making it private.
    private String code;
    // Declaring a variable called description of type String and making it private.
    private String description;
    // Declaring a variable called vaccinetechnology of type String and making it private.
    private String vaccinetechnology;

    /**
     * @param code              A code with five alphanumeric numbers that identify the vaccine type
     * @param description       A short description of the vaccine type
     * @param vaccinetechnology The technology of the vaccine type
     */


    // This is a constructor that is used to create a new object of the class VaccineType.
    public VaccineType(String code, String description, String vaccinetechnology) {
        this.code = code;
        this.description = description;
        this.vaccinetechnology = vaccinetechnology;
    }

    // This is a constructor that is used to create a new object of the class VaccineType.
    public VaccineType() {
        code = CODE_BY_OMISSION;
        description = DESCRIPTION_BY_OMISSION;
        vaccinetechnology = TECHNOLOGY_BY_OMISSION;
    }

    /**
     * This function returns the code of the country
     *
     * @return The code of the course.
     */
    public String getCode() {
        return code;
    }

    /**
     * This function sets the code of the object to the code passed in as a parameter
     *
     * @param code The code that you received from the user.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * > This function returns the description of the object
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This function sets the description of the object
     *
     * @param description The description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This function returns the value of the vaccinetechnology variable
     * 
     * @return The vaccinetechnology is being returned.
     */
    public String getVaccinetechnology() {
        return vaccinetechnology;
    }

    /**
     * This function sets the value of the vaccinetechnology variable
     *
     * @param vaccinetechnology The technology used to create the vaccine.
     */
    public void setVaccinetechnology(String vaccinetechnology) {
        this.vaccinetechnology = vaccinetechnology;
    }

    @Override
    // A method that returns a string with the information of the vaccine type.
    public String toString() {
        return "VaccineType: " +  "\n" +
                "code= " + code + "\n" +
                "Description= " + description + "\n" +
                "Vaccine technology='" + vaccinetechnology + "\n";
    }


    /**
     * Checks if the code has 5 alphanumeric charachters and if it is different from the other vaccine types codes from the company
     *
     * @return true in case all conditions above are valid
     */

    public boolean validateCode() {
        if (code.length()!=5 || StringUtils.isAlphanumeric(code))
            return false;

        Company c = App.getInstance().getCompany();
        for (VaccineType vacctype : vaccinetypes) {
            if (code.equals(vacctype.code)) return false;
        }
        return true;
    }

    /**
     * Checks if the code has 5 alphanumeric characters. Doesn't check for equal codes since this one is to be used when applying a already existent
     * type of vaccine to a new vaccine
     * @return
     */
    public boolean validateCodeForApplying(){
        boolean result = true;
        if(this.code.length() != 5){
            result = false;
            System.out.println("Code length must be 5.");
        }
        for (VaccineType vacctype : vaccinetypes) {
            if (code.equals(vacctype.code)) {
                result = true;
                System.out.println("Vaccine type available");
            }
        }


         return result;
    }
}
