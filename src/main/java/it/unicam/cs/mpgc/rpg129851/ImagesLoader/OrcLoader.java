package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.scene.image.Image;

import java.util.Objects;

public class OrcLoader {
    private static Image imageOrc, imageOrcAttack;

    public static void loadOrcImages(Orc orc){
        String orcName = switch (orc.getExperience().getLevel()) {
            case 1 -> "armored";
            case 2 -> "elite";
            case 3 -> "rider";
            default -> "";
        };
        imageOrc = new Image(Objects.requireNonNull(OrcLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcImages/" + orcName + "Orc.png")));
        imageOrcAttack = new Image(Objects.requireNonNull(OrcLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcImages/" + orcName + "OrcAttack.png")));
    }
    public static Image getImageOrc(){ return imageOrc; }
    public static Image getImageOrcAttack(){ return imageOrcAttack; }
}
