package com.toyblock.toyblockserver.structure.castle.setting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SpawnUser {
    public static HashMap<Player , Location> userCastle = new HashMap<Player , Location > () ;
    public static HashMap<Location , String > castleConnect = new HashMap<Location, String > () ;
    public void dieSpawn(Player player) {
        Location userSpawnLoc = userCastle.get(player);
        player.teleport(userSpawnLoc);
    }
    public void tpSpawn(Player player,Location tpLoc) {
        Location userSpawnLoc = userCastle.get(player);
        if(!(tpLoc == userSpawnLoc)) {
            if(castleConnect.get(tpLoc) == "any") {
                player.teleport(tpLoc);
            }
        }


    }

}
