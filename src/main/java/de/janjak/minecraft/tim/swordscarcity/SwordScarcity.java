package de.janjak.minecraft.tim.swordscarcity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.janjak.minecraft.tim.swordscarcity.data.RelevantAdvancementStorage;
import de.janjak.minecraft.tim.swordscarcity.data.RewardSaveData;

public class SwordScarcity implements ModInitializer {
	public static final String MOD_ID = "sword-scarcity";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.SERVER) {
			LOGGER.warn("This initialization will do nothing, since the mod is not designed to run on clients");
			return;	
		}
		LOGGER.info("Running on server");
		// Initialize all modules
		RelevantAdvancementStorage.initialize();

		// Register events
		EventHandlers.registerEventListeners();
	}
}