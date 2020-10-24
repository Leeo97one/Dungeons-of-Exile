package com.robertx22.dungeons_of_exile.main;

import com.robertx22.library_of_exile.config_utils.PerBiomeConfig;
import com.robertx22.library_of_exile.config_utils.PerDimensionConfig;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

public class FeatureConfig {

    public PerDimensionConfig PER_DIM = new PerDimensionConfig();
    public PerBiomeConfig PER_BIOME = new PerBiomeConfig();

    public boolean isAllowedIn(DimensionType type, Biome biome, World world) {
        return PER_DIM.isAllowedIn(type, world) && PER_BIOME.isAllowedIn(biome, world);
    }

}
