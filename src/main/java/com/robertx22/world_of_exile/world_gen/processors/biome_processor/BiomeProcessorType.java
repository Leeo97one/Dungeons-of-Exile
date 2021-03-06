package com.robertx22.world_of_exile.world_gen.processors.biome_processor;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;

import java.util.Map;

public abstract class BiomeProcessorType {

    public abstract String id();

    public boolean isDefault() {
        return false;
    }

    public abstract boolean isBiomeGood(Biome biome);

    public abstract Map<Block, Block> getReplaceMap();

}
