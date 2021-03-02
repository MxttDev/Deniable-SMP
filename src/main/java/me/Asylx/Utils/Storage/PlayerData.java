package me.Asylx.Utils.Storage;

import me.Asylx.SMP;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.w3c.dom.UserDataHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData implements Listener {

    public static HashMap<UUID, Integer> PlayerBalance = new HashMap<UUID, Integer>();

    public static SMP plugin;
    static File cfile;
    static FileConfiguration Pconfig;
    static File MessageFolder;

    public PlayerData(SMP pl) {
        plugin = pl;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e) {
        createPlayerConfig(e.getPlayer());

        PlayerBalance.put(e.getPlayer().getUniqueId(), getConfig(e.getPlayer()).getInt("Balance"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        getConfig(e.getPlayer()).set("Balance", PlayerBalance.get(e.getPlayer().getUniqueId()));
        PlayerBalance.remove(e.getPlayer().getUniqueId());
    }

    public static void createPlayerConfig(Player p) {
        MessageFolder = new File(plugin.getDataFolder(), "players");
        cfile = new File(MessageFolder, p.getUniqueId().toString() + ".yml");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        if (!MessageFolder.exists()) {
            MessageFolder.mkdirs();
        }

        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
                Bukkit.getLogger().info("NEW USER CONFIG CREATED: " + p.getName());
                getConfig(p).set("Balance", 0);
                save();
            } catch (Exception var2) {
                Bukkit.getServer().getConsoleSender().sendMessage("Error creating " + cfile.getName());
            }
        }

        Pconfig = YamlConfiguration.loadConfiguration(cfile);
    }

    public static void load(Player p) {
        cfile = new File(MessageFolder, p.getUniqueId().toString() + ".yml");
        Pconfig = YamlConfiguration.loadConfiguration(cfile);
    }

    public static FileConfiguration getConfig(Player p) {
        cfile = new File(MessageFolder, p.getUniqueId().toString() + ".yml");
        Pconfig = YamlConfiguration.loadConfiguration(cfile);
        return Pconfig;
    }

    public static void save() {
        try {
            Pconfig.save(cfile);
        } catch (Exception var1) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cfile.getName() + "!");
        }

    }




}
