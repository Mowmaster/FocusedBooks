package com.mowmaster.focusedbooks.creativeTab;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class FocusedTab extends CreativeModeTab
{
    public FocusedTab() {
        super(Reference.MODID+"_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemEnchantableBook.PROT);
    }
}