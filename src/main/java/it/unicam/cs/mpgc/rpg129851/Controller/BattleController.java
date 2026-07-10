package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.System.CombatSystem;
import it.unicam.cs.mpgc.rpg129851.System.DeathSystem;
import it.unicam.cs.mpgc.rpg129851.System.EscapeSystem;
import it.unicam.cs.mpgc.rpg129851.System.HealSystem;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButtonImages;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButtons;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.loadOrcImages;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Timeline.CooldownActivator.cooldownActivation;
import static it.unicam.cs.mpgc.rpg129851.Timeline.CooldownActivator.getPotionsCooldown;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;

public class BattleController extends LoaderController {

    @FXML
    protected ImageView orcView, progressBarViewOrc, btnAttack, btnRun;
    @FXML
    protected Rectangle healthBarOrc, experienceBarOrc;

    private AnimationTimer timer;
    private final CombatSystem combatSystem = new CombatSystem();
    private final DeathSystem deathSystem = new DeathSystem();
    private final HealSystem healSystem = new HealSystem();
    private final EscapeSystem escapeSystem = new EscapeSystem();

    public void initialize(){
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                orcHealthBar.showGameProgressBar();
                orcExperienceBar.showGameProgressBar();
                playerHealthBar.showGameProgressBar();
                playerExperienceBar.showGameProgressBar();
                ViewRegister.of(player).getAttackView().executeAttackView(now, player);
                ViewRegister.of(orcEncountered).getAttackView().executeAttackView(now, orcEncountered);
                deathControl(player, orcEncountered);
                deathControl(orcEncountered, player);
                setPotionObtained();
                loadButtons(btnAttack, btnRun);
            }
        };
        loadButtonImages();
        potionLV1View.setUserData(1);
        potionLV2View.setUserData(2);
        potionLV3View.setUserData(3);

        ViewRegister.ofOrc(orcEncountered).setView(orcView);
        orcHealthBar.setBar(progressBarViewOrc, healthBarOrc);
        orcExperienceBar.setBar(progressBarViewOrc, experienceBarOrc);
        showLevel(orcEncountered, orcHealthBar, levelPane);
        ViewRegister.ofPlayer(player).loadPlayerView();
        setBackgroundView("battleMap.png", backgroundView);
        loadOrcImages(orcEncountered);
        ViewRegister.ofOrc(orcEncountered).loadOrcInBattle();
        timer.start();
    }

    @FXML
    public void attackTurn() {
        combatSystem.attack(player, orcEncountered);
        ViewRegister.of(player).getFrame().setActualFrame(0);
        combatSystem.attack(orcEncountered, player);
        ViewRegister.of(orcEncountered).getFrame().setActualFrame(0);
        cooldownActivation(btnRun, btnAttack, 1.5);
    }

    public void deathControl(Entity attacker, Entity defender){
        if(deathSystem.deathControl(defender)){
            if(deathSystem.deathOrcControl(attacker, defender)){
                ForestController.questCompletedControl();
                timer.stop();
                changeMap("forest");
            }
            if(deathSystem.deathPlayerControl(attacker, defender)){
                timer.stop();
                Platform.exit();
            }
        }
    }

    @FXML
    public void chanceToEscape() {
        if(escapeSystem.escape()){
            timer.stop();
            changeMap("forest");
        }else{
            combatSystem.attack(orcEncountered, player);
        }
        cooldownActivation(btnRun, btnAttack, 1.5);
    }

    @FXML
    private void usePotionInBattle(MouseEvent event){
        if(!(player.getHealth().getStatistic() == player.getHealth().getMaxStatistic()) && !player.getAttack().isAttacking() && !orcEncountered.getAttack().isAttacking() && !getPotionsCooldown()){
            ImageView clicked = (ImageView) event.getSource();
            int level = (int) clicked.getUserData();
            healSystem.potionPressed(clicked, level);
            combatSystem.attack(orcEncountered, player);
        }
    }
}
