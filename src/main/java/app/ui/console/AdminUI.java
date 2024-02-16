package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    // A constructor.
    public AdminUI()
    {
    }

    /**
     * The function creates a list of menu items, and then runs a do while loop that will run until the user selects the
     * option -1
     */
    public void run()
    {
        // Creating a list of menu items.
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem( "New Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem( "Get a list of employees", new ListofEmployeesUI()));
        options.add(new MenuItem( "New vaccine type", new SpecifyVaccineTypeUI()));
        options.add(new MenuItem( "Vaccine Options ", new NewVaccineUI()));
        options.add(new MenuItem( "Load a List of SNS Users ",new LoadSNSUserUI()));
        options.add(new MenuItem("Vaccination Centers", new VaccinationCenterUI()));

        // A do while loop that will run until the user selects the option -1.
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();


            }
        }
        while (option != -1 );
    }
}
