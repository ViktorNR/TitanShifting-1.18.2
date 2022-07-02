package net.viktornr.aotmod.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;
import net.viktornr.aotmod.util.ITransformData;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ITransformData{

    @Shadow public abstract boolean canTakeDamage();

    @Shadow public abstract EntityDimensions getDimensions(EntityPose pose);

    @Shadow public abstract ImmutableList<EntityPose> getPoses();

    @Shadow public abstract boolean isInvulnerableTo(DamageSource damageSource);

    @Shadow protected abstract void initDataTracker();

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    boolean initTransformation;
    boolean playerInit;
    int timeToTransform = 41;


    private static final TrackedData<Boolean> IS_TRANSFORMED = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_TRANSFORMING = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);



    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void cooldownTick(CallbackInfo ci) {

        if (playerInit & timeToTransform != 0) {
            if (timeToTransform > 0) {
                timeToTransform--;
                if (timeToTransform == 0) {
                    timeToTransform = 41;
                    this.sendMessage(new LiteralText("PLAYER: BOOLEAN SET"), false);

                    //data tracker
                    this.setBooleanData(true);
                    this.setInvulnerable(false);
                    this.setIsTransforming(false);
                    playerInit = false;

                    }
                }
            }

        //Stepheight
        if (getBooleanData()) {
            this.stepHeight = 5f;
        }

    }

    @Inject(method = "isImmobile", at = @At("HEAD"), cancellable = true)
    private void stopMoving(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity entity = (PlayerEntity) (Object) this;
        if (getIsTransforming()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getActiveEyeHeight", at = @At("HEAD"), cancellable = true)
    private void getNewEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> cir) {

        try {
            if (getBooleanData()) {
                cir.setReturnValue(8f);
            }
        }
        catch (Exception ignored){

        }
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void customDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(IS_TRANSFORMED, false);
        this.dataTracker.startTracking(IS_TRANSFORMING, false);
    }



    @Override
    public boolean getBooleanData() {
        return this.getDataTracker().get(IS_TRANSFORMED);
    }

    @Override
    public void setBooleanData(boolean newValue) {
        this.getDataTracker().set(IS_TRANSFORMED, newValue);
    }

    @Override
    public boolean getIsTransforming() {
        return this.getDataTracker().get(IS_TRANSFORMING);
    }

    @Override
    public void setIsTransforming(boolean newValue) {
        this.getDataTracker().set(IS_TRANSFORMING, newValue);
    }

    @Override
    public void initTransformation() {
        this.initTransformation = true;
    }


    @Override
    public void playerEntityInit() {
        this.playerInit = true;
        this.setIsTransforming(true);
        this.setInvulnerable(true);
    }

}



