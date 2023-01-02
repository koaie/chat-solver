package net.chat.mixin;

import net.chat.MsgTimerAccess;
import net.chat.Chat;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class msgTimer implements MsgTimerAccess {

    @Unique
    private long ticksUntilMsg;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) { // Fix parameters as needed
        if (--this.ticksUntilMsg == 0L) {
            Chat.msg.send();
        }
    }

    @Override
    public void setMsgTimer(long ticksUntilMsg) {
        this.ticksUntilMsg = ticksUntilMsg;
    }
}
