package com.marzman232.ipp.common.crafting.serializers;
import com.marzman232.ipp.common.crafting.MusicalInstrumentShadowRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;


public class MusicalInstrumentRecipeSerializer<T extends MusicalInstrumentShadowRecipe> implements RecipeSerializer<MusicalInstrumentShadowRecipe> {
	public static final MapCodec<MusicalInstrumentShadowRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
			Ingredient.CODEC.fieldOf("ingredient").forGetter(MusicalInstrumentShadowRecipe::getInputItem),
			Codec.STRING.fieldOf("instrument").forGetter(MusicalInstrumentShadowRecipe::getSerializedInstrument)
	).apply(inst, MusicalInstrumentShadowRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, MusicalInstrumentShadowRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, MusicalInstrumentShadowRecipe::getInputItem,
			ByteBufCodecs.STRING_UTF8, MusicalInstrumentShadowRecipe::getSerializedInstrument,
			MusicalInstrumentShadowRecipe::new
	);

	@Override
	public @NotNull MapCodec<MusicalInstrumentShadowRecipe> codec() {
		return CODEC;
	}

	@Override
	public @NotNull StreamCodec<RegistryFriendlyByteBuf, MusicalInstrumentShadowRecipe> streamCodec() {
		return STREAM_CODEC;
	}
}
