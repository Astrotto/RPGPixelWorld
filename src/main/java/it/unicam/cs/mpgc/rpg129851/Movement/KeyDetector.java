package it.unicam.cs.mpgc.rpg129851.Movement;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyDetector {
    public static final Set<KeyCode> keyPressed = new HashSet<>();
    public static double newX, newY;

    public static void manageKeyPressed(KeyEvent event) {
        keyPressed.add(event.getCode());
    }
    public static void manageKeyReleased(KeyEvent event) {
        keyPressed.remove(event.getCode());
    }

    public Set<KeyCode> getKeyPressed() {
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
