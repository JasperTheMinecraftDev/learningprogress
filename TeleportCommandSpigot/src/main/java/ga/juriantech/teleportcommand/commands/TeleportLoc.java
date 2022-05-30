package ga.juriantech.teleportcommand.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import ga.juriantech.teleportcommand.Teleportcommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportLoc extends BaseCommand {
    Teleportcommand plugin;

    @CommandAlias("teleportloc")
    @CommandCompletion("teleportloc")


    public void Teleport(Player player) {
        if (player instanceof Player) {
            this.plugin = Teleportcommand.getInstance();
            player.sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("messages.teleporting"));
            Location location = new Location(player.getWorld(), plugin.getConfig().getInt("location.x"), plugin.getConfig().getInt("location.y"), plugin.getConfig().getInt("location.z"));
            player.teleport(location);
            player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.teleported").replace("{cords}",  location.getX() + " " + location.getY() + " " + location.getZ()));

        }
    }
}
