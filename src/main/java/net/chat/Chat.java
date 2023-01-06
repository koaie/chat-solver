package net.chat;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class Chat implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final org.slf4j.Logger log = LoggerFactory.getLogger("chat-solver");
	public static final MinecraftClient client = MinecraftClient.getInstance();
	public static ServerInfo server;
	public static final Solver solver = new Solver();
	public static final Msg msg = new Msg();

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (!handler.getConnection().isLocal()) {
				Chat.server = Chat.client.getCurrentServerEntry();
				log.info("logged in to {} with ping {}", Chat.server.address, Chat.server.ping);
				Chat.solver.setLag(Chat.server.ping);
				// ((PingTimerAccess) Chat.client).setPingTimer(100L);
            }
        });
	}
}
