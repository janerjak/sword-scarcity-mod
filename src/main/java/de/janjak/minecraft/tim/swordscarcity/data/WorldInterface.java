package de.janjak.minecraft.tim.swordscarcity.data;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

abstract class WorldInterface {
    static String getLevelName(MinecraftServer server) {
        return server.getSaveProperties().getLevelName();
    }

    static String getOverworldWorldName(MinecraftServer server) {
        RegistryKey<World> worldKey = World.OVERWORLD;
        World world = server.getWorld(worldKey);
        return world.getRegistryKey().getValue().getPath();
    }
}
