package com.toyblock.toyblockserver.structure;

import com.sk89q.worldedit.math.BlockVector3;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import com.toyblock.toyblockserver.tool.tool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class CastleBuild {
    Location castleLoc;
    String castleName;
    int castleLevel = 1;
    int rotate = 0;
    String Color = "red";

    public CastleBuild(Location castleLoc, String castleName, String Color) {
        this.castleLoc = castleLoc;
        this.castleName = castleName;
        this.Color = Color;
    }

    public void setLevel(int level) {
        this.castleLevel = level;
    }

    public void setDirection(String direction) {
        if (direction.equals("S")) {
            this.rotate = 0;
        }
        if (direction.equals("W")) {
            this.rotate = 90;
        }
        if (direction.equals("N")) {
            this.rotate = 180;
        }
        if (direction.equals("E")) {
            this.rotate = 260;
        }


    }

    public void build() {
        int x_loc = tool.change_xyz(castleLoc.getBlockX());
        int z_loc = tool.change_xyz(castleLoc.getBlockZ());
        int y_start = castleLoc.getBlockY();
        int x_start = x_loc + 50;
        int z_start = z_loc + 50;
        BlockVector3 min = BlockVector3.at(x_loc - 50, 0, z_loc - 50);
        BlockVector3 max = BlockVector3.at(x_loc + 50, 300, z_loc + 50);
      //  ProtectedRegion region = new ProtectedCuboidRegion("castle" + Color, min, max);


        WorldEditAPIController edit = new WorldEditAPIController("./Astral_server/schematic/village/castle", "world");
        edit.load(castleName);
        edit.paste(castleLoc, rotate);
        for (int x = -100; x < 1; x = x + 5) {
            for (int y = -200; y < 200; y = y + 10) {
                for (int z = -100; z < 1; z = z + 5) {

                    Location loc = new Location(castleLoc.getWorld(), x_start + x, y_start + y, z_start + z);
                    Main.villge_index_loc.put(loc, "Castle_part");
                    	loc.getBlock().setType(Material.WHITE_WOOL);
                }
            }
        }
        for (int x = -100; x < 1; x = x + 5) {

            for (int z = -100; z < 1; z = z + 5) {

                Location loc = new Location(castleLoc.getWorld(), x_start + x, y_start, z_start + z);
                Main.villge_index_loc.put(loc, "Castle");
                loc.getBlock().setType(Material.RED_WOOL);
            }
        }
        Bukkit.getPlayer("Devil").sendMessage("실행전??");


    }
}


