package com.toyblock.toyblockserver.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class questEvent implements Listener {
    @EventHandler
    public void zombie_5(EntityDeathEvent event) {
        {
            if (event.getEntityType().equals(EntityType.ZOMBIE)) {
                if (event.getEntity().getKiller().getType().equals(EntityType.PLAYER)) {
                    Player player = event.getEntity().getKiller();
                    if (questMap.QUEST_MOB_5.containsKey(player.getUniqueId())) {
                        questMap.QUEST_MOB_5.put(player.getUniqueId(), questMap.QUEST_MOB_5.get(player.getUniqueId()) + 1);
                        player.chat("퀘스트 1회 달성");
                        getQuest quest = new getQuest();
                        quest.create_quest_board(player);
                    }
                }
            }
        }

    }

    public void scoreUps(Player player, HashMap<Player, Integer> map) {
        if (!map.containsKey(player)) {
            return;
        }
        map.put(player, map.get(player) + 1);
    }

    public void score_up(String key) {
        if (!questMap.QUEST.containsKey(key)) {
            return;
        }
        String data = questMap.QUEST.get(key);
        String[] value = quest_str(data);
        questMap.QUEST.put(key, key_up(value));
    }

    @EventHandler
    public void mob_kill(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Monster)) {
            return;
        }
        Player player = ((Monster) entity).getKiller();
    }

    public void create_quest_board(Player player) {
        HashMap<Integer, String> map = quest_list(player);
        HashMap<UUID, Integer> map1 = get_quest_map(map.get(1));
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Quest", "dummy", "퀘스트");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (map1.get(player.getUniqueId()) >= questMap.QUEST_MOB_5_MAX) {
            Score score1 = obj.getScore
                    ("퀘스트 : " + map.get(1) + "(클리어)");
            score1.setScore(1);
            player.setScoreboard(board);
        } else {
            Score score1 = obj.getScore
                    ("퀘스트 : " + map.get(1) + "(" + map1.get(player.getUniqueId()) + "/" + questMap.QUEST_MOB_5_MAX + ")");
            score1.setScore(1);
            player.setScoreboard(board);
        }
    }

    public HashMap<Integer, String> quest_list(Player player) {
        HashMap<Integer, String> list = new HashMap<Integer, String>();
        for (int i = 0; i < 4; i++) {
            if (player_quest(player, questMap.MOB_5)) {
                list.put(i, "몹사냥 5마리");
                continue;
            }
        }
        return list;
    }

    public HashMap<UUID, Integer> get_quest_map(String str) {
        if (str == "몹사냥 5마리") {
            return questMap.QUEST_MOB_5;
        }
        return null;
    }

    public boolean player_quest(Player player, HashMap<Player, Integer> map) {
        if (map.containsKey(player)) {
            return true;
        }
        return false;
    }

    public String[] quest_str(String str) {
        String[] result = str.split("-");
        return result;
    }

    public String key_up(String[] str) {
        try {
            Integer number = Integer.valueOf(str[1]);
            number++;
            return str[0] + "-" + number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return str[0]+"-"+str[1];
    }

}
