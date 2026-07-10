package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;

import it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import it.unicam.cs.mpgc.rpg129851.System.*;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.util.Duration;

public class BattleController extends LoaderController {

    AnimationTimer timer;
    private boolean potionsCooldown = false;
    private CombatSystem combatSystem = new CombatSystem();
    private DeathSystem deathSystem = new DeathSystem();
    private HealSystem healSystem = new HealSystem();
    private EscapeSystem escapeSystem = new EscapeSystem();

    private ButtonLoader buttonLoader;


    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                orcHealthBar.showGameProgressBar();
                orcExperienceBar.showGameProgressBar();
                playerHealthBar.showGameProgressBar();
                playerExperienceBar.showGameProgressBar();
                player.getEntityView().getAttackView().executeAttackView(now, player);
                orcEncountered.getEntityView().getAttackView().executeAttackView(now, orcEncountered);
                deathControl(player, orcEncountered);
                deathControl(orcEncountered, player);

                setPotionObtained();
                buttonLoader.loadButtons();
            }
        };
        potionLV1View.setUserData(1);
        potionLV2View.setUserData(2);
        potionLV3View.setUserData(3);


        buttonLoader = new ButtonLoader(btnAttack, btnRun);
        orcEncountered.getEntityView().setView(orcView);
        orcHealthBar.setBar(progressBarViewOrc,healthBarOrc);
        orcExperienceBar.setBar(progressBarViewOrc,experienceBarOrc);
        showLevel(orcEncountered, orcHealthBar, levelPane);
        player.getEntityView().loadPlayerView();
        setBackgroundView("battleMap.png", backgroundView);
        loadOrcImages(orcEncountered);
        orcEncountered.getEntityView().loadOrcInBattle();
        timer.start();
    }

    @FXML
    public void attackTurn() {
        combatSystem.attack(player, orcEncountered);
        player.getEntityView().getFrame().setActualFrame(0);
        combatSystem.attack(orcEncountered, player);
        orcEncountered.getEntityView().getFrame().setActualFrame(0);
        cooldownActivation(btnRun, btnAttack, 1.5);
    }

    public void deathControl(Entity attacker, Entity defender){
        if(deathSystem.deathControl(defender)){
            if(deathSystem.deathOrcControl(attacker, defender)){
                ForestController.questCompletedControl();
                timer.stop();
                changeMap((Stage) attacker.getEntityView().getView().getScene().getWindow(), "forest");
            }
            if(deathSystem.deathPlayerControl(attacker, defender)){
                //da cambiare
                timer.stop();
                System.exit(0);
            }
        }
    }

    @FXML
    public void chanceToEscape() {
        if(escapeSystem.escape()){
            timer.stop();
            changeMap((Stage) player.getEntityView().getView().getScene().getWindow(), "forest");
        }else{
            combatSystem.attack(orcEncountered, player);
        }
        cooldownActivation(btnRun, btnAttack, 1.5);
    }

    private void cooldownActivation(ImageView button1, ImageView button2, double duration){
        button1.setDisable(true);
        button2.setDisable(true);
        potionsCooldown = true;
        buttonLoader.loadButtonDisabled();
        PauseTransition cooldown = new PauseTransition(Duration.seconds(duration));
        cooldown.setOnFinished(event ->{
            buttonLoader.loadButtonImages();
            button1.setDisable(false);
            button2.setDisable(false);
        });
        potionsCooldown = false;
        cooldown.play();
    }

    @FXML
    private void usePotionInBattle(MouseEvent event){
        if(!(player.getHealth().getStatistic() == player.getHealth().getMaxStatistic()) && !player.getAttack().isAttacking() && !orcEncountered.getAttack().isAttacking() && !potionsCooldown){
            ImageView clicked = (ImageView) event.getSource();
            int level = (int) clicked.getUserData();
            healSystem.potionPressed(clicked, level);
        }
    }


}
