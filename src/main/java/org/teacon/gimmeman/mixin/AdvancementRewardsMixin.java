package org.teacon.gimmeman.mixin;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CacheableFunction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teacon.gimmeman.GimmeMan;

import java.util.List;
import java.util.Optional;

@Mixin(AdvancementRewards.class)
public class AdvancementRewardsMixin {

    @Shadow @Final private Optional<CacheableFunction> function;

    @Shadow @Final private List<ResourceKey<LootTable>> loot;

    @Inject(method = "grant", at = @At("HEAD"), cancellable = true)
    private void cancelGrant(ServerPlayer arg, CallbackInfo ci) {
        if (loot.stream().anyMatch(lootTableResourceKey -> lootTableResourceKey.location().getNamespace().equals(GimmeMan.MOD_ID)))
            return;

        MinecraftServer minecraftserver = arg.server;
        this.function
                .flatMap(argx -> argx.get(minecraftserver.getFunctions()))
                .ifPresent(arg2 -> minecraftserver.getFunctions().execute(arg2, arg.createCommandSourceStack().withSuppressedOutput().withPermission(2)));

        ci.cancel();
    }
}
