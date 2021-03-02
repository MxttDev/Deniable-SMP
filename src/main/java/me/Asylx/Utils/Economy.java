package me.Asylx.Utils;

import me.Asylx.SMP;
import me.Asylx.Utils.Storage.PlayerData;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Economy {

    public static long getBalance(OfflinePlayer offlinePlayer) {
        long balance;
        balance = PlayerData.PlayerBalance.get(offlinePlayer.getPlayer().getUniqueId());

        return balance;
    }

    public void GiveMoney(OfflinePlayer offlinePlayer, int amount) {
        int old = PlayerData.PlayerBalance.get(offlinePlayer.getPlayer().getUniqueId());
        int New = old+amount;

        PlayerData.PlayerBalance.put(offlinePlayer.getPlayer().getUniqueId(), New);
    }

    public void RemoveMoney(OfflinePlayer offlinePlayer, int amount) {
        int old = PlayerData.PlayerBalance.get(offlinePlayer.getPlayer().getUniqueId());
        int New = old-amount;

        PlayerData.PlayerBalance.put(offlinePlayer.getPlayer().getUniqueId(), New);
    }

}
