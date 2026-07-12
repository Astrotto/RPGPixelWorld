package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Interface.GameLogger;
import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.guardian;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class GuardianController {
    private static boolean questReceived = false;
    private static int howMuch;
    static GameLogger questLoad = new PrintGameLog();


    public static void meetForestSpirit(Circle forestSpirit) {
        PlayerView view = ViewRegister.ofPlayer(player);
        Bounds hitboxPlayer = player.getHitbox(view.getLayoutX() + 70, view.getLayoutY() + 55);
        Bounds hitboxForestSpirit = forestSpirit.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForestSpirit)) {
            if(!questReceived){
                questLoad.info(guardian.getRandomQuest().toString());
                howMuch = guardian.getQuestReceived().getHowMuch();
                questReceived = true;
            }
        }
    }
    public static void questCompletedControl(){
        if(questReceived){
            if(orcEncountered.getExperience().getLevel().getActualLevel() == guardian.getQuestReceived().getLevel() && guardian.getQuestReceived().getHowMuch() >= 1){
                howMuch--;
                if(howMuch < 1) {
                    questLoad.info("Hai completato la quest");
                    player.getInventory().addPotion(guardian.getPotionReward(guardian.getQuestReceived().getPotionRewardLevel()));
                    questReceived = false;
                }
            }
        }
    }
    public static void removeQuest(){
        questReceived = false;
    }
}
