package net.viktornr.aotmod.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.viktornr.aotmod.entity.ModEntities;
import net.viktornr.aotmod.entity.custom.ArmoredTitanEntity;
import net.viktornr.aotmod.entity.custom.FemaleTitanEntity;

public class ModRegistries {
    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.ARMORED_TITAN, ArmoredTitanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FEMALE_TITAN, FemaleTitanEntity.setAttributes());
    }
}
