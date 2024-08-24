package com.ssblur.obt.entity;

import com.ssblur.obt.OBTMod;
import com.ssblur.obt.entity.renderer.DadRenderer;
import com.ssblur.obt.entity.renderer.NightmareRenderer;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;

public class OBTEntities {
  public static final String MOD_ID = OBTMod.MOD_ID;

  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MOD_ID, Registries.ENTITY_TYPE);

  public static final RegistrySupplier<EntityType<DadEntity>> DAD = ENTITY_TYPES.register(
    "dad",
    () -> EntityType.Builder.of(
      DadEntity::new,
      MobCategory.MONSTER
    ).sized(1.5f, 1.5f).build("dad")
  );
  public static final RegistrySupplier<EntityType<NightmareEntity>> NIGHTMARE = ENTITY_TYPES.register(
    "nightmare",
    () -> EntityType.Builder.of(
      NightmareEntity::new,
      MobCategory.CREATURE
    ).sized(1.3964844f, 1.6f).clientTrackingRange(10).build("nightmare")
  );

  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

  public static final RegistrySupplier<SoundEvent> DAD_SOUND = SOUNDS.register("dad", () ->
    SoundEvent.createVariableRangeEvent(OBTMod.location("dad")));

  public static void register() {
    ENTITY_TYPES.register();
    SOUNDS.register();

    EntityAttributeRegistry.register(
      DAD, () ->
        new AttributeSupplier.Builder()
          .add(Attributes.MAX_HEALTH, 20)
          .add(Attributes.FOLLOW_RANGE, 12)
          .add(Attributes.ARMOR, 0)
          .add(Attributes.ARMOR_TOUGHNESS, 0)
          .add(Attributes.MAX_ABSORPTION, 0)
          .add(Attributes.KNOCKBACK_RESISTANCE, 0)
          .add(Attributes.ATTACK_DAMAGE, 8)
          .add(Attributes.ATTACK_KNOCKBACK, 2)
          .add(Attributes.ATTACK_SPEED, 6)
          .add(Attributes.MOVEMENT_SPEED, 0.24f)
    );
    EntityAttributeRegistry.register(NIGHTMARE, SkeletonHorse::createAttributes);

    if(Platform.getEnv() == EnvType.CLIENT)
      registerClient();
  }

  public static void registerClient() {
    EntityRendererRegistry.register(DAD, DadRenderer::new);
    EntityRendererRegistry.register(NIGHTMARE, NightmareRenderer::new);
  }
}
