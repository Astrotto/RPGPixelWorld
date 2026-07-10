package it.unicam.cs.mpgc.rpg129851.Timeline;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ChangeSceneTransition {
    public static void startTransition(Rectangle blackScreen, Duration duration, Runnable onFinished) {
        FadeTransition fadeOut = new FadeTransition(duration, blackScreen);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        fadeOut.setOnFinished(event -> {
            onFinished.run();
        });
        fadeOut.play();
    }
}
