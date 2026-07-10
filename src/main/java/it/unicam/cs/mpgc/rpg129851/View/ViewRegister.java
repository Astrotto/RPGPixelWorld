package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Model.Player;

import java.util.HashMap;
import java.util.Map;

public class ViewRegister {
    private static final Map<Entity, EntityView> views = new HashMap<>();

    public static void register(Entity entity, EntityView view) {
        views.put(entity, view);
    }
    public static EntityView of(Entity entity) {
        return views.get(entity);
    }
    public static PlayerView ofPlayer(Player player) {
        return (PlayerView) views.get(player);
    }
    public static OrcView ofOrc(Orc orc) {
        return (OrcView) views.get(orc);
    }
    public static void clearOrcs() {
        views.entrySet().removeIf(entry -> entry.getKey() instanceof Orc);
    }
}
