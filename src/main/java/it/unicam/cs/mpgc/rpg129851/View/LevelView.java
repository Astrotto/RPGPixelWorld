package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelView {
    private static final int[] OFFSETS = {138, 154, 170};

    public static void showLevel(Entity entity, HealthBar healthBar, Pane levelPane){
        int level = entity.getExperience().getLevel().getActualLevel();
        for(int i = 0; i < level && i < OFFSETS.length; i++){
            loadLevel(healthBar, createLevel(levelPane), OFFSETS[i]);
        }
    }
    public static void loadLevel(HealthBar healthBar, Rectangle level, int x){
        level.setVisible(true);
        level.setLayoutX(healthBar.getProgressBarView().getLayoutX() + x);
        level.setLayoutY(healthBar.getProgressBarView().getLayoutY() + 121);
    }
    public static Rectangle createLevel(Pane levelPane){
        Rectangle level = new Rectangle(8.0, 9.0);
        levelPane.getChildren().add(level);
        level.setArcWidth(5.0);
        level.setArcHeight(5.0);
        level.setFill(Color.web("#FFDB58"));
        return level;
    }

}
