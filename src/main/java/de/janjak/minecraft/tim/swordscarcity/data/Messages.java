package de.janjak.minecraft.tim.swordscarcity.data;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public abstract class Messages {
	public static void broadcastSwordObtain(MinecraftServer server, ServerPlayerEntity advancedPlayer, RelevantAdvancement swordAdvancementToBroadcast) {
		SwordElement element = swordAdvancementToBroadcast.rewardingElement();

		Text header = Text.literal("Elementschwerter: ")
			.formatted(Formatting.DARK_GREEN, Formatting.BOLD);

		Text playerPart = advancedPlayer.getDisplayName();

		Text afterPlayerPart = Text.literal(" hat das ");

		Text swordName = Text.literal(SwordElementName.of(element))
			.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(SwordElementColor.of(element))))
			.formatted(Formatting.BOLD);

		Text afterSwordPart = Text.literal(" erhalten! ");

		Text advancementDescriptionPart = Text.literal("(" + swordAdvancementToBroadcast.germanTitle() + ")")
			.setStyle(Style.EMPTY.withColor(TextColor.fromRgb(SwordElementColor.of(element))))
			.formatted(Formatting.BOLD, Formatting.ITALIC);

		Text full = Text.empty()
			.append(header)
			.append(playerPart)
			.append(afterPlayerPart)
			.append(swordName)
			.append(afterSwordPart)
			.append(advancementDescriptionPart);

		server.getPlayerManager().broadcast(full, false);
	}
}
