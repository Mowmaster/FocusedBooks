package com.mowmaster.focusedbooks.compat.pedestals;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.pedestals.api.enchanting.IAreaBook;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemEnchantableBookPedestalsArea extends ItemEnchantableBook implements IAreaBook {

    final EnchantmentType type;
    final ItemStack sudoItemStack;
    final String enchantResLoc;
    final IItemProvider getIngredient;
    final int getColor;
    final boolean works;

    public ItemEnchantableBookPedestalsArea(Item sudoItem, String enchantName, EnchantmentType category, int color, IItemProvider ingredient, boolean doesWork) {
        super(sudoItem, enchantName, category, color, ingredient,doesWork);
        this.type = category;
        this.sudoItemStack = new ItemStack(sudoItem);
        this.enchantResLoc = enchantName;
        this.getIngredient = ingredient;
        this.getColor = color;
        this.works = doesWork;
    }
}
