package de.janjak.minecraft.tim.swordscarcity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import java.util.List;

import de.janjak.minecraft.tim.swordscarcity.mixin.RecipeManagerAccessor;

public abstract class EventHandlers {
	protected static void registerEventListeners() {
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			RecipeManager manager = server.getRecipeManager();
			
			var toRemove = List.of(
				Identifier.of(Constants.ELEMENTAL_SWORDS_MOD_ID, "recipe_id_1"),
				Identifier.of(Constants.ELEMENTAL_SWORDS_MOD_ID, "recipe_id_2")
				);
				
			/*
			toRemove.forEach(id -> {
				((RecipeManagerAccessor) manager).getRecipes().remove(id);
			});
			*/
		});
	}
}
