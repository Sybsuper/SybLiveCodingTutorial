package com.sybsuper.SybLiveCodingTutorial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    Effect effect;
    List<Player> playersThatHaveHeartsEnabled = new ArrayList<>();// li = []

    @Override
    public void onEnable() {
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        try {
            effect = Effect.valueOf(config.getString("particle").replace(" ", "_").toUpperCase());
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Incorrect effect value defined in config: '" + config.getString("particle") + "'");
        }
        Bukkit.getScheduler().runTaskTimer(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (effect == null) {
                    return;
                }
                for (Player player : playersThatHaveHeartsEnabled) {
                    Location location = player.getEyeLocation().add(0, 1, 0);
                    player.playEffect(location, effect, 2);
                }
            }
        }, 2, 2);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {// if the sender is not a player
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }
        Player player = (Player) sender;
        if (playersThatHaveHeartsEnabled.contains(player)) {
            sender.sendMessage("We will now disable hearts!");
            playersThatHaveHeartsEnabled.remove(player);
        } else {
            sender.sendMessage("We will now enable hearts!");
            playersThatHaveHeartsEnabled.add(player);
        }
        return true;
    }
}
