package org.teacon.gimmeman.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.xkmc.l2backpack.events.StartUpGiveItemEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teacon.gimmeman.GiveMeMan;

@Mixin(StartUpGiveItemEvents.class)
public class L2BackpackMixin {

    @WrapOperation(method = "onPlayerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;placeItemBackInInventory(Lnet/minecraft/world/item/ItemStack;)V"))
    private static void beforeBackpackGiven(Inventory instance, ItemStack arg, Operation<Void> original, PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        LootTable lootTable = player.server.reloadableRegistries().getLootTable(
                ResourceKey.create(Registries.LOOT_TABLE, GiveMeMan.id("startup_items")));
        LootParams lootParams = (new LootParams.Builder(player.serverLevel()))
                .withLuck(player.getLuck())
                .create(LootContextParamSet.builder().build());
        lootTable.getRandomItems(lootParams).forEach(itemStack -> {
            if (!player.getInventory().add(itemStack)) {
                player.drop(itemStack, false);
            }
        });
    }
}
