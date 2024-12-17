package org.teacon.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.multiplayer.ClientAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.teacon.GiveMeMan;

import java.util.Objects;

@Mixin(ClientAdvancements.class)
public class MixinClientAdvancements {
    
    @Redirect(method = "update",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/toasts/ToastComponent;addToast(Lnet/minecraft/client/gui/components/toasts/Toast;)V"))
    public void onAddToast(ToastComponent instance, Toast arg){
        var uuid = Objects.requireNonNull(Minecraft.getInstance().player).getUUID();
        if(Objects.requireNonNullElse(GiveMeMan.activeMap.get(uuid),false)) return;
        instance.addToast(arg);
    }
}
