package com.sybsuper.SybLiveCodingTutorial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof CustomInventoryHolder)) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() == null) {
            return;
        }
        e.getWhoClicked().sendMessage(ChatColor.GREEN + "Sowwy.");
        e.getWhoClicked().teleport(e.getWhoClicked().getLocation().add(0, 169, 0));
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory inv = Bukkit.createInventory(new CustomInventoryHolder(), 5 * 9, ChatColor.RED + "Test");
                ItemStack item = new ItemStack(Material.BARRIER);
                item.addUnsafeEnchantment(Enchantment.LUCK, 1);
                inv.setItem(2 * 9 + 4, item);
                e.getPlayer().openInventory(inv);
            }
        }.runTaskLater(Main.INSTANCE, 20);
    }

    public static class CustomInventoryHolder implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
