package com.toyblock.toyblockserver.structure.castle.investment;

import com.toyblock.toyblockserver.structure.castle.Castle_Path;
import com.toyblock.toyblockserver.structure.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.castle.vote.PathInvestment;
import com.toyblock.toyblockserver.structure.castle.vote.VoteItem;
import net.minecraft.world.inventory.InventoryClickType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryClick implements Listener {
    Player player = Bukkit.getPlayer("Devil");
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack emerald = new ItemStack(Material.EMERALD);
        if (!PathInvestment.Inv_amount.containsKey(event.getInventory())) {
            player.chat("미등록");
            return;
        }
        if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            event.setCancelled(true);
            player.chat("노플레이어");
            return;
        }
        if (event.getRawSlot() == 37) {
            event.setCancelled(true);
            player.chat("실행 딱 직전");

            if(!addCountTest(inv,1)) {
                player.chat("어디야");
                return;
            }
            if(!(event.getInventory().getItem(41) == null)) {
                if (event.getInventory().getItem(41).getAmount() == 64) {
                    player.chat("도대체");
                    return;
                }
            }
            player.chat(" 오잉 ㅇ?");
            removeItem_LageChest(player, emerald, 1);
            addInvestment(inv);
            player.chat("성공 한 거니?");
        }
        if (event.getRawSlot() == 50) {
            event.setCancelled(true);
            int pay =InvestmentPay(inv);
            returnInvestment(player,pay);
            inv.clear(41);
        }
        if (event.getRawSlot() == 32) {
            event.setCancelled(true);
            if(inv.getItem(41).equals(null)) {
                return;
            }
            int investment = inv.getItem(41).getAmount();
            giveInvestment(player,inv,investment);
            inv.clear(41);
            if(remnantCount(inv)==0) {
                UUID UUID = PathInvestment.InvestmentUUIDLink.get(inv);
                Location loc = PathInvestment.InvestmentGorundLink.get(UUID);
                Location make = loc.getWorld().getHighestBlockAt(loc).getLocation();
                Castle_Path path = new Castle_Path(make);
                path.build();
            }

        }
    }
    public void giveInvestment (Player player,Inventory inv , int investment) {
        VoteItem item = new VoteItem();
        int nomber = 20;
        for(int i = 0; i < investment;i++) {
            if(inv.getItem(nomber) == null) {
                inv.setItem(nomber,item.investment());
                continue;
            }
            if(inv.getItem(nomber).getAmount()< 64) {
                inv.getItem(nomber).setAmount(inv.getItem(nomber).getAmount()+1);
            }
            else {
                nomber++;
                if(inv.getItem(nomber) == null) {
                    inv.setItem(nomber,item.investment());
                }
                else {
                    inv.getItem(nomber).setAmount(inv.getItem(nomber).getAmount() + 1);
                }
            }
        }
    }
    public void addInvestmentList(Inventory inv,Player player) {
        for(int i = 1;i <= 384 ; i++) {
            if(PathInvestment.InvestmentList.containsKey(inv+"and"+i)) {
                continue;
            }
            PathInvestment.InvestmentList.put(inv+"i",player);
            return;
        }
    }
    public void returnInvestmentList(Inventory inv) {
        for(int i = 1;i <= 384 ; i++) {
            if(PathInvestment.InvestmentList.containsKey(inv+"and"+i)) {

            }
            else {
                return;
            }
        }
    }
    public int goalCount(Inventory inv) {
        int count = 0;
        for (int i = 11;i <= 16; i++) {
            if (inv.getItem(i) == null ) {
                return count;
            }
            count = count+inv.getItem(i).getAmount();
        }
        return count;
    }
    public int nowCount(Inventory inv) {
        int count = 0;
        if(!(inv.getItem(41) == null)) {
            count = count+inv.getItem(41).getAmount();
        }
        for (int i = 20;i <= 25; i++) {
            if (inv.getItem(i) == null ) {
                return count;
            }
            count = count+inv.getItem(i).getAmount();
        }
        return count;
    }
    public int remnantCount(Inventory inv) {
        int count = goalCount(inv) - nowCount(inv);
        return count;
    }
    public boolean addCountTest (Inventory inv,int pay) {
        int remnant = remnantCount(inv);
        if(remnant >= pay) {
            Bukkit.getPlayer("Devil").chat("남은수"+remnant+"페이"+pay);
            return true;
        }
        return false;
    }

    public void removeItem_LageChest(Player player ,ItemStack find_item,int find_amount) {
        int amount;
        Inventory inv = player.getInventory();
        ArrayList<Integer> slot = new ArrayList<>();
        Material find_mate = find_item.getType();
        for (int i = 0;i<35;i++) {
            ItemStack item = inv.getItem(i);
            if(item == null) {
                player.chat("!");
                continue;
            }
            else if(!item.getType().equals(find_item.getType())) {
                player.chat("2");
                continue;

            }
            player.chat("3에메");
            amount = item.getAmount();


            if(!(amount >= find_amount)) {
                slot.add(i);

                player.chat("3");
                continue;
            }
            if(slot.isEmpty()) {

                inv.getItem(i).setAmount(amount-find_amount);
                player.chat("4");
                return ;
            }
            for (int ii : slot) {
                inv.clear(ii);
                player.chat("5");
            }

            if(find_amount-amount == 0) {
                inv.clear(i);
                player.chat("6");
                return ;

            }
            else if(find_amount-amount > 0) {
                inv.getItem(i).setAmount(amount-find_amount);
                player.chat("7");
                return ;
            }


        }

    }

    public void addInvestment(Inventory inv) {
        VoteItem item = new VoteItem();
        int box;
        player.chat("머지??");
        if(inv.getItem(41) == null) {
            player.chat("머지??왜안댐?");
            inv.setItem(41,item.investment_put());
            return;
        }
        box = inv.getItem(41).getAmount();
        if(!(inv.getItem(41) == null)) {
            player.chat("오잉?");
            inv.getItem(41).setAmount(box+1);
        }
        return;
    }
    public int InvestmentPay (Inventory inv ) {
        int pay = inv.getItem(41).getAmount();
        return pay;
    }
    public void returnInvestment (Player player , int pay) {
        ItemStack emerald = new ItemStack(Material.EMERALD,pay);
        player.getInventory().addItem(emerald);
    }

}
