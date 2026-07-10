package it.unicam.cs.mpgc.rpg129851.Movement;

import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class SpawnPoint {
    private static double spawnX = -1, spawnY = -1;
    public static void setSpawnPoint(double x, double y){
        ViewRegister.ofPlayer(player).setLayoutX(x);
        ViewRegister.ofPlayer(player).setLayoutY(y);
    }
    public static double getSpawnX(){
        return spawnX;
    }
    public static void  setSpawnX(double spawnX){
        SpawnPoint.spawnX = spawnX;
    }
    public static double getSpawnY(){
        return spawnY;
    }
    public static void setSpawnY(double spawnY){
        SpawnPoint.spawnY = spawnY;
    }
}
