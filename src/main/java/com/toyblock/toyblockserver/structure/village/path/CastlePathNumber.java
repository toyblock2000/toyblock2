package com.toyblock.toyblockserver.structure.village.path;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;

public class CastlePathNumber {
    public static int number(String castleName) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        int setting = -1;
        for(int i = 1;i<=manager.size();i++) {
            if(manager.hasRegion(castleName+"Path"+i)) {
                continue;
            }
            else{
                setting = i;
                return setting;
            }


        }
        return setting;
    }
}
