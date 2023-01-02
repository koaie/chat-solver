package net.chat;

import net.minecraft.client.gui.screen.ChatScreen;

public class Msg {
	private ChatScreen cs = new ChatScreen("");
	private String text;

	public String setText(String text) {
		this.text = text;
		return text;
	}

	public String getText() {
		return this.text;
	}

	public boolean setTimer(long ticksUntilEvent) {
		Chat.log.info("Message scheduled at {} with {} ticks", System.currentTimeMillis(), ticksUntilEvent);
		((MsgTimerAccess) Chat.client).setMsgTimer(ticksUntilEvent);
		return false;
	}

	public String send() {
		Chat.log.info("Message sent at {}", System.currentTimeMillis());
		Chat.client.setScreen(cs); // set and open chat box
		this.cs.sendMessage(this.text, true); // Send message
		Chat.client.setScreen(null); // Close screen
		return text;
	}
}
