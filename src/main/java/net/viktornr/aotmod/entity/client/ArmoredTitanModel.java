package net.viktornr.aotmod.entity.client;

import net.minecraft.util.Identifier;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.custom.ArmoredTitanEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ArmoredTitanModel extends AnimatedGeoModel<ArmoredTitanEntity> {
    @Override
    public Identifier getModelLocation(ArmoredTitanEntity object) {
        return new Identifier(AotMod.MOD_ID, "geo/armored_titan.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ArmoredTitanEntity object) {
        return new Identifier(AotMod.MOD_ID, "textures/entity/armored_titan/armored_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ArmoredTitanEntity animatable) {
        return new Identifier(AotMod.MOD_ID, "animations/armored.animation.json");
    }

//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Override
//    public void setLivingAnimations(ArmoredTitanEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
//        super.setLivingAnimations(entity, uniqueID, customPredicate);
//        IBone head = this.getAnimationProcessor().getBone("head");
//
//        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
//        if (head != null) {
//            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
//            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
//        }
//    }


}
