package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Collision.MapBoundaryChecker.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import static it.unicam.cs.mpgc.rpg129851.Collision.ObstacleCollisionChecker.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.*;


import it.unicam.cs.mpgc.rpg129851.Movement.MovementProcessor;
import javafx.animation.AnimationTimer;


public class EntityController extends LoaderController {
    public AnimationTimer timer;
    private MovementProcessor movementProcessor;

    public void initialize() {
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayerLocation();
                setPotionObtained();
                player.getEntityView().loadPlayerAnimation(now);
                if(getSpawnX() != -1 || getSpawnY() != -1) {
                    setSpawnPoint(getSpawnX(), getSpawnY());
                }
            }
        };
        movementProcessor = new MovementProcessor();
        player.getEntityView().setImage(getImageWalk());
        timer.start();
    }

    public void updatePlayerLocation() {
        setNewX(player.getEntityView().getLayoutX());
        setNewY(player.getEntityView().getLayoutY());
        player.getEntityView().setIsMoving(false);
        movementProcessor.processMovement();
        mapCollisionDetection();
        worldCollisionDetection(player, gameWorld, getNewX(), getNewY());
        if(getNewY() < 300) {
            obstacleCollisionDetection(player, home, getNewX(), getNewY());
        }else
            obstacleCollisionDetection(player, graveyard, getNewX(), getNewY());
    }





}
