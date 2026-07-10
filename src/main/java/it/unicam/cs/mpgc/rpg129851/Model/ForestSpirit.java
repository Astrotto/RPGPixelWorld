package it.unicam.cs.mpgc.rpg129851.Model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ForestSpirit {
    private List<Quest> quests;
    private Quest questReceived;
    public ForestSpirit() {
        this.quests = new ArrayList<>();
        loadQuests();
    }
    private void loadQuests() {
        Gson gson = new Gson();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("quests.json")) {
            if (is == null) {
                System.err.println("Errore: quests.json non trovato!");
                return;
            }
            JsonReader reader = new JsonReader(new InputStreamReader(is));
            reader.beginArray();

            while (reader.hasNext()) {
                Quest quest = gson.fromJson(reader, Quest.class);
                this.getQuests().add(quest);
            }
            reader.endArray();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Quest getRandomQuest(){
        this.questReceived = quests.get((int) (Math.random() * quests.size()));
        return this.questReceived;
    }
    public Quest getQuestReceived(){
        return this.questReceived;
    }
    public Potion getPotionReward(int level){
        return new Potion(level);
    }
    public List<Quest> getQuests(){
        return quests;
    }
}
