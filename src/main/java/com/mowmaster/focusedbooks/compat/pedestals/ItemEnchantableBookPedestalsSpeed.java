package com.mowmaster.focusedbooks.compat.pedestals;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.pedestals.api.enchanting.ISpeedBook;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class ItemEnchantableBookPedestalsSpeed extends ItemEnchantableBook implements ISpeedBook {

    final EnchantmentType type;
    final ItemStack sudoItemStack;
    final String enchantResLoc;
    final IItemProvider getIngredient;
    final int getColor;
    final boolean works;

    public ItemEnchantableBookPedestalsSpeed(Item sudoItem, String enchantName, EnchantmentType category, int color, IItemProvider ingredient, boolean doesWork) {
        super(sudoItem, enchantName, category, color, ingredient,doesWork);
        this.type = category;
        this.sudoItemStack = new ItemStack(sudoItem);
        this.enchantResLoc = enchantName;
        this.getIngredient = ingredient;
        this.getColor = color;
        this.works = doesWork;
    }
}
