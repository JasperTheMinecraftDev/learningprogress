package ga.juriantech.freezepluginspigot.events;

import ga.juriantech.freezepluginspigot.FreezePluginSpigot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {

    FreezePluginSpigot plugin;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        this.plugin = FreezePluginSpigot.getInstance();
        Player player = event.getPlayer();

        if (plugin.getDataFile().getBoolean(player.getUniqueId() + ".frozen")) {
            event.setCancelled(true);

        }
    }
}
