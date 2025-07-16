package com.example.examplemod;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class Events {
    
    @SubscribeEvent
    public static void on(LivingDamageEvent event) {
        if (event.getEntity() instanceof LocalPlayer player) {
            
        }
    }
}
