package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Player;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ButtonLoader.loadButton;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.setNewScene;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.*;

public class MenuGameController {
    @FXML
    private ImageView btnStart, btnQuit;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Text textMenu;
    private boolean isDeath = false;
    public void initialize(){
        showMenu();
        showButtons();
    }
    public void showMenu(){
        if(!player.isAlive()) {
            textMenu.setText("Sei Morto!");
            isDeath = true;
        }else
            textMenu.setText("Inizia un nuovo Game!");
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
    @FXML
    public void startGame(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if(isDeath){
            player.resetStats();
            player.getAttack().setAttacking(false);
            isDeath = false;
        }
        setNewScene(stage, "map");
        setSpawnPoint(600, 120);

    }

}
