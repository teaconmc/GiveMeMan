package org.teacon.gimmeman.mixin;

import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teacon.gimmeman.GimmeMan;

import java.util.Map;

@Mixin(ServerAdvancementManager.class)
public class AdvancementManagerMixin {

    @Inject(
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("HEAD")
    )
    void preventAdvancementAddition(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
        map.entrySet().removeIf((entry) -> GimmeMan.modsToRemoveAdvancements.contains(entry.getKey().getNamespace()));
    }
}