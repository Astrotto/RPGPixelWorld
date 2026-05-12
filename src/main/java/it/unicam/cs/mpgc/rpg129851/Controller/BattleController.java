package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

public class BattleController extends LoaderController {
    @FXML
    private ImageView playerView, orcView;
    @FXML
    private Image imageOrc, imagePlayer;
    int percentageOfEscape = 20;

    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 64;
    AnimationTimer timer;
    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loadHealthBarOrc(Main.orcEncountered);
                loadHealthBarPlayer();
                deathControl(Main.player);
                deathControl(Main.orcEncountered);

            }
        };
        orcView.setImage(imageOrc);
        orcView.setSmooth(false);
        loadBackground();
        timer.start();
    }

    public void attack(){
        attack(Main.player, Main.orcEncountered);
        attack(Main.orcEncountered, Main.player);
    }
    private void attack(Entity attacker, Entity defender){
        int damage;
        if(defender.getCurrentHp() <= 0){

        }else {
            damage = attacker.attack(defender);
            if (Main.criticalHit) {
                Main.criticalHit = false;
                System.out.println(attacker.getName() + " di LVL" + attacker.getLevel() + " ha inflitto " + damage + " danni a " + defender.getName() + " con un COLPO CRITICO!");
            } else {
                System.out.println(attacker.getName()  + " di LVL" + attacker.getLevel() + " ha inflitto " + damage + " danni a " + defender.getName());
            }
        }
    }
    public void deathControl(Entity entity){
        if(entity.getCurrentHp() <= 0){
            System.out.println(entity.getName() + " e morto!");
            System.exit(0);
            if(entity instanceof Orc){
                System.out.println(entity.getName() + " ha droppato " + ((Orc) entity).getLootExp() + " punti esperienza");
                Main.player.getExperience(((Orc) entity).getLootExp());
            }
        }

    }
    public void loadBackground(){
        loadBackground("forestBattle.png");
        backgroundView.setLayoutY(backgroundView.getLayoutY() - 170);
        backgroundView.setLayoutX(backgroundView.getLayoutX() + 30);
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

    public void run() {
        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            changeMap((Stage) playerView.getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(Main.orcEncountered, Main.player);
        }
    }
}
