//package app.ui.console;
//
//import app.controller.VaccinationStatisticsController;
//import app.ui.console.utils.Utils;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class VaccinationStatisticsUI implements Runnable {
//
//    private final Scanner scanner = new Scanner(System.in);
//
//    VaccinationStatisticsController controller;
//
//    public VaccinationStatisticsUI() {
//        this.controller = new VaccinationStatisticsController();
//
//    }
//
//    @Override
//    // The method that is called when the thread is started.
//    public void run() {
//        String date;
//        boolean flag;
//        Date formatDate = new Date();
//
//        System.out.println("Check and Export the Vaccination Statistics");
//
//        // Asking the user to enter a date and then it checks if the date is valid.
//        /* System.out.println("\nEnter the date (dd/mm/yy): ");
//        do {
//            date = scanner.nextLine();
//            if (!controller.checkDate(date)) {
//                System.out.println("That is not a valid date! Please enter again (dd/mm/yy): ");
//                flag = false;
//            } else {
//                flag = true;
//                String dateFormat = "dd/MM/yyyy";
//                DateFormat sdf = new SimpleDateFormat(dateFormat);
//                try {
//                    formatDate = sdf.parse(date);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        } while (!flag); */
//
//        Date fromDate = Utils.readDateFromConsole("Enter the initial Date: ");
//        Date toDate = Utils.readDateFromConsole("Enter the final date");
//
//        /* controller.convertToLocalDate(fromDate);
//        controller.convertToLocalDate(toDate); */
//
//        int[] fullyVaccinated = controller.getFullyVaccinatedCount(controller.convertToLocalDate(fromDate), controller.convertToLocalDate(toDate));
//        String[] message = new String[fullyVaccinated.length];
//        for (int i = 0; i < fullyVaccinated.length; i++) {
//            message[i] = String.format("%s; %d", controller.convertToLocalDate(fromDate).plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), fullyVaccinated[i]);
//        }
//        // System.out.printf("%n%d users were fully vaccinated at the date %s", fullyVaccinated, dateLocal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        if (Utils.confirm("Do you want to export this statistics to an CSV file[y/n]")) {
//            String path;
//            boolean isValid;
//            do {
//                isValid = true;
//                path = Utils.readLineFromConsole("File path: ");
//                if (!Objects.requireNonNull(path).isBlank()) {
//                    if (path.matches(".*\\.csv")) {
//                        try {
//                            BufferedWriter bf = new BufferedWriter(new FileWriter(path));
//                            for (int i = 0; i < message.length; i++) {
//                                bf.write(message[i] + "\n");
//                                bf.flush();
//                            }
//                            bf.close();
//                            System.out.println();
//                            System.out.printf("The statistics have been exported to %s", path);
//                        } catch (IOException e) {
//                            System.out.println(); // something went wrong
//                            isValid = false;
//                        }
//                    } else {
//                        System.out.println("Has to be .csv");
//                        isValid = false;
//                    }
//                } else {
//                    System.out.println("Cannot be empty");
//                    isValid = false;
//                }
//            } while (!isValid);
//        }
//    }
//}
