package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class LoaderController {
    Player player = new Player( 1, 0, 2);
    public Rectangle healthBar, forest, home;
    public Image imageUp, imageDown, imageLeft, imageRight, imageOrc, imageExclamation, imageHealthBar;
    public ImageView playerView, healthBarView, backgroundView, orcView;
    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;

    public void initialize() {
        loadImages();
        loadEntity();
        loadHealthBar();
    }

    public void loadEntity(){
        playerView.setImage(imageDown);
        orcView.setImage(imageOrc);
        orcView.setSmooth(false);
        playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        playerView.setSmooth(false);
    }

    public void changeMap(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + ".fxml")));
            Parent root = loader.load();
            Stage stage = actualStage;
            Scene forestScene = new Scene(root);
            stage.setScene(forestScene);
            stage.show();
            root.requestFocus();
        }catch(Exception e){
            System.err.println("Loading error of the " + map + " scene");
        }
    }

    public void loadHealthBar(){
        loadHealthBarImage();
        healthBarView.setImage(imageHealthBar);
        healthBar.setLayoutX(healthBarView.getLayoutX() + 50);
        healthBar.setLayoutY(healthBarView.getLayoutY() + 113);
        healthBarView.setSmooth(false);
    }

    public void loadImages() {
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orcoDown.png")));
        imageUp = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerUpSpritesheet.png")));
        imageDown = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerDownSpritesheet.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerLeftSpritesheet.png")));
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerRightSpritesheet.png")));
    }
    public void loadHealthBarImage(){
        imageHealthBar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/healthBar.png")));
    }
    public void loadBackground(String nameMap){;
        backgroundView.setImage(loadBackgroundImage(nameMap));
        backgroundView.setSmooth(false);
    }

    public Image loadBackgroundImage(String backgroundName){
        return  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/" + backgroundName)));
    }
}
