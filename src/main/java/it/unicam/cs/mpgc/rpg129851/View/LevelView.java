package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelView {
    private static Rectangle level;

    public static void showLevel(Entity entity ,HealthBar healthBar, Pane levelPane){
        switch (entity.getExperience().getLevel()) {
            case 1 -> loadLevel(healthBar, createLevel(levelPane), 138);
            case 2 -> {
                loadLevel(healthBar, createLevel(levelPane), 138);
                loadLevel(healthBar, createLevel(levelPane), 154);
            }
            case 3 -> {
                loadLevel(healthBar, createLevel(levelPane), 138);
                loadLevel(healthBar, createLevel(levelPane), 154);
                loadLevel(healthBar, createLevel(levelPane), 170);
            }
        }
    }
    public static void loadLevel(HealthBar healthBar, Rectangle level, int x){
        level.setVisible(true);
        level.setLayoutX(healthBar.getProgressBarView().getLayoutX() + x);
        level.setLayoutY(healthBar.getProgressBarView().getLayoutY() + 121);
    }
    public static Rectangle createLevel(Pane levelPane){
        level = new Rectangle(8.0, 9.0);
        levelPane.getChildren().add(level);
        level.setArcWidth(5.0);
        level.setArcHeight(5.0);
        level.setFill(Color.web("#FFDB58"));

        return level;
    }

}
