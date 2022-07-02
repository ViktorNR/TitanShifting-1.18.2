package net.viktornr.aotmod.item.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.sound.ModSoundsClass;
import net.viktornr.aotmod.util.ITransformData;


public class KnifeItem extends Item {
    public KnifeItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //Set item cooldown.
        user.getItemCooldownManager().set(this, 20);

        //If this is client, return.
        if(world.isClient()) return super.use(world, user, hand);


        ((ITransformData) user).initTransformation();
        ((ITransformData) user).playerEntityInit();
        //((ITransformData) user).setBooleanData(true);
        //ServerPlayNetworking.send((ServerPlayerEntity) user, AotMod.TRANSFORM_UPDATE, PacketByteBufs.empty());

        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                ModSoundsClass.TRANSFORMATION_SOUND, SoundCategory.BLOCKS, 0.8f, 1.0f);

        return TypedActionResult.success(user.getStackInHand(hand));
        //return super.use(world, user, hand);
    }
    

}
