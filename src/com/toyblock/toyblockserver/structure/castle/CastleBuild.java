package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.dungeon.AutoDungeonBuild;
import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.consol;
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
        player.chat("던전 만들기");
        structureHashMap.Chunk.put(castleLoc.getChunk(),"castle");
        player.chat("던전 만들기 진행 1");
        AutoDungeonBuild dungeon = new AutoDungeonBuild();
        dungeon.AutoBuild(castleLoc.getChunk());
        player.chat("던전 생성완료했다");

    }

}
