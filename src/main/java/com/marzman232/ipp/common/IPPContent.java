package com.marzman232.ipp.common;

import com.marzman232.ipp.common.register.IPPBlockTags;
import com.marzman232.ipp.common.register.IPPRecipeSerializers;
import com.marzman232.ipp.common.register.IPPRecipes;
import com.marzman232.ipp.common.register.IPPSoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class IPPContent {
	public static void modConstruction(IEventBus bus) { //, Consumer<Runnable> runLater) {
		IPPRecipes.init(bus);
		IPPRecipeSerializers.init(bus);
		IPPBlockTags.init();
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		IPPSoundEvents.init(event);
	}
}
