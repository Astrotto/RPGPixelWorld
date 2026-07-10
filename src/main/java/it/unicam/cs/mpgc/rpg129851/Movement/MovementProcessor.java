package it.unicam.cs.mpgc.rpg129851.Movement;

import javafx.scene.input.KeyCode;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;


public class MovementProcessor {
    private final KeyDetector keyDetector;

    public MovementProcessor() {
        keyDetector = new KeyDetector();
    }

    public void processMovement(){
        updateLocation("Y", newY, KeyCode.W, KeyCode.UP);
        updateLocation("Y", newY, KeyCode.S, KeyCode.DOWN);
        updateLocation("X", newX, KeyCode.A, KeyCode.LEFT);
        updateLocation("X", newX, KeyCode.D, KeyCode.RIGHT);
    }
    private void updateLocation(String coordinate, double direction, KeyCode keyCode1, KeyCode keyCode2) {
        MovementInput input = new MovementInput(keyDetector.getKeyPressed());
        switch(coordinate){
            case "X" -> {
                double moveX = input.moveX(keyCode1, keyCode2);
                if(moveX > 0)
                    direction += player.getSpeed();
                else if(moveX < 0)
                    direction -= player.getSpeed();
                setNewX(direction);
            }
            case "Y" -> {
                double moveY = input.moveY(keyCode1, keyCode2);
                if(moveY > 0)
                    direction += player.getSpeed();
                else if(moveY < 0)
                    direction -= player.getSpeed();
                setNewY(direction);
            }
        }
    }
}
