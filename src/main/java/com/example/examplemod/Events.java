package com.example.examplemod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber
public final class Events {

    private static int MODE = 1;
    
    @SubscribeEvent
    public static void onInputEventKeyDebug(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player && player.getScoreboardName().equals("Than00ber")) {
            Arrays.stream(NmPacket.values()).toList().get(MODE).send(500);
        }
    }
    
    @SubscribeEvent
    public static void onInputEventKeyDebug(InputEvent.Key event) {
        if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_1) {
            MODE = 1;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_2) {
            MODE = 2;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_3) {
            MODE = 3;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_4) {
            MODE = 4;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_5) {
            MODE = 5;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_6) {
            MODE = 6;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_7) {
            MODE = 7;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_8) {
            MODE = 8;
        } else if (event.getAction() == InputConstants.PRESS && event.getKey() == InputConstants.KEY_9) {
            MODE = 9;
        }
    }
}
