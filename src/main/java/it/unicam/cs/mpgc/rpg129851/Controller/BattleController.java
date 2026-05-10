package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BattleController extends LoaderController {
    @FXML
    private ImageView playerView, orcView;
    @FXML
    private Image imageOrc, imagePlayer;
    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 64;
    Player player;
    Orc orc;
    public void initialize(){
        super.initialize();
        loadBackground();
    }
    public void loadBackground(){
        loadBackground("forestBattle.png");
        backgroundView.setLayoutY(backgroundView.getLayoutY() - 170);
        backgroundView.setLayoutX(backgroundView.getLayoutX() + 30);
    }
    public void setData(Entity attacker, Entity defender) {
        this.player = (Player)attacker;
        this.orc = (Orc)defender;
    }

    public void loadEntity(){
        orcView.setImage(imageOrc);
        playerView.setImage(imagePlayer);

        orcView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcView.setSmooth(false);

        playerView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        playerView.setSmooth(false);
    }

    public void loadImages(){
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orcAttack.png")));
        imagePlayer = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerAttack.png")));
    }
}
