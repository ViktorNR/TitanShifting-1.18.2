package net.viktornr.aotmod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ArmoredTitanEntity extends HostileEntity implements IAnimatable {

    public static int ticksSinceSpawned;

    private AnimationFactory factory = new AnimationFactory(this);


    public ArmoredTitanEntity(EntityType<? extends HostileEntity> entityType, World world) {


        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.00)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 10f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f);
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.armored.idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public void tick() {
        ticksSinceSpawned++;
        super.tick();
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 10.440001f;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_REPAIR;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15f, 1.0f);
    }
}
