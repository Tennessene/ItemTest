package com.acclash.itemtest.commands;

import com.acclash.itemtest.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiveItem implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                // Fancy tab complete stuff
                List<String> argsList = new ArrayList<>(Arrays.asList(args));
                StringBuilder item = new StringBuilder();
                for (String s : argsList) {
                    if (argsList.get(argsList.size() - 1).equals(s)) {
                        item.append(s);
                    } else {
                        item.append(s).append(" ");
                    }
                }
                if (item.toString().equals("Dragon Frog")) {

                    player.getInventory().addItem(CustomItems.getDragonFrog());

                } else if (item.toString().equals("Javelin")) {

                    player.getInventory().addItem(CustomItems.getJavelin());

                // item would be "Thrown Javelin"
                } else {

                    player.getInventory().addItem(CustomItems.getThrownJavelin());

                }
            }
        }
        return true;
    }

    // Fancy tab complete stuff
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> items = new ArrayList<>();
            items.add("Dragon Frog");
            items.add("Javelin");
            items.add("Thrown Javelin");

            return items;
        }
        return null;
    }
}
