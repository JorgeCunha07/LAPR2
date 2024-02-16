package app.ui.console.utils;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    /**
     * It reads a line of text from the console and returns it as a string
     *
     * @param prompt The prompt to display to the user.
     * @return A string
     */
    static public String readLineFromConsole(String prompt)
    {
        try
        {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * > Read an integer from the console, and return it
     *
     * @param prompt The prompt to display to the user.
     * @return An integer
     */
    static public int readIntegerFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * It reads a double from the console, and if the user enters something that's not a double, it keeps asking until the
     * user enters a double
     *
     * @param prompt The prompt to display to the user.
     * @return A double value
     */
    static public double readDoubleFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * It reads a date from the console, and if the user enters an invalid date, it will keep asking the user to enter a
     * valid date
     *
     * @param prompt The prompt to display to the user.
     * @return A date object
     */
    static public Date readDateFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * It reads a line from the console and returns true if the line is "s" or "S" and false otherwise
     *
     * @param message The message to be displayed to the user.
     * @return A boolean value.
     */
    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    /**
     * > It shows a list of objects and returns the one selected by the user
     *
     * @param list The list of objects to be displayed.
     * @param header The header to be displayed at the top of the list.
     * @return The object that was selected from the list.
     */
    static public Object showAndSelectOne(List list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }
    /**
     * > It shows a list of items and then asks the user to select one of them
     *
     * @param list The list of items to be displayed.
     * @param header The header of the list.
     * @return The index of the selected item.
     */
    static public int showAndSelectIndex(List list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }
    /**
     * "Show a list of objects to the user, and return the index of the object they selected."
     *
     * The function takes two parameters:
     *
     * * A list of objects to show to the user.
     * * A header to show above the list
     *
     * @param list The list of objects to be displayed.
     * @param header The header to display before the list.
     */
    static public void showList(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    /**
     * "This function displays a list of options to the user, and returns the selected option."
     *
     * The function takes a list of options as a parameter, and returns the selected option
     *
     * @param list The list of objects to be displayed.
     * @return The object that the user selected.
     */
    static public Object selectsObject(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    /**
     * "Reads a number from the console and returns the index of the corresponding item in the list."
     *
     * The function is a bit more complicated than that, but that's the gist of it
     *
     * @param list The list of options to display
     * @return The index of the selected item.
     */
    static public int selectsIndex(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * It asks the user if he wants to confirm the data he has just inserted
     *
     * @return A boolean value.
     */
    public static boolean confirmCreation() {
        System.out.println("Do you confirm this data?");
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        Scanner sc = new Scanner(System.in);
        System.out.printf("%nType your option: ");
        int check = 0;
        int option = 0;
        do {
            try {
                option = sc.nextInt();
                sc.nextLine();
                check = 1;
            } catch (InputMismatchException e) {
                System.out.println("Insert a valid option.");
                sc.nextLine();
            }
        } while (check == 0);

        return option == 1;
    }

    static public void showCenterUserVaccine(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
    }

    static public int showSelectCenterUserVaccine(List list, String header)
    {
        return showAndSelectIndex(list,header);
    }

    static public int selectIndexCenterUserVaccine(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value <= 0 || value > list.size());

        return value - 1;
    }

    static public Alert createAlert(Alert.AlertType alertType, String title, String header, String message){
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }
}
