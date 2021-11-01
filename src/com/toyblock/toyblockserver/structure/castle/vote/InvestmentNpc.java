package com.toyblock.toyblockserver.structure.castle.vote;

import com.toyblock.toyblockserver.structure.castle.PathLink;
import com.toyblock.toyblockserver.structure.tool.LocBalance;
import locate.tool;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvestmentNpc implements Listener {
    public void pathInvestment(ItemStack vote, Location loc) {
        Location newloc = new Location(loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY()+0.3,loc.getBlockZ()+0.5);
        LivingEntity mob = (LivingEntity) newloc.getWorld().spawnEntity(newloc, EntityType.ARMOR_STAND);
        ArmorStand stand = (ArmorStand) mob;
        stand.getEquipment().setHelmet(vote);
        stand.setSmall(true);
        stand.setVisible(false);
        stand.setCustomName("투자");
        stand.setCustomNameVisible(true);
        stand.setCanMove(false);
        stand.setCanPickupItems(false);
        PathInvestmentInventory inv = new PathInvestmentInventory();
        inv.createInv(stand.getUniqueId());
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
        stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
    }
    @EventHandler
    public void spawnInvestment (PlayerInteractEvent event) {

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        event.getPlayer().chat("투자 실행중");
        Player player = event.getPlayer();

        List castleBuildLore = new ArrayList();
        castleBuildLore.add(0, "투자 건설");

        List checkLore = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if(checkLore==null) {
            return;
        }
        if (castleBuildLore.get(0).equals(checkLore.get(0))) {

            event.getPlayer().chat("생성가능여부확인시작..");

            String view = tool.getDirection(player);


            Location point = event.getPlayer().getTargetBlock(100).getLocation();
            Location loc = new LocBalance().balance(point);

            if (!PathLink.LinkCheck(loc,player,view)) {
                return;
            }
            Location sideloc = PathLink.LinkSideLoc(loc,view);
            VoteItem item = new VoteItem();

            if(sideloc==null) {
                return;
            }

            pathInvestment(item.pathBlock_Level1(),sideloc);
        }
    }
    public static void openInv (Player player , UUID UUID) {
        player.openInventory(PathInvestment.Inv.get(UUID));
    }
    @EventHandler
    public void investmentOpen (PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if(!PathInvestment.Inv.containsKey(event.getRightClicked().getUniqueId())){
            return;
        }
        player.chat("실행으노디고있음");
        if (!event.getRightClicked().getCustomName().equals("투자")) {
            player.chat("노투자");
            return;
        }
        openInv(player,event.getRightClicked().getUniqueId());
    }

}