package de.edvschuleplattling.ekorn.hyperflight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class HelloController {

    @FXML
    private TextField txtStartStation;
    @FXML
    private TextField txtEndStation;
    @FXML
    private Button btnCalculate;
    @FXML
    private Label lblOutput;

    @FXML
    public void onCalculateClick(ActionEvent actionEvent) {
        String start = txtStartStation.getText();
        String end = txtEndStation.getText();
        boolean startCheck = EntfernungsRechner.cityExist(start);
        boolean endCheck = EntfernungsRechner.cityExist(end);
        boolean calcCheck = false;

        // Geht alle möglichen Fehlerprüfungen durch
        if (Objects.equals(start, "") && Objects.equals(end, "")) {
            // System.out.println("Both Empty");
            lblOutput.setText("Bitte Bahnhöfe eingeben!");
        } else if (Objects.equals(start, "")) {
            // System.out.println("Start Empty");
            lblOutput.setText("Bitte Startbahnhof eingeben!");
        } else if (Objects.equals(end, "")) {
            // System.out.println("End Empty");
            lblOutput.setText("Bitte Endbahnhof eingeben!");
        } else if (Objects.equals(start, end)) {
            // System.out.println("Identical");
            lblOutput.setText("Die Bahnhöfe sind gleich!");
        } else {
            if (!startCheck && !endCheck) {
                // System.out.println("Both Not Exist");
                lblOutput.setText("Die Bahnhöfe existieren nicht!");
            } else if (!startCheck) {
                // System.out.println("Start Not Exist");
                lblOutput.setText("Startbahnhof existiert nicht!");
            } else if (!endCheck) {
                // System.out.println("End Not Exist");
                lblOutput.setText("Zielbahnhof existiert nicht!");
            } else {
                // wenn keine Fehler vorkommen
                calcCheck = true;
            }
        }

        // Die Entfernungsberechnung
        if (calcCheck) {
            int distance = EntfernungsRechner.getDistance(start, end);

            if (distance == 0) {
                // System.out.println("No Connection");
                lblOutput.setText("Es gibt keine Verbindung!");
            } else {
                // System.out.println("Success");
                lblOutput.setText("Von " + start + " nach " + end + ": " + Integer.toString(distance) + "km");
            }
        }
    }
}