package de.janjak.minecraft.tim.swordscarcity.mixin;

import net.minecraft.recipe.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    /*

    // NOTE: Seems to be deprecated, so we use Craft Tweaker mod instead

    @Accessor("recipes")
    Map<RecipeType<?>, Map<Identifier, Recipe<?>>> getRecipes();
    */
}