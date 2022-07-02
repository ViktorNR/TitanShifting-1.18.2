package net.viktornr.aotmod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.custom.FemaleTitanEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FemaleTitanRenderer extends GeoEntityRenderer<FemaleTitanEntity> {


    public FemaleTitanRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new FemaleTitanModel());
    }

    @Override
    public Identifier getTextureLocation(FemaleTitanEntity instance) {
        return new Identifier(AotMod.MOD_ID, "textures/entity/female_titan/female_titan.png");
    }


}
