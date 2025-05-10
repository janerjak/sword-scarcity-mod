package de.janjak.minecraft.tim.swordscarcity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

abstract class RewardToInventory {
	// Returns if the item could be directly added to the inventory. Otherwise it will be dropped next to the player
	static boolean addElementalSwordToPlayerInventory(ServerPlayerEntity player, String swordItemName) {
		PlayerInventory inventory = player.getInventory();
		Item swordItem = Registries.ITEM.get(Identifier.of("elementalswords", swordItemName));
		ItemStack rewardStack = new ItemStack(swordItem, 1);
		boolean wasAdded = inventory.insertStack(rewardStack);
		if (!wasAdded) {
			player.dropItem(rewardStack, false);
			return false;
		}

		return true;
	}	
}
