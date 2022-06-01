package ga.juriantech.gamemodecommandspigot;

import co.aikar.commands.BukkitCommandManager;
import ga.juriantech.gamemodecommandspigot.commands.GameMode;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class GamemodeCommandSpigot extends JavaPlugin {

    public Logger logger = Bukkit.getLogger();
    GamemodeCommandSpigot plugin;
    private static GamemodeCommandSpigot instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.plugin = getInstance();
        commands();
        config();
        logger.warning("GamemodeCommand has been started!");

    }
    private void config() {
        plugin.saveDefaultConfig();
    }

    private void commands() {
        BukkitCommandManager manager = new BukkitCommandManager(plugin);
        manager.registerCommand(new GameMode());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.warning("GamemodeCommand has stopped.");
    }

    public static GamemodeCommandSpigot getInstance() {
        return instance;
    }
}
