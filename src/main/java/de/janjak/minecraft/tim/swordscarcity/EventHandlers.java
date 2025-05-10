package de.janjak.minecraft.tim.swordscarcity;

import de.janjak.minecraft.tim.swordscarcity.data.RewardSaveData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public abstract class EventHandlers {
    protected static void registerEventListeners() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            // Build save state, if required
            RewardSaveData.initializeIfRequired(server);
        });

        ServerPlayConnectionEvents.JOIN.register(EventHandlers::onServerPlayConnectionPlayerJoin);
        ServerMessageEvents.GAME_MESSAGE.register(EventHandlers::onGameMessageReceived);
    }

    // Called whenever a player joins
    private static void onServerPlayConnectionPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        AdvancementChecker.checkPlayerForNewAdvancements(server, handler.player);
    }

    // Called on every server game message, crucially: including new advancements
    private static void onGameMessageReceived(MinecraftServer server, Text text, boolean bool) {
        // Scan for new advancements amongst all players
        for (ServerWorld world : server.getWorlds()) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                AdvancementChecker.checkPlayerForNewAdvancements(server, player);
            }
        }
        RewardSaveData.save();
    }
}
