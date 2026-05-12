package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.animation.AnimationTimer;
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
    int percentageOfEscape = 25;

    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 64;
    AnimationTimer timer;
    private boolean attackingPlayer = false, attackingOrc = false;
    private long lastChangeFrame = 0;
    private int actualFrame = 0;


    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loadHealthBarOrc(Main.orcEncountered);
                loadExperienceBarOrc();
                loadLevelOrc();
                loadHealthBarPlayer();
                loadExperienceBarPlayer();
                deathControl(Main.player);
                deathControl(Main.orcEncountered);
                updateAnimationPlayer(now);
                updateAnimationOrc(now);


            }
        };
        loadOrcEncountered(Main.orcEncountered);
        loadBackground();
        timer.start();
    }

    public void attack(){
        attack(Main.player, Main.orcEncountered);
        attackingPlayer = true;
        attack(Main.orcEncountered, Main.player);
        attackingOrc = true;
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
            //
        }
    }
    public void deathControl(Entity entity){
        if(entity.getCurrentHp() <= 0){
            System.out.println(entity.getName() + " e morto!");
            if(entity instanceof Orc){
                System.out.println(entity.getName() + " ha droppato " + entity.getExperience() + " punti esperienza");
                Main.player.earnExperience(entity.getExperience());
            }
            timer.stop();
            changeMap((Stage)playerView.getScene().getWindow(), "map-view");
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
        imagePlayer = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/playerAttack.png")));
    }
    public void loadOrc1(){
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orc1Attack.png")));
    }
    public void loadOrc2(){
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orc2Attack.png")));
    }
    public void loadOrc3(){
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/orc3Attack.png")));
    }
    public void loadOrcEncountered(Orc orcEncountered){
        switch (orcEncountered.getLevel()){
            case 1:
                loadOrc1();
                break;
            case 2:
                loadOrc2();
                break;
            case 3:
                loadOrc3();
        }
        orcView.setImage(imageOrc);
        orcView.setSmooth(false);
    }
    public void run() {
        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            changeMap((Stage) playerView.getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(Main.orcEncountered, Main.player);
            attackingOrc = true;
        }
    }
    private void updateAnimationPlayer(long actualHour){
        if(!attackingPlayer){
            playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 8;
            if(actualFrame == 7){
                attackingPlayer = false;
            }
            double xMovement = actualFrame * FRAME_WIDTH;
            playerView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
    private void updateAnimationOrc(long actualHour){
        if(!attackingOrc){
            orcView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 8;
            if(actualFrame == 7){
                attackingOrc = false;
            }
            double xMovement = actualFrame * FRAME_WIDTH;
            orcView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
}
