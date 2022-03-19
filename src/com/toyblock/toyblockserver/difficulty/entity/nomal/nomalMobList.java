package com.toyblock.toyblockserver.difficulty.entity.nomal;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

public class nomalMobList {
    public void setHealth(LivingEntity mob, double percent) {
        double health = mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double value = (health*percent/100);
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(value);
        mob.setHealth(value);
    }
    public void zombieHurt_1(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 * 1.5);
        mob.setHealth(40);
    }

    public void zombieDamage_1(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.5 * 1.5);
    }

    public void zombieMoveSpeed_1(Zombie mob) {
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23 * 1.5);
    }
    public void skeletonMoveSpeed_x05(Skeleton mob) {
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25*1.5);
    }
    public void skeletonHurt_x05(Skeleton mob) {
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20*1.5);
    }
    public void creeperSlient(Creeper mob) {
        mob.setSilent(true);
    }
    public void creeperMoveSpeed_x05(Creeper mob) {
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2*1.5);
    }
}
