package com.toyblock.toyblockserver.system;

import com.toyblock.toyblockserver.mapList;
import org.bukkit.entity.Player;

public class village {
    // 해시맵 TAX, BELONG
    public void levyTax() {

        for(Player player : mapList.TAX.keySet()) {
            int levyValue = mapList.TAX.get(player) ;
        }
    }
}
