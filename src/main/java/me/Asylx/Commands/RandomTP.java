package me.Asylx.Commands;

import me.Asylx.SMP;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RandomTP implements CommandExecutor {

    SMP plugin;
    private Chat chat = null;

    public RandomTP(SMP main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    private final Map<UUID, Integer> cooldowns = new HashMap<>();
    public int CooldownTime = 20; //SECONDS

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        Random rand = new Random();

        int rangeMin = -15000;
        int rangeMax = 15000;

        int X = rand.nextInt((rangeMax - rangeMin) + 1) + rangeMin;
        int Z = rand.nextInt((rangeMax - rangeMin) + 1) + rangeMin;
        int Y = p.getWorld().getHighestBlockYAt(X, Z);

        Location TPLoc = new Location(p.getWorld(), X, Y+1, Z).add(0.5, 0, 0.5);

        int timeLeft = getCooldown(p.getUniqueId());
        if (timeLeft == 0) {
            p.teleport(TPLoc);

            p.sendMessage(Utils.format("&7Teleported you to a random location."));
            p.sendMessage(Utils.format("  &c▪ &eX: "+TPLoc.getBlockX()));
            p.sendMessage(Utils.format("  &c▪ &eY: "+TPLoc.getBlockY()));
            p.sendMessage(Utils.format("  &c▪ &eZ: "+TPLoc.getBlockZ()));

            setCooldown(p.getUniqueId(), CooldownTime);

            new BukkitRunnable() {
                @Override
                public void run() {
                    int timeLeft = getCooldown(p.getUniqueId());
                    setCooldown(p.getUniqueId(), --timeLeft);
                    if(timeLeft == 0){
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 20, 20);

        } else {
            p.sendMessage(Utils.format("&7You have &e"+timeLeft+" &7seconds until you can teleport again."));
        }

        return false;
    }

    public void setCooldown(UUID player, int time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player,time);
        }
    }

    public int getCooldown(UUID player) {
        return cooldowns.getOrDefault(player, 0);
    }

}
