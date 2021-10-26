package com.toyblock.toyblockserver.structure.dungeon;

import org.bukkit.Chunk;
import org.bukkit.Location;

public class AutoDungeonBuild {
    public void AutoBuild(Chunk castleChunk) {
       Location castle=  castleChunk.getBlock(0,0,0).getLocation();
       Location castleloc = new Location(castle.getWorld(),castle.getBlockX()+300,castle.getBlockY(),castle.getBlockZ()+300);
       Location dungeonloc = castleloc.getWorld().getHighestBlockAt(castleloc).getLocation();
       DungeonBuild setting = new DungeonBuild("dungeon_test",dungeonloc);
       setting.setRotate("S");
       setting.Build();

    }
}
