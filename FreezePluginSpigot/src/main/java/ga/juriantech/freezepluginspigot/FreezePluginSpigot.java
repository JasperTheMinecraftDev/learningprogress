package ga.juriantech.freezepluginspigot;

import co.aikar.commands.BukkitCommandManager;
import dev.dejvokep.boostedyaml.YamlDocument;
import ga.juriantech.freezepluginspigot.commands.Freeze;
import ga.juriantech.freezepluginspigot.events.OnEntityDamageByEntityEvent;
import ga.juriantech.freezepluginspigot.events.OnPlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class FreezePluginSpigot extends JavaPlugin {

    public Logger logger = Bukkit.getLogger();
    FreezePluginSpigot plugin;
    private static FreezePluginSpigot instance;

    private YamlDocument messagesfile, datafile;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.plugin = getInstance();
        commands();
        config();
        events();
        logger.warning("FreezePlugin has been started!");

    }


    private void config() {
        try {
            YamlDocument messagesfile = YamlDocument.create(new File(getDataFolder(), "messages.yml"), getResource("messages.yml"));
            this.messagesfile = messagesfile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            YamlDocument datafile = YamlDocument.create(new File(getDataFolder(), "data.yml"), getResource("data.yml"));
            this.datafile = datafile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void commands() {
        BukkitCommandManager manager = new BukkitCommandManager(plugin);
        manager.registerCommand(new Freeze());
    }

    private void events() {
        Bukkit.getPluginManager().registerEvents(new OnPlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new OnEntityDamageByEntityEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.warning("FreezePlugin has stopped.");
    }

    public static FreezePluginSpigot getInstance() {
        return instance;
    }

    public YamlDocument getMessagesFile() {
        return messagesfile;
    }

    public YamlDocument getDataFile() {
        return datafile;
    }

}