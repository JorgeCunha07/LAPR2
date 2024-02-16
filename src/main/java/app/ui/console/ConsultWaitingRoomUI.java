package app.ui.console;

import app.controller.App;
import app.controller.RegisterSNSUserArrivalController;
import app.domain.model.Company;
import app.domain.model.Store.SNSUserStore;
import app.domain.model.Store.VaccinationCenterStore;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultWaitingRoomUI implements Runnable {

    private static ScheduleVaccineUI instance;
    private Company company;


    private RegisterSNSUserArrivalController controller;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ScheduleVaccineUI getInstance() {
        if (instance == null) {
            instance = new ScheduleVaccineUI();
        }
        return instance;
    }

    /**
     * Instantiates a new Consult Waiting Room ui.
     */
    public ConsultWaitingRoomUI() {
        controller = new RegisterSNSUserArrivalController();
        company = App.getInstance().getCompany();
    }

    @Override
    public void run() {
        int i;
        String optionVaccCenter = "";
        Scanner read = new Scanner(System.in);
        VaccinationCenterStore vacccenterstore = company.getVaccinationCenterStore();
        SNSUserStore snsusers = company.getSnsUserStore();
        String a = "";
        for (i = 0; i < vacccenterstore.vaccinationCenters.size(); i++) {
            a = vacccenterstore.vaccinationCenters.get(i).getName();
            System.out.println(a);
            if (vacccenterstore.vaccinationCenters.isEmpty()) {
                System.out.println("There are no vaccination centers");
                break;
            }

            do {
                System.out.println("Choose a vaccination center or type 0 if you want to go back");
                optionVaccCenter = read.nextLine();
                if(optionVaccCenter.equals("0")){
                    break;
                }
                if (!controller.CheckIfCenterExists(optionVaccCenter)) {
                    System.out.println("Vaccination center doesn't exist");
                } else {
                    if (!controller.getVaccinationCenterArrivals(optionVaccCenter).isEmpty()) {

                        for (int j = 0; j < controller.getVaccinationCenterArrivals(optionVaccCenter).size(); j++) {
                            LocalDateTime today = LocalDateTime.now();
                            if (controller.getVaccinationCenterArrivals(optionVaccCenter).get(j).getEntryDateTime().equals(today)) {

                                for (int k = 0; k < snsusers.snsUserList.size(); k++) {


                                    if (controller.getVaccinationCenterArrivals(optionVaccCenter).get(j).getVaccineSchedule().getSNSNumber().equals(snsusers.snsUserList.get(k).getSnsUserNumber())) {
                                        System.out.println(snsusers.snsUserList.get(k).getName());
                                        System.out.println(snsusers.snsUserList.get(k).getGender());
                                        System.out.println(snsusers.snsUserList.get(k).getBirthDate());
                                        System.out.println(snsusers.snsUserList.get(k).getSnsUserNumber());
                                        System.out.println(snsusers.snsUserList.get(k).getPhoneNumber());


                                    }
                                }
                            }
                        }
                    } else System.out.println("There are no patients in the waiting room");
                }
            } while (!optionVaccCenter.equalsIgnoreCase(a));

        }
    }
}
