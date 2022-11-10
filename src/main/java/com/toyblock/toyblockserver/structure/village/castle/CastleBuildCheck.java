package com.toyblock.toyblockserver.structure.village.castle;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.toyblock.toyblockserver.tool.LocBalance;
import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

public class CastleBuildCheck {
    public CuboidRegion createCastleCuboidRegion(Location center) {
        World world = center.getWorld();

        int x_loc = tool.change_xyz(center.getBlockX());
        int z_loc = tool.change_xyz(center.getBlockZ());

        Location point_main =  world.getBlockAt(x_loc, center.getBlockY(), z_loc).getLocation();
        Location point1 =  world.getBlockAt(x_loc+50, center.getBlockY()+1, z_loc+50).getLocation();
        Location point2 =  world.getBlockAt(point1.getBlockX()-100, point1.getBlockY()+30, point1.getBlockZ()-100).getLocation();
        BlockVector3 pos1 = BlockVector3.at(point1.getX(), point1.getY(), point1.getZ());
        BlockVector3 pos2 = BlockVector3.at(point2.getX(), point2.getY(), point2.getZ());

        com.sk89q.worldedit.world.World w = new BukkitWorld(center.getWorld());
        CuboidRegion region = new CuboidRegion(w, pos1, pos2);
        return region;
    }

    public void checkOn( Location center,String name) {
        CuboidRegion region = createCastleCuboidRegion(center);
        Location mainLoc = LocBalance.balance(center);
        int air = countBlock(region);

        if ( air >= 300000 ) {
            CastleBuild build = new CastleBuild(name,mainLoc);
            build.setRotate("S");
            build.Build();
        }
        else {
            CastleBuild build = new CastleBuild(name,mainLoc);
            build.setRotate("S");
            build.Build();
        }
    }
    public int countBlock(CuboidRegion region) {
        EditSession edit = WorldEdit.getInstance().newEditSession(region.getWorld());

        Set<BaseBlock> airSet = new HashSet<>();
        BlockType air = BlockTypes.AIR;
        BlockState waterState = air.getDefaultState();
        airSet.add(waterState.toBaseBlock());

        int count = edit.countBlocks(region,airSet);
        return count;
    }
}
