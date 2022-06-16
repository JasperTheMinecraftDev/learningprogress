package ga.juriantech.freezepluginspigot.events;

import ga.juriantech.freezepluginspigot.FreezePluginSpigot;
import ga.juriantech.freezepluginspigot.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;


public class OnEntityDamageByEntityEvent implements Listener {

    FreezePluginSpigot plugin;

    @EventHandler
    public void OnEntityDamageByEntityEvent(EntityDamageByEntityEvent event) throws IOException {

        this.plugin = FreezePluginSpigot.getInstance();

        if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
            Player damager = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();
            for (ItemStack freezeitem : damager.getInventory().getContents()) {
                if (freezeitem == null) continue;
                if (freezeitem.getType().equals(Material.ICE)) {
                    if (freezeitem.getItemMeta().getDisplayName().equals(plugin.getMessagesFile().getString("items.ice"))) {
                        if (!plugin.getDataFile().getBoolean(victim.getUniqueId() + ".bypassmode-active")) {
                            if (!plugin.getDataFile().getBoolean(victim.getUniqueId() + ".frozen")) {
                                plugin.getDataFile().set(victim.getUniqueId() + ".frozen", true);
                                plugin.getDataFile().save();
                                damager.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.freeze-activated-on-player")));
                                victim.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-frozen")));
                            } else {
                                for (ItemStack warmupitem : damager.getInventory().getContents()) {
                                    if (warmupitem == null) continue;
                                    if (warmupitem.getType().equals(Material.FLINT_AND_STEEL)) {
                                        if (warmupitem.getItemMeta().getDisplayName().equals(plugin.getMessagesFile().getString("items.flint_and_steel"))) {
                                            if (plugin.getDataFile().getBoolean(victim.getUniqueId() + ".frozen")) {
                                                plugin.getDataFile().set(victim.getUniqueId() + ".frozen", false);
                                                plugin.getDataFile().save();
                                                damager.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.freeze-removed-from-player")));
                                                victim.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.player-unfrozen")));
                                            } else {
                                                damager.sendMessage(ChatUtils.colorize(plugin.getMessagesFile().getString("messages.immune-player")));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
