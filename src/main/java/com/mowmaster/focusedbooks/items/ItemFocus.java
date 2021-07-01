package com.mowmaster.focusedbooks.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.example.focusedbooks.FocusedBooks.FOCUSED_TAB;
import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemFocus extends Item {
    public ItemFocus() {
        super(new Properties().stacksTo(64).tab(FOCUSED_TAB));
    }

    public static final Item FOCUS_BASE = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_base"));
    public static final Item FOCUS_APOTH = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_apoth"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(FOCUS_BASE);
        if(ModList.get().isLoaded("apotheosis")){
            event.getRegistry().register(FOCUS_APOTH);
        }
    }
}
