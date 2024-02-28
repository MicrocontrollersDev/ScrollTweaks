package dev.microcontrollers.scrolltweaks.mixin;

import dev.microcontrollers.scrolltweaks.config.ScrollTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Mouse.class)
public class MouseMixin {
    @ModifyArg(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;scrollInHotbar(D)V"), index = 0)
    private double modifyScroll(double scrollAmount) {
        if (ScrollTweaksConfig.CONFIG.instance().disableScroll) return 0;
        int slot = MinecraftClient.getInstance().player.getInventory().selectedSlot;
        if (ScrollTweaksConfig.CONFIG.instance().reverseScroll) scrollAmount = -scrollAmount;
        if (ScrollTweaksConfig.CONFIG.instance().preventOverflowScroll && ((slot == 8 && scrollAmount < 0) || (slot == 0 && scrollAmount > 0)))
            return 0;
        return scrollAmount;
    }
}
