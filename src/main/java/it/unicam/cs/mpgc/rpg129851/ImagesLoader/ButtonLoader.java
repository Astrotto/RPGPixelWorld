package it.unicam.cs.mpgc.rpg129851.ImagesLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class ButtonLoader {
    private static Image imageBtnAttack, imageBtnRun, imageBtnStart, imageBtnQuit;
    public static void loadButtonImages() {
        imageBtnAttack = getBtnImage("Attack");
        imageBtnRun = getBtnImage("Run");
        imageBtnStart = getBtnImage("Start");
        imageBtnQuit = getBtnImage("Quit");
    }
    public static void loadButton(ImageView button, String buttonName) {
        button.setImage(getBtnImage(buttonName));
    }
    public static Image getBtnImage(String buttonName) {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btn" + buttonName + ".png")));
    }
    public static void loadButtonDisabled() {
        imageBtnAttack = getImageBtnDisabled("Attack");
        imageBtnRun = getImageBtnDisabled("Run");
    }
    public static Image getImageBtnDisabled(String buttonName) {
        return new Image(Objects.requireNonNull(ButtonLoader.class.getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btn" + buttonName+ "Disabled.png")));
    }

    public static void loadButtons(ImageView button1, ImageView button2) {
        button1.setImage(imageBtnAttack);
        button1.setSmooth(false);
        button2.setImage(imageBtnRun);
        button2.setSmooth(false);
    }
}
