package com.toyblock.toyblockserver.structure.smooth;

import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.consol;
import locate.Main;
import locate.WorldEditAPIController;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class SideSmooth {
    public void setSideSmooth(Location loc) {

        Location setloc = loc;
        int up = 0;
        for(int i = 0;i<=10;i++){
            Location locs = new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY()+i,loc.getBlockZ());
            if(locs.getBlock().getType().equals(Material.AIR)) {
                setloc = new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY()+i-1,loc.getBlockZ());
                up = i-1;
                break;
            }
            setloc = new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY()+i,loc.getBlockZ());
            up = i;
        }
        WorldEditAPIController pathedit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle/path/smooth", "world");
        setloc.getBlock().setType(Material.GOLD_BLOCK);
        if(up<2) {
            setloc.getBlock().setType(Material.GOLD_BLOCK);
            Bukkit.getPlayer("Devil").teleport(setloc);
            return;
        }
        if(up>1&&up<4) {
            pathedit.load("up1.schem");
            pathedit.paste(loc, 0);
            setloc.getBlock().setType(Material.EMERALD_BLOCK);
            Bukkit.getPlayer("Devil").teleport(setloc);
        }
        if(up>3&&up<7) {
            pathedit.load("up2.schem");
            pathedit.paste(loc, 0);
            setloc.getBlock().setType(Material.DIAMOND_BLOCK);
            Bukkit.getPlayer("Devil").teleport(setloc);
        }
        if(up>6) {
            pathedit.load("up3.schem");
            pathedit.paste(loc, 0);
            setloc.getBlock().setType(Material.IRON_BLOCK);
            Bukkit.getPlayer("Devil").teleport(setloc);
        }
    }
}
