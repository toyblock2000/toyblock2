package com.toyblock.toyblockserver.structure.village.castle.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;

public class MakeRegion {
    public void Make(Location loc,String name) {
        BlockVector3 min = BlockVector3.at(loc.getBlockX()+2,loc.getBlockY()+5,loc.getBlockZ()+2);
        BlockVector3 max = BlockVector3.at(loc.getBlockX()-2,loc.getBlockY()-4,loc.getBlockZ()-2);
        ProtectedRegion region = new ProtectedCuboidRegion(name,min,max);
        region.setFlag(Flags.GREET_TITLE,"길\n안녕");
        region.setFlag(Flags.FAREWELL_TITLE,"꺼져\n인마");
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(loc.getWorld()));
        regions.addRegion(region);
        region.setFlag(Flags.BLOCK_BREAK, StateFlag.State.DENY);
        try {
            regions.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }
}
