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
import org.bukkit.block.Block;
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

public class Mana implements Listener {
    private String energyName = "Mana";

    public static void createPlayerMana(Player player) {
        String playerUUID = player.getUniqueId().toString();
        mapList.MANA.put(playerUUID,100f);

    }
    public boolean checkPlayerMana(Player player) {
        String playerUUID = player.getUniqueId().toString();
        if(mapList.MANA.containsKey(playerUUID)) {
            return true;
        }
        return false;
    }
    @EventHandler
    public void joinPlayerMana(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(checkPlayerMana(player)) {
            createBoard(player);
            timeRemoveBoard(player);
            return;
        }
        createPlayerMana(player);
        createBoard(player);
        timeRemoveBoard(player);
    }
    public float getPlayerMana(Player player) {
        String playerUUID = player.getUniqueId().toString();
        return mapList.MANA.get(playerUUID);
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

    public boolean usePlayerMana(Player player,Float useMana) {
        String playerUUID = player.getUniqueId().toString();
        Float playerMana = mapList.MANA.get(playerUUID);

        if(!(playerMana >= useMana)) {
            actionBarChat(player,ChatColor.RED+"마나 부족");
            return false;
        }
        mapList.MANA.put(playerUUID,playerMana-(useMana) );
        showUseMana(player,useMana);
        return true;
    }
    public boolean addPlayerMana(Player player,Float addMana) {
        String playerUUID = player.getUniqueId().toString();
        Float playerMana = mapList.MANA.get(playerUUID);
        if(100 <= playerMana) {
            return false;
        }
        Float scarceMana = (100-playerMana);
        if(scarceMana<=addMana) {
            mapList.MANA.put(playerUUID,playerMana+scarceMana);
            showAddMana(player,scarceMana);
        }
        else {
            mapList.MANA.put(playerUUID,playerMana+addMana);
            showAddMana(player,addMana);
        }
        return true;
    }
    public boolean regenPlayerMana(Player player,Float addMana) {
        String playerUUID = player.getUniqueId().toString();
        Float playerMana = mapList.MANA.get(playerUUID);
        if(100 <= playerMana) {
            return false;
        }
        Float scarceMana = (100-playerMana);
        if(scarceMana<=addMana) {
            mapList.MANA.put(playerUUID,playerMana+scarceMana);
        }
        else {
            mapList.MANA.put(playerUUID,playerMana+addMana);
        }
        createBoard(player);
        return true;
    }
    public static void actionBarChat(Player player,String str) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (str) );
    }
    public void setPlayerMana(Player player,Float energy) {
        String playerUUID = player.getUniqueId().toString();
        mapList.MANA.put(playerUUID,energy);
    }
    public void showAddMana(Player player,Float addMana) {
        if(!(player.isOnline())) {
            return ;
        }
        String addValue = comma(addMana);
        String countValue = comma(getPlayerMana(player));
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (ChatColor.AQUA+"마나 +"+addValue+"%" ) );
        createBoard(player);
        return ;
    }
    public static String comma (float f) {
        DecimalFormat form = new DecimalFormat("#.#");
        String nomber = form.format(f);
        return nomber;
    }

    public void showUseMana (Player player,Float useMana) {
        if(!(player.isOnline())) {
            return ;
        }
        String useValue = comma(useMana);
        String countValue = comma(getPlayerMana(player));
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (ChatColor.RED+"마나 -"+useValue+"%" ) );
        createBoard(player);

    }
    public String getColorMana(String commaMana) {
        float energy = Float.parseFloat(commaMana);
        if(energy<=33) {
            return ChatColor.RED+""+energy+"%";
        }
        if(energy<=66) {
            return ChatColor.YELLOW+""+energy+"%";
        }
        if(energy==100) {
            return ChatColor.AQUA+""+100+"%";
        }
        return ChatColor.AQUA+""+energy+"%";

    }
    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("ManaBoard","dummy","마나");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("마나 : "+getColorMana(comma(getPlayerMana(player))) );
        score1.setScore(1);
        player.setScoreboard(board);
    }
    public static void createBoard_full(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("ManaBoard","dummy","마나");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("마나 : "+ChatColor.AQUA+100+"%" );
        score1.setScore(1);
        player.setScoreboard(board);
        timeRemoveBoard(player);
    }
    public static void timeRemoveBoard(Player player) {
        String playerUUID = player.getUniqueId().toString();
        BukkitRunnable Regen = new BukkitRunnable() {
            public void run() {
                if(mapList.MANA_REGEN.containsKey(playerUUID)) {
                    this.cancel();
                }
                player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                this.cancel();
            }
        };
        Regen.runTaskTimer(Main.getPlugin(Main.class) ,300,500);
    }
    @EventHandler
    public void healMana(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        Player player =event.getPlayer();
        if(!(potion.getType().equals(Material.POTION))) {
            return;
        }
        String name = potion.getItemMeta().getDisplayName();
        if(!(name.equals("마나포션"))) {
            return;
        }
        addPlayerMana(player,50f);

    }
    @EventHandler
    public void regenHealMana(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        Player player =event.getPlayer();
        if(!(potion.getType().equals(Material.POTION))) {
            return;
        }
        String name = potion.getItemMeta().getDisplayName();
        if(!(name.equals("마나 재생포션"))) {
            return;
        }
        fastRegenMana(player,1f,100);

    }
    public void fastRegenMana (Player player,Float regenMana,long time) {


        BukkitRunnable Regen = new BukkitRunnable() {
            int count = 0;
            public void run() {
                if(count>100) {
                    this.cancel();
                }
                regenPlayerMana(player,regenMana);
                count++;
            }
        };
        Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }
    public boolean checkHaveMana(Player player) {
        String playerUUID = player.getUniqueId().toString();
        if(mapList.MANA.containsKey(playerUUID)) {
            return true;
        }
        actionBarChat(player,ChatColor.RED+"마나가 확인되지 않습니다, 버그를 관리자에게 보고해주세요");
        return false;
    }
    public float getOreUseMana(Material block) {
        if(MaterialSetTag.COAL_ORES.isTagged(block)) {
            return 5f;
        }
        if(MaterialSetTag.IRON_ORES.isTagged(block)) {
            return 30f;
        }
        if(MaterialSetTag.GOLD_ORES.isTagged(block)) {
            return  30f;
        }
        if(MaterialSetTag.DIAMOND_ORES.isTagged(block)) {
            return  90f;
        }


        return 0f;
    }
    public float getUseBlockMana_Break(Material block) {
        float blockMana = 0;
        if(MaterialTags.ORES.isTagged(block)) {
            blockMana = blockMana+3;
        }
        if(MaterialSetTag.COAL_ORES.isTagged(block)) {
            blockMana = blockMana+10;
        }
        if(MaterialSetTag.IRON_ORES.isTagged(block)) {
            blockMana = blockMana+30;
        }
        if(MaterialSetTag.GOLD_ORES.isTagged(block)) {
            blockMana = blockMana+50;
        }
        if(MaterialSetTag.DIAMOND_ORES.isTagged(block)) {
            blockMana = blockMana+90;
        }
        if(MaterialSetTag.OAK_LOGS.isTagged(block)) {
            blockMana = blockMana+5;
        }


        return blockMana;
    }

    @EventHandler
    public void energyUse_Break(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!(checkHaveMana(player))) {
            event.setCancelled(true);
            return;
        }
        Material block = event.getBlock().getType();
        float useMana = 3+getUseBlockMana_Break(block);
        float bonusCount = 0;
        //할인율
        if(MaterialSetTag.MINEABLE_PICKAXE.isTagged(block)) {
            bonusCount = discountMana_Pickaxe(player); //곡괭이의 보너스 점수
        }
        if(MaterialSetTag.MINEABLE_AXE.isTagged(block)) {
            bonusCount = discountMana_Axe(player);
        }
        if(MaterialSetTag.MINEABLE_SHOVEL.isTagged(block)) {
            bonusCount = discountMana_Shovel(player);
        }
        useMana = useMana- (float) (useMana * bonusCount / 100.0);
        if(!(usePlayerMana(player,useMana))) {
            if(player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }
            event.setCancelled(true);

            return;
        }
        regenMana(player,1f,100);
    }
    @EventHandler
    public void energyUse_Build(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!(checkHaveMana(player))) {
            event.setCancelled(true);
            return;
        }
        if(!(usePlayerMana(player,1f))) {
            if(player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }
            event.setCancelled(true);
            return;
        }
        regenMana(player,1f,100);
    }
    public float discountMana_Pickaxe(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        Material pickaxe = item.getType();
        if(!(MaterialTags.PICKAXES.isTagged(pickaxe))) {
            return 0;
        }
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
    public float discountMana_Pickaxe_test(Player player) {

        ItemStack item = player.getInventory().getItemInMainHand();
        if(!(MaterialTags.PICKAXES.isTagged(item))) {
            return 0;
        }
        return loreFinder(item,"마나 소모");
    }
    public float discountMana_Axe(Player player) {
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
    public float discountMana_Shovel(Player player) {
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
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
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
        return mapList.MANA_REGEN.containsKey(playerUUID);
    }
    public void setRegen(Player player,boolean plug) {
        String playerUUID = player.getUniqueId().toString();
        mapList.MANA_REGEN.put(playerUUID,plug);
    }
    public void removeRegen(Player player) {
        String playerUUID = player.getUniqueId().toString();
        mapList.MANA_REGEN.remove(playerUUID);
    }
    public void regenMana (Player player,Float regenMana,long time) {

        if (getRegen(player)) {
            return;
        }
        setRegen(player,true);

            BukkitRunnable Regen = new BukkitRunnable() {
                public void run() {
                    if(!(checkHaveMana(player))) {
                        this.cancel();
                    }
                    if(!regenPlayerMana(player,regenMana)) {
                        removeRegen(player);
                        actionBarChat(player,ChatColor.AQUA+"마나 100% 회복");
                        timeRemoveBoard(player);
                        this.cancel();
                    }
                }
            };
            Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }


}
