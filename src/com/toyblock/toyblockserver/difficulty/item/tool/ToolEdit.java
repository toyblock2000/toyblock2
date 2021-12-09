package com.toyblock.toyblockserver.difficulty.item.tool;

import com.toyblock.toyblockserver.tool.developer.bug;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToolEdit {
    public void moveItemMeta(ItemStack fromItem , ItemStack item) {
        moveEnchant(fromItem,item); //인첸트 옮기기
        enchantLore(item); //인첸트 설명 붙이기
        if(fromItem.getType()==item.getType()) {
            moveCustomModelData(fromItem,item); //커스텀 모델 데이터 옮기기
        }
        moveAbility(fromItem,item); //무기 추가능력 옮기기
        moveAddSoulBound(fromItem,item); //무기 소울바인딩 플러스 옮기기
        
    }
    public void setPickaxeLore(ItemStack item, int level, int remitLevel , double energy_discount) {
        List lore = new ArrayList();
        //lore.add(" ");
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
            if (!(loreStr.contains("데미지")) ) {
                continue;
            }
            String addLore = loreStr+" + "+addValue;
            lore.remove(i);
            lore.add(i,addLore);
            item.setLore(lore);
            return;
        }
    }
    public  void loreAdd_SoulBound(ItemStack item , float addValue) {
        if(!(item.getItemMeta().hasLore()) ) {
            return;
        }
        List<String> lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++) {

            String loreStr = lore.get(i);
            if (!(loreStr.contains("소울바운드")) ) {
                continue;
            }
            String addLore = loreStr+" + "+addValue+"%";
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
    public void moveAddSoulBound(ItemStack moveItem , ItemStack item) {
        float soulBound = loreFinder_Add(moveItem,"소울바운드");
        if(soulBound==0){
            return;
        }
        loreAdd_SoulBound(item,soulBound);
    }
    public void moveEnchant(ItemStack sub , ItemStack main) {
        if(sub.getEnchantments().isEmpty()) {
            return;
        }
        main.addEnchantments(sub.getEnchantments());
    }
    public void moveCustomModelData(ItemStack fromItem, ItemStack item) {
        if(!(fromItem.getItemMeta().hasCustomModelData())){
            return;
        }
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

                    float data = Float.parseFloat(array[v].replaceAll("[^0-9.]", ""));
                    value = value+data;
                }
                return value;
            }

            return Float.parseFloat(str.replaceAll("[^0-9.]", ""));
        }
        return 0f;
    }

    public void moveAbility(ItemStack moveItem, ItemStack item) {
        if(!(moveItem.getItemMeta().hasLore())) {
            return;
        }
        if(!(item.getItemMeta().hasLore())) {
            return;
        }

        List moveLore = moveItem.getLore();
        List lore = item.getLore();
        for(int i = 0;i<moveLore.size();i++){
            String str = (String)moveLore.get(i);

            if(!(str.contains("어빌리티"))) {
                continue;
            }
            lore.add(str);
        }
        item.setLore(lore);
        return;
    }
    public float loreFinder_Add(ItemStack item, String findStr) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            if(!(str.contains("+"))) {
                continue;
            }
            String[] array = str.split("\\+");



            return Float.parseFloat(array[1].replaceAll("[^0-9.]", ""));
        }
        return 0f;
    }
    public float loreFinder_remit(ItemStack item, String findStr) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            if(!(str.contains("/"))) {
                continue;
            }
            String[] array = str.split("\\/");



            return Float.parseFloat(array[1].replaceAll("[^0-9.]", ""));
        }
        return 0f;
    }
    public float loreFinder_level(ItemStack item, String findStr) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            if(!(str.contains("/"))) {
                continue;
            }
            String[] array = str.split("\\/");



            return Float.parseFloat(array[0].replaceAll("[^0-9.]", ""));
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
            return Float.parseFloat(str.replaceAll("[^0-9.]", ""));
        }
        return 0f;
    }
    public void setSwordAttribute(ItemStack item,int level,int remitLevel,double damage,double attack_speed,double soulBound) {
        double playerAttackSpeed = 4;
        double playerDamange = 1;
        double set_damage = damage - playerDamange;
        double set_speed = attack_speed - playerAttackSpeed;
        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", set_damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", set_speed, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        setSwordAttributeLore(item,level,remitLevel,damage,attack_speed,soulBound);
    }
    public void setPickaxeAttribute(ItemStack item,int level,int remitLevel,double energyEfficiency,double soulBound) {

        setPickaxeAttributeLore(item,level,remitLevel,energyEfficiency,soulBound);
    }
    public void setAxeAttribute(ItemStack item,int level,int remitLevel,double damage,double attack_speed,double energyEfficiency,double soulBound) {
        double playerAttackSpeed = 4;
        double playerDamange = 1;
        double set_damage = damage - playerDamange;
        double set_speed = attack_speed - playerAttackSpeed;
        ItemMeta meta = item.getItemMeta();
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        AttributeModifier attackdamage = new AttributeModifier(UUID.randomUUID(), "무기 공격력", set_damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_DAMAGE, attackdamage);
        AttributeModifier attackspeed = new AttributeModifier(UUID.randomUUID(), "무기 공격속도", set_speed, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND);
        meta.addAttributeModifier (Attribute.GENERIC_ATTACK_SPEED, attackspeed);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        setAxeAttributeLore(item,level,remitLevel,damage,attack_speed,energyEfficiency,soulBound);
    }
    public String comma (double f) {
        DecimalFormat form = new DecimalFormat("#.##");
        String nomber = form.format(f);
        return nomber;
    }
    public void setSwordAttributeLore(ItemStack item,int level, int remitLevel ,double damage, double speed,double soulBound) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level+ ChatColor.WHITE+" / "+remitLevel);
        lore.add(ChatColor.WHITE+" 데미지 : "+ChatColor.YELLOW+comma(damage));
        lore.add(ChatColor.WHITE+" 공격속도 : "+ChatColor.YELLOW+comma(speed));
        lore.add(ChatColor.LIGHT_PURPLE+" 소울바운드 : "+soulBound+"%");
        item.setLore(lore);
    }
    public void setPickaxeAttributeLore(ItemStack item,int level, int remitLevel , double energyEfficiency,double soulBound) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level+ ChatColor.WHITE+" / "+remitLevel);
        lore.add(ChatColor.WHITE+" 에너지 효율 : "+ChatColor.YELLOW+comma(energyEfficiency)+"%");
        lore.add(ChatColor.LIGHT_PURPLE+" 소울바운드 : "+soulBound+"%");
        item.setLore(lore);
    }
    public void setAxeAttributeLore(ItemStack item,int level, int remitLevel , double damage,double speed,double energyEfficiency,double soulBound) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level+ ChatColor.WHITE+" / "+remitLevel);
        lore.add(ChatColor.WHITE+" 데미지 : "+ChatColor.YELLOW+comma(damage));
        lore.add(ChatColor.WHITE+" 공격속도 : "+ChatColor.YELLOW+comma(speed));
        lore.add(ChatColor.WHITE+" 에너지 효율 : "+ChatColor.YELLOW+comma(energyEfficiency)+"%");
        lore.add(ChatColor.LIGHT_PURPLE+" 소울바운드 : "+soulBound+"%");
        item.setLore(lore);
    }
}
