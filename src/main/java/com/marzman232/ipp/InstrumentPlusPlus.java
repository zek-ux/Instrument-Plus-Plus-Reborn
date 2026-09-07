package com.marzman232.ipp;

import com.google.common.collect.Maps;
import com.marzman232.ipp.common.IPPContent;
import com.marzman232.ipp.common.register.IPPBlockTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

import java.util.IdentityHashMap;

import static com.marzman232.ipp.common.register.IPPInstruments.*;

/*
    👋 Hello random person lurking through the source code!
    I'm not a very experienced minecraft developer so please be prepared some spaghetti code mayhem.

    Full credits go to Viola-Siemens for making this wonderful mod and all of its assets.
*/

@Mod(InstrumentPlusPlus.MODID)
public class InstrumentPlusPlus {
    public static final String MODID = "ipp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InstrumentPlusPlus(IEventBus modEventBus, ModContainer modContainer) {
        IPPContent.modConstruction(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::onTagsUpdated);
    }

    public void onTagsUpdated(TagsUpdatedEvent event) {
        SetInstruments();
    }

    static private final IdentityHashMap<Block, NoteBlockInstrument> backups = Maps.newIdentityHashMap();

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void SetInstruments() {
        BuiltInRegistries.BLOCK.forEach(block -> {
            if(block.builtInRegistryHolder().is(IPPBlockTags.BASALTS)) {
                setInstrument(block, BASSOON);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.BLACKSTONES)) {
                setInstrument(block, CLARINET);
            } else if(block.equals(Blocks.IRON_TRAPDOOR)) {
                setInstrument(block, CRASH);
            } else if(block.equals(Blocks.CHAIN) || block.equals(Blocks.IRON_BARS)) {
                setInstrument(block, CYMBAL);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.SNOWS)) {
                setInstrument(block, ELECTRIC_CLEAN);
            } else if(block.equals(Blocks.NETHERRACK)) {
                setInstrument(block, ELECTRIC_OVERDRIVEN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.QUARTZ_BLOCKS)) {
                setInstrument(block, ELECTRIC_PIANO);
            } else if(block.builtInRegistryHolder().is(BlockTags.CORAL_BLOCKS) ||
                    block.builtInRegistryHolder().is(IPPBlockTags.DEAD_CORAL_BLOCKS)) {
                setInstrument(block, ERHU);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.COPPER_BLOCKS)) {
                setInstrument(block, FRENCH_HORN);
            } else if(block.equals(Blocks.MOSS_BLOCK)) {
                setInstrument(block, GUQIN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.END_STONES)) {
                setInstrument(block, KONGHOU);
            } else if(block.equals(Blocks.LAPIS_BLOCK)) {
                setInstrument(block, PAD);
            } else if(block.equals(Blocks.DIRT)) {
                setInstrument(block, SUONA);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.NETHER_BRICKS)) {
                setInstrument(block, TIMPANI);
            } else if(block.equals(Blocks.DRIED_KELP_BLOCK)) {
                setInstrument(block, TROMBONE);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.CUT_COPPER_BLOCKS)) {
                setInstrument(block, TRUMPET);
            } else if(block.equals(Blocks.GRAVEL)) {
                setInstrument(block, TUBA);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.GLAZED_TERRACOTTA)) {
                setInstrument(block, VIOLA);
            } else if(block.builtInRegistryHolder().is(BlockTags.TERRACOTTA)) {
                setInstrument(block, VIOLIN);
            } else if(block.builtInRegistryHolder().is(IPPBlockTags.AMETHYST_BLOCKS)) {
                setInstrument(block, YANGQIN);
            } else if(backups.containsKey(block)) {
                setInstrument(block, backups.get(block));
                backups.remove(block);
            }
        });
    }

    private void setInstrument(Block block, NoteBlockInstrument instrument) {
        if(!backups.containsKey(block)) {
            backups.put(block, block.defaultBlockState().instrument());
        }
        block.getStateDefinition().getPossibleStates().forEach(blockState -> blockState.instrument = instrument);
    }
}
