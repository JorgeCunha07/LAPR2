package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Receptionist ui.
 */
class ReceptionistUI implements Runnable {


    /**
     * Instantiates a new Receptionist ui.
     */
    public ReceptionistUI()
    {
    }

    /**
     * It creates a list of menu items, each of which has a name and a UI object, and then it shows the list of menu items
     * and runs the selected one
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a new SNS User", new RegisterSNSUserUI()));
        options.add(new MenuItem("Register a SNS User arrival", new RegisterSNSUserArrivalUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );
    }
}
