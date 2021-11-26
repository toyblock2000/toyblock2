package com.toyblock.toyblockserver.difficulty.entity.ai;

import com.destroystokyo.paper.event.entity.CreeperIgniteEvent;
import com.sk89q.worldguard.bukkit.event.entity.DamageEntityEvent;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieDunkShot implements Listener {
  //  @EventHandler
    public void zombietuch(DamageEntityEvent event) {
        bug.chat("좀비 실행 디버그");
        if(!(event.getEntity().getType().equals(EntityType.ZOMBIE))) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        LivingEntity creeper = (LivingEntity) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.CREEPER);
        if (entity.getType().equals(EntityType.ZOMBIE)) {
            entity.addPassenger(creeper);
            zombieCreeper(entity);
        }
    }
   // @EventHandler
    public void boom(EntityDamageEvent event) {
        if(event.getEntity().getCustomName().equals("이야")) {
            event.setCancelled(true);
            return;
        }
        if(event.getEntity().getCustomName().equals("boom")) {
            if(event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                Creeper creeper = (Creeper) event.getEntity();
                creeper.explode();
                return;
            }
        }

        if(event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            event.getEntity().teleport(event.getEntity().getWorld().getHighestBlockAt(event.getEntity().getLocation()).getLocation());
        }
    }
  //  @EventHandler
    public void boomer(CreeperIgniteEvent event) {
        if(event.getEntity().getCustomName().equals("boom")) {
            event.getEntity().explode();
        }
    }
    public void zombieCreeper(LivingEntity zombie) {
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5, 5));
        zombie.setJumping(true);
        zombie.swingMainHand();

        Location loc = zombie.getEyeLocation().toVector().add(zombie.getLocation().getDirection().multiply(2)).
                toLocation(zombie.getWorld(), zombie.getLocation().getYaw(), zombie.getLocation().getPitch());
        Fireball fireball = zombie.getWorld().spawn(loc, Fireball.class);
        fireball.setCustomName("이야");
        zombie.getPassengers().get(0).setCustomName("boom");
        fireball.addPassenger(zombie.getPassengers().get(0));
    }
}
