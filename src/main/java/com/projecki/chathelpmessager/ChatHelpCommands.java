package com.projecki.chathelpmessager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatHelpCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && !sender.isOp()) {
            return true;
        }

        if (!command.getName().equalsIgnoreCase("chm")) {
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            ChatHelpMessager.getInstance().getMessageManager().reload();
            sender.sendMessage(ChatColor.GREEN + "Reloaded ChatHelpMessages config successfully");
            return true;
        }

        return true;
    }
}
