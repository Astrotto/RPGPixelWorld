package it.unicam.cs.mpgc.rpg129851.Launch;

import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class ChangerMap {
    public static void changeMap(String mapName){
        setNewScene((Stage) ViewRegister.ofPlayer(player).getView().getScene().getWindow(), mapName);
    }

    public static void setNewScene(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(ChangerMap.class.getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + "-view.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            actualStage.setScene(scene);
            actualStage.show();
            root.requestFocus();
        }catch(Exception e){
            PrintGameLog.error("Loading error of the " + map + " scene");
        }
    }
}
