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
        if (Objects.equals(start, end)) {
            lblOutput.setText("Die Bahnhöfe sind gleich!");
        }
        else if (start != "" && end != "") {
            
            if (!startCheck && !endCheck) {
                lblOutput.setText("Die Bahnhöfe existieren nicht!");
            }
            else if (!startCheck) {
                lblOutput.setText("Startbahnhof existiert nicht!");
            }
            else if (!endCheck) {
                lblOutput.setText("Zielbahnhof existiert nicht!");
            }
            else {
                calcCheck = true;
            }
        }
        else {
            lblOutput.setText("");
        }

        if (calcCheck) {
            lblOutput.setText("Von "+start+" nach "+end);
        }
    }
}