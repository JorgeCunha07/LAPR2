package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    /**
     * This function prints the names of the students that developed the application
     */
    public void run()
    {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t Jorge Cunha - 1200618@isep.ipp.pt \n");
        System.out.printf("\t Antonio Martingo - 1201551@isep.ipp.pt \n");
        System.out.printf("\t Pedro Conceição - 1211018@isep.ipp.pt \n");
        System.out.println("\n");

    }
}
