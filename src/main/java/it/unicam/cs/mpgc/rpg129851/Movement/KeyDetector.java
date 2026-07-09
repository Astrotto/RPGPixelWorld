package it.unicam.cs.mpgc.rpg129851.Movement;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Controller.LoaderController.*;


public class KeyDetector {
    public static final Set<KeyCode> keyPressed = new HashSet<>();
    public static double newX, newY;

    public static void manageKeyPressed(KeyEvent event) {
        keyPressed.add(event.getCode());
    }
    public static void manageKeyReleased(KeyEvent event) {
        keyPressed.remove(event.getCode());
    }

    public static void keyDetection(String coordinate){
        if(coordinate.equals("X")){
            keyDetectionX(KeyCode.D, KeyCode.RIGHT);
            keyDetectionX(KeyCode.A, KeyCode.LEFT);
        }else if(coordinate.equals("Y")){
            keyDetectionY(KeyCode.S, KeyCode.DOWN);
            keyDetectionY(KeyCode.W, KeyCode.UP);
        }
    }
    public static void keyDetectionY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                newY -= player.getSpeed();
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                newY += player.getSpeed();
            }
            player.getEntityView().setIsMoving(true);
        }
    }
    public static void keyDetectionX(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                newX -= player.getSpeed();
                player.getEntityView().setImage(imageLeft);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                newX += player.getSpeed();
                player.getEntityView().setImage(imageRight);
            }
            player.getEntityView().setIsMoving(true);
        }

    }
    public static Set<KeyCode> getKeyPressed() {
        return keyPressed;
    }
    public static double getNewX(){
        return newX;
    }
    public static void setNewX(double newX){
        KeyDetector.newX =  newX;
    }
    public static double getNewY(){
        return newY;
    }
    public static void setNewY(double newY){
        KeyDetector.newY =  newY;
    }
}
