package de.janjak.minecraft.tim.swordscarcity.data;

import java.util.HashMap;
import java.util.Map;

public abstract class RelevantAdvancementStorage {
    public static final Map<String, RelevantAdvancement> titleKeyToAdvancementMap = new HashMap<>();
    private static final RelevantAdvancement[] relevantAdvancements = {
        new RelevantAdvancement(
            "nether/obtain_blaze_rod",
            SwordElement.FIRE,
            "Into Fire",
            "Spiel mit dem Feuer"
        ),
        /*
        Tim changed his mind
        new RelevantAdvancement(
            "nether/return_to_sender",
            SwordElement.FIRE,
            "Return to Sender",
            "Zurück zum Absender"
        ),
        */
        new RelevantAdvancement(
            "end/elytra",
            SwordElement.AIR,
            "Sky's the Limit",
            "Hinterm Horizont geht's weiter"
        ),
        new RelevantAdvancement(
            "adventure/overoverkill",
            SwordElement.EARTH,
            "Over-Overkill",
            "Großmeister des Kampfes"
        ),
        new RelevantAdvancement(
            "adventure/throw_trident",
            SwordElement.WATER,
            "A Throwaway Joke",
            "Ein Witz zum Wegwerfen"
        ),


        // The following are for debugging only
        /*
        new RelevantAdvancement(
            "story/mine_stone",
            SwordElement.EARTH,
            "Debug: Mine stone",
            "Stein abbauen"
        ),
        new RelevantAdvancement(
            "story/upgrade_tools",
            SwordElement.AIR,
            "Debug: Upgrade tools",
            "Werkzeug aufrüsten"
        ),
        */
    };

    public static void initialize() {
        for (RelevantAdvancement advancement : RelevantAdvancementStorage.relevantAdvancements) {
            RelevantAdvancementStorage.titleKeyToAdvancementMap.put(advancement.getTitleTranslationKey(), advancement);
        }
    }
}
