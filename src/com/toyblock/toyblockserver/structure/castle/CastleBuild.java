package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.protect.protect;
import locate.WorldEditAPIController;
import org.bukkit.Location;

public class CastleBuild {
    String castleName;
    Location castleLoc;
    int rotate;
    public CastleBuild(String castleName, Location castleLoc) {
        this.castleName = castleName;
        this.castleLoc= castleLoc;
    }
    public void setRotate(char SWNE) {
        if(SWNE =='S'){this.rotate=90;}
        if(SWNE =='W'){this.rotate=180;}
        if(SWNE =='N'){this.rotate=270;}
        if(SWNE =='E'){this.rotate=0;}
        else{this.rotate=0;}
    }
    public void Build() {
        //생성
        WorldEditAPIController buildTool = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/castle", "world");
        buildTool.load(castleName+".schem");
        buildTool.paste(castleLoc,rotate);
        //보호
        protect protect = new protect("castle",castleLoc);
        protect.protect();
    }

}
