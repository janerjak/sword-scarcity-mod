package de.janjak.minecraft.tim.swordscarcity;

import net.fabricmc.api.ClientModInitializer;

public class SwordScarcityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		SwordScarcity.LOGGER.warn("This mod is designed to run in server environment only. You do not need to install it on Minecraft clients.");
	}
}