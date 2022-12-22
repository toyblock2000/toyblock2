package com.toyblock.toyblockserver.quest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class questInventory {

    public void createInv (UUID UUID , Location loc) {
        quest q = new quest();
        Inventory inv = Bukkit.createInventory(null, 27);
        inv.setItem(0,q.wall);
        inv.setItem(9,q.wall);
        inv.setItem(18,q.wall);

        inv.setItem(1,q.copper);
        inv.setItem(10,q.iron);
        inv.setItem(19,q.gold);

        inv.setItem(2,q.wall);
        inv.setItem(11,q.wall);
        inv.setItem(20,q.wall);

        inv.setItem(6,q.wall);
        inv.setItem(15,q.wall);
        inv.setItem(24,q.wall);

        inv.setItem(7,q.copper_reward);
        inv.setItem(16,q.iron_reward);
        inv.setItem(25,q.gold_reward);

        inv.setItem(8,q.wall);
        inv.setItem(17,q.wall);
        inv.setItem(26,q.wall);
    }
}
