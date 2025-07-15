package com.example.examplemod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public final class Configuration {

	private static final Pair<Common, ForgeConfigSpec> COMMON = new ForgeConfigSpec.Builder().configure(Common::new);
	private static final Pair<Client, ForgeConfigSpec> CLIENT = new ForgeConfigSpec.Builder().configure(Client::new);

	public static void init() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON.getValue());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Configuration.CLIENT.getValue());
	}

	public static class Common {

		public static Common getInstance() {
			return Configuration.COMMON.getKey();
		}

		public Common(ForgeConfigSpec.Builder builder) {
			// init
		}
	}

	public static class Client {

		public static Client getInstance() {
			return CLIENT.getKey();
		}

		public Client(ForgeConfigSpec.Builder builder) {
			// init
		}
	}
}
