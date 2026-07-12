package it.unicam.cs.mpgc.rpg129851.PrintLog;
import it.unicam.cs.mpgc.rpg129851.Interface.GameLogger;

public class PrintGameLog implements GameLogger {
    @Override
    public void info(String message){
        System.out.println(message);
    }
    @Override
    public void error(String message){
        System.err.println(message);
    }
}
