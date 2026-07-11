package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import it.unicam.cs.mpgc.rpg129851.System.CombatSystem;
import it.unicam.cs.mpgc.rpg129851.System.DeathSystem;
import it.unicam.cs.mpgc.rpg129851.System.EscapeSystem;
import it.unicam.cs.mpgc.rpg129851.System.HealSystem;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.loadBackgroundImage;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButtonImages;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButtons;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.loadOrcImages;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Timeline.CooldownActivator.cooldownActivation;
import static it.unicam.cs.mpgc.rpg129851.Timeline.CooldownActivator.getPotionsCooldown;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;

public class MenuGameController extends LoaderController{
    @FXML
    private Pane menuPane;

    public void showMenu(){
        setBackgroundView("menuMap.png", backgroundView);
        menuPane.getChildren().add(backgroundView);
    }
    public void initialize(){
        super.initialize();
        showMenu();
    }
}
