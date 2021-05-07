package com.projecki.chathelpmessager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatHelpMessageManager {

    private List<ChatHelpMessage> helpMessages = new ArrayList<>();

    public void addChatHelpMessage(ChatHelpMessage helpMessage) {
        this.helpMessages.add(helpMessage);
    }

    public Optional<ChatHelpMessage> getPossibleHelpMessage(String rawInputMessage) {

        ChatHelpMessage possibleFound = null;
        String lowerRaw = rawInputMessage.toLowerCase();

        for (ChatHelpMessage helpMessage : this.helpMessages) {
            // dont care about the ones that dont even match the required
            if (!helpMessage.matchedMustContains(lowerRaw)) {
                continue;
            }

            // if there was nothing to begin with then just set
            if (possibleFound == null) {
                possibleFound = helpMessage;
            } else {
                // in the case that they have the same amount of required words matched, check for higher optional words
                if (helpMessage.getMustContain().size() == possibleFound.getMustContain().size()) {
                    if (helpMessage.matchedCouldContains(lowerRaw) > possibleFound.matchedCouldContains(lowerRaw)) {
                        possibleFound = helpMessage;
                    }
                } else if (helpMessage.getMustContain().size() > possibleFound.getMustContain().size()) {
                    // if this one has more required words matched, use it instead
                    possibleFound = helpMessage;
                }
            }
        }

        return Optional.ofNullable(possibleFound);
    }
}
