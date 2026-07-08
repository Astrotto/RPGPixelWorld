package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Frame;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class EntityView {
    private final int FRAME_WIDTH = 100;
    private final int FRAME_HEIGHT = 100;
    private Frame frame;
    private ImageView entityView;
    private boolean isMoving = false;

    public EntityView() {
        this.entityView = new ImageView();
        this.frame = new Frame();
    }

    public Frame getFrame() {
        return frame;
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
        }else if(actualHour - this.getFrame().getLastChangeFrame() > 100_000_000){
            this.getFrame().setActualFrame((this.getFrame().getActualFrame() + 1) % 11);
            if(this.getFrame().getActualFrame() == 10){
                entity.getAttack().setAttacking(false);
            }
            double xMovement = this.getFrame().getActualFrame() * FRAME_WIDTH;
            this.getView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            this.getFrame().setLastChangeFrame(actualHour);
        }
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    public boolean isMoving() {
        return this.isMoving;
    }
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
