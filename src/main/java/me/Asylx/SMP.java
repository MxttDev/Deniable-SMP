package me.Asylx;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.Asylx.Events.Player.onChat;
import me.Asylx.Events.Player.onDeath;
import me.Asylx.Events.Player.onJoin;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class SMP extends JavaPlugin implements Listener, PluginMessageListener {

    public Chat chat = null;

    @Override
    public void onEnable() {
        getLogger().info("SMP IS LOADING.");

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player a : getServer().getOnlinePlayers()) {
                    updateScoreboard();
                }
            }
        }, 1, 500);

        setupEvents();

        setupChat();

        getLogger().info("SMP HAS LOADED SUCCESSFULLY.");

    }

    private void setupEvents() {
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new onJoin(),this);
        getServer().getPluginManager().registerEvents(new onChat(this,chat),this);
        getServer().getPluginManager().registerEvents(new onDeath(),this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    private void updateScoreboard() {
        for (Player a : Bukkit.getServer().getOnlinePlayers()) {
            org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("Main", "dummy", Utils.format( "&6&lDENIABLE"));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score a1 = obj.getScore(Utils.format("&e &a"));
            Score a2 = obj.getScore(Utils.format("&e▪ &8| &b&lPERSONAL"));
            Score a3 = obj.getScore(Utils.format("&8➥ &fBalance &8× &7$0"));
            Score a4 = obj.getScore(Utils.format("&8➥ &fRank &8× &7"+Utils.getRank(a)));
            Score a5 = obj.getScore(Utils.format("&8➥ &fLevel &8× &71"));
            Score a6 = obj.getScore(Utils.format("&e &b"));
            Score a7 = obj.getScore(Utils.format("&e▪ &8| &b&lSERVER"));
            Score a8 = obj.getScore(Utils.format("&8➥ &fOnline &8× &7"+Utils.getServerCount(a, "ALL")));
            Score a9 = obj.getScore(Utils.format("&e "));

            a1.setScore(9);
            a2.setScore(8);
            a3.setScore(7);
            a4.setScore(6);
            a5.setScore(5);
            a6.setScore(4);
            a7.setScore(3);
            a8.setScore(2);
            a9.setScore(1);
            a.setScoreboard(board);

        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        updateScoreboard();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        updateScoreboard();
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            Utils.count.put(server, in.readInt());

        }

    }




    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public Chat getChat() {
        return chat;
    }



}
