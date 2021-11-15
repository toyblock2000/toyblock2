package com.toyblock.toyblockserver.testclass;

import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class zombieAttack {
    public void zombieRiding(LivingEntity zombie,LivingEntity creeper) {
        zombie.addPassenger(creeper);
    }
    public void zombieCreeper(LivingEntity zombie) {
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,5,5));
        zombie.setJumping(true);
        zombie.swingMainHand();
        Location loc = zombie.getEyeLocation().toVector().add(zombie.getLocation().getDirection().multiply(2)).
                toLocation(zombie.getWorld(), zombie.getLocation().getYaw(), zombie.getLocation().getPitch());
        Fireball fireball = zombie.getWorld().spawn(loc, Fireball.class);
        fireball.setShooter(zombie);
    }
}
