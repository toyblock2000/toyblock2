package com.toyblock.toyblockserver.advancements.adventurer;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.AdvancementDisplay;
import eu.endercentral.crazy_advancements.AdvancementVisibility;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Adventurer implements Listener {
    public static AdvancementManager manager = new AdvancementManager();

    public static void createAdventurer () {
        AdvancementDisplay startDisplay =
                new AdvancementDisplay(Material.DARK_OAK_SAPLING, "모험가", "§5모험을 시작합니다", AdvancementDisplay.AdvancementFrame.CHALLENGE, false, true, AdvancementVisibility.ALWAYS);
        startDisplay.setBackgroundTexture("textures/block/azalea_plant.png");

        Advancement start = new Advancement(null, new NameKey("adventurer", "start"), startDisplay);
        start.setCriteria(1);
        manager.addAdvancement(start);

        AdvancementDisplay move1_Display =
                new AdvancementDisplay(Material.GRASS_BLOCK, "탐험중", "§5100블럭을 탐험 하세요", AdvancementDisplay.AdvancementFrame.CHALLENGE, false, true, AdvancementVisibility.PARENT_GRANTED);
        move1_Display.setCoordinates(1,0);

        Advancement move1 = new Advancement(start, new NameKey("adventurer", "move1"), move1_Display);
        move1.setCriteria(100);
        manager.addAdvancement(move1);

        AdvancementDisplay move2_Display =
                new AdvancementDisplay(Material.GRASS_BLOCK, "탐험중2", "§5200블럭을 탐험 하세요", AdvancementDisplay.AdvancementFrame.CHALLENGE, false, true, AdvancementVisibility.PARENT_GRANTED);
        move2_Display.setCoordinates(2,1);

        Advancement move2 = new Advancement(move1, new NameKey("adventurer", "move2"), move2_Display);
        move2.setCriteria(1);
        manager.addAdvancement(move2);
    }
    public static void addPlayer (Player player) {
        manager.addPlayer(player);
        Advancement advancement;
        advancement = manager.getAdvancement(new NameKey("adventurer","start"));

        manager.grantAdvancement(player,advancement);
    }
    @EventHandler
    public static void move1up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
            Advancement advancement;
            advancement = manager.getAdvancement(new NameKey("adventurer","move1"));
            manager.setCriteriaProgress(event.getPlayer(),advancement,manager.getCriteriaProgress(event.getPlayer(),advancement)+1);
            Player player = event.getPlayer();
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(1);
            item.setItemMeta(meta);
            player.getInventory().setItem(0,item);



    }
    @EventHandler
    public static void move2up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        Advancement advancement;
        advancement = manager.getAdvancement(new NameKey("adventurer","move2"));
        Advancement advancementup;
        advancementup = manager.getAdvancement(new NameKey("adventurer","move1"));
        if(!(manager.getCriteriaProgress(event.getPlayer(),advancementup)>=100)) {
            event.getPlayer().chat(""+manager.getCriteriaProgress(event.getPlayer(),advancementup));
            return;
        }
        manager.setCriteriaProgress(event.getPlayer(),advancement,manager.getCriteriaProgress(event.getPlayer(),advancement)+1);



    }


}
