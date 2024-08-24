package com.ssblur.obt.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class DadEntity extends Monster {
  public DadEntity(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0f));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return OBTEntities.DAD_SOUND.get();
  }
}
