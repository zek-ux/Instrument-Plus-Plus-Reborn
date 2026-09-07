package com.marzman232.ipp.common.crafting;

import com.marzman232.ipp.common.register.IPPRecipeSerializers;
import com.marzman232.ipp.common.register.IPPRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;

public class MusicalInstrumentShadowRecipe implements Recipe<RecipeInput> {
	public ResourceLocation id = null;
	public Ingredient bottom = null;
	public NoteBlockInstrument instrument = null;

	public MusicalInstrumentShadowRecipe(ResourceLocation id, Ingredient bottom, NoteBlockInstrument instrument) {
		this.id = id;
		this.bottom = bottom;
		this.instrument = instrument;
	}
	public MusicalInstrumentShadowRecipe(Ingredient bottom, String instrument) {
		this.bottom = bottom;
		this.instrument = Arrays.stream(NoteBlockInstrument.values()).filter(ni -> ni.getSerializedName().equals(instrument)).findAny().orElseThrow();
	}

	@Override
	public boolean canCraftInDimensions(int wid, int hgt) {
		return true;
	}

	@Override @NotNull
	public RecipeSerializer<?> getSerializer() {
		return IPPRecipeSerializers.MUSICAL_INSTRUMENT_SHADOW_SERIALIZER.get();
	}

	@Override @NotNull
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> list = NonNullList.create();
		list.add(this.bottom);
		return list;
	}

	@SuppressWarnings("NullableProblems")
	@Override @Nullable
	public ItemStack assemble(@NotNull RecipeInput container, @NotNull HolderLookup.Provider registryAccess) {
		return null;
	}

	@SuppressWarnings("NullableProblems")
	@Override @Nullable
	public ItemStack getResultItem(@NotNull HolderLookup.Provider registryAccess) {
		return null;
	}

	@Override
	public boolean matches(RecipeInput container, @NotNull Level level) {
		return this.bottom.test(container.getItem(0));
	}

	@NotNull
	public ResourceLocation getId() {
		return this.id;
	}

	public Ingredient getInputItem() {
		return this.bottom;
	}

	@Override @NotNull
	public RecipeType<?> getType() {
		return IPPRecipes.MUSICAL_INSTRUMENT_SHADOW_TYPE.get();
	}

	public String getSerializedInstrument() {
		return this.instrument.getSerializedName();
	}
}
