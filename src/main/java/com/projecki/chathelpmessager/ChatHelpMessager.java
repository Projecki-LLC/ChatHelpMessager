package com.projecki.chathelpmessager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class ChatHelpMessager extends JavaPlugin {

    private static ChatHelpMessager instance;
    public static ChatHelpMessager getInstance() {
        return instance;
    }

    private ChatHelpMessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        messageManager = new ChatHelpMessageManager();
        this.loadConfigMessages(messageManager);

        getServer().getPluginManager().registerEvents(new MessageListener(), this);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    public ChatHelpMessageManager getMessageManager() {
        return messageManager;
    }

    public void loadConfigMessages(ChatHelpMessageManager manager) {
        FileConfiguration config = this.getConfig();

        ConfigurationSection messages = config.getConfigurationSection("messages");
        if (messages == null) return;

        Map<String, Object> values = messages.getValues(false);
        for (String s : values.keySet()) {
            ConfigurationSection section = messages.getConfigurationSection(s);
            if (section == null) continue; // should never happen

            List<String> mustContain = section.getStringList("must-contain");
            if (mustContain.isEmpty()) continue; // must be some required else it will fire on every message

            List<String> couldContain = section.getStringList("could-contain");
            String message = section.getString("message");
            String command = section.getString("command");

            manager.addChatHelpMessage(new ChatHelpMessage(mustContain, couldContain, message, command));
        }
    }
}
