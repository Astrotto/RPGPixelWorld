module it.unicam.cs.mpgc.rpg129851 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires jdk.compiler;
    requires org.json;
    requires gson;
    requires java.sql;

    opens it.unicam.cs.mpgc.rpg129851.Model to gson;
    exports it.unicam.cs.mpgc.rpg129851.Launch;
    opens it.unicam.cs.mpgc.rpg129851.Launch to javafx.fxml;
    exports it.unicam.cs.mpgc.rpg129851.Controller;
    opens it.unicam.cs.mpgc.rpg129851.Controller to javafx.fxml;
    opens it.unicam.cs.mpgc.rpg129851.Interfaces to javafx.fxml;
}