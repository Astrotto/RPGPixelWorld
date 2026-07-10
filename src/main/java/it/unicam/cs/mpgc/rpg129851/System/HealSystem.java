package it.unicam.cs.mpgc.rpg129851.System;

import javafx.scene.image.ImageView;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class HealSystem {

    public void potionPressed(ImageView potionView, int level) {
        if (potionView.isPressed()) {
            if (player.getInventory().getPotionAmount(level) >= 1) {
                player.getHealth().heal(player.getInventory().getPotion(level));
            }
            if (player.getInventory().getPotionAmount(level) == 0)
                setPotionsView(potionView, getNoPotionImage(level));
        }
    }
}
