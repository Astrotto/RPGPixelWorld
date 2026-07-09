package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Controller.InventoryController.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;

import it.unicam.cs.mpgc.rpg129851.Model.CombatSystem;
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
    private CombatSystem combatSystem = new CombatSystem();


    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                orcHealthBar.showHealthBar();
                orcExperienceBar.showExperienceBar();
                playerHealthBar.showHealthBar();
                playerExperienceBar.showExperienceBar();
                player.getEntityView().updateAnimation(now, player);
                orcEncountered.getEntityView().updateAnimation(now, orcEncountered);
                deathControl(player);
                deathControl(orcEncountered);
                loadButtons();
                setPotionObtained();
            }
        };
        orcEncountered.getEntityView().setEntityView(orcView);
        orcHealthBar.setBar(progressBarViewOrc,healthBarOrc);
        orcExperienceBar.setBar(progressBarViewOrc,experienceBarOrc);
        showLevel(orcEncountered, orcHealthBar, levelPane);
        loadButtonImages();
        loadOrcEncountered(orcEncountered);
        loadBackground();
        timer.start();
    }

    public void attack() {
        attack(player, orcEncountered);
        player.getAttack().setAttacking(true);
        player.getEntityView().getFrame().setActualFrame(0);

        attack(orcEncountered, player);
        orcEncountered.getAttack().setAttacking(true);
        orcEncountered.getEntityView().getFrame().setActualFrame(0);

        cooldownActivation(btnRun, btnAttack, 1.5);
    }
    private void attack(Entity attacker, Entity defender){
        int damage;
        if(defender.getHealth().getStatistic() <= 0){

        }else {
            combatSystem.executeAttack(attacker.getAttack(), defender);
            if (attacker.getAttack().isCriticalHit()) {
                attacker.getAttack().setCriticalHit(false);
                System.out.println(attacker.getName() + " di LVL" + attacker.getExperience().getLevel() + " ha inflitto " + attacker.getAttack().getFinalDamage() + " danni a " + defender.getName() + " con un COLPO CRITICO!");
            } else {
                System.out.println(attacker.getName()  + " di LVL" + attacker.getExperience().getLevel() + " ha inflitto " + attacker.getAttack().getFinalDamage() + " danni a " + defender.getName());
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
                System.out.println(entity.getName() + " ha droppato " + entity.getExperience().getStatistic() + " punti esperienza");
                player.getExperience().earnExperience(player, entity.getExperience().getStatistic());
                player.getHealth().heal(20);
                player.getAttack().setAttacking(false);
                timer.stop();
                changeMap((Stage) player.getEntityView().getView().getScene().getWindow(), "forest-view");
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
        orcEncountered.getEntityView().getView().setScaleX(-1);
        orcEncountered.getEntityView().setImage(imageOrcAttack);
        orcEncountered.getEntityView().setViewport(new Rectangle2D(0, 0, FRAME_WIDTH, FRAME_HEIGHT));
        orcEncountered.getEntityView().setSmooth(false);
    }
    public void run() {
        Random random = new Random();
        if(random.nextInt(100) < percentageOfEscape){
            System.out.println("Sei scappato dall'orco");
            timer.stop();
            changeMap((Stage) player.getEntityView().getView().getScene().getWindow(), "forest-view");
        }else{
            percentageOfEscape += 15;
            attack(orcEncountered, player);
            orcEncountered.getAttack().setAttacking(true);
        }
        cooldownActivation(btnRun, btnAttack, 1.5);
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
        if(!(player.getHealth().getStatistic() == player.getHealth().getMaxStatistic()) && !player.getAttack().isAttacking() && !orcEncountered.getAttack().isAttacking() && !potionsCooldown){
            potionSelected(potionLV1View, 1, noPotionLV1);
            potionSelected(potionLV2View, 2, noPotionLV2);
            potionSelected(potionLV3View, 3, noPotionLV3);

        }
    }
    private void potionSelected(ImageView potionView, int level, Image potionImage) {
        if (potionView.isPressed()) {
            if (player.getInventory().getPotionAmount(level) >= 1) {
                player.getHealth().heal(player.getInventory().getPotion(level));
                attack(orcEncountered, player);
                orcEncountered.getAttack().setAttacking(true);
                cooldownActivation(btnRun, btnAttack, 1.5);
            }
            if (player.getInventory().getPotionAmount(level) == 0)
                setPotionsView(potionView, potionImage);
        }
    }
}
