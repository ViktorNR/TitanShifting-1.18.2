package net.viktornr.aotmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.viktornr.aotmod.AotMod;

public class ModItemGroup {
    public static final ItemGroup AOT = FabricItemGroupBuilder.build(new Identifier(AotMod.MOD_ID, "aot"),
            () -> new ItemStack(ModItems.HARDENED_INGOT));
}
