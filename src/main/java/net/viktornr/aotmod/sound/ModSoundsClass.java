package net.viktornr.aotmod.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.viktornr.aotmod.AotMod;

public class ModSoundsClass {
    public static SoundEvent TRANSFORMATION_SOUND = registerSoundEvent("transformation_sound");




    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(AotMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }


    public static void registerModSounds() {
        AotMod.LOGGER.info("Registering Mod Sounds for " + AotMod.MOD_ID);
    }
}