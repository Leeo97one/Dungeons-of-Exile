package com.robertx22.world_of_exile.main;

import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.world_of_exile.items.SilverKeyItem;
import com.robertx22.world_of_exile.items.TeleportBackItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems extends Packets {
    public static ModItems INSTANCE;

    public SilverKeyItem SILVER_KEY = item("silver_key", new SilverKeyItem());
    public TeleportBackItem TP_BACK = item("teleport_back", new TeleportBackItem());
    public Item TELEPORT_RANDOMIZER = item("randomize_teleport", new Item(new Item.Settings().group(CreativeTabs.MAIN)));

    public BlockItem STARGATE = item("stargate", new BlockItem(ModBlocks.INSTANCE.STARGATE, new Item.Settings().group(CreativeTabs.MAIN)));

    <T extends Item> T item(String id, T c) {
        Registry.register(Registry.ITEM, WOE.id(id), c);
        return c;
    }

}
