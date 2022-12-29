package net.chat.mixin;

import net.chat.Solver;
import net.chat.Logger;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.hud.MessageIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.jetbrains.annotations.Nullable;

@Mixin(ChatHud.class)
public class chatMixin {
	@Inject(method = "logChatMessage", at = @At("TAIL"))
	private void onMessageAdd(Text message, @Nullable MessageIndicator indicator, CallbackInfo ci) {
		final MinecraftClient client = MinecraftClient.getInstance();
		ChatScreen cs = new ChatScreen("");
		Solver solver = new Solver(message.getString());
		String solved = solver.testAll();
	
		if (solved == null)
		{
			return;
		}

		// Random delay to avoid bot detection
		/*  */
		long curTime = System.currentTimeMillis();
		long delay = (int) (Math.random() * (1500 + 1 - 700) + 700);
		Logger.LOGGER.info("Delay:{} Current time:{}", delay, curTime);

		while (System.currentTimeMillis() < curTime + delay) {
			;
		}
		Logger.LOGGER.info("Executed at {}", System.currentTimeMillis());
		/* */

		client.setScreen(cs); // set and open chat box
		cs.sendMessage(solved, true); // Send message
		client.setScreen(null); // Close screen
	}
}
