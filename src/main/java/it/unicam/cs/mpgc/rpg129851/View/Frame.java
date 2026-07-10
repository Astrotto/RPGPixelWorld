package it.unicam.cs.mpgc.rpg129851.View;

public class Frame {
    private long lastChangeFrame;
    private int actualFrame;
    public final int FRAME_WIDTH = 100;
    public final int FRAME_HEIGHT = 100;

    public Frame(){
        this.lastChangeFrame = 0;
        this.actualFrame = 0;
    }
    public long  getLastChangeFrame() {
        return lastChangeFrame;
    }
    public void setLastChangeFrame(long lastChangeFrame) {
        this.lastChangeFrame = lastChangeFrame;
    }
    public int getActualFrame() {
        return actualFrame;
    }
    public void setActualFrame(int actualFrame) {
        this.actualFrame = actualFrame;
    }
    public int getFrameWidth(){
        return FRAME_WIDTH;
    }
    public int getFrameHeight(){
        return FRAME_HEIGHT;
    }
}
