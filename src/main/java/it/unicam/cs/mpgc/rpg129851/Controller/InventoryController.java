package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class InventoryController {
    public static Image inventory,
            noPotionLV1, noPotionLV2, noPotionLV3,
            potionLV1, potionLV2, potionLV3;

    public static void loadInventory(){
        loadInventoryImage();
        loadNoPotionImages();
        loadPotionImages();
    }
    private static void loadInventoryImage(){
        inventory = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/inventoryImage.png")));
    }
    private static void loadNoPotionImages(){
        noPotionLV1 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/noPotionLV1.png")));
        noPotionLV2 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/noPotionLV2.png")));
        noPotionLV3 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/noPotionLV3.png")));
    }
    private static void loadPotionImages(){
        potionLV1 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/potionLV1.png")));
        potionLV2 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/potionLV2.png")));
        potionLV3 = new Image(Objects.requireNonNull(InventoryController.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/potionImages/potionLV3.png")));
    }

    public static void setSlotPotions(ImageView slotPotion){
        slotPotion.setImage(inventory);
        slotPotion.setSmooth(false);
    }
    public static void setPotionsView(ImageView potionsView, Image noPotion){
        potionsView.setImage(noPotion);
        potionsView.setSmooth(false);
    }

}
