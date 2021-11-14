package com.toyblock.toyblockserver.structure.smooth;

import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.View;
import com.toyblock.toyblockserver.structure.tool.consol;
import locate.Main;
import locate.WorldEditAPIController;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

import javax.swing.*;

public class SideSmooth {
    public void setSideSmooth(Location loc,String view) {

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
    public void setSideSmoothS(Location loc,String view,int side) {
        WorldEditAPIController edit = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle/path/smooth", "world");
        Location set1 = new Location(loc.getWorld(),loc.getBlockX()+2,loc.getBlockY()+1,loc.getBlockZ()+2);
        Location set2 = new Location(loc.getWorld(),loc.getBlockX()-2,loc.getBlockY()+5,loc.getBlockZ()-2);
        edit.copy(set1,set2,set2);
        int air = edit.airCheck();
        int swne = View.view(view);
        WorldEditAPIController edittree = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle/path/smooth", "world");
        Location set1t = new Location(loc.getWorld(),loc.getBlockX()+2,loc.getBlockY()-4,loc.getBlockZ()+2);
        Location set2t = new Location(loc.getWorld(),loc.getBlockX()-2,loc.getBlockY()+10,loc.getBlockZ()-2);
        edittree.copy(set1t,set2t,set2t);
        if(edittree.treeCheck()>0) {
            return;
        }
        Bukkit.getPlayer("Devil").sendMessage("성공사이드"+swne);
        //5x5x5 블럭 125
        if(air<25) {
            if(side==1) {
                edit.load("upside.schem");
                edit.paste(loc,swne);
                Bukkit.getPlayer("Devil").sendMessage("성공사이드"+swne);

                return;
            }
            edit.load("up.schem");
            edit.paste(loc,swne);
            Bukkit.getPlayer("Devil").sendMessage("성공"+swne);
        }
        else if(air<80) {
            if(side==1) {
                edit.load("up_small_side.schem");
                edit.paste(loc,swne);
                Bukkit.getPlayer("Devil").sendMessage("성공사이드"+swne);
                Bukkit.getPlayer("Devil").sendMessage("성공사이드트리수도 확인"+edit.treeCheck());
                return;
            }
            edit.load("up_small.schem");
            edit.paste(loc,swne);
            Bukkit.getPlayer("Devil").sendMessage("성공"+swne);
            Bukkit.getPlayer("Devil").sendMessage("성공사이드트리수도 확인"+edit.treeCheck());
        }

    }
}
