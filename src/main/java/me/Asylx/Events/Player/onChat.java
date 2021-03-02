package me.Asylx.Events.Player;

import me.Asylx.SMP;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {

    SMP plugin;
    private Chat chat = null;

    public onChat(SMP main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        e.setFormat(Utils.format(Utils.getPrefix(p)+p.getName()+"&7: &f"+e.getMessage()));
    }

}
