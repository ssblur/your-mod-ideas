package com.ssblur.obt.neoforge;

import com.ssblur.obt.OBTMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@SuppressWarnings("unused")
@Mod(OBTMod.MOD_ID)
public final class OBTModNeoForge {
    public OBTModNeoForge(IEventBus bus) {
      OBTMod.init();
    }
}
