package com.toyblock.toyblockserver.structure.castle;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class Castle_Info {

    // LivingEntity mob = (LivingEntity)world.spawnEntity(p.getLocation(), EntityType.ZOMBIE);

    ItemStack vote1 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote2 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote3 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote_house = new ItemStack(Material.SKELETON_SKULL);

    public LivingEntity mini_vote1(ItemStack vote,Location loc) {
        LivingEntity mob = (LivingEntity)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        ArmorStand stand = (ArmorStand)mob;
        stand.getEquipment().setHelmet(vote);
        stand.setSmall(true);
        stand.setVisible(false);
        return mob;
        
    } 
    public void vote_1(Location loc,String view) {
        LivingEntity mob = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        ArmorStand stand = (ArmorStand) mob;
        stand.getEquipment().setHelmet(vote_house);
        stand.setVisible(true);
        stand.addPassenger(mini_vote1(vote1, loc));
    }

}
