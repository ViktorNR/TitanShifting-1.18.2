package net.viktornr.aotmod.entity.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.viktornr.aotmod.AotMod;
import net.viktornr.aotmod.entity.custom.ArmoredTitanEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ArmoredTitanRenderer extends GeoEntityRenderer<ArmoredTitanEntity> {

    public ArmoredTitanRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ArmoredTitanModel());
    }

    @Override
    public Identifier getTextureLocation(ArmoredTitanEntity instance) {
        return new Identifier(AotMod.MOD_ID, "textures/entity/armored_titan/armored_texture.png");
    }


}
