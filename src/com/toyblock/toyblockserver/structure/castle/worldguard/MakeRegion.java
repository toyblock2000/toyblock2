package com.toyblock.toyblockserver.structure.castle.worldguard;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;

public class MakeRegion {
    public void Make(Location loc,String name) {
        BlockVector3 min = BlockVector3.at(loc.getBlockX()+2,loc.getBlockY()+5,loc.getBlockZ()+2);
        BlockVector3 max = BlockVector3.at(loc.getBlockX()-2,loc.getBlockY()-4,loc.getBlockZ()-2);
        ProtectedRegion region = new ProtectedCuboidRegion(name,min,max);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(world);
        regions.addRegion(region);
        region.setFlag(Flags.BLOCK_BREAK, StateFlag.State.DENY);
    }
}
