package ga.juriantech.freezepluginspigot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import ga.juriantech.freezepluginspigot.FreezePluginSpigot;
import ga.juriantech.freezepluginspigot.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

@CommandAlias("freeze")
public class Freeze extends BaseCommand {
    private final FreezePluginSpigot plugin = FreezePluginSpigot.getInstance();

    @Subcommand("on")
    @CommandCompletion("@players")
    @CommandPermission("freeze.use")

    public void oncommand(Player player, String[] args) {

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (!plugin.getDataFile().getBoolean(target.getUniqueId() + ".bypassmode-active")) {
                if (!plugin.getDataFile().getBoolean(target.getUniqueId() + ".frozen")) {
                    plugin.getDataFile().set(target.getUniqueId() + ".frozen", true);
                    try {
                        plugin.getDataFile().save();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.freeze-activated-on-player")));
                    target.sendMessage(plugin.getMessagesFile().getString("player-frozen"));
                    target.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-frozen")));
                } else {
                    player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-already-frozen")));
                }
        } else {
                player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.immune-player")));
            }

        } else {
            player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.specify-arguments")));
        }
    }

    @Subcommand("off")
    @CommandCompletion("@players")
    @CommandPermission("freeze.use")

    public void offcommand(Player player, String[] args) {

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (plugin.getDataFile().getBoolean(target.getUniqueId() + ".frozen")) {
                plugin.getDataFile().set(target.getUniqueId() + ".frozen", false);
                try {
                    plugin.getDataFile().save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.freeze-removed-from-player")));
                target.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-unfrozen")));
            } else {
                player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-not-frozen")));
            }
        } else {
            player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.specify-arguments")));
        }
    }

    @Subcommand("getitems")
    @CommandPermission("freeze.use")

    public void getitems(Player player) {
        ItemStack freezeitem = new ItemStack(Material.ICE, 1);
        ItemStack warmupitem = new ItemStack(Material.FLINT_AND_STEEL, 1);
        ItemMeta freezeitemmeta = freezeitem.getItemMeta();
        ItemMeta warmupitemmeta = warmupitem.getItemMeta();
        freezeitemmeta.setDisplayName(ChatUtils.colorize(plugin.getMessagesFile().getString("items.ice")));
        warmupitemmeta.setDisplayName(ChatUtils.colorize(plugin.getMessagesFile().getString("items.flint_and_steel")));
        freezeitem.setItemMeta(freezeitemmeta);
        warmupitem.setItemMeta(warmupitemmeta);
        player.getInventory().addItem(freezeitem);
        player.getInventory().addItem(warmupitem);
    }

    @Subcommand("reload")
    @CommandPermission("freeze.reload")

    public void reload(Player player) {
        try {
            plugin.getDataFile().save();
            player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.messagesfile-reloaded")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("togglebypass")
    @CommandPermission("freeze.togglebypass")

    public void togglebypass(Player player) {
        if (player instanceof Player) {
            if (!plugin.getDataFile().getBoolean(player.getUniqueId() + ".bypassmode-active")) {
                plugin.getDataFile().set(player.getUniqueId() + ".bypassmode-active", true);
                player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.bypassmode-enabled")));
            } else {
                plugin.getDataFile().set(player.getUniqueId() + ".bypassmode-active", false);
                player.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.bypassmode-disabled")));
            }
        }
    }
}
