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
        er.calculateAll();
    }

    // Die Berechnung der Entfernung
    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        String start = cbStartStation.getValue();
        String end = cbEndStation.getValue();

        // Die Entfernungsberechnung
        int distance = er.getDistance(start, end);
        if (distance == 0) {
            lblOutput.setText("Gleicher Bahnhof!");
        } else if (distance == -1) {
            lblOutput.setText("Keine Verbindung!");
        } else {
            lblOutput.setText(start + " --> " + end + ": " + distance + "km");
            lstHistory.getItems().add(lblOutput.getText());
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