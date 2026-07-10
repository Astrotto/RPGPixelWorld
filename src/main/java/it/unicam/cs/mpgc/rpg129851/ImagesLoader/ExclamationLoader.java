package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class ExclamationLoader {

    public static void loadExclamation(ImageView exclamation){
        PlayerView view = ViewRegister.ofPlayer(player);
        exclamation.setX(view.getLayoutX() + 90);
        exclamation.setY(view.getLayoutY() + 55);
        exclamation.setRotate(5);
        exclamation.setImage(loadExclamationImage());
    }
    private static Image loadExclamationImage(){
        return new Image(Objects.requireNonNull(ExclamationLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/exclamation.png")));
    }
}
