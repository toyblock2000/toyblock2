package com.toyblock.toyblockserver.difficulty.item.tool;

import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ToolEdit {
    public void moveItemMeta(ItemStack fromItem , ItemStack item) {
        moveEnchant(fromItem,item); //인첸트 옮기기
        enchantLore(item); //인첸트 설명 붙이기
        moveCustomModelData(fromItem,item); //커스텀 모델 데이터 옮기기
        
    }
    public void setPickaxeLore(ItemStack item, int level, int remitLevel , double energy_discount) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level);
        lore.add(ChatColor.WHITE+" 레벨제한  : "+remitLevel);

        lore.add(ChatColor.WHITE+"에너지 소모 감소율: "+energy_discount);
        item.setLore(lore);
    }
    public  void loreAdd_EnchantDamage(ItemStack item , String addValue) {
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
    public  void enchantLore(ItemStack item) {
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
    public void moveEnchant(ItemStack sub , ItemStack main) {
        if(!(sub.getEnchantments().isEmpty())) {
            return;
        }
        main.addEnchantments(sub.getEnchantments());
    }
    public void moveCustomModelData(ItemStack fromItem, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(fromItem.getItemMeta().getCustomModelData() );
        item.setItemMeta(meta);
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
            float value = 0;
            if(str.contains("+")) {
                String[] array = str.split("\\+");
                for(int v=0;v<array.length;v++) {

                    float data = Float.parseFloat(array[v].replaceAll("[^0-9]", ""));
                    value = value+data;
                }
                return value;
            }

            return Float.parseFloat(str.replaceAll("[^0-9]", ""));
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
            return Float.parseFloat(str.replaceAll("[^0-9]", ""));
        }
        return 0f;
    }
}
