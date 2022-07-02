package net.viktornr.aotmod.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.viktornr.aotmod.AotMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

public class SaverClass {

    //IGNORE!
    //Block blockBelow = player.getWorld().getBlockState(player.getBlockPos().down(1)).getBlock();
//        BlockPos startingPos = player.getBlockPos().down(1);
//        if (lookingAt.getType() == HitResult.Type.BLOCK) {
//            player.sendMessage(new LiteralText("BLOCK SET"), false);
//            BlockPos finalPos = lookingAt.getBlockPos();
//            //BlockState blockState = world.getBlockState(blockPos);
//            world.setBlockState(startingPos, ModBlocks.HARDENED_BLOCK.getDefaultState());

    //Block blockBelow = player.getWorld().getBlockState(player.getBlockPos().down(1)).getBlock();
    //HardenedBlockBase firstHardenedBlock = (HardenedBlockBase) blockBelow;
    //firstHardenedBlock.setInitPosition(startingPos, finalPos);
    //firstHardenedBlock.testSound(world);
    //world.setBlockState(blockPos, ModBlocks.HARDENED_BLOCK.getDefaultState());
    //world.playSound((PlayerEntity)null, blockPos, SoundEvents.BLOCK_DEEPSLATE_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);




    //world.createExplosion(player, player.getX(), player.getY(), player.getZ(), 3.0f, Explosion.DestructionType.NONE);
}
