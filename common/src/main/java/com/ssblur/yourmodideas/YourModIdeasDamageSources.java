package com.ssblur.yourmodideas;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

public class YourModIdeasDamageSources {
  public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(YourModIdeas.MOD_ID, Registries.DAMAGE_TYPE);
  public static final ResourceKey<DamageType> SQUAT_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(YourModIdeas.MOD_ID, "squat"));

  public static DamageSource squatDamage(Entity entity) {
    var level = entity.level();
    return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(SQUAT_DAMAGE));
  }
}
