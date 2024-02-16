package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SNSUserUI implements Runnable {

    public SNSUserUI() {
    }

    /**
     * The function creates a list of menu items
     */

    /**
     * The function runs a do while loop that will run until the user selects the option -1
     */
    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Schedule a vaccine", new ScheduleVaccineUI()));

        // A do while loop that will run until the user selects the option -1.
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nSNSUser Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }

}
