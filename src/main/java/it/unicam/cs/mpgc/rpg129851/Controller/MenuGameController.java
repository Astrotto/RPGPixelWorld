package it.unicam.cs.mpgc.rpg129851.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;

public class MenuGameController{
    @FXML
    private ImageView btnStart;
    @FXML
    private ImageView backgroundView;

    public void showMenu(){
        setBackgroundView("menuMap.png", backgroundView);
    }
    public void initialize(){
        showMenu();
    }
    @FXML
    public void startGame(){
        changeMap("map");
    }
}
