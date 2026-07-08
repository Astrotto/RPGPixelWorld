package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
    private final int FRAME_WIDTH = 100;
    private final int FRAME_HEIGHT = 100;
    private ImageView entityView;

    public EntityView() {
        this.entityView = new ImageView();
    }
    public ImageView getView() {
        return this.entityView;
    }
    public void setEntityView(ImageView entityView) {
        this.entityView = entityView;
    }
    public void setImage(Image image){
        this.entityView.setImage(image);
    }
    public Rectangle2D getViewport(){
        return this.entityView.getViewport();
    }
    public void setViewport(Rectangle2D rectangle2D){
        this.entityView.setViewport(rectangle2D);
    }
    public void setSmooth(boolean smooth){
        this.entityView.setSmooth(smooth);
    }
    public double getLayoutX(){
        return this.entityView.getLayoutX();
    }
    public void setLayoutX(double layoutX){
        this.entityView.setLayoutX(layoutX);
    }
    public double getLayoutY(){
        return this.entityView.getLayoutY();
    }
    public void setLayoutY(double layoutY){
        this.entityView.setLayoutY(layoutY);
    }
    public void updateAnimation(long actualHour, Entity entity){
        if(!entity.getAttack().isAttacking()){
            this.getView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - entity.getLastChangeFrame() > 100_000_000){
            entity.setActualFrame((entity.getActualFrame() + 1) % 11);
            if(entity.getActualFrame() == 10){
                entity.getAttack().setAttacking(false);
            }
            double xMovement = entity.getActualFrame() * FRAME_WIDTH;
            this.getView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            entity.setLastChangeFrame(actualHour);
        }
    }
}
