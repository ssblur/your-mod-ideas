package com.ssblur.obt;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public class OBTDamageSources {
  public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(OBTMod.MOD_ID, Registries.DAMAGE_TYPE);
  public static final ResourceKey<DamageType> SQUAT_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, OBTMod.location("squat"));
  public static final ResourceKey<DamageType> BABABOOEY_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, OBTMod.location("bababooey"));
  public static final ResourceKey<DamageType> MAYO_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, OBTMod.location("mayo"));

  public static DamageSource squatDamage(Entity entity) {
    var level = entity.level();
    return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(SQUAT_DAMAGE));
  }

  public static DamageSource bababooeyDamage(Entity entity) {
    var level = entity.level();
    return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(BABABOOEY_DAMAGE));
  }

  public static DamageSource mayoDamage(Entity entity) {
    var level = entity.level();
    return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(MAYO_DAMAGE));
  }
}
