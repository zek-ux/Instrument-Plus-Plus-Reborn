package com.marzman232.ipp.common.register;

import com.marzman232.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.marzman232.ipp.common.crafting.serializers.MusicalInstrumentRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.marzman232.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MODID);

	public static final DeferredHolder<RecipeSerializer<?>, MusicalInstrumentRecipeSerializer<MusicalInstrumentShadowRecipe>> MUSICAL_INSTRUMENT_SHADOW_SERIALIZER = REGISTER.register(
			"instrument_shadow", MusicalInstrumentRecipeSerializer::new
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
