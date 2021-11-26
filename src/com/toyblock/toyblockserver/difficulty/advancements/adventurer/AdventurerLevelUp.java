package com.toyblock.toyblockserver.difficulty.advancements.adventurer;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.NameKey;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AdventurerLevelUp implements Listener {
    //@EventHandler
    public static void move1up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Advancement advancement;
        advancement = com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.getAdvancement(new NameKey("adventurer","move1"));
        com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.setCriteriaProgress(event.getPlayer(),advancement, com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager
                .getCriteriaProgress(event.getPlayer(),advancement)+1);



    }
    //@EventHandler
    public static void move2up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        Advancement advancement;
        advancement = com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.getAdvancement(new NameKey("adventurer","move2"));
        Advancement advancementup;
        advancementup = com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.getAdvancement(new NameKey("adventurer","move1"));
        if(!advancement.isDone(event.getPlayer())) {
            event.getPlayer().chat(""+ com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.getCriteriaProgress(event.getPlayer(),advancementup));
            return;
        }
        com.toyblock.toyblockserver.difficulty.advancements.adventurer.Adventurer.manager.setCriteriaProgress(event.getPlayer(),advancement, Adventurer.manager
                .getCriteriaProgress(event.getPlayer(),advancement)+1);



    }
}
