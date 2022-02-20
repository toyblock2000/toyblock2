package com.toyblock.toyblockserver.difficulty.item;

import com.destroystokyo.paper.MaterialTags;
import com.toyblock.toyblockserver.difficulty.item.tool.MakeAxe;
import com.toyblock.toyblockserver.difficulty.item.tool.MakePickaxe;
import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import com.toyblock.toyblockserver.difficulty.item.tool.MakeSword;
import com.toyblock.toyblockserver.tool.developer.bug;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AnvilIUpgrade implements Listener {

    //@EventHandler
    public void enchant(PrepareAnvilEvent event) {
        if(event.getResult().getEnchantmentLevel(Enchantment.DAMAGE_ALL)>0) {
            WeaponLore weapon = new WeaponLore();
            ItemStack up = event.getResult();
            weapon.addEnchantLore(up);
            event.setResult(up);
        }

    }

    String remitLevel_Str = "레벨 제한";
    String level_Str = "레벨";
    public void anvilUpgrade_OrderSheet(PrepareAnvilEvent event) {

        Inventory inv = event.getInventory();
        if(inv.getItem(0).equals(null)) {
            return;
        }
        if(inv.getItem(1).equals(null)) {
           return;
        }
        WeaponLore tool = new WeaponLore();
        ItemStack result = event.getResult();
        ItemStack item1 = inv.getItem(0);
        ItemStack item2 = inv.getItem(1);
        MakeSword wood = new MakeSword();
        if(loreFinder(item1,level_Str) == 0) {
            return;
        }
        if(loreFinder(item1,remitLevel_Str)== 10) {
            return;
        }
        int level = (int)loreFinder(item1,level_Str);
        if(wood.getSword( (level+1)).equals(null)) {
            return;
        }

    }
    public void moveEnchant(ItemStack main , ItemStack sub) {
        if(sub.getEnchantments().isEmpty()) {
            return;
        }
        main.addEnchantments(sub.getEnchantments());
    }
    public void moveCustomModelData(ItemStack item, ItemStack item2) {
        ItemMeta meta = item2.getItemMeta();
        meta.setCustomModelData(item.getItemMeta().getCustomModelData() );
        item2.setItemMeta(meta);
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
    public float loreFinders(ItemStack item, String findStr,String nofindStr) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            if(str.contains(nofindStr)) {
                continue;
            }
            return Integer.parseInt(str.replaceAll("[^0-9]", ""));
        }
        return 0f;
    }
   // @EventHandler
    public void upgradeTool(PrepareSmithingEvent event) {
        Inventory inv = event.getInventory();
        Player player =(Player) event.getView().getPlayer();

        if(inv.getItem(0).equals(null)) {
            return;
        }
        if(inv.getItem(1).equals(null)) {
            return;
        }
        ItemStack item1 = inv.getItem(0);
        ItemStack item2 = inv.getItem(1);
        if(loreFinder(item1,level_Str)==0) {
            return;
        }
        boolean key = false;
        if(item1.getType().equals(Material.WOODEN_SWORD)) {
            key=true;
        }

        int level = (int)loreFinder(item1,level_Str);
        MakeSword tool = new MakeSword();
        ItemStack upItem = tool.getSword(level+1);
        event.setResult(item1);

    }
  //  @EventHandler
    public void upgradeToolClick(InventoryClickEvent event) {
        if(!(event.getInventory().getType().equals(InventoryType.SMITHING))) {
            return;
        }
        if(!(event.getSlot()==2)) {
            return;
        }
        if(!(event.getInventory().getItem(0).getType().equals(Material.WOODEN_SWORD))) {
            return;
        }
        if(!(event.getInventory().getItem(1).getType().equals(Material.OAK_PLANKS))) {
            return;
        }
        if(loreFinder(event.getInventory().getItem(0),level_Str)==0) {
            return;
        }
        int level = (int)loreFinder(event.getInventory().getItem(0),level_Str);
        MakeSword tool = new MakeSword();
        ItemStack upItem = tool.getSword(level+1);
        event.getInventory().clear(0);
         event.getInventory().getItem(1).setAmount(event.getInventory().getItem(1).getAmount()-1);

        event.getWhoClicked().closeInventory();
        event.getWhoClicked().getInventory().addItem(upItem);
    }
   @EventHandler
    public void changeUpgrade_Sword(SmithItemEvent event) {
       ItemStack ingot = event.getInventory().getItem(1);
       if(ingot.getType().equals(Material.NETHERITE_INGOT)) {
           return;
       }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
       if(!(MaterialTags.SWORDS.isTagged(itemType))) {
           return;
       }

       ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");
        int remitLevel = (int) toolEdit.loreFinder_remit(subItem,"레벨");

        Player player = (Player) event.getView().getPlayer();
        MakeSword wood = new MakeSword();
        wood.setType(subItem.getType());

        if(remitLevel <= level) {

            event.setCancelled(true);
            player.sendMessage(ChatColor.RED+"이미 제한 레벨까지 도달했습니다");

            return;
        }

        bug.chat(wood.getSword(level+1).getType().toString());
        ItemStack upItem = wood.getSword(level + 1);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);

        player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
        player.sendMessage(ChatColor.GREEN+"강화 성공");
    }
    @EventHandler
    public void change_Sword(SmithItemEvent event) {
        ItemStack ingot = event.getInventory().getItem(1);
        if(!(ingot.getType().equals(Material.NETHERITE_INGOT))) {
            return;
        }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
        if(!(MaterialTags.SWORDS.isTagged(itemType))) {
            return;
        }

        ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");


        Player player = (Player) event.getView().getPlayer();
        MakeSword wood = new MakeSword();
        wood.setNetheriteSword();
        ItemStack upItem = wood.getSword(level);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);

        player.playSound(player.getLocation(),Sound.UI_TOAST_CHALLENGE_COMPLETE,1,1);
        player.sendMessage(ChatColor.GRAY+"다이아검 네더라이트 강화 성공");
        Bukkit.broadcastMessage(ChatColor.GRAY+""+ChatColor.BOLD+player.getName()+"님이 [네더라이트 검] 을 제작했습니다");
    }
    @EventHandler
    public void change_Pickaxe(SmithItemEvent event) {
        ItemStack ingot = event.getInventory().getItem(1);
        if(!(ingot.getType().equals(Material.NETHERITE_INGOT))) {
            return;
        }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
        if(!(MaterialTags.PICKAXES.isTagged(itemType))) {
            return;
        }

        ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");

        Player player = (Player) event.getView().getPlayer();
        MakePickaxe make = new MakePickaxe();
        make.setNetheritePickaxe();
        ItemStack upItem = make.getPickaxe(level);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);

        player.playSound(player.getLocation(),Sound.UI_TOAST_CHALLENGE_COMPLETE,1,1);
        player.sendMessage(ChatColor.GRAY+"네더라이트 강화 성공");
    }
    @EventHandler
    public void change_Axe(SmithItemEvent event) {
        ItemStack ingot = event.getInventory().getItem(1);
        if(!(ingot.getType().equals(Material.NETHERITE_INGOT))) {
            return;
        }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
        if(!(MaterialTags.AXES.isTagged(itemType))) {
            return;
        }

        ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");


        Player player = (Player) event.getView().getPlayer();
        MakeAxe make = new MakeAxe();
        make.setNetheriteAxe();
        ItemStack upItem = make.getAxe(level);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);

        player.playSound(player.getLocation(),Sound.UI_TOAST_CHALLENGE_COMPLETE,1,1);
        player.sendMessage(ChatColor.GRAY+"네더라이트 강화 성공");
    }
    @EventHandler
    public void changeUpgrade_Pickaxe(SmithItemEvent event) {
        ItemStack ingot = event.getInventory().getItem(1);
        if(ingot.getType().equals(Material.NETHERITE_INGOT)) {
            return;
        }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
        if(!(MaterialTags.PICKAXES.isTagged(itemType))) {
            return;
        }
        ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");
        int remitLevel = (int) toolEdit.loreFinder_remit(subItem,"레벨");
        Player player = (Player) event.getView().getPlayer();
        MakePickaxe wood = new MakePickaxe();
        wood.setType(subItem.getType());
        if(remitLevel <= level) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED+"이미 제한 레벨까지 도달했습니다");

            return;
        }
        ItemStack upItem = wood.getPickaxe(level + 1);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);
        player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
        player.sendMessage(ChatColor.GREEN+"강화 성공");
    }
    @EventHandler
    public void changeUpgrade_Axe(SmithItemEvent event) {
        ItemStack ingot = event.getInventory().getItem(1);
        if(ingot.getType().equals(Material.NETHERITE_INGOT)) {
            return;
        }
        ItemStack subItem = event.getInventory().getItem(0);
        Material itemType = subItem.getType();
        if(!(MaterialTags.AXES.isTagged(itemType))) {
            return;
        }
        ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder_level(subItem, "레벨");
        int remitLevel = (int) toolEdit.loreFinder_remit(subItem,"레벨");
        Player player = (Player) event.getView().getPlayer();
        MakeAxe wood = new MakeAxe();
        wood.setType(subItem.getType());
        if(remitLevel <= level) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED+"이미 제한 레벨까지 도달했습니다");

            return;
        }
        ItemStack upItem = wood.getAxe(level + 1);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);
        player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
        player.sendMessage(ChatColor.GREEN+"강화 성공");
    }

    public static void actionBarChat(Player player,String str) {
        player.spigot (). sendMessage (ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText
                (str) );
    }
    public void enchantLore(ItemStack item) {
        if(!(item.getItemMeta().hasEnchants())) {
            return;
        }
        int value;
        if(item.getEnchantments().containsKey(Enchantment.DAMAGE_ALL)) {
            value = item.getEnchantments().get(Enchantment.DAMAGE_ALL);
            loreAdd_EnchantDamage(item, ChatColor.YELLOW+""+value);
        }
        if(item.getEnchantments().containsKey(Enchantment.DAMAGE_UNDEAD)) {
            value = item.getEnchantments().get(Enchantment.DAMAGE_UNDEAD);
            loreAdd_EnchantDamage(item, ChatColor.WHITE+""+value);
        }
        if(item.getEnchantments().containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
            value = item.getEnchantments().get(Enchantment.DAMAGE_ARTHROPODS);
            loreAdd_EnchantDamage(item, ChatColor.GRAY+""+value);
        }
    }
    public void loreAdd_EnchantDamage(ItemStack item , String addValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {

            String loreStr = lore.get(i);
            if (!(loreStr.contains("무기 데미지")) ) {
                continue;
            }
            String addLore = loreStr+" + "+addValue;
            lore.remove(i);
            lore.add(i,addLore);
            item.setLore(lore);
            return;
        }
    }

}
