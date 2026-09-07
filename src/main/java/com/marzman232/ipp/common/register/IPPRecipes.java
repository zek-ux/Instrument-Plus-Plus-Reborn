package com.marzman232.ipp.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.marzman232.ipp.InstrumentPlusPlus.MODID;

public class IPPRecipes {
	private static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, MODID);
	public static DeferredHolder<RecipeType<?>, RecipeType<Recipe<?>>> MUSICAL_INSTRUMENT_SHADOW_TYPE = register("instrument_shadow");

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	@SuppressWarnings("SameParameterValue")
	private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<Recipe<?>>> register(String name) {
		return REGISTER.register(name, () -> new RecipeType<>() {
			@Override
			public String toString() {
				return ResourceLocation.fromNamespaceAndPath(MODID, name).toString();
			}
		});
	}
}
