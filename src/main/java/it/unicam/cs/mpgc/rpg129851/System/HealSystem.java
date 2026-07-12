package it.unicam.cs.mpgc.rpg129851.System;

import javafx.scene.image.ImageView;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class HealSystem {
    private final ImageView potionView;
    private final int level;

    public HealSystem(ImageView potionView, int level) {
        this.potionView = potionView;
        this.level = level;
    }

    public boolean usePotion() {
        if (potionView.isPressed()) {
            if (player.getInventory().getPotionAmount(level) >= 1) {
                player.getHealth().heal(player.getInventory().getPotion(level));
                if (player.getInventory().getPotionAmount(level) == 0)
                    setPotionsView(potionView, getNoPotionImage(level));
                return true;
            }

        }
        return false;
    }
}
