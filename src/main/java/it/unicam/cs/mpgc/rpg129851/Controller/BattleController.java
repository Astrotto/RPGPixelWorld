package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;

import javafx.fxml.FXML;

import javafx.geometry.Rectangle2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.Random;

public class BattleController extends LoaderController {

    int percentageOfEscape = 25;
    private final int FRAME_WIDTH = 100;
    private final int FRAME_HEIGHT = 100;
    AnimationTimer timer;
    private boolean potionsCooldown = false;


    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                orcHealthBar.showHealthBar();
                //orcExperienceBar.showExperienceBar();
                //loadExperienceBar(experienceBarOrc, healthBarViewOrc, orcEncountered);
                setLevelOrc();
                playerHealthBar.showHealthBar();
                //playerExperienceBar.showExperienceBar();
                //loadExperienceBar(experienceBarPlayer, healthBarViewPlayer, player);
                updateAnimation(now, player);
                updateAnimation(now, orcEncountered);
                updateAnimationOrc(now);
                deathControl(player);
                deathControl(orcEncountered);
                loadButtons();
                setPotionObtained();
            }
        };
        orcEncountered.setEntityView(orcView);
        orcHealthBar.setBar(healthBarViewOrc ,healthBarOrc);
        loadButtonImages();
        loadOrcEncountered(orcEncountered);
        loadBackground();
        timer.start();
    }

    public void attack() {
        attack(player, orcEncountered);
        player.setAttacking(true);
        player.setActualFrame(0);

        attack(orcEncountered, player);
        orcEncountered.setAttacking(true);
        orcEncountered.setActualFrame(0);

        cooldownActivation(btnRun, btnAttack, 1.5);
    }
    private void attack(Entity attacker, Entity defender){
        int damage;
        if(defender.getHealth().getCurrentStats() <= 0){

        }else {
            damage = attacker.attack(defender);
            if (criticalHit) {
                criticalHit = false;
                System.out.println(attacker.getName() + " di LVL" + attacker.getExperience().getLevel() + " ha inflitto " + damage + " danni a " + defender.getName() + " con un COLPO CRITICO!");
            } else {
                System.out.println(attacker.getName()  + " di LVL" + attacker.getExperience().getLevel() + " ha inflitto " + damage + " danni a " + defender.getName());
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
                player.getExperience().earnExperience(entity.getExperience().getCurrentStats());
                player.getHealth().heal(20);
                player.setAttacking(false);
                timer.stop();
                changeMap((Stage) player.getEntityView().getScene().getWindow(), "forest-view");
            }
        }
    }

    public void loadBackground(){
        loadBackground("battleMap.png");
        backgroundView.setLayoutY(backgroundView.getLayoutY() - 170);
        backgroundView.setLayoutX(backgroundView.getLayoutX() + 30);
    }

    public void loadEntity(){
        player.getEntityView().setImage(imagePlayerAttack);

        orcEncountered.getEntityView().setImage(imageOrcAttack);

        orcEncountered.getEntityView().setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcEncountered.getEntityView().setSmooth(false);

        player.getEntityView().setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        player.getEntityView().setSmooth(false);
    }

    public void loadOrcEncountered(Orc orcEncountered){
        loadOrcImages(orcEncountered);
        orcEncountered.getEntityView().setScaleX(-1);
        orcEncountered.getEntityView().setImage(imageOrcAttack);
        orcEncountered.getEntityView().setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcEncountered.getEntityView().setSmooth(false);
    }
    public void run() {
        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            timer.stop();
            changeMap((Stage) player.getEntityView().getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(orcEncountered, player);
            orcEncountered.setAttacking(true);
        }
        cooldownActivation(btnRun, btnAttack, 1.5);
    }
    private void updateAnimation(long actualHour, Entity entity){
        if(!entity.isAttacking()){
            entity.getEntityView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - entity.getLastChangeFrame() > 100_000_000){
            entity.setActualFrame((entity.getActualFrame() + 1) % 11);
            if(entity.getActualFrame() == 10){
                entity.setAttacking(false);
            }
            double xMovement = entity.getActualFrame() * FRAME_WIDTH;
            entity.getEntityView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            entity.setLastChangeFrame(actualHour);
        }
    }

    private void updateAnimationOrc(long actualHour){
        if(!orcEncountered.isAttacking()){
            orcEncountered.getEntityView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - orcEncountered.getLastChangeFrame() > 100_000_000){
            orcEncountered.setActualFrame((orcEncountered.getActualFrame() + 1) % 11);
            if(orcEncountered.getActualFrame() == 10){
                orcEncountered.setAttacking(false);
            }
            double xMovement = orcEncountered.getActualFrame() * FRAME_WIDTH;
            orcEncountered.getEntityView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            orcEncountered.setLastChangeFrame(actualHour);
        }
    }

    private void cooldownActivation(ImageView button1, ImageView button2, double duration){
        button1.setDisable(true);
        button2.setDisable(true);
        potionsCooldown = true;
        loadButtonDisabled();
        PauseTransition cooldown = new PauseTransition(Duration.seconds(duration));
        cooldown.setOnFinished(event ->{
            loadButtonImages();
            button1.setDisable(false);
            button2.setDisable(false);
        });
        potionsCooldown = false;
        cooldown.play();
    }
    @FXML
    private void potionUsed(){
        if(!(player.getHealth().getCurrentStats() == player.getHealth().getMaxStats()) && !player.isAttacking() && !orcEncountered.isAttacking() && !potionsCooldown){
            potionSelected(potionLV1View, 1, InventoryController.noPotionLV1);
            potionSelected(potionLV2View, 2, InventoryController.noPotionLV2);
            potionSelected(potionLV3View, 3, InventoryController.noPotionLV3);

        }
    }
    private void potionSelected(ImageView potionView, int level, Image potionImage) {
        if (potionView.isPressed()) {
            if (player.getInventory().getPotionAmount(level) >= 1) {
                player.usePotion(level);
                attack(orcEncountered, player);
                orcEncountered.setAttacking(true);
                cooldownActivation(btnRun, btnAttack, 1.5);
            }
            if (player.getInventory().getPotionAmount(level) == 0)
                InventoryController.setPotionsView(potionView, potionImage);
        }
    }
}
