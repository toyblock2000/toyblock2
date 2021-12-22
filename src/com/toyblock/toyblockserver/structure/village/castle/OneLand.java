package com.toyblock.toyblockserver.structure.village.castle;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.structureType;
import com.toyblock.toyblockserver.structure.village.castle.worldguard.MakeRegion;
import com.toyblock.toyblockserver.structure.village.path.rule.PathLineCheck;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class OneLand {
    Location loc;
    World world;

    public OneLand(Location Loc) {
        this.loc = Loc;
        this.world = Loc.getWorld();
    }
    private CuboidRegion makeCuboidRegion () {
        Location point1 = world.getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 4, loc.getBlockZ() + 2).getLocation();
        Location point2 = world.getBlockAt(point1.getBlockX() - 4, point1.getBlockY() - 9, point1.getBlockZ() - 4).getLocation();
        BlockVector3 pos1 = BlockVector3.at(point1.getX(), point1.getY(), point1.getZ());
        BlockVector3 pos2 = BlockVector3.at(point2.getX(), point2.getY(), point2.getZ());

        com.sk89q.worldedit.world.World w = new BukkitWorld(Bukkit.getWorld("world"));
        CuboidRegion region = new CuboidRegion(w, pos1, pos2);
        return region;
    }
    public void build() {
        CuboidRegion region = makeCuboidRegion();

        WorldEditAPIController pathedit = new WorldEditAPIController(structureType.VILLAGE_PATH.name(), "world");
        pathedit.load("path_test.schem");
        pathedit.paste(loc, 0);
        StructureMap.Link.put(loc, "true");
        StructureMap.Chunk.put(loc.getChunk(), "path");
        MakeRegion guard = new MakeRegion();
        String pathname = "testPath"+number("test");
        guard.Make(loc,pathname);
        protect pro = new protect(loc);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        pro.connect(regions.getRegion(pathname));

    }
    public static int number(String castleName) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
        int setting = -1;
        for(int i = 1;i<=manager.size();i++) {
            if(manager.hasRegion(castleName+"Path"+i)) {
                continue;
            }
            else{
                setting = i;
                return setting;
            }


        }
        return setting;
    }
}
