package com.toyblock.toyblockserver.difficulty.energy;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class Energy {
    private String energyName = "Energy";

    public void ceatePlayerEnergy(Player player,Float energy) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY.put(playerUUID,energy);

    }
    public float getPlayerEnergy(Player player) {
        String playerUUID = player.getUniqueId().toString();
        return mapList.ENERGY.get(playerUUID);
    }
    public boolean usePlayerEnergy(Player player,Float useEnergy) {
        String playerUUID = player.getUniqueId().toString();
        Float playerEnergy = mapList.ENERGY.get(playerUUID);
        if(!(playerEnergy >= useEnergy)) {
            return false;
        }
        mapList.ENERGY.put(playerUUID,playerEnergy-useEnergy);
        showUseEnergy(player,useEnergy);
        return true;
    }
    public boolean addPlayerEnergy(Player player,Float addEnergy) {
        String playerUUID = player.getUniqueId().toString();
        Float playerEnergy = mapList.ENERGY.get(playerUUID);
        if(100 <= playerEnergy) {
            return false;
        }
        Float scarceEnergy = (100-playerEnergy);
        if(scarceEnergy<=addEnergy) {
            mapList.ENERGY.put(playerUUID,playerEnergy+scarceEnergy);
        }
        else {
            mapList.ENERGY.put(playerUUID,playerEnergy+addEnergy);
        }
        showAddEnergy(player);
        return true;
    }
    public void setPlayerEnergy(Player player,Float energy) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY.put(playerUUID,energy);
    }
    public void showAddEnergy(Player player) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("에너지 : + 1      남은 에너지: "+comma(getPlayerEnergy(player)) ) );
    }
    public static String comma (float f) {
        DecimalFormat form = new DecimalFormat("#.#");
        String nomber = form.format(f);
        return nomber;
    }
    public void showUseEnergy (Player player,Float useEnergy) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("에너지 : -" +useEnergy+"   남은에너지: "+comma(getPlayerEnergy(player)) ) );
    }
    public void createBoard(Player player) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore("에너지 : "+comma(getPlayerEnergy(player)) );
        score1.setScore(1);
        player.setScoreboard(board);
    }
    @EventHandler
    public void energyUse(BlockBreakEvent event) {
        Player player = event.getPlayer();
        usePlayerEnergy(player,5f);
    }
    public boolean getRegen(Player player) {
        String playerUUID = player.getUniqueId().toString();
        return mapList.ENERGY_REGEN.get(playerUUID);
    }
    public void setRegen(Player player,boolean plug) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY_REGEN.put(playerUUID,plug);
    }
    public void regenEnergy (Player player,Float regenEnergy,long time) {

        if (getRegen(player) == true) {
            return;
        }
        setRegen(player,true);

            BukkitRunnable Regen = new BukkitRunnable() {
                public void run() {
                    if (getRegen(player) == false) {
                        this.cancel();
                    }
                    if(!addPlayerEnergy(player,regenEnergy)) {
                        setRegen(player,false);
                        this.cancel();
                    }
                }
            };
            Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
        }
}
