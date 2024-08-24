package com.ssblur.obt.fabric;

import com.ssblur.obt.OBTMod;
import net.fabricmc.api.ModInitializer;

public class OBTModFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    OBTMod.init();
  }
}
