package de.janjak.minecraft.tim.swordscarcity;

import java.util.UUID;

import de.janjak.minecraft.tim.swordscarcity.data.RelevantAdvancement;
import de.janjak.minecraft.tim.swordscarcity.data.RelevantAdvancementStorage;
import de.janjak.minecraft.tim.swordscarcity.data.RewardSaveData;
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
		SwordScarcity.LOGGER.info("Scanning player " + player.getDisplayName().getString() + " (" + player.getUuidAsString() + ") for completed advancements");
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
		RelevantAdvancement relevantAdvancement = RelevantAdvancementStorage.titleKeyToAdvancementMap.getOrDefault(advancementTitleIdentifier, null);
		if (relevantAdvancement == null) {
			// The advancement is not relevant, so we ignore it
			return false;
		}
		// The player completed a relevant advancement
		UUID playerUuid = player.getUuid();
		SwordScarcity.LOGGER.info("Player " + playerUuid + " has completed relevant advancement: " + advancementTitleIdentifier);

		// We now check if they are the first to obtain that advancement and grant the matching sword accordingly
		UUID alreadyCompletedBy = RewardSaveData.advancementAlreadyCompletedBy(relevantAdvancement.locationKey());
		if (alreadyCompletedBy != null) {
			SwordScarcity.LOGGER.info("Player " + playerUuid + " completing " + advancementTitleIdentifier + " is irrelevant, since another player already completed it first: " + alreadyCompletedBy);
			return false;
		}

		// This seems to be the first time someone completed this advancement. Grant the reward and store that in the save data
		RewardToInventory.addElementalSwordToPlayerInventory(player, relevantAdvancement.getRewardItemId());
		RewardSaveData.markAdvancementCompletedSafe(relevantAdvancement.locationKey(), playerUuid);

		// TODO: Server announcement

		return true;
	}
}
