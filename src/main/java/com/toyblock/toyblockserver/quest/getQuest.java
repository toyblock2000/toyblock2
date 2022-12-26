package com.toyblock.toyblockserver.quest;

import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.structure.village.castle.investment.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class getQuest implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains("quest")) {
            return;
        }
        if (event.getCurrentItem() == null) {
            event.setCancelled(true);
            return;
        }
        if( event.getCurrentItem().getItemMeta() == null) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getType()== InventoryType.PLAYER) {
            event.setCancelled(true);
            return;
        }
        questItem item = new questItem();

        if(event.getCurrentItem().getItemMeta().getDisplayName().contains("좀비 5마리 잡기!")) {
            if(questMap.QUEST_MOB_5.containsKey(player.getUniqueId())) {
                player.chat("이미있는 퀘스트입니다!");
                return;
            }
            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
            questMap.QUEST_MOB_5.put(player.getUniqueId(),0);
            create_quest_board(player);
            event.setCancelled(true);
        }
        else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("구리퀘스트 보상")) {
            if(questMap.QUEST_MOB_5.get(player.getUniqueId())>=questMap.QUEST_MOB_5_MAX) {
                BlockStateMeta bsm = (BlockStateMeta) event.getCurrentItem().getItemMeta();
                ShulkerBox box = (ShulkerBox) bsm.getBlockState();
                Inventory inv = box.getInventory();
                player.getInventory().addItem(inv.getItem(0));
                questMap.QUEST_MOB_5.remove(player.getUniqueId());
                player.getWorld().playSound(player.getLocation(),Sound.BLOCK_SHULKER_BOX_OPEN,1,1);
                event.setCancelled(true);
            }
            else{
                event.setCancelled(true);
            }
        }
        else {
            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_EXPERIENCE_BOTTLE_THROW,1,1);
            event.setCancelled(true);
        }
    }
    public void create_quest_board(Player player) {
        HashMap<Integer,String> map = getList(player);
        HashMap<UUID,Integer> map1 = get_quest_map(map.get(1));
        ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective("Quest","dummy","퀘스트");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            if(map1.get(player.getUniqueId())>=questMap.QUEST_MOB_5_MAX) {
                Score score1 = obj.getScore
                        ("퀘스트 : "+map.get(1) +"(클리어)");
                score1.setScore(1);
                player.setScoreboard(board);
        }
            else {
                Score score1 = obj.getScore
                        ("퀘스트 : " + map.get(1) + "(" + map1.get(player.getUniqueId()) + "/" + questMap.QUEST_MOB_5_MAX + ")");
                score1.setScore(1);
                player.setScoreboard(board);
            }
    }
    public HashMap<UUID,Integer> get_quest_map(String str) {
        if(str == "몹사냥 5마리") {
            return questMap.QUEST_MOB_5;
        }
        return null;
    }
    public HashMap<Integer,String> getList(Player player) {
        HashMap<Integer, String > list = new HashMap<Integer,String>();
        for (int i = 0; i<4; i++) {
            if(questMap.QUEST_MOB_5.containsKey(player.getUniqueId())) {
                list.put(i,"몹사냥 5마리");
                continue;
            }
        }
        return list;
    }
}
