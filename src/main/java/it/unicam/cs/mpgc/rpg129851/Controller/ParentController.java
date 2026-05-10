package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class ParentController {
    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;
    public final Set<KeyCode> keyPressed = new HashSet<>();
    Player player = new Player( 1, 0, 2);
    Orc orc = new Orc(45,65, 20, 10);
    public boolean moving = false;
    public double newX, newY;
    public Image imageUp, imageDown, imageLeft, imageRight, imageOrc, imageExclamation, imageHealthBar;
    public ImageView playerView, healthBarView, backgroundView, orcView;
    public Rectangle healthBar, forest, home;
    public Pane gameWorld;
    public AnimationTimer timer;
    private long lastChangeFrame = 0;
    private int actualFrame = 0;

    public void initialize() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateLocation();
                updateAnimation(now);
            }
        };
        loadImages();
        loadEntity();
        loadHealthBar();
        timer.start();
    }
    public void updateAnimation(long actualHour) {
        if(!moving){
            playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 6;
            double xMovement = actualFrame * FRAME_WIDTH;
            playerView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
    public void updateLocation() {
        newX = playerView.getLayoutX();
        newY = playerView.getLayoutY();
        double oldY = newY;
        moving = false;

        Bounds hitboxHome = home.getBoundsInParent();


        keyDetectionY(KeyCode.S, KeyCode.DOWN);
        keyDetectionY(KeyCode.W, KeyCode.UP);
        keyDetectionX(KeyCode.A, KeyCode.LEFT);
        keyDetectionX(KeyCode.D, KeyCode.RIGHT);

        if(newX < 0) newX = 0;
        if(newY < 0) newY = 0;

        collisionDetection(gameWorld, newX, newY);

        if(!player.getHitbox(newX, oldY).intersects(hitboxHome)) {playerView.setLayoutX(newX);}
        if(!player.getHitbox(playerView.getLayoutX(), newY).intersects(hitboxHome)) {playerView.setLayoutY(newY);}
    }
    private void collisionDetection(Pane obstacle, double x, double y) {
        if(x > obstacle.getWidth() - playerView.getViewport().getWidth()) {
            newX = obstacle.getWidth() - playerView.getViewport().getWidth();
        }
        if(y > obstacle.getHeight() - playerView.getViewport().getHeight()){
            newY = obstacle.getHeight() - playerView.getViewport().getHeight();
        }
    }

    public void keyDetectionY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode )  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                newY -= player.getSpeed();
                playerView.setImage(imageUp);
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                newY += player.getSpeed();
                playerView.setImage(imageDown);
            }
            moving = true;
        }
    }
    public void keyDetectionX(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                newX -= player.getSpeed();
                playerView.setImage(imageLeft);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                newX += player.getSpeed();
                playerView.setImage(imageRight);
            }
            moving = true;
        }
    }
    @FXML
    void manageKeyPressed(KeyEvent event) {
        keyPressed.add(event.getCode());
    }

    @FXML
    void manageKeyReleased(KeyEvent event) {
        keyPressed.remove(event.getCode());
    }

    public void loadEntity(){
        playerView.setImage(imageDown);
        orcView.setImage(imageOrc);
        orcView.setSmooth(false);
        playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        playerView.setSmooth(false);
    }
    private void placeOrcRandomly(){
        Random rand  = new Random();

        double orcWidth = orcView.getBoundsInParent().getWidth();
        double orcHeight = orcView.getBoundsInParent().getHeight();

        double minX = forest.getLayoutX();
        double maxX = minX + forest.getWidth() - orcWidth;

        double minY = forest.getLayoutY();
        double maxY = minY + forest.getHeight() - orcHeight;

        double randomX = minX + (maxX - minX) * rand.nextDouble();
        double randomY = minY + (maxY - minY) * rand.nextDouble();

        orcView.setLayoutX(randomX);
        orcView.setLayoutY(randomY);
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
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Loading error of the " + map + " scene");
        }
    }

    public void loadHealthBar(){
        healthBarView.setImage(imageHealthBar);
        healthBar.setLayoutX(healthBarView.getLayoutX() + 50);
        healthBar.setLayoutY(healthBarView.getLayoutY() + 113);
        healthBarView.setSmooth(false);
    }

    public void loadImages() {
        imageHealthBar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/healthBar.png")));
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
