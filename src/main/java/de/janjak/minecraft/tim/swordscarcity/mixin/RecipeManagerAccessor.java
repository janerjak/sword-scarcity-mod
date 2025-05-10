package de.janjak.minecraft.tim.swordscarcity.mixin;

import java.util.Map;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    /*

    // NOTE: Seems to be deprecated, so we use Craft Tweaker mod instead

    @Accessor("recipes")
    Map<RecipeType<?>, Map<Identifier, Recipe<?>>> getRecipes();
    */
}