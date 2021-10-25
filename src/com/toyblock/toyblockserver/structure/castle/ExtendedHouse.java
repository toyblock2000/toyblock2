package com.toyblock.toyblockserver.structure.castle;

import com.toyblock.toyblockserver.structure.protect.structureHashMap;
import org.bukkit.Location;

public class ExtendedHouse {

    public boolean heightCheck(Location loc) {


        for (int i = 0; i == 11; i++) {
            Location downloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 5 + i, loc.getBlockZ());
            if (structureHashMap.protect.containsKey(downloc) || structureHashMap.Link.containsKey(downloc)) {
                return false;
            }

        }
        return true;

    }

    public boolean houseCheck2x2(Location loc, String view) {
        if (view == "S") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            if (!heightCheck(loc) || heightCheck(loc10) || heightCheck(loc01) || heightCheck(loc11)) {
                return false;
            }
            return true;
        }
        if (view == "W") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() + 5);
            if (!heightCheck(loc) || heightCheck(loc10) || heightCheck(loc01) || heightCheck(loc11)) {
                return false;
            }
            return true;
        }
        if (view == "N") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() - 5);
            if (!heightCheck(loc) || heightCheck(loc10) || heightCheck(loc01) || heightCheck(loc11)) {
                return false;
            }
            return true;
        }
        if (view == "E") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() - 5);
            if (!heightCheck(loc) || heightCheck(loc10) || heightCheck(loc01) || heightCheck(loc11)) {
                return false;
            }
            return true;
        }
        return false;

    }
    public void houseMapPut(Location loc, String view) {
        if (view == "S") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            structureHashMap.protect.put(loc,"House");
            structureHashMap.protect.put(loc10,"House");
            structureHashMap.protect.put(loc01,"House");
            structureHashMap.protect.put(loc11,"House");

        }
        if (view == "W") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() + 5);
            structureHashMap.protect.put(loc,"House");
            structureHashMap.protect.put(loc10,"House");
            structureHashMap.protect.put(loc01,"House");
            structureHashMap.protect.put(loc11,"House");
        }
        if (view == "N") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() - 5);
                structureHashMap.protect.put(loc,"House");
                structureHashMap.protect.put(loc10,"House");
                structureHashMap.protect.put(loc01,"House");
                structureHashMap.protect.put(loc11,"House");
        }
        if (view == "E") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() - 5);
            structureHashMap.protect.put(loc, "House");
            structureHashMap.protect.put(loc10, "House");
            structureHashMap.protect.put(loc01, "House");
            structureHashMap.protect.put(loc11, "House");

        }
    }
    public int viewInt(String view) {
        if(view == "S") {
            return 0;
        }
        if(view == "W") {
            return 90;
        }
        if(view == "N") {
            return 180;
        }
        if(view == "E") {
            return 360;
        }

        return 0;
    }
}

