package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Objects;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class PotionLoader {

    public static void loadPotionObtained(ImageView potionView, Text amount, int level){
        if(player.getInventory().getPotionAmount(level) > 0){
            potionView.setImage(getPotionImage(level));
            loadAmount(amount, player.getInventory().getPotionAmount(level));
        }else{
            loadAmount(amount, 0);
        }
    }
    public static Image getPotionImage(int level){
        return new Image(Objects.requireNonNull(InventoryLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/potionLV" + level + ".png")));
    }
    private static void loadAmount(Text text, int amount){
        text.setText("" + amount);
    }
    public static Image getNoPotionImage(int level){
        return new Image(Objects.requireNonNull(InventoryLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/noPotionLV" + level + ".png")));
    }
    public static void setPotionsView(ImageView potionsView, Image potionImage){
        potionsView.setImage(potionImage);
        potionsView.setSmooth(false);
    }
}
