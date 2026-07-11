package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Movement.MovementProcessor;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.Collision.MapBoundaryChecker.mapCollisionDetection;
import static it.unicam.cs.mpgc.rpg129851.Collision.ObstacleCollisionChecker.obstacleCollisionDetection;
import static it.unicam.cs.mpgc.rpg129851.Collision.ObstacleCollisionChecker.worldCollisionDetection;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.getImageWalk;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.*;

public class EntityController extends LoaderController {
    @FXML
    protected Pane gameWorld;
    @FXML
    protected Rectangle home, graveyard;

    protected AnimationTimer timer;
    private MovementProcessor movementProcessor;

    public void initialize() {
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayerLocation();
                setPotionObtained();
                ViewRegister.ofPlayer(player).loadPlayerAnimation(now);
                statsControl();
                if(getSpawnX() != -1 || getSpawnY() != -1) {
                    setSpawnPoint(getSpawnX(), getSpawnY());
                }
            }
        };
        movementProcessor = new MovementProcessor();
        ViewRegister.ofPlayer(player).setImage(getImageWalk());
        timer.start();
    }
    public void statsControl(){
        if(player.getExperience().getLevel().levelUp()) {
            player.updateStatistic(player.getExperience().getLevel().getActualLevel(), player.getExperience().getStatistic());
            player.getExperience().getLevel().setLevelUp(false);
        }
    }

    public void updatePlayerLocation() {
        setNewX(ViewRegister.ofPlayer(player).getLayoutX());
        setNewY(ViewRegister.ofPlayer(player).getLayoutY());
        ViewRegister.ofPlayer(player).setIsMoving(false);
        movementProcessor.processMovement();
        mapCollisionDetection();
        worldCollisionDetection(player, gameWorld, getNewX(), getNewY());
        if(getNewY() < 300) {
            obstacleCollisionDetection(player, home, getNewX(), getNewY());
        }else
            obstacleCollisionDetection(player, graveyard, getNewX(), getNewY());
    }
}
