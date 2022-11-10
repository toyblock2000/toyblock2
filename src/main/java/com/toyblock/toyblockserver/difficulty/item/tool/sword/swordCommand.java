package com.toyblock.toyblockserver.difficulty.item.tool.sword;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class swordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        ItemStack item = new ItemStack(Material.STONE);
        WoodenSword sword = new WoodenSword();
        player.getInventory().addItem(sword.get(1));
        player.getInventory().addItem(sword.get(2));
        player.getInventory().addItem(sword.get(3));
        player.getInventory().addItem(sword.get(4));
        StoneSword sword1 = new StoneSword();
        player.getInventory().addItem(sword1.get(1));
        player.getInventory().addItem(sword1.get(2));
        player.getInventory().addItem(sword1.get(3));
        player.getInventory().addItem(sword1.get(4));

        return true;
    }
}
