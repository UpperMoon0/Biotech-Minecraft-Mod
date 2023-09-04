package com.nhat.biotech.blocks.block_entites;

import com.nhat.biotech.blocks.IOHatchBlock;
import com.nhat.biotech.networking.EnergyPacket;
import com.nhat.biotech.networking.ModPackets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyHatchBlockEntity extends CapabilityBlockEntity {
    protected final EnergyStorage energyStorage = initEnergyStorage();

    protected final LazyOptional<IEnergyStorage> lazyEnergyStorage = initLazyEnergyStorage();

    protected abstract EnergyStorage initEnergyStorage();

    protected abstract LazyOptional<IEnergyStorage> initLazyEnergyStorage();

    public EnergyHatchBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing)
    {
        if (capability == ForgeCapabilities.ENERGY)
            if (facing == getBlockState().getValue(IOHatchBlock.FACING) || facing == null)
                return lazyEnergyStorage.cast();
        return super.getCapability(capability, facing);
    }

    public static <T extends BlockEntity> void serverTick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        EnergyHatchBlockEntity blockEntity = (EnergyHatchBlockEntity) t;
        if (!level.isClientSide()) {
            ModPackets.sendToClients(new EnergyPacket(blockEntity.energyStorage.getEnergyStored(), blockPos));
            setChanged(level, blockPos, blockState);
        }
    }

    public void setEnergy(int energy) {
        energyStorage.receiveEnergy(energy, false);
    }
}
