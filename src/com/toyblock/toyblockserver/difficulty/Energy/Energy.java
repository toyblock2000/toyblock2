package com.toyblock.toyblockserver.difficulty.Energy;
import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
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
            timeRemoveBoard(player);
            return;
        }
        createPlayerEnergy(player);
        createBoard(player);
        timeRemoveBoard(player);
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
    public static void actionBarChat(Player player,String str) {
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
    public String getColorEnergy(String commaEnergy) {
        float energy = Float.parseFloat(commaEnergy);
        if(energy<=33) {
            return ChatColor.RED+""+energy+"%";
        }
        if(energy<=66) {
            return ChatColor.YELLOW+""+energy+"%";
        }
        if(energy==100) {
            return ChatColor.GREEN+""+100+"%";
        }
        return ChatColor.GREEN+""+energy+"%";

    }
    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("에너지 : "+getColorEnergy(comma(getPlayerEnergy(player))) );
        score1.setScore(1);
        player.setScoreboard(board);
    }
    public static void createBoard_full(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("EnergyBoard","dummy","에너지");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = obj.getScore
                ("에너지 : "+ChatColor.GREEN+100+"%" );
        score1.setScore(1);
        player.setScoreboard(board);
        timeRemoveBoard(player);
    }
    public static void timeRemoveBoard(Player player) {
        String playerUUID = player.getUniqueId().toString();
        BukkitRunnable Regen = new BukkitRunnable() {
            public void run() {
                if(mapList.ENERGY_REGEN.containsKey(playerUUID)) {
                    this.cancel();
                }
                player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                this.cancel();
            }
        };
        Regen.runTaskTimer(Main.getPlugin(Main.class) ,300,500);
    }
    @EventHandler
    public void healEnergy(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        Player player =event.getPlayer();
        if(!(potion.getType().equals(Material.POTION))) {
            return;
        }
        String name = potion.getItemMeta().getDisplayName();
        if(!(name.equals(ChatColor.GREEN+"에너지 포션"))) {
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
        if(!(name.equals(ChatColor.GREEN+"에너지 재생포션"))) {
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
    public boolean checkHaveEnergy(Player player) {
        String playerUUID = player.getUniqueId().toString();
        if(mapList.ENERGY.containsKey(playerUUID)) {
            return true;
        }
        actionBarChat(player,ChatColor.RED+"에너지가 확인되지 않습니다, 버그를 관리자에게 보고해주세요");
        return false;
    }
    public float getOreUseEnergy(Material block) {
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
    public float getUseBlockEnergy_Break(Material block) {
        float blockEnergy = 0;
        if(MaterialTags.ORES.isTagged(block)) {
            blockEnergy = blockEnergy+3;
        }
        if(MaterialSetTag.COAL_ORES.isTagged(block)) {
            blockEnergy = blockEnergy+10;
        }
        if(MaterialSetTag.IRON_ORES.isTagged(block)) {
            blockEnergy = blockEnergy+30;
        }
        if(MaterialSetTag.GOLD_ORES.isTagged(block)) {
            blockEnergy = blockEnergy+50;
        }
        if(MaterialSetTag.DIAMOND_ORES.isTagged(block)) {
            blockEnergy = blockEnergy+90;
        }
        if(MaterialSetTag.OAK_LOGS.isTagged(block)) {
            blockEnergy = blockEnergy+5;
        }


        return blockEnergy;
    }

    @EventHandler
    public void energyUse_Break(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!(checkHaveEnergy(player))) {
            event.setCancelled(true);
            return;
        }
        Material block = event.getBlock().getType();
        float useEnergy = 3+getUseBlockEnergy_Break(block);
        float bonusCount = 0;
        //할인율
        if(MaterialSetTag.MINEABLE_PICKAXE.isTagged(block)) {
            bonusCount = discountEnergy_Pickaxe(player); //곡괭이의 보너스 점수
        }
        if(MaterialSetTag.MINEABLE_AXE.isTagged(block)) {
            bonusCount = discountEnergy_Axe(player);
        }
        if(MaterialSetTag.MINEABLE_SHOVEL.isTagged(block)) {
            bonusCount = discountEnergy_Shovel(player);
        }
        useEnergy = useEnergy- (float) (useEnergy * bonusCount / 100.0);
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
        if(!(checkHaveEnergy(player))) {
            event.setCancelled(true);
            return;
        }
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
                if(!(checkHaveEnergy(player))) {
                    this.cancel();
                }
                if(!regenPlayerEnergy(player,regenEnergy)) {
                    removeRegen(player);
                    actionBarChat(player,ChatColor.GREEN+"에너지 100% 회복");
                    timeRemoveBoard(player);
                    this.cancel();
                }
            }
        };
        Regen.runTaskTimer(Main.getPlugin(Main.class) , time, time);
    }


}
