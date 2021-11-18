package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.dungeon.AutoDungeonBuild;
import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.protect.StructrueMap;
import com.toyblock.toyblockserver.structure.smooth.SideSmooth;
import locate.WorldEditAPIController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CastleBuild {
    String castleName;
    Location castleLoc;
    int rotate;
    Player player = Bukkit.getPlayer("Devil");
    public CastleBuild(String castleName, Location castleLoc) {
        this.castleName = castleName;
        this.castleLoc= castleLoc;
    }
    public void setRotate(String SWNE) {
        if(SWNE =="S"){this.rotate=90;}
        if(SWNE =="W"){this.rotate=180;}
        if(SWNE =="N"){this.rotate=270;}
        if(SWNE =="E"){this.rotate=0;}
        else{this.rotate=0;}
    }
    public void Build() {
        //생성
        WorldEditAPIController buildTool = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle", "world");
        buildTool.load(castleName+".schem");
        buildTool.paste(castleLoc,rotate);
        //보호
        protect protect = new protect("Castle",castleLoc);
        protect.protect();
        castleSmooth();
        StructrueMap.Chunk.put(castleLoc.getChunk(),"castle");
        AutoDungeonBuild dungeon = new AutoDungeonBuild();
        dungeon.AutoBuild(castleLoc.getChunk());

    }
    public void castleSmooth() {
        Location locS = castleLoc;
        for (int i = 0;i<11;i++) {
            locS = new Location(locS.getWorld(),locS.getX(),locS.getBlockY(),locS.getBlockZ()+5);
        }
        for (int i = 0;i<10;i++) {
            locS = new Location(locS.getWorld(),locS.getX()+5,locS.getBlockY(),locS.getBlockZ());
        }
        Location uplocS= new Location(locS.getWorld(),locS.getX()+5,locS.getBlockY()+5,locS.getBlockZ()+5);
        for(int i = 0;i<=21;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <21) {
                smooth.setSideSmoothS(locS, "S", 0);
                locS = new Location(locS.getWorld(),locS.getX()-5,locS.getBlockY(),locS.getBlockZ());

            }
            else {
                smooth.setSideSmoothS(locS, "S", 1);
            }
        }
        for(int i = 0;i<=23;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <23) {
                smooth.setSideSmoothS(uplocS, "S", 0);
                uplocS = new Location(uplocS.getWorld(),uplocS.getX()-5,uplocS.getBlockY(),uplocS.getBlockZ());

            }
            else {
                smooth.setSideSmoothS(uplocS, "S", 1);
            }
        }

        Location locW = castleLoc;
        for (int i = 0;i<11;i++) {
            locW = new Location(locW.getWorld(),locW.getX()-5,locW.getBlockY(),locW.getBlockZ());
        }
        for (int i = 0;i<10;i++) {
            locW = new Location(locW.getWorld(),locW.getX(),locW.getBlockY(),locW.getBlockZ()+5);
        }
        Location uplocW= new Location(locW.getWorld(),locW.getX()-5,locW.getBlockY()+5,locW.getBlockZ()+5);
        for(int i = 0;i<=21;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <21) {
                smooth.setSideSmoothS(locW, "W", 0);
                locW = new Location(locW.getWorld(),locW.getX(),locW.getBlockY(),locW.getBlockZ()-5);
            }
            else {
                smooth.setSideSmoothS(locW, "W", 1);
            }
        }
        for(int i = 0;i<=23;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <23) {
                smooth.setSideSmoothS(uplocW, "W", 0);
                uplocW = new Location(uplocW.getWorld(),uplocW.getX(),uplocW.getBlockY(),uplocW.getBlockZ()-5);
            }
            else {
                smooth.setSideSmoothS(uplocW, "W", 1);
            }
        }

        Location locN = castleLoc;
        for (int i = 0;i<11;i++) {
            locN = new Location(locN.getWorld(),locN.getX(),locN.getBlockY(),locN.getBlockZ()-5);
        }
        for (int i = 0;i<10;i++) {
            locN = new Location(locN.getWorld(),locN.getX()-5,locN.getBlockY(),locN.getBlockZ());
        }
        Location uplocN= new Location(locN.getWorld(),locN.getX()-5,locN.getBlockY()+5,locN.getBlockZ()-5);
        for(int i = 0;i<=21;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <21) {
                smooth.setSideSmoothS(locN, "N", 0);
                locN = new Location(locN.getWorld(),locN.getX()+5,locN.getBlockY(),locN.getBlockZ());
            }
            else {
                smooth.setSideSmoothS(locN, "N", 1);
            }
        }
        for(int i = 0;i<=23;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <23) {
                smooth.setSideSmoothS(uplocN, "N", 0);
                uplocN = new Location(uplocN.getWorld(),uplocN.getX()+5,uplocN.getBlockY(),uplocN.getBlockZ());

            }
            else {
                smooth.setSideSmoothS(uplocN, "N", 1);
            }
        }
        Location locE = castleLoc;
        for (int i = 0;i<11;i++) {
            locE = new Location(locE.getWorld(),locE.getX()+5,locE.getBlockY(),locE.getBlockZ());
        }
        for (int i = 0;i<10;i++) {
            locE = new Location(locE.getWorld(),locE.getX(),locE.getBlockY(),locE.getBlockZ()-5);
        }
        Location uplocE= new Location(locE.getWorld(),locE.getX()+5,locE.getBlockY()+5,locE.getBlockZ()-5);

        for(int i = 0;i<=21;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <21) {
                smooth.setSideSmoothS(locE, "E", 0);
                locE = new Location(locE.getWorld(),locE.getX(),locE.getBlockY(),locE.getBlockZ()+5);
            }
            else {
                smooth.setSideSmoothS(locE, "E", 1);
            }
        }
        for(int i = 0;i<=23;i++) {
            SideSmooth smooth = new SideSmooth();
            if(i <23) {
                smooth.setSideSmoothS(uplocE, "E", 0);
                uplocE = new Location(uplocE.getWorld(),uplocE.getX(),uplocE.getBlockY(),uplocE.getBlockZ()+5);

            }
            else {
                smooth.setSideSmoothS(uplocE, "E", 1);
            }
        }

    }

}
