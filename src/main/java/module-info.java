module it.unicam.cs.mpgc.rpg129851.pixelworld {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens it.unicam.cs.mpgc.rpg129851 to javafx.fxml;
    exports it.unicam.cs.mpgc.rpg129851.Launch;
    opens it.unicam.cs.mpgc.rpg129851.Launch to javafx.fxml;
    exports it.unicam.cs.mpgc.rpg129851.Controller;
    opens it.unicam.cs.mpgc.rpg129851.Controller to javafx.fxml;
}