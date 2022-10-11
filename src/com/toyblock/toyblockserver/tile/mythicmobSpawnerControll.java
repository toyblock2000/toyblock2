package com.toyblock.toyblockserver.tile;

import com.toyblock.toyblockserver.Main;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.adapters.AbstractWorld;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.adapters.BukkitWorld;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;
import io.lumine.mythic.core.spawning.spawners.SpawnerManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class mythicmobSpawnerControll {
    public void test (Location loc) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("SkeletalKnight").orElse(null);
        SpawnerManager manager = new SpawnerManager(MythicBukkit.inst());
        manager.createSpawner("tester",loc,"SkeletonKing");
        manager.unload();
    }
    public boolean isSpawnerStand(LivingEntity entity) {
        if(entity.getCustomName().isEmpty()) {
            return false;
        }
        if(entity.getType().equals(EntityType.ARMOR_STAND)) {
            return false;
        }
        if(!entity.getEquipment().getHelmet().hasItemMeta()) {
            return false;
        }
        return true;

    }
}
