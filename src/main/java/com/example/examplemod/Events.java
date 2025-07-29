package com.example.examplemod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class Events {

    @SubscribeEvent
    public static void onInputEventKeyDebug(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player && player.getScoreboardName().equals("Than00ber")) {
            NmPacket.MODE_4.send(500);
        }
    }
    
    @SubscribeEvent
    public static void onInputEventKeyDebug(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        
        if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), NmScreen.OPEN_SCREEN.getKey().getValue())) {
            minecraft.setScreen(new NmScreen());
        }
    }
}
