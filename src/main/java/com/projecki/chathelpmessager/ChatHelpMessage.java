package com.projecki.chathelpmessager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ChatHelpMessage {

    private final List<String> mustContain;
    private final List<String> couldContain;
    private final String message;

    public ChatHelpMessage(List<String> mustContain, List<String> couldContain, String message) {
        this.mustContain = mustContain;
        this.couldContain = couldContain;
        this.message = message;
    }

    public List<String> getMustContain() {
        return mustContain;
    }

    public List<String> getCouldContain() {
        return couldContain;
    }

    public boolean matchedMustContains(String rawInput) {
        String rawLower = rawInput.toLowerCase();
        for (String s : this.getMustContain()) {
            if (!rawLower.contains(s.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public int matchedCouldContains(String rawInput) {
        String rawLower = rawInput.toLowerCase();
        int count = 0;

        for (String s : this.getCouldContain()) {
            if (rawLower.contains(s.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public void send(Player player) {
        if (player.isOnline()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.message));
        }
    }
}
