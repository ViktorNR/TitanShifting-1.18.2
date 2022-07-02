package net.viktornr.aotmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityPose;
import net.viktornr.aotmod.entity.ModEntities;
import net.viktornr.aotmod.entity.client.ArmoredTitanRenderer;
import net.viktornr.aotmod.entity.client.FemaleTitanRenderer;
import org.lwjgl.glfw.GLFW;

public class AotModClientMod implements ClientModInitializer {

    public static final KeyBinding TRANSFORM_KEY =
            new KeyBinding(
                    "key.aotmod.transform_key",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_LEFT_ALT,
                    "key.categories.aotmod"
            );

    public static final KeyBinding DE_TRANSFORM_KEY =
            new KeyBinding(
                    "key.aotmod.de_transform_key",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_O,
                    "key.categories.aotmod"
            );

//    public static final KeyBinding SWITCH_TITAN =
//            new KeyBinding(
//                    "key.aotmod.switch_titan_key",
//                    InputUtil.Type.KEYSYM,
//                    GLFW.GLFW_KEY_M,
//                    "key.categories.aotmod"
//            );

    public static final KeyBinding ABILITY_KEY =
            new KeyBinding(
                    "key.aotmod.ability_key",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    "key.categories.aotmod"
            );





    @Override
    public void onInitializeClient() {


        KeyBindingHelper.registerKeyBinding(TRANSFORM_KEY);

        EntityRendererRegistry.register(ModEntities.ARMORED_TITAN, ArmoredTitanRenderer::new);
        EntityRendererRegistry.register(ModEntities.FEMALE_TITAN, FemaleTitanRenderer::new);



        //Keybindings
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ABILITY_KEY.wasPressed()) {
                //assert client.player != null;
                ClientPlayNetworking.send(AotMod.ABILITY_REQUEST, PacketByteBufs.empty());
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (DE_TRANSFORM_KEY.wasPressed()) {
                assert client.player != null;
                client.player.setPose(EntityPose.SWIMMING);
                client.options.setPerspective(Perspective.FIRST_PERSON);
                ClientPlayNetworking.send(AotMod.DETRANSFORM_REQUEST, PacketByteBufs.empty());
            }
        });



        //Packets
        ClientPlayNetworking.registerGlobalReceiver(AotMod.TRANSFORM_UPDATE, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                // Everything in this lambda is run on the render thread
                assert client.player != null;
                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
                client.player.setPose(EntityPose.SWIMMING);
            });
        });



    }





//Final
}
