package it.unicam.cs.mpgc.rpg129851.Launch;

import it.unicam.cs.mpgc.rpg129851.Model.ForestSpirit;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.ExperienceBar;
import it.unicam.cs.mpgc.rpg129851.View.HealthBar;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.setNewScene;

public class Main extends Application {

    public static Player player = new Player(3, 0, 2);
    public static List<Orc> orcs = new ArrayList<>();
    public static Orc orcEncountered;
    public static ForestSpirit guardian = new ForestSpirit();
    public static HealthBar playerHealthBar = new HealthBar(player);
    public static ExperienceBar playerExperienceBar = new ExperienceBar(player);
    public static HealthBar orcHealthBar;
    public static ExperienceBar orcExperienceBar;

    @Override
    public void start(Stage primaryStage) {
        ViewRegister.register(player, new PlayerView());
        setNewScene(primaryStage, "forest");
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
