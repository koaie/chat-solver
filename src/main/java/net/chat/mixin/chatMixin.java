package net.chat.mixin;

import net.chat.solver;
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
	@Inject(method = "logChatMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;)V", at = @At("TAIL"))
	private void onMessageAdd(Text message,@Nullable MessageIndicator indicator, CallbackInfo ci) {
		solver.LOGGER.info("msg:{}",message.getString());
	}
}
