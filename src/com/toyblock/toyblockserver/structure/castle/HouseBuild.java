package com.toyblock.toyblockserver.structure.castle;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.LocBalance;
import locate.WorldEditAPIController;
import locate.tool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseBuild {
    Location loc;
    World world;
    Player player;
    String view;
    public HouseBuild(Location Loc, Player player,String view) {
        this.loc = Loc;
        this.world = Loc.getWorld();
        this.player = player;
        this.view=view;
    }
    public void build() {


    }

}
