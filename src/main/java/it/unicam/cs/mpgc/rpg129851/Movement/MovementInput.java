package it.unicam.cs.mpgc.rpg129851.Movement;

import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.scene.input.KeyCode;

import java.util.Set;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class MovementInput {
    private final Set<KeyCode> keyPressed;
    private double moveX = 0, moveY = 0;

    public MovementInput(Set<KeyCode> keyPressed) {
        this.keyPressed = keyPressed;
    }

    public double moveY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode) || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                moveY -= 1;
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                moveY += 1;
            }
            ViewRegister.ofPlayer(player).setIsMoving(true);
        }
        return moveY;
    }
    public double moveX(KeyCode keyCode, KeyCode keyCode2) {
        PlayerView view = ViewRegister.ofPlayer(player);
        if (keyPressed.contains(keyCode) || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                moveX -= 1;
                view.getView().setScaleX(-1);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                moveX += 1;
                view.getView().setScaleX(1);
            }
            view.setIsMoving(true);
        }
        return moveX;
    }
}
