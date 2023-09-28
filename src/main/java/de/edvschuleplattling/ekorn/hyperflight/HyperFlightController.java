package de.edvschuleplattling.ekorn.hyperflight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HyperFlightController implements Initializable {

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
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnClearSelected;

    // Objekt Entfernungsrechner
    private EntfernungsRechner er = new EntfernungsRechner("stationen.csv");

    // Initialisierung beim Programmstart oder beim Refresh (Daten werden geholt und Choiceboxen werden befüllt)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    // Die Berechnung der Entfernung
    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        String start = cbStartStation.getValue();
        String end = cbEndStation.getValue();
        // boolean startCheck = EntfernungsRechner.cityExist(start);
        // boolean endCheck = EntfernungsRechner.cityExist(end);
        boolean calcCheck = false;

        // Fehlermeldungen
        // Falls eine Choicebox leer it oder wenn beide gleich sind
        if (Objects.equals(start, end)) {
            lblOutput.setText("Bahnhöfe sind gleich!");
        } else {
            calcCheck = true;
        }
        /* } else {
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
            int distance = er.getDistance(start, end);
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

    // Löscht die Historie
    @FXML
    public void onClearClick(ActionEvent actionEvent) {
        lstHistory.getItems().clear();
    }

    @FXML
    public void onClearSelectedClick(ActionEvent actionEvent) {
        int selectIndex = lstHistory.getSelectionModel().getSelectedIndex();
        if (selectIndex >= 0) {
            lstHistory.getItems().remove(selectIndex);
        }
    }

    @FXML
    public void onRefreshClick(ActionEvent actionEvent) {
        refresh();
    }

    // Daten werden aktualisiert
    private void refresh() {
        er.loadData();
        cbStartStation.getItems().clear();
        addArrayToChoiceBox(er.cities, cbStartStation);
        cbStartStation.getSelectionModel().select(0);

        cbEndStation.getItems().clear();
        addArrayToChoiceBox(er.cities, cbEndStation);
        cbEndStation.getSelectionModel().select(0);
    }

    // fügt ein String Array in eine Choicebox ein
    private void addArrayToChoiceBox(String[] cities, ChoiceBox<String> cb) {
        for (String city : cities) {
            cb.getItems().add(city);
        }
    }
}