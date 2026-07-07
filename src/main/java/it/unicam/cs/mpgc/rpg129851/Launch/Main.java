 package it.unicam.cs.mpgc.rpg129851.Launch;

import it.unicam.cs.mpgc.rpg129851.Model.*;
import it.unicam.cs.mpgc.rpg129851.View.HealthBar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Main extends Application {

    public static Player player = new Player(3, 40, 2);
    public static List<Orc> orcs = new ArrayList<>();
    public static Orc orcEncountered;
    public static ForestSpirit guardian = new ForestSpirit();
    public static HealthBar playerHealthBar = new HealthBar(player);
    public static HealthBar orcHealthBar;


    public static boolean criticalHit = false;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unicam/cs/mpgc/rpg129851/forest-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PixelWorld");
        primaryStage.show();
        root.requestFocus();
    }
    public static void setOrcEncountered(Orc orc){
        orcEncountered = orc;
        orcHealthBar = new HealthBar(orcEncountered);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
