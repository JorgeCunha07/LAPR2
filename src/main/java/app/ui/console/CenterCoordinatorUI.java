package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable {

    public CenterCoordinatorUI() {
    }

    /**
     * The function shows the menu options to the user and runs the corresponding function when the user selects an option
     */
    /**
     * It creates a list of menu items, each of which has a name and a UI object. It then displays the list of menu items
     * and asks the user to select one. If the user selects a valid menu item, it runs the UI object associated with that
     * menu item
     */
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        //options.add(new MenuItem("Check the Vaccination Statistics", new VaccinationStatisticsUI()));
        options.add(new MenuItem("Analysis of the performance of the center", new AnalyseCenterPerformanceUI()));
        options.add(new MenuItem("Import data from a legacy system", new LegacyDataUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nCenter Coordinator Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();


            }
        }
        while (option != -1);
    }

}
