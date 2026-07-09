package it.unicam.cs.mpgc.rpg129851.ImagesLoader;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ButtonLoader {
    private final ImageView btnAttack;
    private final ImageView btnRun;
    private Image imageBtnAttack, imageBtnRun;
    public ButtonLoader(ImageView btnAttack, ImageView btnRun) {
        this.btnAttack = btnAttack;
        this.btnRun = btnRun;
        loadButtonImages();
    }
    public Image getImageBtnAttackDisabled() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttackDisabled.png")));
    }
    public Image getImageBtnAttack() {
         return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttack.png")));
    }
    public Image getImageBtnRunDisabled() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRunDisabled.png")));
    }
    public Image getImageBtnRun() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRun.png")));
    }
    public void loadButtonImages() {
        imageBtnAttack = getImageBtnAttack();
        imageBtnRun = getImageBtnRun();
    }
    public void loadButtonDisabled() {
        imageBtnAttack = getImageBtnAttackDisabled();
        imageBtnRun = getImageBtnRunDisabled();
    }
    public void loadButtons(){
        btnAttack.setImage(imageBtnAttack);
        btnAttack.setSmooth(false);
        btnRun.setImage(imageBtnRun);
        btnRun.setSmooth(false);
    }
}
