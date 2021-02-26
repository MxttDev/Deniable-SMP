package me.Asylx.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.Asylx.SMP;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Utils {

    public static HashMap<String, Integer> count = new HashMap<String, Integer>();
    public static SMP plugin = SMP.getPlugin(SMP.class);
    public static Chat chat = plugin.getChat();

    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String getRank(Player p) {
        String rank;

        if (chat.getPlayerPrefix(p).equals("") || chat.getPlayerPrefix(p).equals(null)) {
            rank = "default";
        } else {
            rank = chat.getPlayerPrefix(p);
        }

        return rank;
    }

    public static void getCount(Player player, String server) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public static int getServerCount(Player p, String server) {
        getCount(p, server);
        if (Utils.count.get(server) != null) {
            return Utils.count.get(server);
        }
        return 0;
    }

    public static String getPrefix(Player p) {
        String prefix;
        if (chat.getPrimaryGroup(p.getPlayer()).equals("default")|| chat.getPlayerPrefix(p).equals(null)) {
            prefix = "&7";
        } else {
            prefix = chat.getPlayerPrefix(p);
        }

        return prefix;
    }


}
