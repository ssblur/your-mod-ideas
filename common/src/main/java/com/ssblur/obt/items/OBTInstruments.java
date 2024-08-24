package com.ssblur.obt.items;

import com.ssblur.obt.OBTMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;

@SuppressWarnings("unused")
public class OBTInstruments {
  public static final String MOD_ID = OBTMod.MOD_ID;

  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);
  public static final RegistrySupplier<SoundEvent> GOAT_HORN_FNAF_SOUND = SOUNDS.register("fnaf_goat_horn", () ->
    SoundEvent.createVariableRangeEvent(OBTMod.location("fnaf_goat_horn")));

  public static final DeferredRegister<Instrument> INSTRUMENTS = DeferredRegister.create(MOD_ID, Registries.INSTRUMENT);
  public static final RegistrySupplier<Instrument> GOAT_HORN_FNAF = INSTRUMENTS.register("fnaf_goat_horn", () ->
    new Instrument(GOAT_HORN_FNAF_SOUND, 140, 256f));

  public static void register() {
    SOUNDS.register();
    INSTRUMENTS.register();
  }
}
