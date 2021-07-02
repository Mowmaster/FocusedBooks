package com.mowmaster.focusedbooks;

import com.mowmaster.focusedbooks.creativeTab.CreativeTab;
import com.mowmaster.focusedbooks.items.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("focusedbooks")
public class FocusedBooks
{

    public static final ItemGroup FOCUSED_TAB = new CreativeTab();

    public FocusedBooks() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemRegistry(RegistryEvent.Register<Item> event)
        {
            ItemRegistry.onItemRegistryReady(event);
        }
    }
}
