package it.unicam.cs.mpgc.rpg129851.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
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
}
