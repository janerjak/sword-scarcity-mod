package de.janjak.minecraft.tim.swordscarcity.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		return "saves/swordscarcity_obtained_swords_" + WorldInterface.getLevelName(server) + ".json";
	}

	public static void initializeIfRequired(MinecraftServer server) {
		if (RewardSaveData.completedAdvancements == null) {
			RewardSaveData.SAVE_FILE = new File(RewardSaveData.getSaveFilePath(server));

			RewardSaveData.completedAdvancements = new HashMap<>();
			RewardSaveData.load();
		}
	}

	public static void markAdvancementCompletedSafe(String locationKey, UUID advancedPlayerUuid) {
		if (!RewardSaveData.completedAdvancements.containsKey(locationKey)) {
			RewardSaveData.completedAdvancements.put(locationKey, advancedPlayerUuid);
		}
		save();
	}

	public static UUID advancementAlreadyCompletedBy(String locationKey) {
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
				e.printStackTrace();
            }
        }
    }

	public static void save() {
		try (FileWriter writer = new FileWriter(SAVE_FILE)) {
			GSON_CONVERTER.toJson(completedAdvancements, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}