package org.teacon.gimmeman.mixin;

import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(PlayerAdvancements.class)
public class MixinPlayerAdvancements {
    
    @Shadow private ServerPlayer player;
    
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Redirect(method = "award", at = @At(value = "INVOKE", target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"))
    public void onBoardCastMessage(Optional<DisplayInfo> instance, Consumer<DisplayInfo> action){
        if (player.tickCount < 100) return;
        instance.ifPresent(action);
    }
}
