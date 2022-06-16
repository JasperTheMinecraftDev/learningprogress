package ga.juriantech.freezepluginspigot.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
