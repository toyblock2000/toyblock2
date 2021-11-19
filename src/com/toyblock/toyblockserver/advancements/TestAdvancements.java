package com.toyblock.toyblockserver.advancements;

import eu.endercentral.crazy_advancements.*;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import com.toyblock.toyblockserver.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class TestAdvancements  implements Listener {
    int a = 0;
    ConsoleCommandSender consol = Bukkit.getConsoleSender();
    BukkitTask task = null;
    Main main;
    public AdvancementManager manager = new AdvancementManager();
    Advancement side;
    public void testcancel() {
        task.cancel();
    }
    public void testjoin(Player player) {
        manager.addPlayer(player);
        player.getInventory().setItem(0, new ItemStack(Material.PLAYER_HEAD));
        consol.sendMessage("작동");
        AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.BEDROCK, "모험가", "§5모험을떠나세염", AdvancementDisplay.AdvancementFrame.CHALLENGE, true, true, AdvancementVisibility.ALWAYS);
        rootDisplay.setBackgroundTexture("textures/block/azalea_plant.png");
        Advancement root = new Advancement(null, new NameKey("custom", "root"), rootDisplay);
        root.setCriteria(1);
        root.displayMessageToEverybody(player);
        manager.addAdvancement(root);
        manager.grantAdvancement(player, root);
        player.chat("실행ㄴㄴㄴㄴ");
        AdvancementDisplay sideDisplay = new AdvancementDisplay(Material.DIAMOND_CHESTPLATE, "DDHow...?", "§5CollectDD Bedrock", AdvancementDisplay.AdvancementFrame.CHALLENGE, true, true, AdvancementVisibility.ALWAYS);
    //    sideDisplay.setBackgroundTexture("textures/block/beacon.png");
        sideDisplay.setCoordinates(1,0);
        side = new Advancement(root, new NameKey("customs", "roots"), sideDisplay);
        root.setCriteria(1);
        root.displayMessageToEverybody(player);
        manager.addAdvancement(side);
        manager.grantAdvancement(player,side);
    }

}
