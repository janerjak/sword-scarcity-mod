package de.janjak.minecraft.tim.swordscarcity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public abstract class EventHandlers {
	protected static void registerEventListeners() {
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			
		});
	}
}
