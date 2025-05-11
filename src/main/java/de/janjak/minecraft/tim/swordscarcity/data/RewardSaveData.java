package de.janjak.minecraft.tim.swordscarcity.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.janjak.minecraft.tim.swordscarcity.SwordScarcity;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class RewardSaveData {
    private static Map<String, UUID> completedAdvancements = null;
    private static File SAVE_FILE = null;
    private static final Gson GSON_CONVERTER = new Gson();
    
    private static final String getSaveFilePath(MinecraftServer server) {
        return "config/swordscarcity_obtained_swords_" + WorldInterface.getLevelName(server) + ".json";
    }

    public static void initializeIfRequired(MinecraftServer server) {
        if (RewardSaveData.completedAdvancements == null) {
            if (server != null) {
                RewardSaveData.SAVE_FILE = new File(RewardSaveData.getSaveFilePath(server));
                RewardSaveData.completedAdvancements = new HashMap<>();
                RewardSaveData.load();
            }
            // If there is still no save data after it should have been loaded, clear all save data, but warn the user
            if (RewardSaveData.completedAdvancements == null) {
                SwordScarcity.LOGGER.warn("After trying to load the save file, the advancement map is still null. I'm continuing from a blank save file, but this should probably be investigated!");
                RewardSaveData.completedAdvancements = new HashMap<>();
            }
        }
    }

    public static void initializeIfRequired() {
        RewardSaveData.initializeIfRequired(null);
    }

    public static void markAdvancementCompletedSafe(String locationKey, UUID advancedPlayerUuid) {
        RewardSaveData.initializeIfRequired();
        if (!RewardSaveData.completedAdvancements.containsKey(locationKey)) {
            RewardSaveData.completedAdvancements.put(locationKey, advancedPlayerUuid);
        }
        save();
    }

    public static UUID advancementAlreadyCompletedBy(String locationKey) {
        RewardSaveData.initializeIfRequired();
        return RewardSaveData.completedAdvancements.getOrDefault(locationKey, null);
    }

    public static boolean isAdvancementAlreadyCompleted(String locationKey) {
        return RewardSaveData.advancementAlreadyCompletedBy(locationKey) != null;
    }
    
    private static void load() {
        if (SAVE_FILE.exists()) {
            try (FileReader reader = new FileReader(SAVE_FILE)) {
                Type type = (new TypeToken<Map<String, UUID>>(){}).getType();
                completedAdvancements = GSON_CONVERTER.fromJson(reader, type);
            } catch (IOException e) {
                SwordScarcity.LOGGER.error(e.toString());
            }
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            GSON_CONVERTER.toJson(completedAdvancements, writer);
        } catch (IOException e) {
            SwordScarcity.LOGGER.error(e.toString());
        }
    }
}