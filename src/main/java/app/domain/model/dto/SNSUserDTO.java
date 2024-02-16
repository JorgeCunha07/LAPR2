package app.domain.model.dto;

import java.io.Serializable;

public class SNSUserDTO implements Serializable {

    private final String name;
    private final int snsNumber;
    private final String email;
    private final int smsPermission;

    /**
     * Constructor of the class
     *
     * @param name Name of the SNS user
     * @param snsNumber SNS number of the user
     * @param email E-mail of the SNS user
     * @param smsPermission Permission to receive SMS from the company
     */
    public SNSUserDTO(String name, int snsNumber, String email, int smsPermission) {
        this.name = name;
        this.snsNumber = snsNumber;
        this.email = email;
        this.smsPermission = smsPermission;
    }

    /**
     * Method to get the SNS user name
     *
     * @return SNS user name
     */

    public String getName() {
        return name;
    }

    /**
     * Method to get the SNS user number
     *
     * @return SNS user number
     */

    public int getSnsNumber() {
        return snsNumber;
    }

    /**
     * Method to get the SNS user e-mail
     *
     * @return SNS user e-mail
     */


    public String getEmail() {
        return email;
    }

    /**
     * Method to get the SNS user permission of SMS
     *
     * @return SNS user permission of SMS
     */

    public int getSmsPermission() {
        return smsPermission;
    }


}
