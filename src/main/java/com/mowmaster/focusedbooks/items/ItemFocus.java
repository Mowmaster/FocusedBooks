package com.mowmaster.focusedbooks.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.mowmaster.focusedbooks.FocusedBooks.FOCUSED_TAB;
import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemFocus extends Item {
    public ItemFocus() {
        super(new Properties().stacksTo(64).tab(FOCUSED_TAB));
    }

    public static final Item FOCUS_BASE = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_base"));
    public static final Item FOCUS_APOTH = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_apoth"));
    public static final Item FOCUS_ALEXS = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_alexs"));
    public static final Item FOCUS_ARSN = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_arsn"));
    public static final Item FOCUS_ASTRAL = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_astral"));
    public static final Item FOCUS_BEF = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_bef"));
    //public static final Item FOCUS_COFH = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_cofhcore"));
    public static final Item FOCUS_ENSORC = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_ensorcellation"));
    public static final Item FOCUS_FARMD = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_farmersd"));
    public static final Item FOCUS_GWOR = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_gunsnroses"));
    public static final Item FOCUS_MVL = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_mvl"));
    public static final Item FOCUS_MYTHICB = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_mythicb"));
    public static final Item FOCUS_NATURESA = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_naturesa"));
    public static final Item FOCUS_PEDS = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_pedestals"));
    public static final Item FOCUS_TRAVELA = new ItemFocus().setRegistryName(new ResourceLocation(MODID, "focus_travela"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(FOCUS_BASE);
        if(ModList.get().isLoaded("apotheosis")){
            event.getRegistry().register(FOCUS_APOTH);
        }
        if(ModList.get().isLoaded("alexsmobs")){
            event.getRegistry().register(FOCUS_ALEXS);
        }
        if(ModList.get().isLoaded("ars_nouveau")){
            event.getRegistry().register(FOCUS_ARSN);
        }
        if(ModList.get().isLoaded("astralsorcery")){
            event.getRegistry().register(FOCUS_ASTRAL);
        }
        if(ModList.get().isLoaded("betterendforge")){
            event.getRegistry().register(FOCUS_BEF);
        }
        //if(ModList.get().isLoaded("cofh_core")){
        //    event.getRegistry().register(FOCUS_COFH);
        //}
        if(ModList.get().isLoaded("ensorcellation")){
            event.getRegistry().register(FOCUS_ENSORC);
        }
        if(ModList.get().isLoaded("farmersdelight")){
            event.getRegistry().register(FOCUS_FARMD);
        }
        if(ModList.get().isLoaded("gunswithoutroses")){
            event.getRegistry().register(FOCUS_GWOR);
        }
        if(ModList.get().isLoaded("morevanillalib")){
            event.getRegistry().register(FOCUS_MVL);
        }
        if(ModList.get().isLoaded("mythicbotany")){
            event.getRegistry().register(FOCUS_MYTHICB);
        }
        if(ModList.get().isLoaded("naturesaura")){
            event.getRegistry().register(FOCUS_NATURESA);
        }
        if(ModList.get().isLoaded("pedestals")){
            event.getRegistry().register(FOCUS_PEDS);
        }
        if(ModList.get().isLoaded("travel_anchors")){
            event.getRegistry().register(FOCUS_TRAVELA);
        }
    }
}
