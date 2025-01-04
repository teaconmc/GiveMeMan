package org.teacon.gimmeman.mixin;

import net.minecraft.advancements.DisplayInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DisplayInfo.class)
public class DisplayInfoMixin {

    @Inject(method = "shouldAnnounceChat", at = @At("HEAD"), cancellable = true)
    void shouldAnnounceChat(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
