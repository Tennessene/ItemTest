package com.acclash.itemtest.listeners;

import com.acclash.itemtest.CustomItems;
import com.acclash.itemtest.ItemTest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ThrowListener implements Listener {

    public static long lastMainHandClick;

    public static boolean mHandHasFullAutoTask;

    public static int mid;

    // Part of making damageable custom item throwable - experimental
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            ItemStack item = player.getItemInHand();
            if (e.getItem() == null) return;
            if (!e.getItem().getItemMeta().isUnbreakable()) return;
            if (e.getItem().getType() != Material.DIAMOND_HOE) return;
            if (((Damageable) item.getItemMeta()).getDamage() != 1) return;
            lastMainHandClick = player.getWorld().getGameTime();
            if (mHandHasFullAutoTask) return;
            mid = Bukkit.getScheduler().scheduleSyncRepeatingTask(ItemTest.getPlugin(), () -> {
                if (player.getWorld().getGameTime() - lastMainHandClick > 3) {
                    Bukkit.getScheduler().cancelTask(mid);
                    CustomItems.throwDragonFrog(player);
                    mHandHasFullAutoTask = false;
                } else {
                    mHandHasFullAutoTask = true;
                }
            }, 0, 1);
        }
    }

    // For javelin
    @EventHandler
    public void onJavelinThrow(EntityShootBowEvent e) {
        System.out.println(e.getProjectile().getVelocity());
        if (e.getBow().getItemMeta().getCustomModelData() == 1) {
            System.out.println(e.getConsumable());
            System.out.println(e.getProjectile());
            System.out.println(e.shouldConsumeItem());
            //e.getProjectile().setVisibleByDefault(false);
            //Snowball snowball = (Snowball) e.getProjectile().getWorld().spawnEntity(e.getProjectile().getLocation(), EntityType.SNOWBALL);
            //snowball.setItem(CustomItems.getJavelin());
            ArmorStand snowball = (ArmorStand) e.getProjectile().getWorld().spawnEntity(e.getProjectile().getLocation(), EntityType.ARMOR_STAND);
            snowball.getEquipment().setItemInMainHand(CustomItems.getJavelin());
            //e.setProjectile(snowball);
            e.getProjectile().addPassenger(snowball);
            e.getProjectile().getPassengers().get(0).getBoundingBox();
        }
    }

    @EventHandler
    public void onJavelinnThrow(ProjectileLaunchEvent e) {
        System.out.println(e.getEntity().getVelocity());
        Projectile snowball = e.getEntity();
        //
        //snowball.setInvulnerable(true);
        System.out.println(e.getEntity().getShooter());
       // System.out.println(e.getEntity().getPersistentDataContainer()..toString());
        System.out.println(e.getEntity().getMetadata("item").toString());
    }

    @EventHandler
    public void onJavelinnThrodw(ProjectileHitEvent e) {
        System.out.println("Hit " + e.getEntity());
    }
}

