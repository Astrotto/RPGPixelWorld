package it.unicam.cs.mpgc.rpg129851.Controller;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class OrcController {
    private static Random rand = new Random();

    public static void placeOrcRandomlyInACorner(Rectangle spawnCorner, Pane orcSpawn) {
        int numeroOrchi = rand.nextInt(4) + 1;
        for (int i = 0; i < numeroOrchi; i++) {
            Orc orcSpawned = spawnOrc();
            //orcView.setVisible(false);
            placeOrc(orcSpawned, spawnCorner);
            orcSpawn.getChildren().add(orcSpawned.getEntityView().getView());
        }
    }
    private static Orc spawnOrc(){
        Orc orc = generateOrc();
        loadOrcImages(orc);
        orc.getEntityView().setOrcView();
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
        Orc orc = new Orc(lvlOrc);

        return orc;
    }
    private static void placeOrc(Orc orc, Rectangle spawnCorner){
        orc.getEntityView().setLayoutX(setXOrc(spawnCorner, orc.getEntityView().getView()));
        orc.getEntityView().setLayoutY(setYOrc(spawnCorner, orc.getEntityView().getView()));
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
