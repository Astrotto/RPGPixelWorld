package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class BattleController extends LoaderController {
    @FXML
    private ImageView playerView, orcView;
    @FXML
    private Button btnAttack, btnRun;
    int percentageOfEscape = 25;

    private final int FRAME_WIDTH = 100;
    private final int FRAME_HEIGHT = 100;
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
                updateAnimationPlayer(now);
                updateAnimationOrc(now);
                deathControl(Main.player);
                deathControl(Main.orcEncountered);
            }
        };
        loadOrcEncountered(Main.orcEncountered);
        loadBackground();
        timer.start();
    }

    public void attack(){
        btnAttack.setDisable(true);

        attack(Main.player, Main.orcEncountered);
        attackingPlayer = true;
        actualFrame = 0;
        attack(Main.orcEncountered, Main.player);
        attackingOrc = true;
        actualFrame = 0;

        PauseTransition cooldown = new PauseTransition(Duration.seconds(2));
        cooldown.setOnFinished(event -> btnAttack.setDisable(false));
        cooldown.play();
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
                System.out.println(attacker.getMaxExp()  + " di LVL" + attacker.getLevel() + " ha inflitto " + damage + " danni a " + defender.getName());
            }
        }
    }
    public void deathControl(Entity entity){
        if(!entity.isAlive()){
            if(entity instanceof Player){
                //System.exit(0);
            }
            System.out.println(entity.getName() + " e morto!");
            if(entity instanceof Orc){
                System.out.println(entity.getName() + " ha droppato " + entity.getExperience() + " punti esperienza");
                Main.player.earnExperience(entity.getExperience());
                Main.player.addHp(15); //da mettere 10 senno troppo facile
            }
            timer.stop();
            changeMap((Stage)playerView.getScene().getWindow(), "forest-view");
        }

    }

    public void loadBackground(){
        loadBackground("battleMap.png");
        backgroundView.setLayoutY(backgroundView.getLayoutY() - 170);
        backgroundView.setLayoutX(backgroundView.getLayoutX() + 30);
    }

    public void loadEntity(){
        orcView.setImage(imageOrcAttack);
        playerView.setImage(imagePlayerAttack);

        orcView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcView.setSmooth(false);

        playerView.setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        playerView.setSmooth(false);
    }


    public void loadOrc1(){
        imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV1Images/armoredOrcAttack.png")));
    }
    public void loadOrc2(){
        imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV2Images/eliteOrcAttack.png")));
    }
    public void loadOrc3(){
        imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV3Images/riderOrcAttack.png")));
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
        orcView.setImage(imageOrcAttack);
        orcView.setSmooth(false);
    }
    public void run() {

        btnRun.setDisable(true);

        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            changeMap((Stage) playerView.getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(Main.orcEncountered, Main.player);
            attackingOrc = true;
        }

        PauseTransition cooldown = new PauseTransition(Duration.seconds(1.5));

        cooldown.setOnFinished(event -> btnRun.setDisable(false));

        cooldown.play();

    }
    private void updateAnimationPlayer(long actualHour){
        playerView.setScaleX(-1);
        if(!attackingPlayer){
            playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 11;
            if(actualFrame == 10){
                attackingPlayer = false;
            }
            double xMovement = actualFrame * FRAME_WIDTH;
            playerView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
    private void updateAnimationOrc(long actualHour){
        if(!attackingOrc){
            orcView.setViewport(new Rectangle2D(0,0,100,100));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 11;
            if(actualFrame == 10){
                attackingOrc = false;
            }
            double xMovement = actualFrame * 100;
            orcView.setViewport(new Rectangle2D(xMovement, 0, 100, 100));
            lastChangeFrame = actualHour;
        }
    }
}
