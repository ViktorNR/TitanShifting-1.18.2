package net.viktornr.aotmod.abilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.viktornr.aotmod.entity.custom.FemaleTitanEntity;
//WIP!!!
public class WarhammerAbilityClass extends TitanAbilityBaseClass{

    int countdown = 600;

    @Override
    public void useAbility(PlayerEntity player, String titan, World world) {
        BlockHitResult hitResult = (BlockHitResult) player.raycast(20, 1, false);
        Vec3d playerRotationVec = player.getRotationVec(1);
        Vec3d firstPos = playerRotationVec.add(player.getPos());
        EntityType<?> entity = Registry.ENTITY_TYPE.get(new Identifier("aotmod", "female_titan"));
        Entity firstPillar = entity.create(world);
        firstPillar.setPos(firstPos.x, firstPos.y, firstPos.z);
        world.spawnEntity(firstPillar);
        FemaleTitanEntity hardenedPillarRef = (FemaleTitanEntity) firstPillar;
        hardenedPillarRef.canContinueToSpawn = false;
        player.sendMessage(new LiteralText("LET'S GO!"), false);
    }


}


