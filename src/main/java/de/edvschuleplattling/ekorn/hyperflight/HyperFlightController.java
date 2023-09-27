package de.edvschuleplattling.ekorn.hyperflight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class HyperFlightController {

    @FXML
    private Button btnCalculate;
    @FXML
    private Label lblOutput;
    @FXML
    private ListView<String> lstHistory;
    @FXML
    private Button btnClearHistory;
    @FXML
    private ChoiceBox<String> cbStartStation;
    @FXML
    private ChoiceBox<String> cbEndStation;

    public void initialize() {
        addArrayToChoiceBox(EntfernungsRechner.getCities(), cbStartStation);
        addArrayToChoiceBox(EntfernungsRechner.getCities(), cbEndStation);
    }

    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        String start = cbStartStation.getValue();
        String end = cbEndStation.getValue();
        // boolean startCheck = EntfernungsRechner.cityExist(start);
        // boolean endCheck = EntfernungsRechner.cityExist(end);
        boolean calcCheck = false;

        // Fehlermeldungen
        // Falls eine Choicebox leer it oder wenn beide gleich sind
        if (Objects.equals(start, null) && Objects.equals(end, null)) {
            lblOutput.setText("Bitte Bahnhöfe auswählen!");
        } else if (Objects.equals(start, null)) {
            lblOutput.setText("Bitte Startbahnhof auswählen!");
        } else if (Objects.equals(end, null)) {
            lblOutput.setText("Bitte Endbahnhof auswählen!");
        } else if (Objects.equals(start, end)) {
            lblOutput.setText("Bahnhöfe sind gleich!");
        } else {
            calcCheck = true;
        }
        /* else if (Objects.equals(start, end)) {
            // System.out.println("Identical");
            //lblOutput.setText("Die Bahnhöfe sind gleich!");
        //} else {
            // // Falls eingegebene Bahnhöfe nicht existieren
            //if (!startCheck && !endCheck) {
                // // System.out.println("Both Not Exist");
                // lblOutput.setText("Die Bahnhöfe existieren nicht!");
            //} else if (!startCheck) {
                // // System.out.println("Start Not Exist");
                // lblOutput.setText("Startbahnhof existiert nicht!");
            // } else if (!endCheck) {
                // // System.out.println("End Not Exist");
                // lblOutput.setText("Zielbahnhof existiert nicht!");
            // } else {
                // // wenn keine Fehler vorkommen
                // calcCheck = true;
            // }
        // }
        */

        // Die Entfernungsberechnung
        if (calcCheck) {
            int distance = EntfernungsRechner.getDistance(start, end);
            if (distance == 0) {
                // System.out.println("No Connection");
                lblOutput.setText("Es gibt keine Verbindung!");
            } else {
                // System.out.println("Success");
                lblOutput.setText(start + " --> " + end + ": " + distance + "km");
                lstHistory.getItems().add(lblOutput.getText());
            }
        }
    }

    @FXML
    public void onClearClick(ActionEvent actionEvent) {
        lstHistory.getItems().clear();
    }

    public void addArrayToChoiceBox(String[] cities, ChoiceBox<String> cb) {
        for (String city : cities) {
            cb.getItems().add(city);
        }
    }
}