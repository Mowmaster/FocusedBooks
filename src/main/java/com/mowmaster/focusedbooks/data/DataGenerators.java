package com.mowmaster.focusedbooks.data;

import com.mowmaster.focusedbooks.data.Client.ItemModelProviderGen;
import com.mowmaster.focusedbooks.data.Client.RecipeProviderGen;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void getData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existFileHelper = event.getExistingFileHelper();

        gen.addProvider(new ItemModelProviderGen(gen, existFileHelper));
        gen.addProvider(new RecipeProviderGen(gen));
    }
}
