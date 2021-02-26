package me.Asylx.Events.Player;

import me.Asylx.SMP;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class onDeath implements Listener {
    SMP plugin;
    private Chat chat = null;

    public onDeath(SMP main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();

        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        World world = p.getWorld();
        Location loc = new Location(world,x,y,z);
        loc.getBlock().setType(Material.CHEST);

        ItemStack[] drops = toItemStack(e.getDrops());
        String DropsString = Arrays.toString(drops);

        Chest c = (Chest) loc.getBlock().getState();
        c.getBlockInventory().setContents(drops);

        int countdown;
        countdown = 10;

        p.sendMessage(Utils.format("&3You have &bDIED!"));
        p.sendMessage(Utils.format("&3 "));
        p.sendMessage(Utils.format("&7Your death location was: &b"+x+" "+y+" "+z));
        p.sendMessage(Utils.format("&3 "));
        p.sendMessage(Utils.format("&3You have "+countdown+" minutes until it despawns!"));

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                p.sendMessage(Utils.format("&cYour death-chest has been removed."));
                c.getBlockInventory().clear();
                loc.getBlock().setType(Material.AIR);
            }
        }, 20 * 60 * countdown);


    }

    public ItemStack[] toItemStack(List<ItemStack> list) {
        ItemStack[] items = new ItemStack[list.size()];
        int index = 0;

        for (ItemStack i : list) {
            items[index] = i;
            index++;
        }
        return items;
    }


}
