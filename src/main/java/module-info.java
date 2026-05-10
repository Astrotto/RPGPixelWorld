module it.unicam.cs.mpgc.rpg129851 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires javafx.base;

    opens it.unicam.cs.mpgc.rpg129851 to javafx.fxml;
    exports it.unicam.cs.mpgc.rpg129851.Launch;
    opens it.unicam.cs.mpgc.rpg129851.Launch to javafx.fxml;
    exports it.unicam.cs.mpgc.rpg129851.Controller;
    opens it.unicam.cs.mpgc.rpg129851.Controller to javafx.fxml;
}