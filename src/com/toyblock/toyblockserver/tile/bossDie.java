package com.toyblock.toyblockserver.tile;

import com.toyblock.toyblockserver.Main;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class bossDie implements Listener {
    @EventHandler
    public void bossSet(EntitySpawnEvent event) {
        if(!event.getEntity().getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG)) {
            return;
        }
        if ( event.getEntity().getType().equals(EntityType.ZOGLIN)) {
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        entity.setCustomName("boss");
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
        entity.setHealth(100);

    }
    @EventHandler
    public void bossDie(EntityDeathEvent event) {
        EntityType type = event.getEntityType();
        if(!(type == EntityType.ZOGLIN)) {
            return;
        }
        if(event.getEntity().getCustomName().isEmpty()) {
            return;
        }
        Player player =event.getEntity().getKiller();
        player.sendMessage("boss die!");
        player.getWorld().spawnEntity(player.getLocation(),EntityType.FIREWORK);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = getTileCenter(event.getEntity().getLocation());
                tileSpawn spawn = new tileSpawn();
                spawn.spawn(loc,555555);
                this.cancel();
            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class), 500, 500);




    }
    public Location getTileCenter(Location loc) {
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        x = x-(x%95)+48;
        z = z-(z%95)+48;
        Location newLoc = new Location(loc.getWorld(),x,63,z);
        return newLoc;
    }
}
