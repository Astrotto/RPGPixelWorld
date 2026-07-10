package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Timeline.CooldownActivator.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.*;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import it.unicam.cs.mpgc.rpg129851.System.*;
import javafx.animation.AnimationTimer;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BattleController extends LoaderController {

    AnimationTimer timer;
    private CombatSystem combatSystem = new CombatSystem();
    private DeathSystem deathSystem = new DeathSystem();
    private HealSystem healSystem = new HealSystem();
    private EscapeSystem escapeSystem = new EscapeSystem();


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
                loadButtons(btnAttack,  btnRun);
            }
        };
        loadButtonImages();
        potionLV1View.setUserData(1);
        potionLV2View.setUserData(2);
        potionLV3View.setUserData(3);


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
                changeMap("forest");
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
            combatSystem.executeAttack(orcEncountered.getAttack(), player);
        }
    }


}
