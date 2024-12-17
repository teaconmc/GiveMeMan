package org.teacon;

import it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenHashMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Map;
import java.util.UUID;

@Mod(GiveMeMan.MOD_ID)
public final class GiveMeMan {
    public static final String MOD_ID = "gimmeman";
    
    public static Map<UUID,Boolean> activeMap = new Object2BooleanLinkedOpenHashMap<>();

    public GiveMeMan(ModContainer modContainer, IEventBus bus) {
        NeoForge.EVENT_BUS.addListener(GiveMeMan::onPlayerTick);
    }
    
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        var player = event.getEntity();
        if(player.tickCount >= 120) return;
        if (!(player instanceof ServerPlayer sp)) return;
        var time = sp.getStats().getValue(Stats.CUSTOM.get(Stats.PLAY_TIME));
        activeMap.put(player.getUUID(), time < 100);
        //activeMap.put(player.getUUID(), true);
    }
}
