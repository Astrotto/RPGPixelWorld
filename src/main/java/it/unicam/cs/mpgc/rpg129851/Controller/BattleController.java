package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.System.CombatSystem;
import it.unicam.cs.mpgc.rpg129851.System.DeathSystem;
import it.unicam.cs.mpgc.rpg129851.System.EscapeSystem;
import it.unicam.cs.mpgc.rpg129851.System.HealSystem;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.Controller.GuardianController.questCompletedControl;
import static it.unicam.cs.mpgc.rpg129851.Controller.GuardianController.removeQuest;
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
    CombatSystem playerAttack = new CombatSystem(player, orcEncountered);
    CombatSystem orcAttack = new CombatSystem(orcEncountered, player);
    DeathSystem orcDeath = new DeathSystem(player, orcEncountered);
    DeathSystem playerDeath = new DeathSystem(orcEncountered, player);
    EscapeSystem escapeSystem = new EscapeSystem();

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
                deathControl();
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
        playerAttack.attack();
        ViewRegister.of(player).getFrame().setActualFrame(0);
        orcAttack.attack();
        ViewRegister.of(orcEncountered).getFrame().setActualFrame(0);
        cooldownActivation(btnRun, btnAttack, 1.5);
    }
    public void deathControl() {
        if (orcDeath.checkDeath()) {
            handleOrcDefeated();
        } else if (playerDeath.checkDeath()) {
            handlePlayerDefeated();
        }
    }

    private void handleOrcDefeated() {
        questCompletedControl();
        timer.stop();
        changeMap("forest");
    }
    private void handlePlayerDefeated() {
        removeQuest();
        timer.stop();
        changeMap("menu");
    }

    @FXML
    public void chanceToEscape() {
        if(escapeSystem.tryEscape()){
            timer.stop();
            changeMap("forest");
        }else{
            orcAttack.attack();
        }
        cooldownActivation(btnRun, btnAttack, 1.5);
    }

    @FXML
    private void usePotionInBattle(MouseEvent event){
        ImageView clicked = (ImageView) event.getSource();
        int level = (int) clicked.getUserData();
        HealSystem healSystem = new HealSystem(clicked, level);
        if(!(player.getHealth().getStatistic() == player.getHealth().getMaxStatistic()) && !player.getAttack().isAttacking() && !orcEncountered.getAttack().isAttacking() && !getPotionsCooldown()){
            if(healSystem.usePotion()) {
                orcAttack.attack();
                cooldownActivation(btnRun, btnAttack, 1.5);
            }
        }
    }
}
