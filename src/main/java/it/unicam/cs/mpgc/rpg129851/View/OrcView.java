package it.unicam.cs.mpgc.rpg129851.View;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.OrcLoader.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class OrcView extends EntityView {

    public void loadOrcInBattle(){
        this.getView().setScaleX(-1);
        this.setImage(getImageOrcAttack());
        this.setViewport(new Rectangle2D(0, 0, this.getFrame().getFrameWidth(), this.getFrame().getFrameHeight()));
        this.setSmooth(false);
    }
    public void setOrcView(){
        this.setEntityView(new ImageView(getImageOrc()));
        this.setViewport(new Rectangle2D(0, 0 , this.getFrame().getFrameWidth(), this.getFrame().getFrameHeight()));
        this.getView().setFitWidth(260);
        this.getView().setFitHeight(260);
        this.getView().setPreserveRatio(true);
    }

}
