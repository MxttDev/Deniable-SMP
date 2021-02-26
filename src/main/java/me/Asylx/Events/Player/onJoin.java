package me.Asylx.Events.Player;

import me.Asylx.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(Utils.format("&7" + Utils.getPrefix(p) + p.getName() + "&b has joined SMP!"));


    }



}
