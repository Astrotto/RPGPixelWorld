package it.unicam.cs.mpgc.rpg129851.ImagesLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class ButtonLoader {
    private static Image imageBtnAttack, imageBtnRun;
    public static void loadButtonImages() {
        imageBtnAttack = getImageBtnAttack();
        imageBtnRun = getImageBtnRun();
    }
    public static Image getImageBtnAttack() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttack.png")));
    }
    public static Image getImageBtnRun() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRun.png")));
    }
    public static void loadButtonDisabled() {
        imageBtnAttack = getImageBtnAttackDisabled();
        imageBtnRun = getImageBtnRunDisabled();
    }
    public static Image getImageBtnAttackDisabled() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttackDisabled.png")));
    }

    public static Image getImageBtnRunDisabled() {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRunDisabled.png")));
    }
    public static void loadButtons(ImageView button1, ImageView button2) {
        button1.setImage(imageBtnAttack);
        button1.setSmooth(false);
        button2.setImage(imageBtnRun);
        button2.setSmooth(false);
    }
}
