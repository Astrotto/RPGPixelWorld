package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.GameProgressBar;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.InventoryLoader.loadInventory;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.loadPlayerImages;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.loadPlayerView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.getNoPotionImage;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.loadPotionObtained;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.showLevel;

public class LoaderController {

    @FXML
    protected Rectangle healthBarPlayer, experienceBarPlayer;
    @FXML
    protected ImageView playerView, progressBarViewPlayer, backgroundView,
            slotPotionLV1View, slotPotionLV2View, slotPotionLV3View,
            potionLV1View, potionLV2View, potionLV3View;
    @FXML
    protected Text amountLV1, amountLV2, amountLV3;
    @FXML
    protected Pane levelPane;

    public void initialize() {
        ViewRegister.ofPlayer(player).setView(playerView);
        showGameProgressBar(playerHealthBar, progressBarViewPlayer, healthBarPlayer);
        showGameProgressBar(playerExperienceBar, progressBarViewPlayer, experienceBarPlayer);
        loadPlayerImages();
        showLevel(player, playerHealthBar, levelPane);
        loadPlayerView();
        setInventory();
    }

    private void showGameProgressBar(GameProgressBar gameProgressBar, ImageView progressBarView, Rectangle progressBar) {
        gameProgressBar.setBar(progressBarView, progressBar);
        gameProgressBar.showGameProgressBar();
    }
    private void setInventory(){
        ImageView[] slots = {slotPotionLV1View, slotPotionLV2View, slotPotionLV3View};
        ImageView[] potions = {potionLV1View, potionLV2View, potionLV3View};
        for(int i = 0; i < slots.length; i++){
            loadInventory(slots[i], potions[i], getNoPotionImage(i + 1));
        }
    }
    public void setPotionObtained(){
        ImageView[] potions = {potionLV1View, potionLV2View, potionLV3View};
        Text[] amounts = {amountLV1, amountLV2, amountLV3};
        for(int i = 0; i < potions.length; i++){
            loadPotionObtained(potions[i], amounts[i], i + 1);
        }
    }
}
