package com.toyblock.toyblockserver.advancements.adventurer;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.NameKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AdventurerLevelUp implements Listener {
    @EventHandler
    public static void move1up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Advancement advancement;
        advancement = Adventurer.manager.getAdvancement(new NameKey("adventurer","move1"));
        Adventurer.manager.setCriteriaProgress(event.getPlayer(),advancement,Adventurer.manager
                .getCriteriaProgress(event.getPlayer(),advancement)+1);



    }
    @EventHandler
    public static void move2up(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        Advancement advancement;
        advancement = Adventurer.manager.getAdvancement(new NameKey("adventurer","move2"));
        Advancement advancementup;
        advancementup = Adventurer.manager.getAdvancement(new NameKey("adventurer","move1"));
        if(!(Adventurer.manager.getCriteriaProgress(event.getPlayer(),advancementup)>=100)) {
            event.getPlayer().chat(""+Adventurer.manager.getCriteriaProgress(event.getPlayer(),advancementup));
            return;
        }
        Adventurer.manager.setCriteriaProgress(event.getPlayer(),advancement,Adventurer.manager
                .getCriteriaProgress(event.getPlayer(),advancement)+1);



    }
}
