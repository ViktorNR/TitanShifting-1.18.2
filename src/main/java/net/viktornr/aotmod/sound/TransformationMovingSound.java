package net.viktornr.aotmod.sound;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class TransformationMovingSound extends MovingSoundInstance {

    private final PlayerEntity player;


    public TransformationMovingSound(PlayerEntity player) {
        super(ModSoundsClass.TRANSFORMATION_SOUND, SoundCategory.BLOCKS);
        this.player = player;
    }

    @Override
    public void tick() {
        this.x = (float)this.player.getX();
        this.y = (float)this.player.getY();
        this.z = (float)this.player.getZ();
    }
}

