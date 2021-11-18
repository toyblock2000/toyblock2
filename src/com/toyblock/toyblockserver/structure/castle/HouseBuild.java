package com.toyblock.toyblockserver.structure.castle;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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
