package net.viktornr.aotmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.viktornr.aotmod.util.ITransformData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }




    @Redirect(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    )
    public void redirectRender(LivingEntityRenderer renderer, LivingEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        //LivingEntity titan = PlayerTitanData.getTitan((PlayerEntity) player);

        EntityType<?> entity = Registry.ENTITY_TYPE.get(new Identifier("minecraft", "giant"));
        Entity Oldtitan = entity.create(player.getWorld());
        LivingEntity titan = (LivingEntity) Oldtitan;

        if (((ITransformData) player).getBooleanData()) {
            titan.lastLimbDistance = player.lastLimbDistance;
            titan.limbDistance = player.limbDistance;
            titan.limbAngle = player.limbAngle;
            titan.handSwinging = player.handSwinging;
            titan.handSwingTicks = player.handSwingTicks;
            titan.lastHandSwingProgress = player.lastHandSwingProgress;
            titan.handSwingProgress = player.handSwingProgress;
            titan.bodyYaw = player.bodyYaw;
            titan.prevBodyYaw = player.prevBodyYaw;
            titan.headYaw = player.headYaw;
            titan.prevHeadYaw = player.prevHeadYaw;
            titan.age = player.age;
            titan.preferredHand = player.preferredHand;
            titan.setOnGround(player.isOnGround());
            titan.setVelocity(player.getVelocity());



            titan.setPitch(player.getPitch());
            titan.prevPitch = player.prevPitch;
            
            titan.setPose(player.getPose());


            EntityRenderer titanRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(titan);
            titanRenderer.render(titan, f, g, matrixStack, vertexConsumerProvider, i);

        } else {
            super.render((AbstractClientPlayerEntity) player, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }

}