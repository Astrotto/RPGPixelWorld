package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BattleController {
    @FXML
    private ImageView background, playerView, orcView;
    @FXML
    private Image imageBackground, imageOrc, imagePlayer;
    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 64;
    public void initialize(){
        loadImages();
        loadBackground();
        loadEntity();
    }
    public void setData(Entity attacker, Entity defender) {

    }
    private void loadEntity(){
        orcView.setImage(imageOrc);
        playerView.setImage(imagePlayer);

        orcView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcView.setSmooth(false);

        playerView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        playerView.setSmooth(false);
    }
    private void loadBackground(){
        background.setImage(imageBackground);
        background.setSmooth(false);
        background.setLayoutY(background.getLayoutY() - 170);
        background.setLayoutX(background.getLayoutX() + 30);
    }
    private void loadImages(){
        imageBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/forestBattle.png")));
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orcAttack.png")));
        imagePlayer = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerAttack.png")));
    }
}
