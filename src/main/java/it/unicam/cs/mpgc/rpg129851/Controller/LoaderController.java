package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.InventoryLoader.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.*;


import it.unicam.cs.mpgc.rpg129851.ImagesLoader.InventoryLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoaderController {

    @FXML
    public Rectangle forest, home, graveyard,
                     healthBarPlayer, healthBarOrc,
                     experienceBarPlayer, experienceBarOrc;
    @FXML
    public static Image imageExclamation;
    @FXML
    public ImageView playerView, orcView,
                     progressBarViewPlayer, progressBarViewOrc,
                     experienceBarViewPlayer, experienceBarViewOrc,
                     backgroundView,
                     slotPotionLV1View, slotPotionLV2View, slotPotionLV3View,
                     potionLV1View, potionLV2View, potionLV3View,
                     btnAttack, btnRun;
    public Text amountLV1, amountLV2, amountLV3;
    public Pane gameWorld, levelPane;

    public void initialize() {
        player.getEntityView().setEntityView(playerView);
        playerHealthBar.setBar(progressBarViewPlayer, healthBarPlayer);
        playerExperienceBar.setBar(progressBarViewPlayer, experienceBarPlayer);

        playerHealthBar.showHealthBar();
        playerExperienceBar.showExperienceBar();
        loadPlayerImages();
        showLevel(player, playerHealthBar, levelPane);
        loadPlayerView();
        setInventory();
    }

    private void setInventory(){
        loadInventory(slotPotionLV1View, potionLV1View, getNoPotionImage(1));
        loadInventory(slotPotionLV2View, potionLV2View, getNoPotionImage(2));
        loadInventory(slotPotionLV3View, potionLV3View, getNoPotionImage(3));

    }
    public void setPotionObtained(){
        loadPotionObtained(potionLV1View, amountLV1, 1);
        loadPotionObtained(potionLV2View, amountLV2, 2);
        loadPotionObtained(potionLV3View, amountLV3, 3);

    }











    public void changeMap(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + "-view.fxml")));
            Parent root = loader.load();
            Stage stage = actualStage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            root.requestFocus();
        }catch(Exception e){
            System.err.println("Loading error of the " + map + " scene");
        }
    }
}
