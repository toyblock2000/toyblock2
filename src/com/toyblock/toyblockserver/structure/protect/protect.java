package com.toyblock.toyblockserver.structure.protect;

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
            Location pointLoc1 = new Location(this.world, endLoc.getX() -100, endLoc.getY() -10 , endLoc.getZ() -100);
            Location pointLoc2 = new Location(this.world, endLoc.getX() , endLoc.getY() +20 , endLoc.getZ() );
            BoundingBox box = new BoundingBox(pointLoc1.getX(),pointLoc1.getBlockY(),pointLoc1.getBlockZ(),pointLoc2.getX(),pointLoc2.getBlockY(),pointLoc2.getBlockZ());
            box.
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
