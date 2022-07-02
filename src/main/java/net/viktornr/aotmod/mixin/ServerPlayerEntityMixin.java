package net.viktornr.aotmod.mixin;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.util.ITransformData;
import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends LivingEntity implements ITransformData{

    @Shadow public abstract boolean isInvulnerableTo(DamageSource damageSource);

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    boolean isTransformed;
    boolean initTransformation;
    int transformCooldown;
    int timeToTransform = 41;


    private static final TrackedData<Boolean> IS_TRANSFORMED = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);



    protected ServerPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void cooldownTick(CallbackInfo ci) {

        //Decrease cooldown.
        if (transformCooldown > 0) {
            transformCooldown--;
        }

        //Initiate transformation sequence.
        if (initTransformation & timeToTransform != 0) {
            if (timeToTransform > 0) {
                timeToTransform--;
                if (timeToTransform % 10 == 0) {
                    transformationSequence();
                    if (timeToTransform == 0) {
                        transformationEnd();
                    }
                }
            }
        }

        //transform
//        if (isTransformed) {
//            this.sendMessage(new LiteralText("SERVER: TRUE"), false);
//            this.stepHeight = 5f;
//        }



    }

    private void transformationSequence() {
        //Lightning
        this.sendMessage(new LiteralText("SERVER: SUMMON LIGHTNING: " + timeToTransform),
                false);
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
        lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(this.getBlockPos()));
        world.spawnEntity(lightningEntity);

        //Explosion
        Explosion.DestructionType destructionType = Explosion.DestructionType.NONE;
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)8, destructionType);
        //this.spawnEffectsCloud();
    }

    private void transformationEnd() {

        initTransformation = false;
        timeToTransform = 41;
        isTransformed = true;

        //Explosion
        Explosion.DestructionType destructionType = Explosion.DestructionType.DESTROY;
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)8, destructionType);

        ServerPlayerEntity server = (ServerPlayerEntity) (Object) (this);
        ServerPlayNetworking.send(server, AotMod.TRANSFORM_UPDATE, PacketByteBufs.empty());

    }






    @Override
    public void initTransformation() {
        this.initTransformation = true;
    }


}



