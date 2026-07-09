package it.unicam.cs.mpgc.rpg129851.Movement;
import static it.unicam.cs.mpgc.rpg129851.Controller.LoaderController.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import it.unicam.cs.mpgc.rpg129851.Collision.MapBoundaryChecker;
import it.unicam.cs.mpgc.rpg129851.Collision.ObstacleCollisionChecker;
import it.unicam.cs.mpgc.rpg129851.Interfaces.CollisionChecker;
import it.unicam.cs.mpgc.rpg129851.Model.Player;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MovementProcessor {
    private KeyDetector keyDetector;

    public MovementProcessor() {
        keyDetector = new KeyDetector();
    }
    public void processMovement(){
        updateLocation("Y", newY, KeyCode.W, KeyCode.UP);
        updateLocation("Y", newY, KeyCode.S, KeyCode.DOWN);
        updateLocation("X", newX, KeyCode.A, KeyCode.LEFT);
        updateLocation("X", newX, KeyCode.D, KeyCode.RIGHT);
    }
    public void updateLocation(String coordinate, double direction , KeyCode keyCode1, KeyCode keyCode2) {
        MovementInput input = new MovementInput(keyDetector.getKeyPressed());
        switch(coordinate){
            case "X" -> {
                if(input.getDirectionX(keyCode1, keyCode2) > 0)
                    direction += player.getSpeed();
                else if(input.getDirectionX(keyCode1, keyCode2) < 0)
                    direction -= player.getSpeed();
                setNewX(direction);
            }
            case "Y" -> {
                if(input.getDirectionY(keyCode1, keyCode2) > 0)
                    direction += player.getSpeed();
                else if(input.getDirectionY(keyCode1, keyCode2) < 0)
                    direction -= player.getSpeed();
                setNewY(direction);
            }
        }
    }
}