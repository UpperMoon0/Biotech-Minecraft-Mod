package com.nhat.biotech.jei;

import com.nhat.biotech.Biotech;
import com.nhat.biotech.blocks.block_entites.hatches.FluidHatchBlockEntity;
import com.nhat.biotech.blocks.block_entites.hatches.FluidInputHatchBlockEntity;
import com.nhat.biotech.blocks.block_entites.machines.MachineRegistries;
import com.nhat.biotech.recipes.BreedingChamberRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BreedingChamberCategory implements IRecipeCategory<BreedingChamberRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(Biotech.MOD_ID, MachineRegistries.BREEDING_CHAMBER.id());
    public static final ResourceLocation TEXTURE = new ResourceLocation(Biotech.MOD_ID, "textures/gui/jei/" + MachineRegistries.BREEDING_CHAMBER.id() + ".png");

    public static final RecipeType<BreedingChamberRecipe> TYPE = new RecipeType<>(UID, BreedingChamberRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public BreedingChamberCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 122, 38);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MachineRegistries.BREEDING_CHAMBER.blockItem().get()));
    }

    @Override
    public @NotNull RecipeType<BreedingChamberRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.biotech." + MachineRegistries.BREEDING_CHAMBER.id());
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull BreedingChamberRecipe recipe, @NotNull IFocusGroup focuses) {

        List<Ingredient> ingredients = recipe.getItemIngredients();
        FluidStack fluidIngredient = recipe.getFluidIngredients().get(0);
        List<Ingredient> itemOutputs = recipe.getItemOutputs();

        builder.addSlot(RecipeIngredientRole.INPUT, 23, 1).addIngredients(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 41, 1).addIngredients(ingredients.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 21).addFluidStack(fluidIngredient.getFluid(), fluidIngredient.getAmount()).setFluidRenderer(fluidIngredient.getAmount(), false, 16, 16);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 101, 11).addIngredients(itemOutputs.get(0));
    }
}
