package it.unicam.cs.mpgc.rpg129851.Launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class ChangerMap {
    public static void changeMap(String mapName){
        setNewScene((Stage)player.getEntityView().getView().getScene().getWindow(), mapName);
    }

    public static void setNewScene(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(ChangerMap.class.getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + "-view.fxml")));
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
