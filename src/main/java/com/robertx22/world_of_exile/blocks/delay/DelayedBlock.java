package com.robertx22.world_of_exile.blocks.delay;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class DelayedBlock extends Block implements BlockEntityProvider {
    public DelayedBlock() {
        super(AbstractBlock.Settings.of(Material.STONE));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new DelayedBlockEntity();
    }

}
