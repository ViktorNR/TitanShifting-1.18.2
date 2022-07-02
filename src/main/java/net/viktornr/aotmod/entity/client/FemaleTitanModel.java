package net.viktornr.aotmod.entity.client;

import net.minecraft.util.Identifier;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.custom.ArmoredTitanEntity;
import net.viktornr.aotmod.entity.custom.FemaleTitanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FemaleTitanModel extends AnimatedGeoModel<FemaleTitanEntity> {
    @Override
    public Identifier getModelLocation(FemaleTitanEntity object) {
        return new Identifier(AotMod.MOD_ID, "geo/female_titan.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FemaleTitanEntity object) {
        return new Identifier(AotMod.MOD_ID, "textures/entity/female_titan/female_titan.png");
    }

    @Override
    public Identifier getAnimationFileLocation(FemaleTitanEntity animatable) {
        return new Identifier(AotMod.MOD_ID, "animations/female.animation.json");
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
