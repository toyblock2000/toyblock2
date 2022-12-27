package com.toyblock.toyblockserver.quest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class questScore {
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
    public int value(String str) {
        try {
            Integer number = Integer.valueOf(str);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public String[] quest_str(String str) {
        String[] result = str.split("-");
        return result;
    }
    public void score_up(String key) {
        if (!questMap.QUEST.containsKey(key)) {
            return;
        }
        String value = questMap.QUEST.get(key);
        String[] data = quest_str(value);
        questMap.QUEST.put(key, key_up(data));
    }
    public String getKey(Player player , int a) {
        return player.getName()+"-"+a;
    }
    public void create_quest_board(Player player) {
        questMap.QUEST.put("toy_block-1","좀비킬-2");
        questMap.QUEST.put("toy_block-2","좀비킬-5");
        questMap.QUEST.put("toy_block-3","나무캐기-2");
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Quest", "dummy", "퀘스트");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score;
        for(int a=0;a<=3;a++) {
            if (questMap.QUEST.containsKey(getKey(player,a))) {
                String[] quest = quest_str(questMap.QUEST.get(getKey(player,a)));
                String quest_name = ChatColor.GREEN + quest[0] + ChatColor.WHITE;
                String quest_value = ChatColor.YELLOW + quest[1]+ ChatColor.WHITE;
                if(value(quest[1])==0) {
                    quest_name = ChatColor.YELLOW + quest[0] + ChatColor.WHITE;
                }
                //score = obj.getScore(""+quest_name+" ( "+ quest_value+"/"+quest_max+" )");
                score = obj.getScore(quest_name);
                score.setScore(value(quest[1]));
            }
        }

        player.setScoreboard(board);
    }
}
