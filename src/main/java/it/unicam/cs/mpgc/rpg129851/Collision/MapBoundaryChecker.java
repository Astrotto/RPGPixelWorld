package it.unicam.cs.mpgc.rpg129851.Collision;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.setNewY;

public class MapBoundaryChecker {
    public static void mapCollisionDetection(){
        if(getNewX() < -60) setNewX(-60);
        if(getNewY() < -60) setNewY(-60);
    }
}