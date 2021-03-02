package me.Asylx.Events.Player;

import me.Asylx.SMP;
import me.Asylx.Utils.Utils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class onJoin implements Listener {

    SMP plugin;
    private Chat chat = null;

    public onJoin(SMP main, Chat chat) {
        this.plugin = main;
        this.chat = chat;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.setPlayerListName(Utils.format(Utils.getPrefix(p)+p.getName()));

        if (!p.hasPlayedBefore()) {
            e.setJoinMessage(Utils.format("&7Please welcome &e"+e.getPlayer().getName()+" &7to the SMP!"));
            loadKitStarter(p);
            return;
        } else {
            e.setJoinMessage(Utils.format("&7" + Utils.getPrefix(p) + p.getName() + "&b has joined SMP!"));
        }
    }

    private void loadKitStarter(Player p) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ItemStack stonePickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemStack stoneAxe = new ItemStack(Material.STONE_AXE);
        ItemStack stoneShovel = new ItemStack(Material.STONE_SHOVEL);

        ItemStack Food = new ItemStack(Material.COOKED_PORKCHOP, 32);

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setBoots(boots);

        p.getInventory().setItem(0, stoneSword);
        p.getInventory().setItem(1, stonePickaxe);
        p.getInventory().setItem(2, stoneAxe);
        p.getInventory().setItem(3, stoneShovel);
        p.getInventory().setItem(4, Food);

    }



}
