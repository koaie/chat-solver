package net.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;

public class Msg {
    private final MinecraftClient client = MinecraftClient.getInstance();
	private ChatScreen cs = new ChatScreen("");

    public String send(String text) {
		this.client.setScreen(cs); // set and open chat box
		this.cs.sendMessage(text, true); // Send message
		this.client.setScreen(null); // Close screen
		return text;
	}
}
