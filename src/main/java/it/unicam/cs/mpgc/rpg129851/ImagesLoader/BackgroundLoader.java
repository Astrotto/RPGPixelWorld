package it.unicam.cs.mpgc.rpg129851.ImagesLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class BackgroundLoader {

    public static void setBackgroundView(String nameMap, ImageView backgroundView){;
        backgroundView.setImage(loadBackgroundImage(nameMap));
        backgroundView.setSmooth(false);
    }

    public static Image loadBackgroundImage(String backgroundName){
        return  new Image(Objects.requireNonNull(BackgroundLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/mapImages/" + backgroundName)));
    }

}
