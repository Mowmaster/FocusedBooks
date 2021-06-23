package com.mowmaster.focusedbooks.creativeTab;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTab extends ItemGroup
{
    public CreativeTab() {
        super(Reference.MODID+"_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemEnchantableBook.PROT);
    }
}
