package net.chat.mixin;

import net.chat.Solver;
import net.chat.Chat;
import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.ChatHud;
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
		Solver solver = new Solver(message.getString());
		String solved = solver.testAll();
	
		if (solved == null)
		{
			return;
		}

		Chat.msg.send(solved);
	}
}
