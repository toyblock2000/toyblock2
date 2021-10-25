package hashmap;

import com.toyblock.toyblockserver.structure.castle.ExtendedHouse;
import com.toyblock.toyblockserver.structure.protect.LocationSave;
import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import org.bukkit.Location;

public class MapSaveTool {
    public void protectMapSave() {
        LocationSave save = new LocationSave();
        for(Location location : structureHashMap.protect.keySet()){
            String str_loc = save.locSave(location);

        }
    }
}
