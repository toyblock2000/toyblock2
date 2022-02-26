package com.toyblock.toyblockserver;

import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.AdvancementVisibility;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class jobClass {
    public void jobTest() {
        ItemStack icon = new ItemStack(Material.STONE);
        JSONMessage title = new JSONMessage(new TextComponent("Title"));
        JSONMessage description = new JSONMessage(new TextComponent("Description"));
        AdvancementDisplay.AdvancementFrame frame = AdvancementDisplay.AdvancementFrame.TASK;
        AdvancementVisibility visibility = AdvancementVisibility.ALWAYS;
        AdvancementDisplay display = new AdvancementDisplay(icon, title, description, frame, visibility);
        display.setBackgroundTexture("textures/block/yellow_concrete.png");
        display.setX(1);
        display.setY(1.5f);

    }
}
