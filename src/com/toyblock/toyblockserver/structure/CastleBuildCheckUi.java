package com.toyblock.toyblockserver.structure;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CastleBuildCheckUi {

    public void checkOn(Player player, Location point) {
        World world = point.getWorld();
        Location point1 =  world.getBlockAt(point.getBlockX()+50, point.getBlockY(), point.getBlockZ()+50).getLocation();
        Location point2 =  world.getBlockAt(point1.getBlockX()-100, point1.getBlockY(), point1.getBlockZ()-100).getLocation();

        BlockVector3 pos1 = BlockVector3.at(point1.getX(), point1.getY(), point1.getZ());
        BlockVector3 pos2 = BlockVector3.at(point2.getX(), point2.getY(), point2.getZ());

        com.sk89q.worldedit.world.World w = new BukkitWorld(Bukkit.getWorld("world"));
        CuboidRegion region = new CuboidRegion(w, pos1, pos2);



        EditSession edit = WorldEdit.getInstance().newEditSession(region.getWorld());

        Set<BaseBlock> airSet = new HashSet<>();
        BlockType air = BlockTypes.AIR;
        BlockState waterState = air.getDefaultState();
        airSet.add(waterState.toBaseBlock());

        int count = edit.countBlocks(region,airSet);
        player.chat("확인"+count);



    }
}
