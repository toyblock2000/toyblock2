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
import com.toyblock.toyblockserver.structure.castle.CastleBuild;
import locate.tool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CastleBuildCheckUi {

    public void checkOn(Player player, Location point) {
        World world = point.getWorld();

        int x_loc = tool.change_xyz(point.getBlockX());
        int z_loc = tool.change_xyz(point.getBlockZ());

        Location point_main =  world.getBlockAt(x_loc, point.getBlockY(), z_loc).getLocation();
        Location point1 =  world.getBlockAt(x_loc+50, point.getBlockY()+1, z_loc+50).getLocation();
        Location point2 =  world.getBlockAt(point1.getBlockX()-100, point1.getBlockY()+30, point1.getBlockZ()-100).getLocation();

        world.spawnParticle(Particle.CLOUD,point1.getX(),point1.getY(),point1.getZ(),10);
        world.spawnParticle(Particle.CLOUD,point2.getX(),point2.getY(),point2.getZ(),10);

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

        if ( count >= 300000 ) {
            player.chat(" 성공완료 "
            );
            com.toyblock.toyblockserver.structure.castle.CastleBuild build = new CastleBuild("castle_test",point_main);
            build.setRotate("S");
            build.Build();

            player.chat(" 생성완료 "
            );



        }
        else {
            player.chat("실패");
        }



    }
}
