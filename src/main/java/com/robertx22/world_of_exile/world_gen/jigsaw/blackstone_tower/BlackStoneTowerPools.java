package com.robertx22.world_of_exile.world_gen.jigsaw.blackstone_tower;

import com.robertx22.world_of_exile.main.ModProcessors;
import com.robertx22.world_of_exile.main.WOE;
import com.robertx22.world_of_exile.world_gen.AbstractPool;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

public class BlackStoneTowerPools {

    public static StructurePool STARTPOOL;

    public static void init() {

    }

    static class Pool extends AbstractPool {

        public Pool(Identifier poolId) {
            super(poolId);
        }

        @Override
        public StructureProcessorList processorList() {
            return ModProcessors.INSTANCE.BLACKSTONE_TOWER;
        }

    }

    static {

        AbstractPool startBuilder = new Pool(WOE.id("bigtower_start"));
        startBuilder.add(WOE.id("blackstone_tower/start/start0"));
        STARTPOOL = startBuilder.build();

        AbstractPool middleBuilder = new Pool(WOE.id("bigtower_middle"));
        middleBuilder.add(WOE.id("blackstone_tower/middle/middle0"));
        middleBuilder.add(WOE.id("blackstone_tower/middle/top"), 1);
        middleBuilder.build();

        AbstractPool sideBuilder = new Pool(WOE.id("bigtower_side"));
        sideBuilder.add(WOE.id("blackstone_tower/side/emptyside"));
        sideBuilder.add(WOE.id("blackstone_tower/side/bigside0"), 500);
        sideBuilder.add(WOE.id("blackstone_tower/side/side1"), 150);
        sideBuilder.build();

    }

}
