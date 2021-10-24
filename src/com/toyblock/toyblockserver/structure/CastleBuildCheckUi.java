package com.toyblock.toyblockserver.structure;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.World;

public class CastleBuildCheckUi {

    public void checkOn(Location point) {
        World world = point.getWorld();
        Location point1 =  world.getBlockAt(point.getBlockX()+50, point.getBlockY(), point.getBlockZ()+50).getLocation();
        Location point2 =  world.getBlockAt(point1.getBlockX()-100, point1.getBlockY(), point1.getBlockZ()-100).getLocation();

        BlockVector3 pos1 = BlockVector3.at(point1.getX(), point1.getY(), point1.getZ());
        BlockVector3 pos2 = BlockVector3.at(point2.getX(), point2.getY(), point2.getZ());

        CuboidRegion region = new CuboidRegion(, pos1, pos2);
        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1);
        editSession.countBlocks(region,bas)


    }
}
