package com.toyblock.toyblockserver.advancements;

import eu.endercentral.crazy_advancements.*;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class TestAdvancements extends JavaPlugin implements Listener {
    int a = 0;
    BukkitTask task = null;
    public void test(Player player) {
        AdvancementManager manager = new AdvancementManager();
        AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.BEDROCK, "How...?", "§5Collect Bedrock", AdvancementDisplay.AdvancementFrame.CHALLENGE, true, true, AdvancementVisibility.ALWAYS);
        rootDisplay.setBackgroundTexture("textures/block/compass.png");
        Advancement root = new Advancement(null, new NameKey("custom", "root"), rootDisplay);
        root.setCriteria(1);
        root.displayMessageToEverybody(player);
        manager.addAdvancement(root);
        manager.grantAdvancement(player, root);
        player.chat("실행ㄴㄴㄴㄴ");
        task = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                manager.addPlayer(player);
                if(!manager.getPlayers().isEmpty()) {
                    testcancel();
                }
            }
        },3,3);
    }
    public void testcancel() {
        task.cancel();
    }
}
