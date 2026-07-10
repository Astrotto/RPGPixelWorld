package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.OrcView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.loadOrcImages;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.orcs;

public class OrcController {
    private static final Random rand = new Random();

    public static void placeOrcRandomlyInACorner(Rectangle spawnCorner, Pane orcSpawn) {
        int numeroOrchi = rand.nextInt(4) + 1;
        for (int i = 0; i < numeroOrchi; i++) {
            Orc orcSpawned = spawnOrc();
            placeOrc(orcSpawned, spawnCorner);
            orcSpawn.getChildren().add(ViewRegister.ofOrc(orcSpawned).getView());
        }
    }
    private static Orc spawnOrc(){
        Orc orc = generateOrc();
        loadOrcImages(orc);
        OrcView view = new OrcView();
        ViewRegister.register(orc, view);
        view.setOrcView();
        orcs.add(orc);
        return orc;
    }
    private static Orc generateOrc(){
        int randomLevel = rand.nextInt(10) + 1;
        int lvlOrc;
        if(randomLevel <= 6){
            lvlOrc = 1;
        }else if(randomLevel <= 9){
            lvlOrc = 2;
        }else{
            lvlOrc = 3;
        }
        return new Orc(lvlOrc);
    }
    private static void placeOrc(Orc orc, Rectangle spawnCorner){
        OrcView view = ViewRegister.ofOrc(orc);
        view.setLayoutX(setXOrc(spawnCorner, view.getView()));
        view.setLayoutY(setYOrc(spawnCorner, view.getView()));
    }
    private static double setXOrc(Rectangle spawnCorner, ImageView orcView){
        double orcWidth = orcView.getBoundsInParent().getWidth();
        double minX = spawnCorner.getLayoutX();
        double maxX = minX + spawnCorner.getWidth() - orcWidth;
        return minX + (maxX - minX) * rand.nextDouble();
    }
    private static double setYOrc(Rectangle spawnCorner, ImageView orcView){
        double orcHeight = orcView.getBoundsInParent().getHeight();
        double minY = spawnCorner.getLayoutY();
        double maxY = minY + spawnCorner.getHeight() - orcHeight;
        return minY + (maxY - minY) * rand.nextDouble();
    }
}
