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
        if(SWNE =='E'){this.rotate=360;}
        else{this.rotate=0;}
    }
    public void Build() {
        WorldEditAPIController buildTool = new WorldEditAPIController("../toyblock/castle/schematics", "world");
        buildTool.load(castleName);
        buildTool.paste(castleLoc,rotate);
        protect protect = new protect("castle",castleLoc);
        protect.protect();
    }
}
