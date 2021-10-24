package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PathLink {
    public static boolean LinkCheck(Location loc, Player player, String view) {
        int x=0;
        int y1=0;
        int  y2=0;
        int y3 = 0;
        int z=0;
        if (view.equals("S")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX());
            z=(loc.getBlockZ()-5);
        }
        if (view.equals("E")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX()-5);
            z=(loc.getBlockZ());
        }
        if (view.equals("W")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX()+5);
            z=(loc.getBlockZ());
        }
        if (view.equals("N")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX());
            z=(loc.getBlockZ()+5);
        }
        Location loc1= new Location(loc.getWorld(),x,y1,z);
        Location loc2= new Location(loc.getWorld(),x,y2,z);
        Location loc3= new Location(loc.getWorld(),x,y3,z);

        if (!(structureHashMap.Link.containsKey(loc1)==true||
                structureHashMap.Link.containsKey(loc2)==true||
                structureHashMap.Link.containsKey(loc3)==true)) {
            player.chat("길과 연결되어 있지 않음");

            return false;
        }
        player.chat("길과 연결되어 있습니다");
        player.chat(""+player.getLocation());
        return true;
    }
}
