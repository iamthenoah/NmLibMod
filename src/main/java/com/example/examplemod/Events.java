package com.example.examplemod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class Events {

    @SubscribeEvent
    public static void onInputEventKeyDebug(InputEvent.Key event) {
        if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_K) {
            NmPacket.MODE_1.send(500);
        }
    }
}
