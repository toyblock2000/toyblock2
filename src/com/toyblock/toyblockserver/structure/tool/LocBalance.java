package com.toyblock.toyblockserver.structure.tool;

import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Location;

public class LocBalance {
    public Location balance(Location loc ) {
        int x = tool.change_xyz(loc.getBlockX());
        int z = tool.change_xyz(loc.getBlockZ());
        int y = loc.getBlockY();

        Location balanceLoc = new Location( loc.getWorld(),x,y,z);
        return balanceLoc;
    }
}
