package com.toyblock.toyblockserver.structure.village.castle.vote;

import com.toyblock.toyblockserver.structure.village.path.PathLink;
import com.toyblock.toyblockserver.tool.LocBalance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PathInvestment {
    public static HashMap<UUID, Integer > InvestmentInv = new HashMap<UUID,Integer > () ;
    public static HashMap<UUID, Inventory > Inv = new HashMap<UUID, Inventory>() ;
    public static HashMap<Inventory, Integer > Inv_amount= new HashMap<Inventory, Integer>() ;

    public static HashMap<Inventory, Integer > OpenInv = new HashMap<Inventory, Integer>() ;
    public static HashMap<String , Player > InvestmentList = new HashMap<String, Player>() ;
    public static HashMap<Location , Inventory > InvestmentLink = new HashMap<Location, Inventory>() ;
    public static HashMap<UUID , Location > InvestmentGorundLink = new HashMap<UUID,Location>();
    public static HashMap<Inventory , UUID > InvestmentUUIDLink = new HashMap<Inventory,UUID>();


    // LivingEntity mob = (LivingEntity)world.spawnEntity(p.getLocation(), EntityType.ZOMBIE);

    ItemStack vote1 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote2 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote3 = new ItemStack(Material.SKELETON_SKULL);
    ItemStack vote_house = new ItemStack(Material.SKELETON_SKULL);

    public LivingEntity trueInvestment(ItemStack vote, Location loc) {
        LivingEntity mob = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        ArmorStand stand = (ArmorStand) mob;
        stand.getEquipment().setHelmet(vote);
        stand.setSmall(true);
        stand.setVisible(false);
        return mob;

    }


    public void Investment(Location loc, String view) {
        Location vote_loc = new Location (loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY(),loc.getBlockZ()+0.5);
        LivingEntity mob = (LivingEntity) loc.getWorld().spawnEntity(vote_loc, EntityType.ARMOR_STAND);
        ArmorStand stand = (ArmorStand) mob;
        stand.getEquipment().setHelmet(vote_house);
        stand.setVisible(true);
        InvestmentInv.put(stand.getUniqueId(),0);
    }
    public void InvestmentOpen(Location loc,String view , Player player) {
        Location balanceLoc = new LocBalance().balance(loc);
        if(PathLink.LinkSideLoc(balanceLoc,view)==null) {
            return;
        }
        Location investmentloc = PathLink.LinkSideLoc(balanceLoc,view);
        Investment(investmentloc,view);
    }

}
