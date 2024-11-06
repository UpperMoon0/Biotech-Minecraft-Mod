package com.nhat.biotech.recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class GreenhouseRecipe extends BiotechRecipe<GreenhouseRecipe> {
    public static final RecipeSerializer<GreenhouseRecipe> SERIALIZER =
            new RecipeSerializerFactory<GreenhouseRecipe>().createSerializer(GreenhouseRecipe::new);

    public static final RecipeType<GreenhouseRecipe> TYPE = new RecipeType<>() {
    };

    public GreenhouseRecipe(ResourceLocation id, BiotechRecipeData recipe) {
        super(id, recipe, SERIALIZER, TYPE);
    }

    @Override
    protected GreenhouseRecipe createInstance(ResourceLocation id, BiotechRecipeData recipe) {
        return new GreenhouseRecipe(id, recipe);
    }
}
