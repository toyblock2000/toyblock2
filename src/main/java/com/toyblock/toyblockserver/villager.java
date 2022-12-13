package com.toyblock.toyblockserver;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class villager {
    public MerchantRecipe recipe (ItemStack item) {
        MerchantRecipe recipe = new MerchantRecipe(item, 10);
        recipe.addIngredient(new ItemStack(Material.BREAD, 1));
        return recipe;
    }
    //직업
    //상세 직업
    //지역
    @EventHandler
    public void levelUp(VillagerAcquireTradeEvent event) {
        Villager npc = (Villager) event.getEntity();

    }
    // 뉴비킷
    // 성레벨에 따른 아이템
    //도구킷 toolkit
    public void spawnTrader(Location loc,String kit) {
        WanderingTrader trader = (WanderingTrader) loc.getWorld().spawnEntity(loc, EntityType.WANDERING_TRADER);
        trader.setDespawnDelay(10000);



    }
}
