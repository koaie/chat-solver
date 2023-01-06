package net.chat.mixin;

import net.chat.PingTimerAccess;
import net.chat.Chat;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class pingTimer implements PingTimerAccess {

    @Unique
    private long ticksUntilPing;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) { // Fix parameters as needed
        if (--this.ticksUntilPing == 0L) {
            if (Chat.server == null) {
                return;
            }
            Chat.solver.setLag(Chat.server.ping);
            this.ticksUntilPing = 500L;
        }
    }

    @Override
    public void setPingTimer(long ticksUntilPing) {
        Chat.log.info("Timer scheduled to set in {} ticks", ticksUntilPing);
        this.ticksUntilPing = ticksUntilPing;
    }
}
