package net.chat;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

public class Chat implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final org.slf4j.Logger log = LoggerFactory.getLogger("chat-solver");
	public static final Msg msg = new Msg();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		log.info("Hello Fabric world!");
	}
}
