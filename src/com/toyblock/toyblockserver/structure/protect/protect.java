package com.toyblock.toyblockserver.structure.protect;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.structure.StructureMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class protect {
    Location structureLoc;
    World world;
    public protect (Location structureLoc){
        this.structureLoc=structureLoc;
        this.world = structureLoc.getWorld();
    }
    public void VILLAGE_CASTLE() { //100X100

            Location endLoc = new Location(this.world, this.structureLoc.getX() + 50, this.structureLoc.getY(), this.structureLoc.getZ() + 50);
            Location loc1 = new Location(this.world, this.structureLoc.getX() + 52 , this.structureLoc.getY() + 300, this.structureLoc.getZ() + 52);
            Location loc2 = new Location(this.world, this.structureLoc.getX() - 52, this.structureLoc.getY() - 300, this.structureLoc.getZ() - 52);
            castleguard(loc1, loc2, "test");
            for (int x = -100; x < 1; x = x + 5) {
                for (int y = -400; y < 401; y = y + 5) {
                    for (int z = -100; z < 1; z = z + 5) {
                        Location pointLoc = new Location(this.world, endLoc.getX() + x, endLoc.getY() + y, endLoc.getZ() + z);
                        if (pointLoc.getY() == endLoc.getY()) {
                            StructureMap.Link.put(pointLoc, "true");

                        }

                    }
                }
            }

    }
    public void DUNGEON_MOB() { //200X200
            Location endLoc = new Location(this.world, this.structureLoc.getX()+100, this.structureLoc.getY(), this.structureLoc.getZ()+100);
            for(int x=-200; x<1; x=x+5) {
                for (int y=-400; y<401; y=y+5) {
                    for (int z=-200; z<1; z=z+5) {
                        Location pointLoc = new Location(this.world, endLoc.getX()+x, endLoc.getY()+y, endLoc.getZ()+z);
                    }
                }
            }
            for(int x=-200; x<1; x=x+5) {
                for (int y = -400; y < 401; y = y + 5) {
                    for (int z = -200; z < 1; z = z + 5) {
                        Location pointLoc = new Location(this.world, endLoc.getX() + x, endLoc.getY() + y, endLoc.getZ() + z);

                        Block blockadd = world.getBlockAt(pointLoc);
                            StructureMap.Link.put(pointLoc,"Dungeon");

                    }
                }
            }
        }
    public void VILLAGE_PATH() {
        StructureMap.Link.put(structureLoc,"true");
    }
    public void castleguard(Location loc1,Location loc2,String name) {
        BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
        BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());

        ProtectedRegion region = new ProtectedCuboidRegion(name, pos1, pos2);
        region.setFlag(Flags.GREET_TITLE,"마을\n안녕");
        region.setFlag(Flags.FAREWELL_TITLE,"꺼져\n인마");
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(loc1.getChunk().getWorld()));
        if(!(manager.getRegion(name) == null)) {
            manager.removeRegion(name);
            manager.addRegion(region);
        }
        else{
            manager.addRegion(region);
        }
        try {
            manager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }
    public void connect(ProtectedRegion build) {
        String name = "test";
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        ProtectedRegion castle = manager.getRegion(name);
        addWorldGuard(castle,build,name);
    }
    public void addWorldGuard(ProtectedRegion castle,ProtectedRegion build,String name) {
        BlockVector3 castle1 = castle.getMinimumPoint();
        BlockVector3 castle2 = castle.getMaximumPoint();
        BlockVector3 build1 = build.getMinimumPoint();
        BlockVector3 build2 = build.getMaximumPoint();
        BlockVector3 newcastle1 = BlockVector3.at(Math.min(castle1.getBlockX(),build1.getBlockX()),-64, Math.min(castle1.getBlockZ(),build1.getBlockZ()));
        BlockVector3 newcastle2 = BlockVector3.at(Math.max(castle2.getBlockX(),build2.getBlockX()),300, Math.max(castle2.getBlockZ(),build2.getBlockZ()));
        ProtectedRegion region = new ProtectedCuboidRegion(name, newcastle1, newcastle2);
        region.setFlag(Flags.GREET_TITLE,"마을\n안녕");
        region.setFlag(Flags.FAREWELL_TITLE,"꺼져\n인마");



        BlockVector3 newcastle1side = BlockVector3.at(Math.min(castle1.getBlockX(),build1.getBlockX())-20,-64, Math.min(castle1.getBlockZ(),build1.getBlockZ())-20);
        BlockVector3 newcastle2side = BlockVector3.at(Math.max(castle2.getBlockX(),build2.getBlockX())+20,300, Math.max(castle2.getBlockZ(),build2.getBlockZ())+20);
        ProtectedRegion regionside = new ProtectedCuboidRegion(name+"side", newcastle1side, newcastle2side);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        manager.removeRegion(name);
        manager.removeRegion(name+"side");
        manager.addRegion(region);
        manager.addRegion(regionside);
        manager.getApplicableRegionsIDs(castle1);
        try {
            manager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }

    }
    public void protect(Location loc1, Location loc2,String name) {

        BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
        BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());
        com.sk89q.worldedit.world.World world = (com.sk89q.worldedit.world.World)loc1.getWorld();
        ProtectedRegion region = new ProtectedCuboidRegion(name,pos1,pos2);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(world);
        regions.addRegion(region);
        region.setFlag(Flags.BLOCK_BREAK, StateFlag.State.DENY);
        try {
            regions.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }

    }
    public void protecst(Location loc1, Location loc2,String name) {

        BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
        BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());
        com.sk89q.worldedit.world.World world = (com.sk89q.worldedit.world.World)loc1.getWorld();
        ProtectedRegion region = new ProtectedCuboidRegion(name,pos1,pos2);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(world);
        regions.addRegion(region);
        region.setFlag(Flags.BLOCK_BREAK, StateFlag.State.DENY);
        try {
            regions.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }

    }
}
