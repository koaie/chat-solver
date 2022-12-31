package net.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;

public class Msg {
    final MinecraftClient client = MinecraftClient.getInstance();

    public String send(String text) {
		ChatScreen cs = new ChatScreen("");

		this.client.setScreen(cs); // set and open chat box
		cs.sendMessage(text, true); // Send message
		this.client.setScreen(null); // Close screen
		return text;
	}
}
