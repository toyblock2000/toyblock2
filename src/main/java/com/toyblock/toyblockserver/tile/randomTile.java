package com.toyblock.toyblockserver.tile;

import com.toyblock.toyblockserver.structure.StructureMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class randomTile implements Listener {
    @EventHandler
    public void randomTile(PlayerInteractEvent event) {
        if(event.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)) {
            event.getPlayer().chat("randomtile");
            tileSpawn spawn = new tileSpawn();
            randomSpawn(event.getClickedBlock().getLocation(),event.getPlayer());
         }
    }
    public void randomSpawn(Location spawnLoc, Player player) {
        for (int x = (0); x < (95*4); x = x + 95) {
            for (int z = (0); z < (95*4); z = z + 95) {
                Location updateLoc = new Location(spawnLoc.getWorld(),spawnLoc.getBlockX()+x,63,spawnLoc.getBlockZ()+z);
                tileSpawn spawn = new tileSpawn();
                //spawn.spawn(updateLoc);
                }





            }
        }
    }
