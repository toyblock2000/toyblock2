package com.toyblock.toyblockserver.difficulty.inventory.dropchance;

import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import com.toyblock.toyblockserver.tool.developer.bug;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DropChance implements Listener {
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
    // @EventHandler
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
    @EventHandler
    public void deathDropChance(PlayerDeathEvent event) {
        ToolEdit toolEdit = new ToolEdit();
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        int invSize = event.getDrops().size();
        int saveCount = 0;
        for (Iterator<ItemStack> iterator = event.getDrops().iterator(); iterator.hasNext(); ) {
            ItemStack drop = iterator.next();
            List<String> lore = drop.getLore();
            if (!drop.getItemMeta().hasLore()) {
                continue;
            }
            float soulBound = toolEdit.loreFinder(drop,"소울바운드");
            if(soulBound == 0 ) {
                continue;
            }
            int random = (int)(Math.random()*100);
            if (soulBound < random ) {
                continue;
            }
            bug.chat(soulBound+"<"+random);
            iterator.remove();
            event.getItemsToKeep().add(drop);
            saveCount = saveCount+1;
        }
        if(saveCount == 0 ) {
            player.sendMessage(ChatColor.RED+"아이템을 모두 잃었습니다");
            return;
        }
        player.sendMessage(ChatColor.WHITE+""+invSize+ChatColor.RED+"개의 아이템중"+ChatColor.GREEN+saveCount+ChatColor.RED+"개의 아이템만은 끝까지 지켜냈습니다");
    }
}