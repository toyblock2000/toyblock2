package com.toyblock.toyblockserver.structure.protect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSave {
    public String locSave(Location loc) {
        String x = String.valueOf(loc.getBlockX());
        String y = String.valueOf(loc.getBlockY());
        String z = String.valueOf(loc.getBlockZ());
        String world = loc.getWorld().getName();


        return world+"x"+x+"y"+y+"z"+z;

    }
    public Location locput(String loc) {
        int x_dex = loc.indexOf("x");
        int y_dex = loc.indexOf("y");
        int z_dex = loc.indexOf("z");

        String world = loc.substring(0, x_dex);
        int x = Integer.parseInt(loc.substring(x_dex + 1, y_dex));
        int y = Integer.parseInt(loc.substring(y_dex + 1, z_dex));
        int z = Integer.parseInt(loc.substring(z_dex + 1));

        return new Location(Bukkit.getWorld(world), x, y, z);

    }
    public int x(String loc) {
            int x_dex = loc.indexOf("x");
            int y_dex = loc.indexOf("y");
            int z_dex = loc.indexOf("z");

            String world =loc.substring(0,x_dex);
            int x = Integer.parseInt(loc.substring(x_dex+1,y_dex));
            int y = Integer.parseInt(loc.substring(y_dex+1,z_dex));
            int z = Integer.parseInt(loc.substring(z_dex+1));

            return x;


    }
    public int y(String loc) {
        int x_dex = loc.indexOf("x");
        int y_dex = loc.indexOf("y");
        int z_dex = loc.indexOf("z");

        String world =loc.substring(0,x_dex);
        int x = Integer.parseInt(loc.substring(x_dex+1,y_dex));
        int y = Integer.parseInt(loc.substring(y_dex+1,z_dex));
        int z = Integer.parseInt(loc.substring(z_dex+1));

        return y;


    }    public int z(String loc) {
        int x_dex = loc.indexOf("x");
        int y_dex = loc.indexOf("y");
        int z_dex = loc.indexOf("z");

        String world =loc.substring(0,x_dex);
        int x = Integer.parseInt(loc.substring(x_dex+1,y_dex));
        int y = Integer.parseInt(loc.substring(y_dex+1,z_dex));
        int z = Integer.parseInt(loc.substring(z_dex+1));

        return z;


    }    public String world(String loc) {
        int x_dex = loc.indexOf("x");
        int y_dex = loc.indexOf("y");
        int z_dex = loc.indexOf("z");

        String world =loc.substring(0,x_dex);
        int x = Integer.parseInt(loc.substring(x_dex+1,y_dex));
        int y = Integer.parseInt(loc.substring(y_dex+1,z_dex));
        int z = Integer.parseInt(loc.substring(z_dex+1));

        return world;


    }



}



