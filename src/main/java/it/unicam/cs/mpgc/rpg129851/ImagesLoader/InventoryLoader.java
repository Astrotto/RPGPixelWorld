package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;
public class InventoryLoader {

    public static void loadInventory(ImageView slotPotionView, ImageView potionView, Image noPotion){
        setSlotInventory(slotPotionView);
        setPotionsView(potionView, noPotion);
    }
    public static void setSlotInventory(ImageView slotInventory){
        slotInventory.setImage(getInventoryImage());
        slotInventory.setSmooth(false);
    }
    private static Image getInventoryImage(){
        return new Image(Objects.requireNonNull(InventoryLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/inventoryImage.png")));
    }
}
