package de.janjak.minecraft.tim.swordscarcity;

import de.janjak.minecraft.tim.swordscarcity.data.RelevantAdvancementStorage;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;

abstract class AdvancementChecker {
	static boolean checkPlayerForNewAdvancements(MinecraftServer server, ServerPlayerEntity player) {
		SwordScarcity.LOGGER.info("Scanning player " + player.getDisplayName().getString() + " for completed advancements");
		boolean foundNewRelevantAdvancement = false;
		
		for (AdvancementEntry advancement : server.getAdvancementLoader().getAdvancements()) {
			AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
			if (!progress.isDone()) {
				continue;
			}

			// The advancement progress is done. Extract the translation key from the display optional
			AdvancementDisplay advancementDisplay = advancement.value().display().orElse(null);
			if (advancementDisplay == null) {
				continue;
			}
			Text advancementTitle = advancementDisplay.getTitle();
			String advancementTitleIdentifier = ((TranslatableTextContent) ((TextContent) advancementTitle.getContent())).getKey();
			
			if (handleAdvancementCompleted(server, player, advancementTitleIdentifier)) {
				foundNewRelevantAdvancement = true;
			}
		}

		return foundNewRelevantAdvancement;
	}

	private static boolean handleAdvancementCompleted(MinecraftServer server, ServerPlayerEntity player, String advancementTitleIdentifier) {
		if (RelevantAdvancementStorage.titleKeyToAdvancementMap.containsKey(advancementTitleIdentifier)) {
			// The player completed a relevant advancement
			// We now check if they are the first to obtain that advancement and grant the matching sword accordingly
		}

		String playerDisplayName = player.getDisplayName().getString();
		SwordScarcity.LOGGER.info("Player " + playerDisplayName + " has completed relevant advancement: " + advancementTitleIdentifier);

		return true;
	}
}
