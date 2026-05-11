package it.unicam.cs.mpgc.rpg129851.Launch;

import it.unicam.cs.mpgc.rpg129851.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static final Player player = new Player(1, 0, 2);
    public static final Orc orc = new Orc(45,65, 20, 10);
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
    public static void main(String[] args) {
        launch(args);
    }
}
