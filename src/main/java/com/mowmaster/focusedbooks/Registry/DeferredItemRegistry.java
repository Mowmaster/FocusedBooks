package com.mowmaster.focusedbooks.Registry;

import com.mowmaster.focusedbooks.Items.BaseEnchantableBook;
import com.mowmaster.mowlib.Tabs.MowLibTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.mowmaster.focusedbooks.FocusedBooksUtil.References.MODID;

public class DeferredItemRegistry
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static final RegistryObject<Item> BASEBOOK = ITEMS.register("basebook",
            () -> new BaseEnchantableBook(new Item.Properties().tab(MowLibTab.TAB_ITEMS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
