package it.unicam.cs.mpgc.rpg129851.Model;

public class Frame {
    private long lastChangeFrame;
    private int actualFrame;

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
}
