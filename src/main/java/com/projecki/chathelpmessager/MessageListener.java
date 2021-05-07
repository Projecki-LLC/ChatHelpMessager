package com.projecki.chathelpmessager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class MessageListener implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        ChatHelpMessageManager manager = ChatHelpMessager.getInstance().getMessageManager();

        if (event.getRecipients().isEmpty()) {
            return;
        }

        Optional<ChatHelpMessage> possibleHelpMessage = manager.getPossibleHelpMessage(msg);
        new BukkitRunnable() {
            @Override
            public void run() {
                possibleHelpMessage.ifPresent(chatHelpMessage -> chatHelpMessage.send(event.getPlayer()));
            }
        }.runTaskLater(ChatHelpMessager.getInstance(), 15L);
    }
}
