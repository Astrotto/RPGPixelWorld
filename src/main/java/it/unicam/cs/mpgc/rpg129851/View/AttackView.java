package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.geometry.Rectangle2D;

public class AttackView {

    public void executeAttackView(long actualHour, Entity entity){
        EntityView view = ViewRegister.of(entity);
        Frame frame = view.getFrame();
        if(!entity.getAttack().isAttacking()){
            view.getView().setViewport(new Rectangle2D(0, 0, frame.getFrameWidth(), frame.getFrameHeight()));
        }else if(actualHour - frame.getLastChangeFrame() > 100_000_000){
            frame.setActualFrame((frame.getActualFrame() + 1) % 11);
            if(frame.getActualFrame() == 10){
                entity.getAttack().setAttacking(false);
            }
            double xMovement = frame.getActualFrame() * frame.getFrameWidth();
            view.getView().setViewport(new Rectangle2D(xMovement, 0, frame.getFrameWidth(), frame.getFrameHeight()));
            frame.setLastChangeFrame(actualHour);
        }
    }
}
