package com.toyblock.toyblockserver.difficulty.inventory.dropchance;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class dropchance {
    public int itemChance(ItemStack item) {
        String lore =item.getItemMeta().getLore().get(5);
        lore = lore.replaceAll("[^0-9]","");
        int chance = Integer.parseInt(lore);
        return chance;
    }
    public boolean doordie(double probabilityTrue) {
        return Math.random()*100 >= 100 - probabilityTrue;
    }
    public void drop(Inventory inv) {

    }
   @EventHandler
    public void death(PlayerDeathEvent event) {
        Inventory inv =event.getEntity().getInventory();
       List<Integer> arr = new ArrayList<>();
       for(int i = 0;i<=35;i++) {
           arr.add(i);
       }
       int nomber =0;
       int hard = 10;
       int nomal = 20;
       int easy = 30;
       Collections.shuffle(Arrays.asList(arr));
       for (int i = 0;i<=35;i++) {
           int pass = arr.get(i);
           int chance = itemChance(inv.getItem(pass));
           if(nomber == 10) {
               return;
           }
           if (doordie(chance)) {
               nomber++;
               continue;
           }
           event.getItemsToKeep().add(inv.getItem(pass));
       }
    }
}