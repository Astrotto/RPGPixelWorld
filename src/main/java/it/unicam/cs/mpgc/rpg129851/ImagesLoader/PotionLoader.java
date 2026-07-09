package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import static it.unicam.cs.mpgc.rpg129851.Controller.InventoryController.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class PotionLoader {

    private Text amountLV1, amountLV2, amountLV3;

    public static void loadPotionObtained(ImageView potionView, Text amount, int level){
        if(player.getInventory().getPotionAmount(level) > 0){
            potionView.setImage(getPotionImages(level));
            loadAmount(amount, player.getInventory().getPotionAmount(level));
        }else{
            loadAmount(amount, 0);
        }
    }
    private static void loadAmount(Text text, int amount){
        text.setText("" + amount);
    }

}
