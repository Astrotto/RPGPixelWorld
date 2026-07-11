package it.unicam.cs.mpgc.rpg129851.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButton;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class MenuGameController{
    @FXML
    private ImageView btnStart, btnQuit;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Text deathText;
    public void initialize(){
        showMenu();
        showButtons();
    }
    public void showMenu(){
        if(!player.isAlive())
            deathText.setVisible(true);
        setBackgroundView("menuMap.png", backgroundView);
    }
    private void showButtons(){
        loadButton(btnStart, "Start");
        loadButton(btnQuit, "Quit");
    }

    @FXML
    public void quiGame(){
        System.exit(0);
    }
}
