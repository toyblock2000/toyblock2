package com.toyblock.toyblockserver.structure.village.house;

import com.toyblock.toyblockserver.structure.StructrueMap;
import com.toyblock.toyblockserver.tool.consol;
import org.bukkit.Location;

public class ExtendedHouse {

    public boolean heightCheck(Location loc) {


        for (int i = 0; i < 11; i++) {
            Location downloc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 5 + i, loc.getBlockZ());
            if (StructrueMap.protect.containsKey(downloc) || StructrueMap.Link.containsKey(downloc)) {
                return false;
            }

        }
        return true;

    }

    public boolean houseCheck2x2(Location loc, String view) {
        consol.send("집체크");
        if (view == "S") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            if (heightCheck(loc) && heightCheck(loc10) && heightCheck(loc01) && heightCheck(loc11)) {
                consol.send("집체크성공");
                return true;
            }
            else {
                return false;
            }
        }
        if (view == "W") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() + 5);
            if (heightCheck(loc) && heightCheck(loc10) && heightCheck(loc01) && heightCheck(loc11)) {
                consol.send("집체크성공");
                return true;
            }
            else {
                return false;
            }
        }
        if (view == "N") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() - 5);
            if (heightCheck(loc) && heightCheck(loc10) && heightCheck(loc01) && heightCheck(loc11)) {
                consol.send("집체크성공");
                return true;
            }
            else {
                return false;
            }
        }
        if (view == "E") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() - 5);
            if (heightCheck(loc) && heightCheck(loc10) && heightCheck(loc01) && heightCheck(loc11)) {
                consol.send("집체크성공");
                return true;
            }
            else {
                return false;
            }
        }
        consol.send("집체크실패");
        return false;

    }
    public void houseMapPut(Location loc, String view) {
        if (view == "S") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            StructrueMap.Link.put(loc,"House");
            StructrueMap.Link.put(loc10,"House");
            StructrueMap.Link.put(loc01,"House");
            StructrueMap.Link.put(loc11,"House");

        }
        if (view == "W") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() + 5);
            StructrueMap.protect.put(loc,"House");
            StructrueMap.protect.put(loc10,"House");
            StructrueMap.protect.put(loc01,"House");
            StructrueMap.protect.put(loc11,"House");
        }
        if (view == "N") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ());
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() - 5, loc.getBlockY(), loc.getBlockZ() - 5);
                StructrueMap.protect.put(loc,"House");
                StructrueMap.protect.put(loc10,"House");
                StructrueMap.protect.put(loc01,"House");
                StructrueMap.protect.put(loc11,"House");
        }
        if (view == "E") {
            Location loc10 = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 5);
            Location loc01 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            Location loc11 = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ() - 5);
            StructrueMap.protect.put(loc, "House");
            StructrueMap.protect.put(loc10, "House");
            StructrueMap.protect.put(loc01, "House");
            StructrueMap.protect.put(loc11, "House");

        }
    }
    public int viewInt(String view) {
        if(view == "S") {
            return 0;
        }
        if(view == "W") {
            return 270;
        }
        if(view == "N") {
            return 180;
        }
        if(view == "E") {
            return 90;
        }

        return 0;
    }
}

