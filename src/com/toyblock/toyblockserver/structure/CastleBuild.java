package com.toyblock.toyblockserver.structure;

import locate.Main;
import locate.WorldEditAPIController;
import locate.tool;
import org.bukkit.Location;

public class CastleBuild {
    Location castleLoc;
    String castleName;
    int castleLevel =1;
    int rotate =0;

    public CastleBuild(Location castleLoc , String castleName) {
    this.castleLoc=castleLoc;
    this.castleName=castleName;
    }
    public void setLevel(int level) {
        this.castleLevel=level;
    }
    public void setDirection (String direction) {
        if(direction.equals("S")) {
            rotate=0;
        }
        if(direction.equals("W")) {
            rotate=60;
        }
        if(direction.equals("N")) {
            rotate=90;
        }
        if(direction.equals("E")) {
            rotate=120;
        }


    }
    public void build() {
        WorldEditAPIController edit = new WorldEditAPIController("./Astral_server/schematic/village/castle", "world");
        edit.load(castleName+castleLevel);
        edit.paste(castleLoc,rotate);
        int x_loc = tool.change_xyz(castleLoc.getBlockX());
        int z_loc = tool.change_xyz(castleLoc.getBlockZ());
        int y_start = castleLoc.getBlockY();
        int x_start = x_loc+50;
        int z_start = z_loc+50;
        for(int x=-100; x<1; x=x+5)
        {
            for(int y=-200; y<200; y=y+10) {
                for(int z=-100; z<1; z=z+5) {

                    Location loc = new Location(castleLoc.getWorld(), x_start+x,y_start+y,z_start+z);
                    locate.Main.villge_index_loc.put(loc,"Castle_part");
                    //	loc.getBlock().setType(Material.WHITE_WOOL);
                }
            }
        }
        for(int x=-100; x<1; x=x+5)
        {

            for(int z=-100; z<1; z=z+5) {

                Location loc = new Location(castleLoc.getWorld(), x_start+x,y_start,z_start+z);
                Main.villge_index_loc.put(loc,"Castle");
                //loc.getBlock().setType(Material.RED_WOOL);
            }
        }


    }


}

}
