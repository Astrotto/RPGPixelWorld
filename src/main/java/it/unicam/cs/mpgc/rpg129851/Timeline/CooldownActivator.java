package it.unicam.cs.mpgc.rpg129851.Timeline;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.*;

public class CooldownActivator {
    private static boolean potionsCooldown = false;

    public static void cooldownActivation(ImageView button1, ImageView button2, double duration){
        button1.setDisable(true);
        button2.setDisable(true);
        setPotionsCooldown(true);
        loadButtonDisabled();
        PauseTransition cooldown = new PauseTransition(Duration.seconds(duration));
        cooldown.setOnFinished(event ->{
            loadButtonImages();
            button1.setDisable(false);
            button2.setDisable(false);
            setPotionsCooldown(false);
        });
        cooldown.play();
    }
    public static void setPotionsCooldown(boolean cooldown){
        potionsCooldown = cooldown;
    }
    public static boolean getPotionsCooldown(){
        return potionsCooldown;
    }
}
