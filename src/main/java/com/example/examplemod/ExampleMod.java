package com.example.examplemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(ExampleMod.MODID)
public final class ExampleMod {

	public static final String MODID = "examplemod";

	public ExampleMod() {
		Configuration.init();
	}

	public static ResourceLocation resource(String resource) {
		return new ResourceLocation(MODID, resource);
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static final class CommonSetupEvents {

		@SubscribeEvent
		public static void onFMLCommonSetupEvent(FMLCommonSetupEvent event) {
			// common setup event
		}
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static final class ClientSetupEvents {

		@SubscribeEvent
		public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {
			// client setup event
		}
	}
}
