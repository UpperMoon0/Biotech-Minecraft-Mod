package com.nhat.biotech.view.io_hatches.energy;

import com.nhat.biotech.Biotech;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EnergyHatchScreen<T extends EnergyHatchMenu> extends AbstractContainerScreen<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Biotech.MODID, "textures/gui/energy_hatch.png");
    public EnergyHatchScreen(T menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if (menu.getEnergy() > 0) {
            graphics.blit(TEXTURE, this.leftPos + 80, this.topPos + 69 - menu.calculateEnergyBarHeight(), 176, 52 - menu.calculateEnergyBarHeight(), 16, menu.calculateEnergyBarHeight());
        }
    }
}
