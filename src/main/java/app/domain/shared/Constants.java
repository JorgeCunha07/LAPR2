package app.domain.shared;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

/**
 * It's a class that contains all the constants used in the project
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_CENTER_COORDINATOR = "CENTER COORDINATOR";
    public static final String ROLE_SNS_USER = "SNS USER";
    public static final int PHONE_NUMBER_DIGITS_LIMIT = 9;
    public static final int PHONE_NUMBER_FIRST_DIGIT_PT = 9;
    public static final int PHONE_NUMBER_SECOND_DIGIT_VERIFICATION1 = 1;
    public static final int PHONE_NUMBER_SECOND_DIGIT_VERIFICATION2 = 2;
    public static final int PHONE_NUMBER_SECOND_DIGIT_VERIFICATION3 = 3;
    public static final int PHONE_NUMBER_SECOND_DIGIT_VERIFICATION4 = 6;
    public static final int PHONE_NUMBER_SECOND_DIGIT_VERIFICATION5 = 5;

    public static final int NUMBER_OF_CITIZEN_CARD_DIGITS = 12;

    public static final int FIRST_SECOND_DIGIT_CC = 2;

    public static final int MAX_LENGTH_OF_SNS_USER_NUMBER = 9;

    public static final int LOT_NUMBER_LENGTH = 8;

    public static final String LANGUAGE_EN = "0";
    public static final String LANGUAGE_PT = "1";

    public static final int TYPE_WITH_HEADER = 1;
    public static final int TYPE_WITHOUT_HEADER = 0;
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final int FIRST_DOSE = 0;
    public static final int NOT_FIRST_DOSE = -1;

    public static final long DAY_TO_MS =86400000;
    public static final int HOUR_TO_MS = 3600000;
    public static final int MINUTE_TO_MS = 60000;

}
