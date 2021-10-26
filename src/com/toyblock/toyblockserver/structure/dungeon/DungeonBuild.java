package com.toyblock.toyblockserver.structure.dungeon;

import com.toyblock.toyblockserver.structure.protect.protect;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import com.toyblock.toyblockserver.structure.tool.consol;
import locate.WorldEditAPIController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DungeonBuild {
    String dungeonName;
    Location dungeonLoc;
    int rotate;

    Player player = Bukkit.getPlayer("Devil");
    public DungeonBuild(String dungeonName, Location dungeonLoc) {
        this.dungeonName = dungeonName;
        this.dungeonLoc= dungeonLoc;
    }
    public void setRotate(String SWNE) {
        if(SWNE =="S"){this.rotate=0;}
        if(SWNE =="W"){this.rotate=270;}
        if(SWNE =="N"){this.rotate=180;}
        if(SWNE =="E"){this.rotate=90;}
        else{this.rotate=0;}
    }
    public void Build() {
        player.chat("테스트에 진입 1");
        WorldEditAPIController buildTool = new WorldEditAPIController("C:/Users/82105/Desktop/paper 1.17.1/plugins/Astral_server/schematic/village/dungeon", "world");
        buildTool.load(dungeonName+".schem");
        buildTool.paste(dungeonLoc,rotate);
        player.chat("테스트에 진입 2");
        protect protect = new protect("Dungeon",dungeonLoc);
        protect.protect();
        structureHashMap.Chunk.put(dungeonLoc.getChunk(),"dungeon");
        consol consol = new consol();
        player.chat("테스트에 진입 3");
        consol.send("던전좌표"+dungeonLoc);
        player.chat("던전좌표"+dungeonLoc);
    }

}
