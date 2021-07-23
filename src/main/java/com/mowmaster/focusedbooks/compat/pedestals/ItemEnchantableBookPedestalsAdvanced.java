package com.mowmaster.focusedbooks.compat.pedestals;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.pedestals.api.enchanting.IAdvancedBook;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemEnchantableBookPedestalsAdvanced extends ItemEnchantableBook implements IAdvancedBook {

    public ItemEnchantableBookPedestalsAdvanced(Item sudoItem, String enchantName, EnchantmentType category, int color, IItemProvider ingredient, boolean doesWork) {
        super(sudoItem, enchantName, category, color, ingredient,doesWork);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        if(p_77624_1_.getItem() instanceof ItemEnchantableBookPedestalsAdvanced)
        {
            ItemEnchantableBook itemBook = (ItemEnchantableBook)p_77624_1_.getItem();
            Enchantment ench = itemBook.getEnchantment();

            TranslationTextComponent minNeeded = new TranslationTextComponent( MODID + ".minNeeded");
            minNeeded.withStyle(TextFormatting.WHITE);
            minNeeded.append(""+ench.getMinCost(ench.getMinLevel())+"");
            minNeeded.withStyle(TextFormatting.GREEN);
            p_77624_3_.add(minNeeded);

            TranslationTextComponent maxNeeded = new TranslationTextComponent( MODID + ".maxNeeded");
            maxNeeded.withStyle(TextFormatting.WHITE);
            maxNeeded.append(""+ench.getMaxCost(ench.getMaxLevel())+"");
            maxNeeded.withStyle(TextFormatting.BLUE);
            p_77624_3_.add(maxNeeded);

            if(ench.isTreasureOnly())
            {
                //toString() is the new translationKey() (item reg name)
                TranslationTextComponent isTreasure = new TranslationTextComponent( MODID + ".isTreasure");
                isTreasure.withStyle(TextFormatting.RED);
                p_77624_3_.add(isTreasure);
            }

            if(!ench.canApplyAtEnchantingTable(itemBook.getSudoItemStack()))
            {
                //toString() is the new translationKey() (item reg name)
                TranslationTextComponent cantEnchantTable = new TranslationTextComponent( MODID + ".cantEnchantTable");
                cantEnchantTable.withStyle(TextFormatting.YELLOW);
                p_77624_3_.add(cantEnchantTable);
            }
        }

    }
}
