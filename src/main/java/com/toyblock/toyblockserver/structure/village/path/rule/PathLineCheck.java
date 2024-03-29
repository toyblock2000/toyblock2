package com.toyblock.toyblockserver.structure.village.path.rule;

import com.toyblock.toyblockserver.structure.StructureMap;
import com.toyblock.toyblockserver.tool.consol;
import org.bukkit.Location;

public class PathLineCheck {
    public boolean pathLineCheck(Location loc) {
        //길 3x3 안됨
        Location sloc = new Location(loc.getWorld(), loc.getBlockX()+5, loc.getBlockY(), loc.getBlockZ());
        Location swloc = new Location(loc.getWorld(), loc.getBlockX()+5, loc.getBlockY(), loc.getBlockZ()+5);
        Location wloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+5);
        Location nwloc = new Location(loc.getWorld(), loc.getBlockX()-5, loc.getBlockY(), loc.getBlockZ());
        Location nloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-5);
        Location neloc = new Location(loc.getWorld(), loc.getBlockX()-5, loc.getBlockY(), loc.getBlockZ()-5);
        Location eloc = new Location(loc.getWorld(), loc.getBlockX()+5, loc.getBlockY(), loc.getBlockZ()-5);
        Location seloc = new Location(loc.getWorld(), loc.getBlockX()-5, loc.getBlockY(), loc.getBlockZ()+5);
        if(!canHeightCheck(sloc)||canHeightCheck(swloc)||canHeightCheck(wloc)||canHeightCheck(nwloc)||canHeightCheck(nloc)||canHeightCheck(neloc)||canHeightCheck(eloc)||canHeightCheck(seloc)) {
            return false;
        }
        return true;
    }

    public boolean canHeightCheck(Location loc) {
            consol.send("하이첵뜨루");
            for (int i = 0; i < 11; i++) {
                Location downloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 5 + i, loc.getBlockZ());
                consol.send("로케이션");
                if (StructureMap.Link.containsKey(downloc)) {
                    consol.send("리턴");
                    return false;
                }

            }

            return true;

        }






}
