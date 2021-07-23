package com.mowmaster.focusedbooks.compat.pedestals;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.pedestals.api.enchanting.IEnchantableBook;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemEnchantableBookPedestals extends ItemEnchantableBook implements IEnchantableBook {

    final EnchantmentType type;
    final ItemStack sudoItemStack;
    final String enchantResLoc;
    final IItemProvider getIngredient;
    final int getColor;
    final boolean works;

    public ItemEnchantableBookPedestals(Item sudoItem, String enchantName, EnchantmentType category, int color, IItemProvider ingredient, boolean doesWork) {
        super(sudoItem, enchantName, category, color, ingredient,doesWork);
        this.type = category;
        this.sudoItemStack = new ItemStack(sudoItem);
        this.enchantResLoc = enchantName;
        this.getIngredient = ingredient;
        this.getColor = color;
        this.works = doesWork;
    }

    public static EnchantmentType PEDESTALS = EnchantmentType.create("pedestalupgrade", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item PEDESTALS_ADVANCED = new ItemEnchantableBookPedestalsAdvanced(ItemEnchantableBookPedestals.PEDESTALS_ADVANCED,"pedestals:upgradeadvanced",PEDESTALS, 16776960,  Items.NETHER_STAR, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradeadvanced"));
    public static final Item PEDESTALS_AREA = new ItemEnchantableBookPedestalsArea(ItemEnchantableBookPedestals.PEDESTALS_AREA,"pedestals:upgradearea",PEDESTALS, 16776960,  Items.IRON_INGOT, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradearea"));
    public static final Item PEDESTALS_CAP = new ItemEnchantableBookPedestalsCapacity(ItemEnchantableBookPedestals.PEDESTALS_CAP,"pedestals:upgradecapacity",PEDESTALS, 16776960,  Items.EMERALD, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradecapacity"));
    public static final Item PEDESTALS_MAGNET = new ItemEnchantableBookPedestalsMagnet(ItemEnchantableBookPedestals.PEDESTALS_MAGNET,"pedestals:upgrademagnet",PEDESTALS, 16776960,  Blocks.HOPPER, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgrademagnet"));
    public static final Item PEDESTALS_RANGE = new ItemEnchantableBookPedestalsRange(ItemEnchantableBookPedestals.PEDESTALS_RANGE,"pedestals:upgraderange",PEDESTALS, 16776960,  Items.GOLD_INGOT, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgraderange"));
    public static final Item PEDESTALS_SPEED = new ItemEnchantableBookPedestalsSpeed(ItemEnchantableBookPedestals.PEDESTALS_SPEED,"pedestals:upgradespeed",PEDESTALS, 16776960,  Items.DIAMOND, true ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradespeed"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        if(ModList.get().isLoaded("pedestals")) {
            event.getRegistry().register(PEDESTALS_ADVANCED);
            event.getRegistry().register(PEDESTALS_AREA);
            event.getRegistry().register(PEDESTALS_CAP);
            event.getRegistry().register(PEDESTALS_MAGNET);
            event.getRegistry().register(PEDESTALS_RANGE);
            event.getRegistry().register(PEDESTALS_SPEED);
        }
    }

    public static void handleItemColors(ColorHandlerEvent.Item event)
    {
        if(ModList.get().isLoaded("pedestals")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_ADVANCED);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_AREA);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_CAP);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_MAGNET);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_RANGE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_SPEED);
        }
    }
}
