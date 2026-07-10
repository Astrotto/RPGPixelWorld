package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.geometry.Rectangle2D;

public class AttackView {

    public void executeAttackView(long actualHour, Entity entity){
        if(!entity.getAttack().isAttacking()){
            entity.getEntityView().getView().setViewport(new Rectangle2D(0,0,entity.getEntityView().getFrame().getFrameWidth(),entity.getEntityView().getFrame().getFrameHeight()));
        }else if(actualHour - entity.getEntityView().getFrame().getLastChangeFrame() > 100_000_000){
            entity.getEntityView().getFrame().setActualFrame((entity.getEntityView().getFrame().getActualFrame() + 1) % 11);
            if(entity.getEntityView().getFrame().getActualFrame() == 10){
                entity.getAttack().setAttacking(false);
            }
            double xMovement = entity.getEntityView().getFrame().getActualFrame() * entity.getEntityView().getFrame().getFrameWidth();
            entity.getEntityView().getView().setViewport(new Rectangle2D(xMovement, 0, entity.getEntityView().getFrame().getFrameWidth(), entity.getEntityView().getFrame().getFrameHeight()));
            entity.getEntityView().getFrame().setLastChangeFrame(actualHour);
        }
    }

}
