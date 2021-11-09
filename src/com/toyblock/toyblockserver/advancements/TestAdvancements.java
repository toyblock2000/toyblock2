package com.toyblock.toyblockserver.advancements;

import eu.endercentral.crazy_advancements.*;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class TestAdvancements {
    public void test(Player player) {
        AdvancementManager manager = new AdvancementManager(player);
        net.md_5.bungee.api.chat.TextComponent message = new TextComponent("Hello world");
        message.setColor(net.md_5.bungee.api.ChatColor.RED);
        message.setBold(true);
        AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.ARMOR_STAND, "My Custom Advancements", "With cool additions", AdvancementDisplay.AdvancementFrame.TASK, false, false, AdvancementVisibility.ALWAYS);
        rootDisplay.setBackgroundTexture("textures/block/yellow_concrete.png");
        Advancement root = new Advancement(null, new NameKey("custom", "root"), rootDisplay);

        AdvancementDisplay childrenDisplay = new AdvancementDisplay(Material.ENDER_EYE, "오른쪽으로", "당신의 목표", AdvancementDisplay.AdvancementFrame.GOAL, true, true, AdvancementVisibility.VANILLA);
        childrenDisplay.setCoordinates(1, 0);//x, y
        Advancement children = new Advancement(root, new NameKey("custom", "right"), childrenDisplay);
        AdvancementDisplay display = new AdvancementDisplay(Material.ANVIL, new JSONMessage(message), new JSONMessage(message), AdvancementDisplay.AdvancementFrame.GOAL, true, true, AdvancementVisibility.VANILLA);
    }
}
