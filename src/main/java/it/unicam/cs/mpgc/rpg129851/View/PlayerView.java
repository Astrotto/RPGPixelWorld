package it.unicam.cs.mpgc.rpg129851.View;
import javafx.geometry.Rectangle2D;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.getImagePlayerAttack;

public class PlayerView extends EntityView {
    public void loadPlayerAnimation(long actualHour) {
        if(!this.isMoving()){
            this.setViewport(new Rectangle2D(0,0,this.getFrame().getFrameWidth(),this.getFrame().getFrameHeight()));
        }else if(actualHour - this.getFrame().getLastChangeFrame() > 100_000_000){
            this.getFrame().setActualFrame((this.getFrame().getActualFrame() + 1) % 8);
            double xMovement = this.getFrame().getActualFrame() * this.getFrame().getFrameWidth();
            this.setViewport(new Rectangle2D(xMovement, 0, this.getFrame().getFrameWidth(), this.getFrame().getFrameHeight()));
            this.getFrame().setLastChangeFrame(actualHour);
        }
    }
    public void loadPlayerView(){
        this.setImage(getImagePlayerAttack());
        this.setViewport(new Rectangle2D(0, 0, this.getFrame().getFrameWidth(), this.getFrame().getFrameHeight()));
        this.setSmooth(false);
    }
}
