package com.robertx22.dungeons_of_exile.main;

import com.google.common.collect.ImmutableList;
import com.robertx22.dungeons_of_exile.world_gen.jigsaw.dungeon.DungeonPools;
import com.robertx22.dungeons_of_exile.world_gen.jigsaw.dungeon.ModDungeonFeature;
import com.robertx22.dungeons_of_exile.world_gen.processors.BeaconProcessor;
import com.robertx22.dungeons_of_exile.world_gen.processors.SignProcessor;
import com.robertx22.dungeons_of_exile.world_gen.processors.biome_processor.BiomeProcessor;
import com.robertx22.dungeons_of_exile.world_gen.tower.TowerFeature;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ModWorldGen {

    public static ModWorldGen INSTANCE = new ModWorldGen();

    public static void init() {

    }

    public StructureProcessorList DEFAULT_PROCESSORS = regProcs("my_processors", ImmutableList.of(
        new BeaconProcessor(),
        new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.getDefaultState())))
        , new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE.getDefaultState())))

    ));

    public StructureFeature<StructurePoolFeatureConfig> DUNGEON = new ModDungeonFeature(StructurePoolFeatureConfig.CODEC);
    public StructureFeature<DefaultFeatureConfig> TOWER = new TowerFeature(DefaultFeatureConfig.CODEC);

    public ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> CONFIG_DUNGEON = DUNGEON.configure(new StructurePoolFeatureConfig(() -> {
        return DungeonPools.STARTPOOL;
    }, 6));

    public ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CONFIG_TOWER = TOWER.configure(DefaultFeatureConfig.INSTANCE);

    public StructureProcessorType<BiomeProcessor> BIOME_PROCESSOR = StructureProcessorType.register(Ref.MODID + ":biome_processor", BiomeProcessor.CODEC);
    public StructureProcessorType<BeaconProcessor> BEACON_PROCESSOR = StructureProcessorType.register(Ref.MODID + ":mob_processor", BeaconProcessor.CODEC);
    public StructureProcessorType<SignProcessor> SIGN_PROCESSOR = StructureProcessorType.register(Ref.MODID + ":sign_processor", SignProcessor.CODEC);

    public ModWorldGen() {

        FabricStructureBuilder.create(new Identifier(Ref.MODID, "dungeon"), DUNGEON)
            .step(GenerationStep.Feature.SURFACE_STRUCTURES)
            .defaultConfig(DungeonConfig.get().DUNGEON_SPACING, DungeonConfig.get().DUNGEON_SEPARATION, 378235)
            .superflatFeature(CONFIG_DUNGEON)
            .register();

        FabricStructureBuilder.create(new Identifier(Ref.MODID, "tower"), TOWER)
            .step(GenerationStep.Feature.SURFACE_STRUCTURES)
            .defaultConfig(DungeonConfig.get().TOWER_SPACING, DungeonConfig.get().TOWER_SEPARATION, 278235)
            .superflatFeature(CONFIG_TOWER)
            .register();

    }

    private static StructureProcessorList regProcs(String id, ImmutableList<StructureProcessor> processorList) {
        Identifier identifier = new Identifier(Ref.MODID, id);

        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);

        return (StructureProcessorList) BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, (Identifier) identifier, structureProcessorList);
    }
}
