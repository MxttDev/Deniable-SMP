package me.Asylx.Commands;

import me.Asylx.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Tpa implements CommandExecutor {

    private HashMap<UUID, UUID> PlayerTeleport = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                p.sendMessage(Utils.format("&cPlayer not found!"));
                return false;
            }
            if (args[0].equalsIgnoreCase(target.getName())) {
                p.sendMessage(Utils.format("&7TPA Request sent to &e"+target.getName()));

                PlayerTeleport.put(p.getUniqueId(), target.getUniqueId()); // PUTS OLD PLAYER AND NEW PLAYER IN HASHMAP

                target.sendMessage(Utils.format("&7TPA Request from &e"+p.getName()));
            }




        } else {
            p.sendMessage(Utils.format("&cIncorrect Usage: /tpa (player)"));
        }


        return false;
    }
}
