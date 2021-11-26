package com.toyblock.toyblockserver.structure.village.path;

import com.toyblock.toyblockserver.structure.StructrueMap;
import org.bukkit.Location;
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

        if (!(StructrueMap.Link.containsKey(loc1)==true||
                StructrueMap.Link.containsKey(loc2)==true||
                StructrueMap.Link.containsKey(loc3)==true)) {
            player.chat("길과 연결되어 있지 않음");

            return false;
        }
        player.chat("길과 연결되어 있습니다");
        player.chat(""+player.getLocation());
        return true;
    }
    public static Location LinkSideLoc(Location loc, String view) {
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

        if (!(StructrueMap.Link.containsKey(loc1)==true||
                StructrueMap.Link.containsKey(loc2)==true||
                StructrueMap.Link.containsKey(loc3)==true)) {


            return null;
        }
        if (view.equals("S")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX());
            z=(loc.getBlockZ()-3);
        }
        if (view.equals("E")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX()-3);
            z=(loc.getBlockZ());
        }
        if (view.equals("W")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX()+3);
            z=(loc.getBlockZ());
        }
        if (view.equals("N")) {
            y1=(loc.getBlockY());
            y2=(loc.getBlockY()-1);
            y3=(loc.getBlockY()+1);
            x=(loc.getBlockX());
            z=(loc.getBlockZ()+3);
        }
        Location buildloc1= new Location(loc.getWorld(),x,y1,z);
        Location buildloc2= new Location(loc.getWorld(),x,y2,z);
        Location buildloc3= new Location(loc.getWorld(),x,y3,z);
        if(StructrueMap.Link.containsKey(loc1)) {
            return buildloc1;
        }
        if(StructrueMap.Link.containsKey(loc2)) {
            return buildloc2;
        }
        if(StructrueMap.Link.containsKey(loc3)) {
            return buildloc3;
        }
        return null;
    }
}