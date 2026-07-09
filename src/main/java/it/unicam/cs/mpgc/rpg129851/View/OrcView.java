package it.unicam.cs.mpgc.rpg129851.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.Controller.OrcController.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.getImageOrc;

public class OrcView extends EntityView {

    public void setOrcView(){
        this.setEntityView(new ImageView(getImageOrc()));
        this.setViewport(new Rectangle2D(0, 0 , 100, 100));
        this.getView().setFitWidth(260);
        this.getView().setFitHeight(260);
        this.getView().setPreserveRatio(true);
    }

}
