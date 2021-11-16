package com.toyblock.toyblockserver.entity.ai;

import com.toyblock.toyblockserver.developer.bug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class TargetMisses implements Listener {
    @EventHandler
    public void targerMisses (EntityDeathEvent event) {
        if(event.getEntity().getType().equals(EntityType.ZOMBIE)) {
            bug.chat("좀비 확인됨 흑 생성 시작");
            Location loc = event.getEntity().getLocation();
            World world = event.getEntity().getWorld();
            loc.getBlock().setType(Material.DIRT);
            bug.chat("좀비 흙 생성 완료");
            LivingEntity fish = (LivingEntity) world.spawnEntity(loc,EntityType.SILVERFISH);
            fish.setJumping(true);
            fish.setCustomName("virus");
        }
        if(event.getEntity().getCustomName().equals("virus")) {
            bug.chat("바이러스 확인");
            event.getEntity().getLocation().getBlock().setType(Material.DIRT);
        }

    }
}
