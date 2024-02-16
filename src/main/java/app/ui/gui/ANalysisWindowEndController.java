package app.ui.gui;

import app.domain.model.Analysis;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ANalysisWindowEndController {

    // Used to store the location of the FXML file.
    @FXML
    private ResourceBundle resources;

    // Used to store the location of the FXML file.
    @FXML
    private URL location;

    // A list view that is used to display the data from the analysis object.
    @FXML
    private ListView<String> listViewOfAnalysis = new ListView<>();

    // A private variable that is used to store the analysis object.
    private Analysis analysis;

    // A constructor that takes an analysis object and sets it to the analysis variable.
    public ANalysisWindowEndController(Analysis analysis) {
        this.analysis = analysis;
        initialize();
    }

    @FXML
    public void initialize() {
    /**
     * It takes the data from the analysis object and puts it into a list view
     */
        ArrayList<String> lista = new ArrayList<>();
        lista.add("VaccinationCenter: " + analysis.getVaccinationCenter());
        lista.add("Differences: " + Arrays.toString(analysis.getDifferences()));
        lista.add("MaximumSum: " + Arrays.toString(analysis.getMaximumSum()));
        lista.add("Hours Period:" + Arrays.toString(analysis.getHoursPeriod()) );
        lista.add("Sum: " + String.valueOf(analysis.getSum()));
        listViewOfAnalysis.getItems().addAll(lista);
    }
}

