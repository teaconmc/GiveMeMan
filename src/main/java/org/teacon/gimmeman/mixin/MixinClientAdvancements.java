package org.teacon.gimmeman.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.multiplayer.ClientAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientAdvancements.class)
public class MixinClientAdvancements {
    
    @Redirect(method = "update",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/toasts/ToastComponent;addToast(Lnet/minecraft/client/gui/components/toasts/Toast;)V"))
    public void onAddToast(ToastComponent instance, Toast arg){
        assert Minecraft.getInstance().player != null;
        if (Minecraft.getInstance().player.tickCount < 100) return;
        instance.addToast(arg);
    }
}
