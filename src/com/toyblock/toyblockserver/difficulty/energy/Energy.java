package com.toyblock.toyblockserver.difficulty.energy;

import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import com.toyblock.toyblockserver.Main;
import com.toyblock.toyblockserver.mapList;
import com.toyblock.toyblockserver.tool.developer.bug;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
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
import java.util.List;

public class Energy implements Listener {
    private String energyName = "Energy";

    public static void createPlayerEnergy(Player player) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY.put(playerUUID,100f);

    }
    public boolean checkPlayerEnergy(Player player) {
        String playerUUID = player.getUniqueId().toString();
        if(mapList.ENERGY.containsKey(playerUUID)) {
            return true;
        }
        return false;
    }
    @EventHandler
    public void joinPlayerEnergy(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(checkPlayerEnergy(player)) {
            createBoard(player);
            return;
        }
        createPlayerEnergy(player);
        createBoard(player);
    }
    public float getPlayerEnergy(Player player) {
        String playerUUID = player.getUniqueId().toString();
        return mapList.ENERGY.get(playerUUID);
    }
    public float bonusConut(Player player) {
        return 0f;
    }
    public float bonusItemCount(Player player) {
        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return 0f;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        return 0f;
    }

    public boolean usePlayerEnergy(Player player,Float useEnergy) {
        String playerUUID = player.getUniqueId().toString();
        Float playerEnergy = mapList.ENERGY.get(playerUUID);

        if(!(playerEnergy >= useEnergy)) {
            actionBarChat(player,ChatColor.RED+"에너지 부족");
            return false;
        }
        mapList.ENERGY.put(playerUUID,playerEnergy-(useEnergy) );
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
            showAddEnergy(player,scarceEnergy);
        }
        else {
            mapList.ENERGY.put(playerUUID,playerEnergy+addEnergy);
            showAddEnergy(player,addEnergy);
        }
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
        createBoard(player);
        return true;
    }
    public void actionBarChat(Player player,String str) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (str) );
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
                (ChatColor.GREEN+"에너지 +"+addValue+"%" ) );
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
                (ChatColor.RED+"에너지 -"+useValue+"%" ) );
        createBoard(player);

    }
    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("에너지 : "+comma(getPlayerEnergy(player))+"%" );
        score1.setScore(1);
        player.setScoreboard(board);
    }
    public static void createBoard_full(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("에너지 : "+100+"%" );
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
    public void regenHealEnergy(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        Player player =event.getPlayer();
        if(!(potion.getType().equals(Material.POTION))) {
            return;
        }
        String name = potion.getItemMeta().getDisplayName();
        if(!(name.equals("에너지 재생포션"))) {
            return;
        }
        fastRegenEnergy(player,1f,100);

    }
    public void fastRegenEnergy (Player player,Float regenEnergy,long time) {


        BukkitRunnable Regen = new BukkitRunnable() {
            int count = 0;
            public void run() {
                if(count>100) {
                    this.cancel();
                }
                regenPlayerEnergy(player,regenEnergy);
                count++;
            }
        };
        Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }

    @EventHandler
    public void energyUse_Break(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material block = event.getBlock().getType();
        float useEnergy = 3;
        if(MaterialSetTag.MINEABLE_PICKAXE.isTagged(block)) {

            float bonusCount = discountEnergy_Pickaxe_test(player);
            useEnergy = useEnergy- (float) (useEnergy * bonusCount / 100.0);
        }
        if(MaterialSetTag.MINEABLE_AXE.isTagged(block)) {
            float bonusCount = discountEnergy_Axe(player);
            useEnergy = useEnergy- (float) (useEnergy * bonusCount / 100.0);
        }
        if(MaterialSetTag.MINEABLE_SHOVEL.isTagged(block)) {
            float bonusCount = discountEnergy_Shovel(player);
            useEnergy = useEnergy- (float) (useEnergy * bonusCount / 100.0);
        }

        if(!(usePlayerEnergy(player,useEnergy))) {
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
        if(!(usePlayerEnergy(player,1f))) {
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
    public float discountEnergy_Pickaxe_test(Player player) {

        ItemStack item = player.getInventory().getItemInMainHand();
        if(!(MaterialTags.PICKAXES.isTagged(item))) {
            return 0;
        }
        return loreFinder(item,"에너지 소모");
    }
    public float discountEnergy_Axe(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        Material pickaxe = item.getType();
        if(pickaxe == Material.STONE_AXE) {
            return 10f;
        }
        if(pickaxe == Material.IRON_AXE) {
            return 20f;
        }
        if(pickaxe == Material.DIAMOND_AXE) {
            return 30f;
        }
        if(pickaxe == Material.GOLDEN_AXE) {
            return 35f;
        }
        if(pickaxe == Material.NETHERITE_AXE) {
            return 40f;
        }
        return 0;
    }
    public float discountEnergy_Shovel(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        Material pickaxe = item.getType();
        if(pickaxe == Material.STONE_SHOVEL) {
            return 10f;
        }
        if(pickaxe == Material.IRON_SHOVEL) {
            return 20f;
        }
        if(pickaxe == Material.DIAMOND_SHOVEL) {
            return 30f;
        }
        if(pickaxe == Material.GOLDEN_SHOVEL) {
            return 35f;
        }
        if(pickaxe == Material.NETHERITE_SHOVEL) {
            return 40f;
        }
        return 0;
    }
    public float loreFinder(ItemStack item, String findStr) {
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            return Integer.parseInt(str.replaceAll("[^0-9]", ""));
        }
        return 0f;
    }

    public boolean getRegen(Player player) {
        String playerUUID = player.getUniqueId().toString();
        return mapList.ENERGY_REGEN.containsKey(playerUUID);
    }
    public void setRegen(Player player,boolean plug) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY_REGEN.put(playerUUID,plug);
    }
    public void removeRegen(Player player) {
        String playerUUID = player.getUniqueId().toString();
        mapList.ENERGY_REGEN.remove(playerUUID);
    }
    public void regenEnergy (Player player,Float regenEnergy,long time) {

        if (getRegen(player)) {
            return;
        }
        setRegen(player,true);

            BukkitRunnable Regen = new BukkitRunnable() {
                public void run() {
                    if (getRegen(player) == false) {
                        this.cancel();
                    }

                    if(!regenPlayerEnergy(player,regenEnergy)) {
                        removeRegen(player);
                        actionBarChat(player,ChatColor.GREEN+"에너지 100% 회복");
                        this.cancel();
                    }
                }
            };
            Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }


}
