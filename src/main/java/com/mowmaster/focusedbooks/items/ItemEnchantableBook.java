package com.mowmaster.focusedbooks.items;

import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.example.focusedbooks.FocusedBooks.FOCUSED_TAB;
import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemEnchantableBook extends BookItem {

    final EnchantmentType type;
    final ItemStack sudoItemStack;
    final String enchantResLoc;

    public ItemEnchantableBook(Item sudoItem,String enchantName, EnchantmentType category) {
        super(new Properties().stacksTo(64).tab(FOCUSED_TAB));
        this.type = category;
        this.sudoItemStack = new ItemStack(sudoItem);
        this.enchantResLoc = enchantName;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 10;
    }

    public Enchantment getEnchantment()
    {
        Enchantment prot = Registry.ENCHANTMENT.get(ResourceLocation.tryParse(this.enchantResLoc));
        return prot;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        if(enchantment.equals(this.getEnchantment()))return enchantment.category == this.type || enchantment.canApplyAtEnchantingTable(this.sudoItemStack);
        else return false;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return stack.getCount() == 1;
    }

    //Converts book to normal enchanted book in players inventory
    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
        if(p_77663_1_.isEnchanted())
        {
            Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(p_77663_1_);
            if(p_77663_3_ instanceof PlayerEntity)
            {
                ItemStack newBook = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantmentHelper.setEnchantments(enchants,newBook);
                PlayerEntity player = (PlayerEntity) p_77663_3_;
                p_77663_1_.shrink(1);
                ItemHandlerHelper.giveItemToPlayer(player,newBook,player.inventory.findSlotMatchingItem(p_77663_1_));
            }
        }
    }

    //For automated enchanters, just drop book on ground to convert
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {

        if(stack.isEnchanted())
        {
            Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
            ItemStack newBook = new ItemStack(Items.ENCHANTED_BOOK,stack.getCount());
            EnchantmentHelper.setEnchantments(enchants,newBook);
            entity.setItem(newBook);
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        if(p_77624_1_.getItem() instanceof ItemEnchantableBook)
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
        }

    }

/*
    public static final Item APOTH_KNOWLEDGE = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:knowledge",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_apothknowledge"));//Leather
*/

    public static final Item PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:protection",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_protection"));//Leather
    public static final Item FIRE_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:fire_protection",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_fireprotection"));//magma_cream
    public static final Item FEATHER = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:feather_falling",EnchantmentType.ARMOR_FEET).setRegistryName(new ResourceLocation(MODID, "book_featherfall"));//feather
    public static final Item BLAST_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:blast_protection",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_blastprotection"));//gunpowder
    public static final Item PROJ_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:projectile_protection",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_projectileprotection"));//arrow
    public static final Item BREATHE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:respiration",EnchantmentType.ARMOR_HEAD).setRegistryName(new ResourceLocation(MODID, "book_respiration"));//pufferfish
    public static final Item WATER_WORKER = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:aqua_affinity",EnchantmentType.ARMOR_HEAD).setRegistryName(new ResourceLocation(MODID, "book_aquaaffinity"));//salmon
    public static final Item THORNY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"minecraft:thorns",EnchantmentType.ARMOR_CHEST).setRegistryName(new ResourceLocation(MODID, "book_thorns"));//sweat_berries
    public static final Item WATER_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:depth_strider",EnchantmentType.ARMOR_FEET).setRegistryName(new ResourceLocation(MODID, "book_depthstrider"));//scute

    public static final Item FROSTY_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:frost_walker",EnchantmentType.ARMOR_FEET).setRegistryName(new ResourceLocation(MODID, "book_frostwalker"));//blue_ice
    public static final Item SOUL_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:soul_speed",EnchantmentType.ARMOR_FEET).setRegistryName(new ResourceLocation(MODID, "book_soulspeed"));//soul_soil
    public static final Item SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:sharpness",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_sharpness"));//grindstone
    public static final Item DEAD_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:smite",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_smite"));//rotten_flesh
    public static final Item SPIDER_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:bane_of_arthropods",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_baneofawfulturds"));//spider_eye
    public static final Item FIRE_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:fire_aspect",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_fireaspect"));//flint_and_steel
    public static final Item SWEEP_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:sweeping",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_sweeping"));//hay_block
    public static final Item PUSHY = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:knockback",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_knockback"));//piston
    public static final Item LOOTER = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:looting",EnchantmentType.WEAPON).setRegistryName(new ResourceLocation(MODID, "book_looting"));//netherite_scrap

    public static final Item FAST_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:efficiency",EnchantmentType.DIGGER).setRegistryName(new ResourceLocation(MODID, "book_efficiency"));//sugar
    public static final Item SILK_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:silk_touch",EnchantmentType.DIGGER).setRegistryName(new ResourceLocation(MODID, "book_silktouch"));//sponge
    public static final Item FORT_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:fortune",EnchantmentType.DIGGER).setRegistryName(new ResourceLocation(MODID, "book_fortune"));//ancient_debris
    public static final Item UNBREAK = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:unbreaking",EnchantmentType.DIGGER).setRegistryName(new ResourceLocation(MODID, "book_unbreaking"));//iron_block
    public static final Item POWER_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:power",EnchantmentType.BOW).setRegistryName(new ResourceLocation(MODID, "book_power"));//blaze_rod
    public static final Item PUSHY_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:punch",EnchantmentType.BOW).setRegistryName(new ResourceLocation(MODID, "book_punch"));//sticky_piston
    public static final Item FIREY_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:flame",EnchantmentType.BOW).setRegistryName(new ResourceLocation(MODID, "book_flame"));//fire_charge
    public static final Item UNLIM_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:infinity",EnchantmentType.BOW).setRegistryName(new ResourceLocation(MODID, "book_infinity"));//end_stone
    public static final Item LUCKY_PHISH = new ItemEnchantableBook(Items.FISHING_ROD,"minecraft:luck_of_the_sea",EnchantmentType.FISHING_ROD).setRegistryName(new ResourceLocation(MODID, "book_luckofthesea"));//lapis_block

    public static final Item SPEEDY_PHISH = new ItemEnchantableBook(Items.FISHING_ROD,"minecraft:lure",EnchantmentType.FISHING_ROD).setRegistryName(new ResourceLocation(MODID, "book_lure"));//composter
    public static final Item LOYAL_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:loyalty",EnchantmentType.TRIDENT).setRegistryName(new ResourceLocation(MODID, "book_loyality"));//bone
    public static final Item STABBY_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:impaling",EnchantmentType.TRIDENT).setRegistryName(new ResourceLocation(MODID, "book_impaling"));//bamboo
    public static final Item WATERY_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:riptide",EnchantmentType.TRIDENT).setRegistryName(new ResourceLocation(MODID, "book_riptide"));//prismarine
    public static final Item LIT_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:channeling",EnchantmentType.TRIDENT).setRegistryName(new ResourceLocation(MODID, "book_channeling"));//end_rod
    public static final Item MULTI_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:multishot",EnchantmentType.CROSSBOW).setRegistryName(new ResourceLocation(MODID, "book_multishot"));//ghast_tear
    public static final Item QUICK_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:quick_charge",EnchantmentType.CROSSBOW).setRegistryName(new ResourceLocation(MODID, "book_quickcharge"));//firework_star
    public static final Item STABBY_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:piercing",EnchantmentType.CROSSBOW).setRegistryName(new ResourceLocation(MODID, "book_piercing"));////firework_rocket
    public static final Item FIXERUPPER = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:mending",EnchantmentType.DIGGER).setRegistryName(new ResourceLocation(MODID, "book_mending"));//nether_star

    public static final Item BOUND_CURSE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:binding_curse",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_bindingcurse"));//chain
    public static final Item VANISH_CURSE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:vanishing_curse",EnchantmentType.ARMOR).setRegistryName(new ResourceLocation(MODID, "book_vanishingcurse"));//fermented_spider_eye

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
        /*if(ModList.get().isLoaded("apotheosis"))
        {
            event.getRegistry().register(APOTH_KNOWLEDGE);
        }*/
        event.getRegistry().register(PROT);
        event.getRegistry().register(FIRE_PROT);
        event.getRegistry().register(BLAST_PROT);
        event.getRegistry().register(PROJ_PROT);

        event.getRegistry().register(BREATHE);
        event.getRegistry().register(WATER_WORKER);

        event.getRegistry().register(THORNY);

        event.getRegistry().register(FEATHER);
        event.getRegistry().register(WATER_JESUS);
        event.getRegistry().register(FROSTY_JESUS);
        event.getRegistry().register(SOUL_JESUS);

        event.getRegistry().register(SHARP);
        event.getRegistry().register(DEAD_SHARP);
        event.getRegistry().register(SPIDER_SHARP);
        event.getRegistry().register(FIRE_SHARP);
        event.getRegistry().register(SWEEP_SHARP);
        event.getRegistry().register(PUSHY);
        event.getRegistry().register(LOOTER);

        event.getRegistry().register(POWER_ARROW);
        event.getRegistry().register(PUSHY_ARROW);
        event.getRegistry().register(FIREY_ARROW);
        event.getRegistry().register(UNLIM_ARROW);

        event.getRegistry().register(MULTI_CROSS);
        event.getRegistry().register(QUICK_CROSS);
        event.getRegistry().register(STABBY_CROSS);

        event.getRegistry().register(LOYAL_FORK);
        event.getRegistry().register(STABBY_FORK);
        event.getRegistry().register(WATERY_FORK);
        event.getRegistry().register(LIT_FORK);

        event.getRegistry().register(FAST_DIG);
        event.getRegistry().register(SILK_DIG);
        event.getRegistry().register(FORT_DIG);

        event.getRegistry().register(LUCKY_PHISH);
        event.getRegistry().register(SPEEDY_PHISH);

        event.getRegistry().register(UNBREAK);
        event.getRegistry().register(FIXERUPPER);

        event.getRegistry().register(VANISH_CURSE);
        event.getRegistry().register(BOUND_CURSE);
    }
}
