package com.mowmaster.focusedbooks.items;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.example.focusedbooks.FocusedBooks.FOCUSED_TAB;
import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemFocus extends Item {
    public ItemFocus() {
        super(new Properties().stacksTo(64).tab(FOCUSED_TAB));
    }

    public static final Item FOCUS_BASE = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_base"));
    public static final Item FOCUS_NETHER = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_nether"));
    public static final Item FOCUS_END = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_end"));
    public static final Item FOCUS_AIR = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_air"));
    public static final Item FOCUS_EARTH = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_earth"));
    public static final Item FOCUS_WATER = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_water"));
    public static final Item FOCUS_FIRE = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_fire"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(FOCUS_BASE);
    }
}
