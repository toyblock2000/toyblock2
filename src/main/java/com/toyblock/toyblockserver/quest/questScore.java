package com.toyblock.toyblockserver.quest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class questScore {
    public String key_down(String[] str) {
        try {
            Integer number = Integer.valueOf(str[1]);
            if(number == 0) {
                return str[0] + "-" + str[1] + "-" +str[2] + "-" + str[3];
            }
            number--;
            return str[0] + "-" + number + "-" +str[2] + "-" + str[3];
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return str[0] + "-" + str[1] + "-" +str[2] + "-" + str[3];
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
    public void score_down(String key) {
        if (!questMap.QUEST.containsKey(key)) {
            return;
        }
        String value = questMap.QUEST.get(key);
        String[] data = quest_str(value);
        questMap.QUEST.put(key, key_down(data));
    }
    public void quest_triger(Player player , String triger) {
        for(int i=0;i<=3;i++) {
        if(!questMap.QUEST.containsKey(getKey(player,i))) {
            continue;
        }
        if(questMap.QUEST.get(getKey(player,i)).contains(triger)) {
            player.chat(questMap.QUEST.get(getKey(player,i)));
            score_down(getKey(player,i));
        }
        }
    }
    public String getKey(Player player , int a) {
        return player.getName()+"-"+a;
    }
    public String getTear(String tear) {
        String color = ""+ChatColor.WHITE;
        if(tear.contains("C")) {
            color = ""+ChatColor.GOLD;
        }
        if(tear.contains("B")) {
            color = ""+ChatColor.WHITE;
        }
        if(tear.contains("A")) {
            color = ""+ChatColor.YELLOW;
        }
        return color+"■ ";
    }
    public void create_quest_board(Player player) {
        //questMap.QUEST.put("toy_block-1","좀비킬-2-C-mobkill");
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("Quest", "dummy", "퀘스트");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score;
        for(int a=0;a<=3;a++) {
            if (questMap.QUEST.containsKey(getKey(player,a))) {
                String[] quest = quest_str(questMap.QUEST.get(getKey(player,a)));
                String quest_name = ""+getTear(quest[2])+ChatColor.WHITE+quest[0];
                String quest_value = ChatColor.YELLOW + quest[1]+ ChatColor.WHITE;
                if(value(quest[1])==0) {
                    quest_name = quest_name+ChatColor.YELLOW+ChatColor.BOLD+"✔";
                }
                //score = obj.getScore(""+quest_name+" ( "+ quest_value+"/"+quest_max+" )");
                score = obj.getScore(quest_name);
                score.setScore(value(quest[1]));
            }
        }

        player.setScoreboard(board);
    }
}
