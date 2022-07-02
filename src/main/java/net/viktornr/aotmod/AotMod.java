package net.viktornr.aotmod;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.viktornr.aotmod.abilities.BasicAbilityClass;
import net.viktornr.aotmod.abilities.WarhammerAbilityClass;
import net.viktornr.aotmod.block.ModBlocks;
import net.viktornr.aotmod.item.ModItems;
import net.viktornr.aotmod.sound.ModSoundsClass;
import net.viktornr.aotmod.util.ITransformData;
import net.viktornr.aotmod.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AotMod implements ModInitializer {

	public static final String MOD_ID = "aotmod";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	//Creates new packet identifier
	public static final Identifier TRANSFORM_UPDATE = new Identifier(AotMod.MOD_ID, "transform_update");
	public static final Identifier DETRANSFORM_REQUEST = new Identifier(AotMod.MOD_ID, "detransform_request");
	public static final Identifier CLIENT_DETRANSFORM_REQUEST = new Identifier(AotMod.MOD_ID, "detransform_request");
	public static final Identifier ABILITY_REQUEST = new Identifier(AotMod.MOD_ID, "ability_request");


	@Override
	public void onInitialize() {

//		ServerPlayNetworking.registerGlobalReceiver(ABILITY_REQUEST, (server, player, handler, buf, responseSender) -> {
//			server.execute(() -> {
//				//ColossalAbilityClass ability = new ColossalAbilityClass();
//				WarhammerAbilityClass ability = new WarhammerAbilityClass();
//				ability.useAbility(player, "jackhammer", player.getWorld());
//				player.sendMessage(new LiteralText("Ability request received."), false);
//			});
//		});

		ServerPlayNetworking.registerGlobalReceiver(ABILITY_REQUEST, (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				//ColossalAbilityClass ability = new ColossalAbilityClass();
				BasicAbilityClass ability = new BasicAbilityClass();
				ability.useAbility(player, "basic", player.getWorld());
				player.sendMessage(new LiteralText("Ability request received."), false);
			});
		});

		ServerPlayNetworking.registerGlobalReceiver(DETRANSFORM_REQUEST, (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				((ITransformData) player).setBooleanData(false);
			});
		});


		//Mod Register Stuff


		ModItems.registerModItems();
        ModBlocks.registerModBlock();
		ModSoundsClass.registerModSounds();

		ModRegistries.registerModStuffs();

		LOGGER.info("Hello Fabric world!");
	}
}
