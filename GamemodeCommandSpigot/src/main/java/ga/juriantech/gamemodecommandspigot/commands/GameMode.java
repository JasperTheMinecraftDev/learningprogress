package ga.juriantech.gamemodecommandspigot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import ga.juriantech.gamemodecommandspigot.GamemodeCommandSpigot;
import ga.juriantech.gamemodecommandspigot.Utils.ChatUtils;
import org.bukkit.entity.Player;

@CommandAlias("gamemodes")
public class GameMode extends BaseCommand {

    GamemodeCommandSpigot plugin;

    @Subcommand("creative")
    @CommandCompletion("creative")
    @CommandPermission("gamemodes.use")
    public void creative(Player player) {
        this.plugin = GamemodeCommandSpigot.getInstance();
        player.setGameMode(org.bukkit.GameMode.CREATIVE);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Creative")));
    }

    @Subcommand("survival")
    @CommandCompletion("survival")
    @CommandPermission("gamemodes.use")
    public void survival(Player player) {
        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Survival")));

    }

    @Subcommand("spectator")
    @CommandCompletion("spectator")
    @CommandPermission("gamemodes.use")
    public void spectator(Player player) {
        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Spectator")));

    }

    @Subcommand("adventure")
    @CommandCompletion("adventure")
    @CommandPermission("gamemodes.use")
    public void adventure(Player player) {
        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Adventure")));

    }
}
