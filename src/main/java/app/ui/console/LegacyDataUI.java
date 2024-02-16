package app.ui.console;

import app.controller.LegacyDataController;
import app.domain.model.EntryRecord;
import app.domain.model.LeavingRecord;
import app.domain.model.LegacyData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LegacyDataUI implements Runnable {


    private static LegacyDataUI instance;
    private LegacyDataController controller;

    private Scanner read = new Scanner(System.in);

    public LegacyDataUI() {
        this.controller = new LegacyDataController();
    }

    public static LegacyDataUI getInstance() {
        if (instance == null) {
            instance = new LegacyDataUI();
        }
        return instance;
    }


    @Override
    public void run() {
        ArrayList<LegacyData> legacyData = controller.getLegacyData();
        ArrayList<EntryRecord> entryData = controller.getEntryRecords();
        ArrayList<LeavingRecord> leavingData = controller.getLeavingRecords();
        String exportFileName;
        int saveAnswer, sortingAnswer, criteriaAnswer, sortingOption;
        int nLinhas = 0, sortingOrderOption;


        System.out.println("[1] Sort data with this system data + csv file data \n [2] Sort data from csv file only");
        sortingOption = read.nextInt();
        if (sortingOption == 1) {
            if (entryData.isEmpty() || leavingData.isEmpty()) {
                System.out.println("There's no system data available to import.");
            } else if (!entryData.isEmpty() && !leavingData.isEmpty()) {
                System.out.println("Exporting this system data...");
                controller.exportToLegacyData(entryData, leavingData);
            }
        }

        /*This function checks the header order and loads the data accordingly*/
        controller.addToArrayList(); //the header doesn't get added to the arraylist
        nLinhas = controller.getSizeOfArrayList();

        if (nLinhas > 0) {
            System.out.println("Imported all valid data.");
            System.out.println("" + nLinhas + " administration processes were loaded.");

            System.out.println("Please, select the sorting method you wish to use: \n [1] Bubble sorting \n [2] Selection Sorting");
            sortingAnswer = read.nextInt();
            switch (sortingAnswer) {
                case 1:
                    System.out.println("Bubble sorting selected.");
                    System.out.println("Please select the sorting criteria: \n [1] Arrival Time \n [2] Leaving time");
                    criteriaAnswer = read.nextInt();
                    switch (criteriaAnswer) {
                        case 1:
                            System.out.println("Do you wish to order the data by increasing or decreasing order? \n [1] Increasing \n [2] Decreasing");
                            sortingOrderOption = read.nextInt();
                            switch (sortingOrderOption) {
                                case 1:
                                    System.out.println("Bubble sorting the data by arrival time in a increasing order [First to Last}...");
                                    controller.bubbleSort(nLinhas, 1, 1);
                                    break;
                                case 2:
                                    System.out.println("Bubble sorting the data by arrival time in a decreasing order[Last to first]...");
                                    controller.bubbleSort(nLinhas, 1, 2);
                                    break;
                            }

                            break;
                        case 2:
                            System.out.println("Do you wish to order the data by increasing or decreasing order? \n [1] Increasing \n [2] Decreasing");
                            sortingOrderOption = read.nextInt();
                            switch (sortingOrderOption) {
                                case 1:
                                    System.out.println("Bubble sorting the data by leaving time in a increasing order [First to Last]");
                                    controller.bubbleSort(nLinhas, 2, 1);
                                    break;
                                case 2:
                                    System.out.println("Bubble sorting the data by leaving time in a decreasing order [First to Last]");
                                    controller.bubbleSort(nLinhas, 2, 2);
                                    break;
                            }
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Selection sorting selected.");
                    System.out.println("Please select the sorting criteria: \n [1] Arrival Time \n [2] Leaving time");
                    criteriaAnswer = read.nextInt();
                    switch (criteriaAnswer) {
                        case 1:
                            System.out.println("Do you wish to order the data by increasing or decreasing order? \n [1] Increasing \n [2] Decreasing");
                            sortingOrderOption = read.nextInt();
                            switch (sortingOrderOption) {
                                case 1:
                                    System.out.println("Selection sorting the data by arrival time in a increasing order [First to Last}...");
                                    controller.selectionSort(1, 1);
                                    break;
                                case 2:
                                    System.out.println("Selection sorting the data by arrival time in a decreasing order[Last to first]...");
                                    controller.selectionSort(1, 2);
                                    break;
                            }

                            break;
                        case 2:
                            System.out.println("Do you wish to order the data by increasing or decreasing order? \n [1] Increasing \n [2] Decreasing");
                            sortingOrderOption = read.nextInt();
                            switch (sortingOrderOption) {
                                case 1:
                                    System.out.println("Selection sorting the data by leaving time in a increasing order [First to Last]");
                                    controller.selectionSort(2, 1);
                                    break;
                                case 2:
                                    System.out.println("Selection sorting the data by leaving time in a decreasing order [First to Last]");
                                    controller.selectionSort(2, 2);
                                    break;
                            }
                            break;
                    }
                    break;
            }


            System.out.println("Data sorted: \n" + legacyData);



            System.out.println("Do you want to save the sorted data on a file? \n [1]-Yes \n [2]-No");
            saveAnswer = read.nextInt();

            boolean flag;
            switch (saveAnswer) {
                case 1:
                    System.out.println("Please specify the file name: (ex: ficheiro1.csv)");
                    exportFileName = read.next();
                    do {
                        try {
                            controller.createFile(exportFileName);
                            flag = true;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            flag = false;
                        }
                    } while (!flag);
                    break;
                case 2:
                    System.out.println("The sorted data will not be exported.");
                    break;
            }


        } else System.out.println("No data in the file was valid. Unknown users or vaccine.");

    }
}
