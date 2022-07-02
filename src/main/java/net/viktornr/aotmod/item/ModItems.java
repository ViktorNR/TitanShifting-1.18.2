package net.viktornr.aotmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.ModEntities;
import net.viktornr.aotmod.item.custom.*;

public class ModItems {

    public static final Item HARDENED_INGOT = registerItem("hardened_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.AOT)));

    public static final Item KNIFE_ITEM = registerItem("knife",
            new KnifeItem(new FabricItemSettings().group(ModItemGroup.AOT).maxCount(1)));

    public static final Item EMPTY_SYRINGE = registerItem("empty_syringe",
            new Item(new FabricItemSettings().group(ModItemGroup.AOT)));

    public static final Item FULL_SYRINGE = registerItem("full_syringe",
            new Item(new FabricItemSettings().group(ModItemGroup.AOT)));

    public static final Item ARMORED_TITAN_SPAWN_EGG = registerItem("armored_titan_spawn_egg",
            new SpawnEggItem(ModEntities.ARMORED_TITAN, 0x948e8d, 0x3b3635,
                    new FabricItemSettings().group(ModItemGroup.AOT).maxCount(1)));

    public static final Item FEMALE_TITAN_SPAWN_EGG = registerItem("female_titan_spawn_egg",
            new SpawnEggItem(ModEntities.FEMALE_TITAN, 0x948e8d, 0x3b3635,
                    new FabricItemSettings().group(ModItemGroup.AOT).maxCount(1)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(AotMod.MOD_ID, name), item);
    }


    public static void registerModItems() {
        AotMod.LOGGER.info("Registering Mod Items for " + AotMod.MOD_ID);
    }

}
