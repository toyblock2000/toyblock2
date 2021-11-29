package com.toyblock.toyblockserver.difficulty.energy;

import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class Energy implements Listener {
    private String energyName = "Energy";

    public void createPlayerEnergy(Player player,Float energy) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY.put(playerUUID,energy);
        mapList.ENERGY_REGEN.put(playerUUID,false);

    }
    @EventHandler
    public void joinPlayerEnergy(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        createPlayerEnergy(player,100f);
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
        Float percent = discountEnergy_Pickaxe(player);
        Float discount = (float) (useEnergy * percent / 100.0);
        mapList.ENERGY.put(playerUUID,playerEnergy-(useEnergy-discount) );
        showUseEnergy(player,useEnergy-discount);
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
        showAddEnergy(player,addEnergy);
        return true;
    }
    public boolean regenPlayerEnergy(Player player,Float addEnergy) {
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
        return true;
    }
    public void setPlayerEnergy(Player player,Float energy) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY.put(playerUUID,energy);
    }
    public void showAddEnergy(Player player,Float addEnergy) {
        if(!(player.isOnline())) {
            return ;
        }
        String addValue = comma(addEnergy);
        String countValue = comma(getPlayerEnergy(player));
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (ChatColor.GREEN+"에너지 +"+addValue ) );
        createBoard(player);
        return ;
    }
    public static String comma (float f) {
        DecimalFormat form = new DecimalFormat("#.#");
        String nomber = form.format(f);
        return nomber;
    }

    public void showUseEnergy (Player player,Float useEnergy) {
        if(!(player.isOnline())) {
            return ;
        }
        String useValue = comma(useEnergy);
        String countValue = comma(getPlayerEnergy(player));
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (ChatColor.RED+"에너지 -"+useValue ) );
        createBoard(player);

    }
    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("에너지 : "+comma(getPlayerEnergy(player)) );
        score1.setScore(1);
        player.setScoreboard(board);
    }
    @EventHandler
    public void healEnergy(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        Player player =event.getPlayer();
        if(!(potion.getType().equals(Material.POTION))) {
            return;
        }
        String name = potion.getItemMeta().getDisplayName();
        if(!(name.equals("에너지포션"))) {
            return;
        }
        addPlayerEnergy(player,50f);

    }
    @EventHandler
    public void energyUse_Break(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!(usePlayerEnergy(player,5f))) {
            if(player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }
            event.setCancelled(true);

            return;
        }
        regenEnergy(player,1f,100);
    }
    @EventHandler
    public void energyUse_Build(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!(usePlayerEnergy(player,5f))) {
            if(player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }
            event.setCancelled(true);
            return;
        }
        regenEnergy(player,1f,100);
    }
    public float discountEnergy_Pickaxe(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        Material pickaxe = item.getType();
        if(pickaxe == Material.STONE_PICKAXE) {
            return 10f;
        }
        if(pickaxe == Material.IRON_PICKAXE) {
            return 20f;
        }
        if(pickaxe == Material.DIAMOND_PICKAXE) {
            return 30f;
        }
        if(pickaxe == Material.GOLDEN_PICKAXE) {
            return 35f;
        }
        if(pickaxe == Material.NETHERITE_PICKAXE) {
            return 40f;
        }



        return 0;
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
                    if(!regenPlayerEnergy(player,regenEnergy)) {
                        setRegen(player,false);
                        this.cancel();
                    }
                }
            };
            Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }


}
