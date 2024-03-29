package com.toyblock.toyblockserver.structure.village.castle;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import java.util.HashSet;
import java.util.Set;

public class BlockBuild {

    public boolean BlockCheck (Location loc) {
        World world = loc.getWorld();
        Location point1 = world.getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 4, loc.getBlockZ() + 2).getLocation();
        Location point2 = world.getBlockAt(point1.getBlockX() - 4, point1.getBlockY() - 9, point1.getBlockZ() - 4).getLocation();

        world.spawnParticle(Particle.CLOUD, point1.getX(), point1.getY(), point1.getZ(), 10);
        world.spawnParticle(Particle.CLOUD, point2.getX(), point2.getY(), point2.getZ(), 10);

        BlockVector3 pos1 = BlockVector3.at(point1.getX(), point1.getY(), point1.getZ());
        BlockVector3 pos2 = BlockVector3.at(point2.getX(), point2.getY(), point2.getZ());

        com.sk89q.worldedit.world.World w = new BukkitWorld(Bukkit.getWorld("world"));
        CuboidRegion region = new CuboidRegion(w, pos1, pos2);


        EditSession edit = WorldEdit.getInstance().newEditSession(region.getWorld());

        Set<BaseBlock> airSet = new HashSet<>();
        BlockType air = BlockTypes.AIR;
        BlockState waterState = air.getDefaultState();
        airSet.add(waterState.toBaseBlock());

        int count = edit.countBlocks(region, airSet);


        if (count >= 80) {

            return true;
        } else {
            return false;
        }
    }
}
