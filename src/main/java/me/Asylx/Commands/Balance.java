package me.Asylx.Commands;

import me.Asylx.SMP;
import me.Asylx.Utils.Storage.PlayerData;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Balance implements CommandExecutor {
    SMP plugin;
    private Chat chat = null;

    public Balance(SMP main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        String format = "&7Balance: &e$"+ PlayerData.PlayerBalance.get(p.getUniqueId());
        p.sendMessage(Utils.format(format));
        return false;
    }
}
