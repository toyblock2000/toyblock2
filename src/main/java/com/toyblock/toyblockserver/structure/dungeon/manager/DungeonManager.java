package com.toyblock.toyblockserver.structure.dungeon.manager;

import com.toyblock.toyblockserver.tool.WorldEditAPIController;
import org.bukkit.Location;
import org.bukkit.World;

public class DungeonManager {
    Location managerLoc;
    World world;
    public DungeonManager(Location managerLoc) {
        this.managerLoc = managerLoc;
        this.world = managerLoc.getWorld();
    }
    public void Save (String saveName) {
        Location managerLoc = this.managerLoc;
        Location centerLoc = new Location(managerLoc.getWorld(), managerLoc.getX()-2, managerLoc.getY()-9, managerLoc.getZ()-2);
        Location saveLoc1 = new Location(centerLoc.getWorld(), centerLoc.getX()-2, centerLoc.getY()-9, centerLoc.getZ()-2);
        Location saveLoc2 = new Location(centerLoc.getWorld(), centerLoc.getX()-2, centerLoc.getY()-9, centerLoc.getZ()-2);

        WorldEditAPIController saveTool = new WorldEditAPIController("../toyblock/dungeon/schematics", this.world.getName());
        saveTool.copy(saveLoc1,saveLoc2,centerLoc);
        saveTool.save(saveName);
    }
    public void Load (String loadName) {
        Location managerLoc = this.managerLoc;
        Location centerLoc = new Location(managerLoc.getWorld(), managerLoc.getX()-2, managerLoc.getY()-9, managerLoc.getZ()-2);
        Location saveLoc1 = new Location(centerLoc.getWorld(), centerLoc.getX()-2, centerLoc.getY()-9, centerLoc.getZ()-2);
        Location saveLoc2 = new Location(centerLoc.getWorld(), centerLoc.getX()-2, centerLoc.getY()-9, centerLoc.getZ()-2);

        WorldEditAPIController loadTool = new WorldEditAPIController("../toyblock/dungeon/schematics", this.world.getName());
        loadTool.load(loadName);
        loadTool.paste(centerLoc,0);
    }
}
