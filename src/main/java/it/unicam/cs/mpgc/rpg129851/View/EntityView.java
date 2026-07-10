package it.unicam.cs.mpgc.rpg129851.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
    private final Frame frame;
    private ImageView entityView;
    private final AttackView attackView;
    private boolean isMoving = false;

    public EntityView() {
        this.entityView = new ImageView();
        this.attackView = new AttackView();
        this.frame = new Frame();
    }

    public Frame getFrame() {
        return frame;
    }
    public AttackView getAttackView(){ return this.attackView; }
    public ImageView getView() {
        return this.entityView;
    }
    public void setView(ImageView entityView) {
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
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    public boolean isMoving() {
        return this.isMoving;
    }

}
