package it.unicam.cs.mpgc.rpg129851.Launch;

import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import it.unicam.cs.mpgc.rpg129851.View.ExperienceBar;
import it.unicam.cs.mpgc.rpg129851.View.HealthBar;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

 public class Main extends Application {

    public static Player player = new Player(1, 84, 2);
    public static List<Orc> orcs = new ArrayList<>();
    public static Orc orcEncountered;
    public static ForestSpirit guardian = new ForestSpirit();
    public static HealthBar playerHealthBar = new HealthBar(player);
    public static ExperienceBar playerExperienceBar = new ExperienceBar(player);
    public static HealthBar orcHealthBar;
    public static ExperienceBar orcExperienceBar;

    @Override
    public void start(Stage primaryStage) {
        changeMap(primaryStage, "map");
    }
    public static void setOrcEncountered(Orc orc){
        orcEncountered = orc;
        orcHealthBar = new HealthBar(orcEncountered);
        orcExperienceBar = new ExperienceBar(orcEncountered);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
