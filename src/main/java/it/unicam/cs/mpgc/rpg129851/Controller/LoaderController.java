package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class LoaderController {

    @FXML
    public Rectangle healthBarPlayer, forest, home, healthBarOrc;
    @FXML
    public Image imageUp, imageDown, imageLeft, imageRight, imageOrc, imageExclamation, imageHealthBar;
    @FXML
    public ImageView playerView, healthBarViewPlayer, backgroundView, orcView, healthBarViewOrc;;
    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;

    public void initialize() {
        loadImages();
        loadEntity();
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            root.requestFocus();
        }catch(Exception e){
            System.err.println("Loading error of the " + map + " scene");
        }
    }

    public void loadHealthBarPlayer(){
        loadHealthBarImage();
        healthBarViewPlayer.setImage(imageHealthBar);
        healthBarPlayer.setWidth(Main.player.getHealthPercentage() * 130);
        changeColorHealthBar(Main.player, healthBarPlayer);
        healthBarPlayer.setLayoutX(healthBarViewPlayer.getLayoutX() + 50);
        healthBarPlayer.setLayoutY(healthBarViewPlayer.getLayoutY() + 113);
        healthBarViewPlayer.setSmooth(false);
    }
    public void loadHealthBarOrc(){
        loadHealthBarImage();
        healthBarViewOrc.setImage(imageHealthBar);
        healthBarOrc.setWidth(Main.orc.getHealthPercentage() * 130);
        changeColorHealthBar(Main.orc, healthBarOrc);
        healthBarOrc.setLayoutX(healthBarViewOrc.getLayoutX() + 50);
        healthBarOrc.setLayoutY(healthBarViewOrc.getLayoutY() + 113);
        healthBarViewOrc.setSmooth(false);
    }
    public void changeColorHealthBar(Entity entity, Rectangle healthBar){
        if(entity.getHealthPercentage() > 0.60){
            healthBar.setFill(Color.GREEN);
        }else if(entity.getHealthPercentage() <= 0.60 && entity.getHealthPercentage() >= 0.30){
            healthBar.setFill(Color.ORANGE);
        }else {
            healthBar.setFill(Color.RED);
        }
    }
    public void loadHealthBarImage(){
        imageHealthBar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/healthBar.png")));
    }

    public void loadImages() {
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orcoDown.png")));
        imageUp = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerUpSpritesheet.png")));
        imageDown = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerDownSpritesheet.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerLeftSpritesheet.png")));
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerRightSpritesheet.png")));
    }

    public void loadBackground(String nameMap){;
        backgroundView.setImage(loadBackgroundImage(nameMap));
        backgroundView.setSmooth(false);
    }

    public Image loadBackgroundImage(String backgroundName){
        return  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/" + backgroundName)));
    }

}
