package ga.juriantech.gamemodecommandspigot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import ga.juriantech.gamemodecommandspigot.GamemodeCommandSpigot;
import ga.juriantech.gamemodecommandspigot.Utils.ChatUtils;
import org.bukkit.entity.Player;

@CommandAlias("gamemodes")
public class GameMode extends BaseCommand {

    GamemodeCommandSpigot plugin;

    @Subcommand("creative")
    @CommandCompletion("creative")
    public void creative(Player player) {
        this.plugin = GamemodeCommandSpigot.getInstance();
        checkperm(player);
        player.setGameMode(org.bukkit.GameMode.CREATIVE);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Creative")));
    }

    @Subcommand("survival")
    @CommandCompletion("survival")
    public void survival(Player player) {
        checkperm(player);
        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Survival")));

    }

    @Subcommand("spectator")
    @CommandCompletion("spectator")
    public void spectator(Player player) {
        checkperm(player);
        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Spectator")));

    }

    @Subcommand("adventure")
    @CommandCompletion("adventure")
    public void adventure(Player player) {
        checkperm(player);
        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
        player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.gamemode-changed").replace("{gamemode}", "Adventure")));

    }

    public void checkperm (Player player) {
        if (!player.hasPermission("gamemodes.use"))
            player.sendMessage(ChatUtils.colorize(plugin.getConfig().getString("messages.no-permission")));
        return;
    }
}
