package com.toyblock.toyblockserver.structure.protect;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

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
                        if(com.toyblock.toyblockserver.structure.protect.StructrueMap.protect.containsKey(pointLoc)) {
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
                            com.toyblock.toyblockserver.structure.protect.StructrueMap.Link.put(pointLoc,"Castle_connect");

                        }
                        else {
                            com.toyblock.toyblockserver.structure.protect.StructrueMap.protect.put(pointLoc,"Castle");

                        }
                    }
                }
            }
        }

        if(size=="Dungeon") { //200x200
            Location endLoc = new Location(this.world, this.structureLoc.getX()+100, this.structureLoc.getY(), this.structureLoc.getZ()+100);
            for(int x=-200; x<1; x=x+5) {
                for (int y=-400; y<401; y=y+5) {
                    for (int z=-200; z<1; z=z+5) {
                        Location pointLoc = new Location(this.world, endLoc.getX()+x, endLoc.getY()+y, endLoc.getZ()+z);
                        if(com.toyblock.toyblockserver.structure.protect.StructrueMap.protect.containsKey(pointLoc)) {
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
                            com.toyblock.toyblockserver.structure.protect.StructrueMap.protect.put(pointLoc,"Dungeon");

                    }
                }
            }


        }
    }
}
