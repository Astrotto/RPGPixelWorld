package it.unicam.cs.mpgc.rpg129851.Launch;

import it.unicam.cs.mpgc.rpg129851.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    public static Player player = new Player(3, 0, 2);
    public static Map<Orc, ImageView> orcs = new HashMap<>();
    public static Orc orcEncountered;
    public static ForestSpirit guardian = new ForestSpirit();

    public static boolean criticalHit = false;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unicam/cs/mpgc/rpg129851/forest-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My RPG");
        primaryStage.show();
        root.requestFocus();
    }
    public static void setOrcEncountered(Orc orc){
        orcEncountered = orc;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
