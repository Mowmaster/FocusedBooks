package com.mowmaster.focusedbooks.Items;

import com.mowmaster.mowlib.MowLibUtils.MowLibCompoundTagUtils;
import com.mowmaster.mowlib.MowLibUtils.MowLibNameComponentUtils;
import com.mowmaster.mowlib.MowLibUtils.MowLibTooltipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static com.mowmaster.focusedbooks.focusedbooks.MODID;

public class BaseEnchantableBook extends BookItem {
    public BaseEnchantableBook(Properties p_40643_) {
        super(p_40643_);
    }


    /*=============================
    ===== Start of Setup Stuff ====
    =============================*/

    public static int getCoverColor(ItemStack stack)
    {
        return MowLibCompoundTagUtils.readIntegerFromNBT(MODID, stack.getOrCreateTag(), "_covercolor");
    }

    public static int getAccentColor(ItemStack stack)
    {
        return MowLibCompoundTagUtils.readIntegerFromNBT(MODID, stack.getOrCreateTag(), "_accentcolor");
    }

    public static int getPageColor(ItemStack stack)
    {
        return MowLibCompoundTagUtils.readIntegerFromNBT(MODID, stack.getOrCreateTag(), "_pagecolor");
    }

    /*=============================
    ====== End of Setup Stuff =====
    =============================*/





    /*=============================
    == Start of Enchanting Stuff ==
    =============================*/

    @Override
    public boolean isEnchantable(ItemStack p_40646_) {
        return p_40646_.getCount() == 1;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 10;
    }

    public ItemStack getSudoStack(ItemStack stack)
    {
        String sudoString = MowLibCompoundTagUtils.readStringFromNBT(MODID, stack.getOrCreateTag(), "_sudostack");
        ResourceLocation resource = ResourceLocation.tryParse(sudoString);
        if(resource !=null)
        {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(resource));
        }

        return new ItemStack(Items.BOOK);
    }

    public EnchantmentCategory getEnchantType(ItemStack stack)
    {
        return getEnchantment(stack).category;
    }

    public Enchantment getEnchantment(ItemStack stack)
    {
        String enchantString = MowLibCompoundTagUtils.readStringFromNBT(MODID, stack.getOrCreateTag(), "_enchantment");
        ResourceLocation resource = ResourceLocation.tryParse(enchantString);

        if(resource !=null)
        {
            return ForgeRegistries.ENCHANTMENTS.getValue(resource);
        }

        return null;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(getEnchantment(stack) !=null)
        {
            if(enchantment.equals(this.getEnchantment(stack)))return enchantment.category == this.getEnchantType(stack) || enchantment.canApplyAtEnchantingTable(this.getSudoStack(stack));
        }

        return super.canApplyAtEnchantingTable(getSudoStack(stack), enchantment);
    }

    public static boolean allowInCompatibleCombining(ItemStack stack)
    {
        return MowLibCompoundTagUtils.readBooleanFromNBT(MODID, stack.getOrCreateTag(), "_allowincompatiblecombining");
    }

    public static boolean allowOverCombining(ItemStack stack)
    {
        return MowLibCompoundTagUtils.readBooleanFromNBT(MODID, stack.getOrCreateTag(), "_allowovercombining");
    }

    public int maxEnchantLevel(ItemStack stack)
    {
        int max = MowLibCompoundTagUtils.readIntegerFromNBT(MODID, stack.getOrCreateTag(), "_maxlevel");
        return (max == 0)?(getEnchantment(stack).getMaxLevel()):((allowOverCombining(stack))?(max):(getEnchantment(stack).getMaxLevel()));
    }


    /*=============================
    === End of Enchanting Stuff ===
    =============================*/


    @Override
    public Component getName(ItemStack p_41458_) {
        if(getEnchantment(p_41458_) != null)
        {
            int level = EnchantmentHelper.getTagEnchantmentLevel(getEnchantment(p_41458_),p_41458_);
            String levelstring = Component.translatable("enchantment.level."+level).getString();
            String ending = Component.translatable(MODID + ".focusedbook").getString();
            MutableComponent comp = Component.translatable(getEnchantment(p_41458_).getDescriptionId()).append((level != 0)?(" "+levelstring+ending):(ending));
            return MowLibNameComponentUtils.createComponentName(comp,"");
        }

        return super.getName(p_41458_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        if(getEnchantment(p_41421_) != null)
        {
            if(getEnchantment(p_41421_).isTreasureOnly())
            {
                MutableComponent treasure = Component.translatable(MODID + ".treasurebook").withStyle(ChatFormatting.LIGHT_PURPLE);
                MowLibTooltipUtils.addTooltipMessage(p_41423_,p_41421_,treasure);
            }

            MutableComponent maxLevel = Component.translatable(MODID + ".maxlevel").withStyle(ChatFormatting.RED);
            maxLevel.append(Component.literal(maxEnchantLevel(p_41421_) + "").withStyle((getEnchantment(p_41421_).getMaxLevel()<maxEnchantLevel(p_41421_))?(ChatFormatting.GOLD):(ChatFormatting.WHITE)));
            MowLibTooltipUtils.addTooltipMessage(p_41423_,maxLevel);

            MutableComponent minLevel = Component.translatable(MODID + ".minlevel").withStyle(ChatFormatting.GREEN);
            minLevel.append(Component.literal(getEnchantment(p_41421_).getMinLevel() + "").withStyle(ChatFormatting.WHITE));
            MowLibTooltipUtils.addTooltipMessage(p_41423_,minLevel);
        }

        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
