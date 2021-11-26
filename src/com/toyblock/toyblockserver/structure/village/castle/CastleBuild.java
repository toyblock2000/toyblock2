package com.toyblock.toyblockserver.structure.village.castle;

import com.toyblock.toyblockserver.structure.dungeon.AutoDungeonBuild;
import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.structure.structureType;
import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import org.bukkit.Location;

public class CastleBuild {
    String castleName;
    Location castleLoc;
    int rotate;

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
        WorldEditAPIController buildTool = new WorldEditAPIController(structureType.VILLAGE_CASTLE.name(), "world");
        buildTool.load(castleName+".schem");
        buildTool.paste(castleLoc,rotate);
        //보호
        protect protect = new protect(castleLoc);
        protect.VILLAGE_CASTLE();
        StructureMap.Chunk.put(castleLoc.getChunk(),"Castle");
        //던전
        AutoDungeonBuild dungeon = new AutoDungeonBuild();
        dungeon.AutoBuild(castleLoc.getChunk());
    }

}
