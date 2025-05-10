package de.janjak.minecraft.tim.swordscarcity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwordScarcity implements ModInitializer {
	public static final String MOD_ID = "sword-scarcity";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.SERVER) {
			LOGGER.error("This mod only runs in a server environment");
			return;	
		}
		LOGGER.info("Running on server");
		EventHandlers.registerEventListeners();
	}

	
}