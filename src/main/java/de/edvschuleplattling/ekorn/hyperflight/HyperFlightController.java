package de.edvschuleplattling.ekorn.hyperflight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    // Object DistanceCalculator
    private final DistanceCalculator er = new DistanceCalculator("stationen.csv");

    // Data gets loaded when booting the program
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    // Distance calculation button
    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        String start = cbStartStation.getValue();
        String end = cbEndStation.getValue();

        // Die Entfernungsberechnung
        int distance = er.getDistance(start, end);
        if (distance == 0) {
            lblOutput.setText("Same Trainstation!");
        } else {
            lblOutput.setText(start + " --> " + end + ": " + distance + "km");
            lstHistory.getItems().add(lblOutput.getText());
        }
    }

    // Deletes the history
    @FXML
    public void onClearClick(ActionEvent actionEvent) {
        lblOutput.setText("");
        lstHistory.getItems().clear();
    }

    // Deletes the selected history entry
    @FXML
    public void onClearSelectedClick(ActionEvent actionEvent) {
        int selectIndex = lstHistory.getSelectionModel().getSelectedIndex();
        if (selectIndex >= 0) {
            lstHistory.getItems().remove(selectIndex);
        }
    }

    // Refreshes all data
    @FXML
    public void onRefreshClick(ActionEvent actionEvent) {
        lblOutput.setText("");
        refresh();
    }

    private void refresh() {
        er.loadData();
        er.calculateShortestPaths();

        cbStartStation.getItems().clear();
        addArrayToChoiceBox(er.getCities(), cbStartStation);
        cbStartStation.getSelectionModel().select(0);

        cbEndStation.getItems().clear();
        addArrayToChoiceBox(er.getCities(), cbEndStation);
        cbEndStation.getSelectionModel().select(0);
    }

    // Fills the choiceboxes with the cities
    private void addArrayToChoiceBox(String[] cities, ChoiceBox<String> cb) {
        if (cities == null) return;
        for (String city : cities) {
            cb.getItems().add(city);
        }
    }
}