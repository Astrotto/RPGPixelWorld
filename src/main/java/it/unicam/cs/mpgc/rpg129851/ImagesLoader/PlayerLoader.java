package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import java.util.Objects;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class PlayerLoader {
    private static Image imageWalk, imagePlayerAttack;

    public static void loadPlayerView(){
        player.getEntityView().setViewport(new Rectangle2D(0,0,100,100));
        player.getEntityView().setSmooth(false);
    }
    public static void loadPlayerImages(){
        String playerName = switch (player.getExperience().getLevel()) {
            case 1 -> "knight";
            case 2 -> "templarKnight";
            case 3 -> "lancer";
            default -> "";
        };
        imageWalk = new Image(Objects.requireNonNull(PlayerLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightImages/" + playerName + "Walk.png")));
        imagePlayerAttack = new Image(Objects.requireNonNull(PlayerLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightImages/" + playerName + "Attack.png")));
    }
    public static Image getImageWalk(){ return imageWalk; }
    public static Image getImagePlayerAttack(){ return imagePlayerAttack; }
}
