package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable{
    /**
     * Instantiates a new Nurse ui.
     */
    public NurseUI()
    {
    }

    /**
     * It shows a menu with two options, and when the user selects one of them, it runs the corresponding UI
     */
    /**
     * It shows a menu with three options, and when the user selects one of them, it runs the corresponding UI
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Consult waiting room of a vaccination center ", new ConsultWaitingRoomUI()));
        options.add(new MenuItem("Export the adverse reactions of an SNS User", new AdverseReactionsUI()));
        options.add(new MenuItem("Record the administration of a vaccine to a SNS User", new VaccineAdministrationUI()));


        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\n Nurse Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );
    }
}

