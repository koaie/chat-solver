package net.chat.mixin;

import net.chat.TimerAccess;
import net.chat.Chat;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class clientTimer implements TimerAccess {

    @Unique
    private long ticksUntilEvent;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) { // Fix parameters as needed
        if (--this.ticksUntilEvent == 0L) {
            Chat.msg.send();
        }
    }

    @Override
    public void setTimer(long ticksUntilEvent) {
        this.ticksUntilEvent = ticksUntilEvent;
    }
}
