package com.acclash.itemtest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {

    public static ItemStack getDragonFrog() {
        // Create an item that will become your custom item
        ItemStack dFrog = new ItemStack(Material.DIAMOND_HOE);
        // Item meta allows for modifying its data
        Damageable dFrogMeta = (Damageable) dFrog.getItemMeta();
        // Set damage to 1 or like how we did in the json except the actual number
        // Why can't they do it this way in the json?
        dFrogMeta.setDamage(1);
        // Make it always stay the same durability
        dFrogMeta.setUnbreakable(true);
        // Use the following line to remove the extra lore we don't need
        dFrogMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        // Name it whatever you want
        dFrogMeta.setDisplayName("My Custom Item");
        dFrog.setItemMeta(dFrogMeta);

        return dFrog;
    }

    public static ItemStack getJavelin() {
        // Create a bow that will become your javelin
        ItemStack javelin = new ItemStack(Material.BOW);
        // Item meta allows for modifying its data
        ItemMeta javelinMeta = javelin.getItemMeta();
        // Set damage to 1 or like how we did in the json except the actual number
        // Why can't they do it this way in the json?
        javelinMeta.setCustomModelData(1);
        // Make it always stay the same durability
        javelinMeta.setUnbreakable(true);
        // Use the following line to remove the extra lore we don't need
        javelinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        // Name it whatever you want
        javelinMeta.setDisplayName("My Javelin");
        javelin.setItemMeta(javelinMeta);

        return javelin;
    }

    // If you want to make damageable custom item throwable - experimental
    public static void throwDragonFrog(Player player) {

        ItemStack dFrog = player.getItemInHand();
        ItemStack throwStack = new ItemStack(dFrog);
        throwStack.setAmount(1);
        int amt = dFrog.getAmount();
        Location pLoc = player.getEyeLocation();

        Item thrownDFrog = player.getWorld().dropItem(pLoc, throwStack);
        thrownDFrog.setVelocity(pLoc.getDirection());
        dFrog.setAmount(amt - 1);
        player.setItemInHand(dFrog);
    }

    public static ItemStack getThrownJavelin() {
        // To get the thrown javelin or projectile
        ItemStack javelin = new ItemStack(Material.SNOWBALL);
        // Item meta allows for modifying its data
        ItemMeta javelinMeta = javelin.getItemMeta();
        // Set damage to 1 or like how we did in the json except the actual number
        // Why can't they do it this way in the json?
        javelinMeta.setCustomModelData(3);
        // Make it always stay the same durability
        javelinMeta.setUnbreakable(true);
        // Use the following line to remove the extra lore we don't need
        javelinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        // Name it whatever you want
        javelinMeta.setDisplayName("My Thrown Javelin");
        javelin.setItemMeta(javelinMeta);

        return javelin;
    }
}
