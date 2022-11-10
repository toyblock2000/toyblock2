package com.toyblock.toyblockserver.tool;

import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Location;

public class LocBalance {
    public static Location balance(Location loc ) {
        int x = tool.change_xyz(loc.getBlockX());
        int z = tool.change_xyz(loc.getBlockZ());
        int y = loc.getBlockY();

        Location balanceLoc = new Location( loc.getWorld(),x,y,z);
        return balanceLoc;
    }
}
