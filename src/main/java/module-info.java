module de.edvschuleplattling.ekorn.hyperflight {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens de.edvschuleplattling.ekorn.hyperflight to javafx.fxml;
    exports de.edvschuleplattling.ekorn.hyperflight;
}