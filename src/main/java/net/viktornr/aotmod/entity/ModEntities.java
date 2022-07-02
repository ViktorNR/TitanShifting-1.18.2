package net.viktornr.aotmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.custom.ArmoredTitanEntity;
import net.viktornr.aotmod.entity.custom.FemaleTitanEntity;

public class ModEntities {

    public static final EntityType<ArmoredTitanEntity> ARMORED_TITAN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(AotMod.MOD_ID, "armored_titan"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ArmoredTitanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 2f)).build());

    public static final EntityType<FemaleTitanEntity> FEMALE_TITAN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(AotMod.MOD_ID, "female_titan"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FemaleTitanEntity::new).
                    dimensions(EntityDimensions.fixed(0.7f, 2f)).build());


}
