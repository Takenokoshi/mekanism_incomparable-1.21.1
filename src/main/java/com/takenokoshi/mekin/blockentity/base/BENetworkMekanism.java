package com.takenokoshi.mekin.blockentity.base;

import appeng.api.networking.GridHelper;
import appeng.api.networking.IGrid;
import appeng.api.networking.IManagedGridNode;
import appeng.api.networking.storage.IStorageService;
import appeng.api.storage.MEStorage;
import appeng.api.util.AECableType;
import appeng.blockentity.grid.AENetworkedBlockEntity;
import appeng.hooks.ticking.TickHandler;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class BENetworkMekanism extends TileEntityMekanism implements IGridConnectedBlockEntity {

    AENetworkedBlockEntity q;

    private final IManagedGridNode mainNode;
    private boolean setChangedQueued = false;

    public BENetworkMekanism(BlockRegistryObject<?, ?> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        this.mainNode = this.createMainNode()
                .setVisualRepresentation(blockProvider)
                .setInWorldNode(true)
                .setTagName("proxy");
    }

    protected IManagedGridNode createMainNode() {
        return GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        loadTag(tag);
    }

    public void loadTag(CompoundTag data) {
        this.getMainNode().loadFromNBT(data);
    }

    @Override
    public void saveAdditional(CompoundTag data, HolderLookup.Provider provider) {
        super.saveAdditional(data, provider);
        this.getMainNode().saveToNBT(data);
    }

    @Override
    public final IManagedGridNode getMainNode() {
        return this.mainNode;
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.SMART;
    }

    protected void scheduleInit() {
        GridHelper.onFirstTick(this, BENetworkMekanism::onReady);
    }

    public void onReady() {
        this.getMainNode().create(this.getLevel(), this.getBlockPos());
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        this.getMainNode().destroy();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        this.scheduleInit();
    }

    @Override
    public void saveChanges() {
        if (this.level != null) {
            if (this.level.isClientSide) {
                this.setChanged();
            } else {
                this.level.blockEntityChanged(this.worldPosition);
                if (!this.setChangedQueued) {
                    TickHandler.instance().addCallable((LevelAccessor) null, this::setChangedAtEndOfTick);
                    this.setChangedQueued = true;
                }
            }
        }
    }

    private Object setChangedAtEndOfTick(Level level) {
        this.setChanged();
        this.setChangedQueued = false;
        return null;
    }

    public MEStorage getMeStorage() {
        IGrid grid = mainNode.getGrid();
        if (grid == null) {
            return null;
        }
        IStorageService storageService = grid.getStorageService();
        if (storageService == null) {
            return null;
        }
        return storageService.getInventory();
    }

}