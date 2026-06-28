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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.util.Duration;
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
        btnRun.setDisable(true);

        attack(Main.player, Main.orcEncountered);
        attackingPlayer = true;
        actualFrame = 0;
        attack(Main.orcEncountered, Main.player);
        attackingOrc = true;
        actualFrame = 0;

        cooldownActivation(btnRun, btnAttack);
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
        if(!entity.isAlive()){
            if(entity instanceof Player){
                System.exit(0);
            }
            System.out.println(entity.getName() + " e morto!");
            if(entity instanceof Orc){
                ForestController.questCompletedControl();
                System.out.println(entity.getName() + " ha droppato " + entity.getExperience() + " punti esperienza");
                Main.player.earnExperience(entity.getExperience());
                Main.player.addHp(20);
                timer.stop();
                changeMap((Stage)playerView.getScene().getWindow(), "forest-view");
            }
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

    public void loadOrcEncountered(Orc orcEncountered){
        loadOrcAttack(orcEncountered);
        orcView.setImage(imageOrcAttack);
        orcView.setSmooth(false);
    }
    public void run() {
        btnRun.setDisable(true);
        btnAttack.setDisable(true);

        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            changeMap((Stage) playerView.getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(Main.orcEncountered, Main.player);
            attackingOrc = true;
        }
        cooldownActivation(btnRun, btnAttack);
    }
    private void updateAnimationPlayer(long actualHour){
        orcView.setScaleX(-1);
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
            orcView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 11;
            if(actualFrame == 10){
                attackingOrc = false;
            }
            double xMovement = actualFrame * FRAME_WIDTH;
            orcView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
    private void cooldownActivation(Button button1, Button button2){
        PauseTransition cooldown = new PauseTransition(Duration.seconds(2.5));
        cooldown.setOnFinished(event ->{
            button1.setDisable(false);
            button2.setDisable(false);
        });
        cooldown.play();
    }
}
