package com.toyblock.toyblockserver.difficulty.item;

import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import com.toyblock.toyblockserver.difficulty.item.tool.MakeSword;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
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
    public void upgrade(PrepareAnvilEvent event) {
        if(!(event.getInventory().getItem(1).getItemMeta().getDisplayName().equals("업그레이드"))) {
            return;
        }
        Inventory inv = event.getInventory();
        WeaponLore weapon = new WeaponLore();
        ItemStack up = weapon.upgradeTear(event.getResult());
        weapon.addEnchantLore(up);
        event.setResult(up);
    }
    //@EventHandler
    public void enchant(PrepareAnvilEvent event) {
        if(event.getResult().getEnchantmentLevel(Enchantment.DAMAGE_ALL)>0) {
            WeaponLore weapon = new WeaponLore();
            ItemStack up = event.getResult();
            weapon.addEnchantLore(up);
            event.setResult(up);
        }

    }
    //@EventHandler
    public void anvilupgrade(PrepareAnvilEvent event) {

        Inventory inv = event.getInventory();
        WeaponLore weapon = new WeaponLore();
        if (weapon.getTear(inv.getItem(0)) < weapon.getTear(inv.getItem(1))) {
            ItemStack newitem = weapon.setUpgradeTear(inv.getItem(1), inv.getItem(2));
            weapon.addEnchantLore(newitem);
            event.setResult(newitem);
            return;
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
        ItemStack subItem = event.getInventory().getItem(0);
       ToolEdit toolEdit = new ToolEdit();
        int level = (int) toolEdit.loreFinder(subItem, "레벨");
        int remitLevel = (int) toolEdit.loreFinder_remit(subItem,"레벨");
        Player player = (Player) event.getView().getPlayer();
        MakeSword wood = new MakeSword();
        if(remitLevel == level) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED+"이미 제한 레벨까지 도달했습니다");

            return;
        }
        ItemStack upItem = wood.getSword(level + 1);
        toolEdit.moveItemMeta(subItem,upItem);
        event.getInventory().setResult(upItem);
        //player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
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
