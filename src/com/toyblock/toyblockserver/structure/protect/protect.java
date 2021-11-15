package com.toyblock.toyblockserver.structure.protect;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.toyblock.toyblockserver.structure.smooth.SideSmooth;
import locate.Main;
import locate.WorldEditAPIController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;

public class protect {
    Location structureLoc;
    String size;
    World world;
    public protect (String size, Location structureLoc){
        this.size = size;
        this.structureLoc=structureLoc;
        this.world = structureLoc.getWorld();
    };
    public void protect() {
        if(size=="Castle") { //100x100
            Location endLoc = new Location(this.world, this.structureLoc.getX()+50, this.structureLoc.getY(), this.structureLoc.getZ()+50);
            for(int x=-100; x<1; x=x+5) {
                for (int y=-400; y<401; y=y+5) {
                    for (int z=-100; z<1; z=z+5) {
                        Location pointLoc = new Location(this.world, endLoc.getX()+x, endLoc.getY()+y, endLoc.getZ()+z);
                        if(structureHashMap.protect.containsKey(pointLoc)) {
                            return;
                        }
                    }
                }
            }
            for(int x=-100; x<1; x=x+5) {
                for (int y = -400; y < 401; y = y + 5) {
                    for (int z = -100; z < 1; z = z + 5) {
                        Location pointLoc = new Location(this.world, endLoc.getX() + x, endLoc.getY() + y, endLoc.getZ() + z);
                        if(pointLoc.getY()==endLoc.getY()) {
                            structureHashMap.Link.put(pointLoc,"Castle_connect");

                        }
                        else {
                            structureHashMap.protect.put(pointLoc,"Castle");

                        }
                    }
                }
            }
            Location loc1 = new Location(this.world, endLoc.getX() -100, endLoc.getY() -10 , endLoc.getZ() -100);
            Location loc2 = new Location(this.world, endLoc.getX() , endLoc.getY() +20 , endLoc.getZ() );
            BlockVector3 pos1 = BlockVector3.at(loc1.getX(), loc1.getY(), loc1.getZ());
            BlockVector3 pos2 = BlockVector3.at(loc2.getX(), loc2.getY(), loc2.getZ());
            CuboidRegion region = new CuboidRegion  ((com.sk89q.worldedit.world.World) Bukkit.getWorld("World"), pos1, pos2);
            region.getBoundingBox();
        }

        if(size=="Dungeon") { //200x200
            Location endLoc = new Location(this.world, this.structureLoc.getX()+100, this.structureLoc.getY(), this.structureLoc.getZ()+100);
            for(int x=-200; x<1; x=x+5) {
                for (int y=-400; y<401; y=y+5) {
                    for (int z=-200; z<1; z=z+5) {
                        Location pointLoc = new Location(this.world, endLoc.getX()+x, endLoc.getY()+y, endLoc.getZ()+z);
                        if(structureHashMap.protect.containsKey(pointLoc)) {
                           return;
                        }
                    }
                }
            }
            for(int x=-200; x<1; x=x+5) {
                for (int y = -400; y < 401; y = y + 5) {
                    for (int z = -200; z < 1; z = z + 5) {
                        Location pointLoc = new Location(this.world, endLoc.getX() + x, endLoc.getY() + y, endLoc.getZ() + z);

                        Block blockadd = world.getBlockAt(pointLoc);
                            structureHashMap.protect.put(pointLoc,"Dungeon");

                    }
                }
            }


        }
    }
}
