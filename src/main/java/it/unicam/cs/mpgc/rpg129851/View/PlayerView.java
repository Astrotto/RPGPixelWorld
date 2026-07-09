package it.unicam.cs.mpgc.rpg129851.View;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import javafx.geometry.Rectangle2D;

public class PlayerView extends EntityView {

    public void loadPlayerAnimation(long actualHour) {
        if(!this.isMoving()){
            player.getEntityView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - this.getFrame().getLastChangeFrame() > 100_000_000){
            this.getFrame().setActualFrame((this.getFrame().getActualFrame() + 1) % 8);
            double xMovement = this.getFrame().getActualFrame() * FRAME_WIDTH;
            player.getEntityView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            this.getFrame().setLastChangeFrame(actualHour);
        }
    }
}
