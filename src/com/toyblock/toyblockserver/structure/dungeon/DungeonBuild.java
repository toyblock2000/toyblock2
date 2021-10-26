package com.toyblock.toyblockserver.structure.dungeon;

import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.consol;
import locate.WorldEditAPIController;
import org.bukkit.Location;

public class DungeonBuild {
    String dungeonName;
    Location dungeonLoc;
    int rotate;
    public DungeonBuild(String dungeonName, Location dungeonLoc) {
        this.dungeonName = dungeonName;
        this.dungeonLoc= dungeonLoc;
    }
    public void setRotate(String SWNE) {
        if(SWNE =="S"){this.rotate=90;}
        if(SWNE =="W"){this.rotate=180;}
        if(SWNE =="N"){this.rotate=270;}
        if(SWNE =="E"){this.rotate=360;}
        else{this.rotate=0;}
    }
    public void Build() {
        WorldEditAPIController buildTool = new WorldEditAPIController("../toyblock/dungeon/schematics", "world");
        buildTool.load(dungeonName+".schem");
        buildTool.paste(dungeonLoc,rotate);
        protect protect = new protect("Dungeon",dungeonLoc);
        protect.protect();
        structureHashMap.Chunk.put(dungeonLoc.getChunk(),"dungeon");
        consol consol = new consol();
        consol.send("던전좌표"+dungeonLoc);
    }

}
