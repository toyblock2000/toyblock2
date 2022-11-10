package com.toyblock.toyblockserver.tile;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.difficulty.entity.Laser;
import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class worldCreative {
    public void worldCreative(Player player) {

        BukkitRunnable task = new BukkitRunnable() {
            private int a = 0;
            private int x = 1;
            private int z = 1;
            @Override
            public void run() {
                if(!(x+95>3900)) {
                    Location updateLoc = new Location(Bukkit.getWorld("world"),x+47,63,z+47);
                    tileSpawn spawn = new tileSpawn();
                    spawn.spawn(updateLoc,a);
                    a++;
                    x = x+95;
                    player.chat("생성중..");
                }
                else{
                    if(z+95>3900) {
                        player.chat("생성 완료");
                        this.cancel();
                    }
                        z = z + 95;
                        x = 1;
                        Location updateLoc = new Location(Bukkit.getWorld("world"), x+47, 63, z+47);
                        tileSpawn spawn = new tileSpawn();
                        spawn.spawn(updateLoc,a);
                        a++;
                    player.chat("생성중..");
                }

            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class), 100, 100);
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
