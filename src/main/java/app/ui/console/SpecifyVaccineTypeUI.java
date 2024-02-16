package app.ui.console;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.domain.model.Store.VaccineTypeStore;
import app.ui.console.utils.Utils;
import app.controller.AddnewVaccinetypeController;
import java.util.Scanner;

public class SpecifyVaccineTypeUI implements Runnable {
    private AddnewVaccinetypeController controller;
    private static SpecifyVaccineTypeUI instance;
    private VaccineType vacctype;
    private VaccineTypeStore vacctypes;
    private VaccineType newvacctype;
    private Company company;
    private boolean create;



    /**
     * If the instance variable is null, create a new instance of the class and assign it to the instance variable. If the
     * instance variable is not null, return the instance variable
     *
     * @return The instance of the SpecifyVaccineTypeUI class.
     */
    public static SpecifyVaccineTypeUI getInstance() {
        if (instance == null) {
            instance = new SpecifyVaccineTypeUI();
        }
        return instance;
    }

    public SpecifyVaccineTypeUI() {
        controller = new AddnewVaccinetypeController();
        company = App.getInstance().getCompany();
    }

    // This is the run method of the SpecifyVaccineTypeUI class. This method is used to specify a new vaccine type.
    @Override
    public void run() {
        vacctype= new VaccineType();
        newvacctype= new VaccineType();
        boolean response = false, checks;
        Scanner in = new Scanner(System.in);
        System.out.println("Specify a new vacine:");
        String code = Utils.readLineFromConsole("Insert a 5 digit alphanumeric code for the vaccine type");
        vacctype.setCode(code);
        System.out.println();
        String description = Utils.readLineFromConsole("Insert the descrition of the vaccine type");
        vacctype.setDescription(description);
        int options;
        String technology = null;
        int option;
        do {
            options=1;
            System.out.println("Select a vaccine technology");
            for (String technologyselect : vacctype.vaccinetechnologies) {
                System.out.printf("%d-%s\n", options, technologyselect);
                options++;
            }
            System.out.println();
            System.out.println("Select your option:");
            option = in.nextInt();
            if (option >= 1 && option <= 6) {
                technology = vacctype.vaccinetechnologies[option - 1];
                vacctype.setVaccinetechnology(technology);
            } else {
                System.out.println("Invalid option");
            }
        } while (option < 1 || option > 6);
        newvacctype = controller.specifyNewVaccineType(vacctype);
        create = controller.saveVaccineType(newvacctype);
        if(!create){
            System.out.println("Vaccine type is invalid");
        }else{
            System.out.println("Vaccine type added succesfully");
            System.out.println();
            System.out.println(newvacctype);
        }

    }
}
