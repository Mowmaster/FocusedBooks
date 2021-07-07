package com.mowmaster.focusedbooks.items;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.mowmaster.focusedbooks.FocusedBooks.FOCUSED_TAB;
import static com.mowmaster.focusedbooks.references.Reference.MODID;

public class ItemEnchantableBook extends BookItem {

    final EnchantmentType type;
    final ItemStack sudoItemStack;
    final String enchantResLoc;
    final IItemProvider getIngredient;
    final int getColor;
    final boolean works;

    public ItemEnchantableBook(Item sudoItem, String enchantName, EnchantmentType category, int color, IItemProvider ingredient,boolean doesWork) {
        super(new Properties().stacksTo(64).tab(FOCUSED_TAB));
        this.type = category;
        this.sudoItemStack = new ItemStack(sudoItem);
        this.enchantResLoc = enchantName;
        this.getIngredient = ingredient;
        this.getColor = color;
        this.works = doesWork;
    }

    public static int getColor(ItemStack itemStack){
        if(itemStack.getItem() instanceof ItemEnchantableBook)
        {
            return ((ItemEnchantableBook)itemStack.getItem()).getColor;
        }
        else return 0;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 10;
    }

    public IItemProvider getGetIngredient()
    {
        return getIngredient;
    }

    public ItemStack getSudoItemStack()
    {
        return sudoItemStack;
    }

    public EnchantmentType getEnchantType()
    {
        return type;
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

    //Added so maybe Ensorcellation Bulwark would work?
    /*
    * Removed as although it 'fixes' the issue, it also allows any enchant to be applied (for shields)
    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        if(getSudoItemStack().getItem().equals(Items.SHIELD)){
            return true;
        }
        else return false;
    }*/

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    //SHould disable anvil book combines
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
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
                ItemHandlerHelper.giveItemToPlayer(player,newBook,-1);
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

            if(works)
            {
                if(ench.isAllowedOnBooks())
                {
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
                else
                {
                    TranslationTextComponent notOnBooks = new TranslationTextComponent( MODID + ".notOnBooks");
                    notOnBooks.withStyle(TextFormatting.RED);
                    p_77624_3_.add(notOnBooks);
                }
            }
            else
            {
                TranslationTextComponent disabled = new TranslationTextComponent( MODID + ".disabled");
                disabled.withStyle(TextFormatting.RED);
                p_77624_3_.add(disabled);
            }
        }

    }




    public static final Item PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:protection",EnchantmentType.ARMOR, 11206655, Items.LEATHER, true ).setRegistryName(new ResourceLocation(MODID, "book_protection"));//Leather
    public static final Item FIRE_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:fire_protection",EnchantmentType.ARMOR, 16733440,  Items.MAGMA_CREAM, true ).setRegistryName(new ResourceLocation(MODID, "book_fireprotection"));//magma_cream
    public static final Item FEATHER = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:feather_falling",EnchantmentType.ARMOR_FEET, 16777215,  Items.FEATHER, true ).setRegistryName(new ResourceLocation(MODID, "book_featherfall"));//feather
    public static final Item BLAST_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:blast_protection",EnchantmentType.ARMOR, 16755200,  Items.GUNPOWDER, true ).setRegistryName(new ResourceLocation(MODID, "book_blastprotection"));//gunpowder
    public static final Item PROJ_PROT = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:projectile_protection",EnchantmentType.ARMOR, 11184810,  Items.ARROW, true ).setRegistryName(new ResourceLocation(MODID, "book_projectileprotection"));//arrow
    public static final Item BREATHE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:respiration",EnchantmentType.ARMOR_HEAD, 65535,  Items.PUFFERFISH, true ).setRegistryName(new ResourceLocation(MODID, "book_respiration"));//pufferfish
    public static final Item WATER_WORKER = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:aqua_affinity",EnchantmentType.ARMOR_HEAD, 5636095,  Items.SALMON, true ).setRegistryName(new ResourceLocation(MODID, "book_aquaaffinity"));//salmon
    public static final Item THORNY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"minecraft:thorns",EnchantmentType.ARMOR_CHEST, 21760,  Items.SWEET_BERRIES, true ).setRegistryName(new ResourceLocation(MODID, "book_thorns"));//sweat_berries
    public static final Item WATER_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:depth_strider",EnchantmentType.ARMOR_FEET, 22015,  Items.SCUTE, true ).setRegistryName(new ResourceLocation(MODID, "book_depthstrider"));//scute
    public static final Item FROSTY_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:frost_walker",EnchantmentType.ARMOR_FEET, 43775,  Blocks.BLUE_ICE, true ).setRegistryName(new ResourceLocation(MODID, "book_frostwalker"));//blue_ice
    public static final Item SOUL_JESUS = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"minecraft:soul_speed",EnchantmentType.ARMOR_FEET, 5570560,  Blocks.SOUL_SOIL, true ).setRegistryName(new ResourceLocation(MODID, "book_soulspeed"));//soul_soil
    public static final Item SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:sharpness",EnchantmentType.WEAPON, 16733525,  Blocks.GRINDSTONE, true ).setRegistryName(new ResourceLocation(MODID, "book_sharpness"));//grindstone
    public static final Item DEAD_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:smite",EnchantmentType.WEAPON, 11162965,  Items.ROTTEN_FLESH, true ).setRegistryName(new ResourceLocation(MODID, "book_smite"));//rotten_flesh
    public static final Item SPIDER_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:bane_of_arthropods",EnchantmentType.WEAPON, 11163050,  Items.SPIDER_EYE, true ).setRegistryName(new ResourceLocation(MODID, "book_baneofawfulturds"));//spider_eye
    public static final Item FIRE_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:fire_aspect",EnchantmentType.WEAPON, 16733440,  Items.FLINT_AND_STEEL, true ).setRegistryName(new ResourceLocation(MODID, "book_fireaspect"));//flint_and_steel
    public static final Item SWEEP_SHARP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:sweeping",EnchantmentType.WEAPON, 11184810,  Blocks.HAY_BLOCK, true ).setRegistryName(new ResourceLocation(MODID, "book_sweeping"));//hay_block
    public static final Item PUSHY = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:knockback",EnchantmentType.WEAPON, 11206485,  Blocks.PISTON, true ).setRegistryName(new ResourceLocation(MODID, "book_knockback"));//piston
    public static final Item LOOTER = new ItemEnchantableBook(Items.DIAMOND_SWORD,"minecraft:looting",EnchantmentType.WEAPON, 16776960,  Items.NETHERITE_SCRAP, true ).setRegistryName(new ResourceLocation(MODID, "book_looting"));//netherite_scrap
    public static final Item FAST_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:efficiency",EnchantmentType.DIGGER, 16755200,  Items.SUGAR, true ).setRegistryName(new ResourceLocation(MODID, "book_efficiency"));//sugar
    public static final Item SILK_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:silk_touch",EnchantmentType.DIGGER, 16777215,  Blocks.SPONGE, true ).setRegistryName(new ResourceLocation(MODID, "book_silktouch"));//sponge
    public static final Item FORT_DIG = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:fortune",EnchantmentType.DIGGER, 16776960,  Blocks.ANCIENT_DEBRIS, true ).setRegistryName(new ResourceLocation(MODID, "book_fortune"));//ancient_debris
    public static final Item UNBREAK = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:unbreaking",EnchantmentType.DIGGER, 16777215,  Blocks.IRON_BLOCK, true ).setRegistryName(new ResourceLocation(MODID, "book_unbreaking"));//iron_block
    public static final Item POWER_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:power",EnchantmentType.BOW, 16733525,  Items.BLAZE_ROD, true ).setRegistryName(new ResourceLocation(MODID, "book_power"));//blaze_rod
    public static final Item PUSHY_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:punch",EnchantmentType.BOW, 11206485,  Blocks.STICKY_PISTON, true ).setRegistryName(new ResourceLocation(MODID, "book_punch"));//sticky_piston
    public static final Item FIREY_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:flame",EnchantmentType.BOW, 16733440,  Items.FIRE_CHARGE, true ).setRegistryName(new ResourceLocation(MODID, "book_flame"));//fire_charge
    public static final Item UNLIM_ARROW = new ItemEnchantableBook(Items.BOW,"minecraft:infinity",EnchantmentType.BOW, 16777215,  Blocks.END_STONE, true ).setRegistryName(new ResourceLocation(MODID, "book_infinity"));//end_stone
    public static final Item LUCKY_PHISH = new ItemEnchantableBook(Items.FISHING_ROD,"minecraft:luck_of_the_sea",EnchantmentType.FISHING_ROD, 65280,  Blocks.LAPIS_BLOCK, true ).setRegistryName(new ResourceLocation(MODID, "book_luckofthesea"));//lapis_block
    public static final Item SPEEDY_PHISH = new ItemEnchantableBook(Items.FISHING_ROD,"minecraft:lure",EnchantmentType.FISHING_ROD, 16755200,  Blocks.COMPOSTER, true ).setRegistryName(new ResourceLocation(MODID, "book_lure"));//composter
    public static final Item LOYAL_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:loyalty",EnchantmentType.TRIDENT, 16777215,  Items.BONE, true ).setRegistryName(new ResourceLocation(MODID, "book_loyality"));//bone
    public static final Item STABBY_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:impaling",EnchantmentType.TRIDENT, 16733525,  Items.BAMBOO, true ).setRegistryName(new ResourceLocation(MODID, "book_impaling"));//bamboo
    public static final Item WATERY_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:riptide",EnchantmentType.TRIDENT, 65535,  Blocks.PRISMARINE, true ).setRegistryName(new ResourceLocation(MODID, "book_riptide"));//prismarine
    public static final Item LIT_FORK = new ItemEnchantableBook(Items.TRIDENT,"minecraft:channeling",EnchantmentType.TRIDENT, 16776960,  Blocks.END_ROD, true ).setRegistryName(new ResourceLocation(MODID, "book_channeling"));//end_rod
    public static final Item MULTI_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:multishot",EnchantmentType.CROSSBOW, 16776960,  Items.GHAST_TEAR, true ).setRegistryName(new ResourceLocation(MODID, "book_multishot"));//ghast_tear
    public static final Item QUICK_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:quick_charge",EnchantmentType.CROSSBOW, 16755200,  Items.FIREWORK_STAR, true ).setRegistryName(new ResourceLocation(MODID, "book_quickcharge"));//firework_star
    public static final Item STABBY_CROSS = new ItemEnchantableBook(Items.CROSSBOW,"minecraft:piercing",EnchantmentType.CROSSBOW, 16733525,  Items.FIREWORK_ROCKET, true ).setRegistryName(new ResourceLocation(MODID, "book_piercing"));////firework_rocket
    public static final Item FIXERUPPER = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"minecraft:mending",EnchantmentType.DIGGER, 16777215,  Items.NETHER_STAR, true ).setRegistryName(new ResourceLocation(MODID, "book_mending"));//nether_star
    public static final Item BOUND_CURSE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:binding_curse",EnchantmentType.ARMOR, 11141120,  Blocks.CHAIN, true ).setRegistryName(new ResourceLocation(MODID, "book_bindingcurse"));//chain
    public static final Item VANISH_CURSE = new ItemEnchantableBook(Items.DIAMOND_HELMET,"minecraft:vanishing_curse",EnchantmentType.ARMOR, 16711680,  Items.FERMENTED_SPIDER_EYE, true ).setRegistryName(new ResourceLocation(MODID, "book_vanishingcurse"));//fermented_spider_eye

    public static final Item APOTH_BANE = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:bane_of_illagers",EnchantmentType.WEAPON, 11162965,  Items.EMERALD, true ).setRegistryName(new ResourceLocation(MODID, "book_apothbaneofillagers"));
    public static final Item APOTH_CRAZY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"apotheosis:berserk",EnchantmentType.ARMOR, 16711680,  Items.BLAZE_ROD, true ).setRegistryName(new ResourceLocation(MODID, "book_apothberserk"));
    public static final Item APOTH_CAPTURE = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:capturing",EnchantmentType.WEAPON, 11184640,  Items.EGG, true ).setRegistryName(new ResourceLocation(MODID, "book_apothcapturing"));
    public static final Item APOTH_CRESCENDO = new ItemEnchantableBook(Items.CROSSBOW,"apotheosis:crescendo",EnchantmentType.CROSSBOW, 11206655,  Items.ARROW, true ).setRegistryName(new ResourceLocation(MODID, "book_apothcrescendo"));
    public static final Item APOTH_MINER = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"apotheosis:depth_miner",EnchantmentType.DIGGER, 16755200,  Blocks.OBSIDIAN, true ).setRegistryName(new ResourceLocation(MODID, "book_apothdepthminer"));
    public static final Item APOTH_HELLINFUSION = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:hell_infusion",EnchantmentType.WEAPON, 16711765,  Blocks.NETHERRACK, true ).setRegistryName(new ResourceLocation(MODID, "book_apothhellinfusion"));
    public static final Item APOTH_COLDPOKEY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"apotheosis:icy_thorns",EnchantmentType.ARMOR_CHEST, 43775,  Items.SWEET_BERRIES, true ).setRegistryName(new ResourceLocation(MODID, "book_apothicythorns"));
    public static final Item APOTH_KNOWITALL = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:knowledge",EnchantmentType.WEAPON, 65280,  Items.EXPERIENCE_BOTTLE, true ).setRegistryName(new ResourceLocation(MODID, "book_apothknowledge"));
    public static final Item APOTH_VAMPIRE = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"apotheosis:life_mending",EnchantmentType.BREAKABLE, 11141205,  Items.WITHER_SKELETON_SKULL, true ).setRegistryName(new ResourceLocation(MODID, "book_apothlifemending"));
    public static final Item APOTH_WITCHY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"apotheosis:magic_protection",EnchantmentType.ARMOR, 11163135,  Items.POTION, true ).setRegistryName(new ResourceLocation(MODID, "book_apothmagicprotection"));
    public static final Item APOTH_DRYAD = new ItemEnchantableBook(Items.DIAMOND_HOE,"apotheosis:natures_blessing",EnchantmentType.DIGGER, 5635925,  Blocks.VINE, true ).setRegistryName(new ResourceLocation(MODID, "book_apothnaturesblessing"));
    public static final Item APOTH_BYEBYE = new ItemEnchantableBook(Items.ANVIL,"apotheosis:obliteration",EnchantmentType.BREAKABLE, 0,  Items.LAVA_BUCKET, true ).setRegistryName(new ResourceLocation(MODID, "book_apothobliteration"));
    public static final Item APOTH_BOING = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"apotheosis:rebounding",EnchantmentType.ARMOR, 11206485,  Items.SLIME_BALL, true ).setRegistryName(new ResourceLocation(MODID, "book_apothrebounding"));
    public static final Item APOTH_CHING = new ItemEnchantableBook(Items.SHIELD,"apotheosis:reflective",EnchantmentType.BREAKABLE, 16777215,  Blocks.GLASS, true ).setRegistryName(new ResourceLocation(MODID, "book_apothreflective"));
    public static final Item APOTH_SCAV = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:scavenger",EnchantmentType.WEAPON, 16776960,  Items.WHEAT_SEEDS, true ).setRegistryName(new ResourceLocation(MODID, "book_apothscavenger"));
    public static final Item APOTH_SEAINFUSION = new ItemEnchantableBook(Items.DIAMOND_SWORD,"apotheosis:sea_infusion",EnchantmentType.WEAPON, 65535,  Items.PRISMARINE_SHARD, true ).setRegistryName(new ResourceLocation(MODID, "book_apothseainfusion"));
    public static final Item APOTH_CABAL = new ItemEnchantableBook(Items.SHIELD,"apotheosis:shield_bash",EnchantmentType.BREAKABLE, 11162880,  Items.SHIELD, true ).setRegistryName(new ResourceLocation(MODID, "book_apothshieldbash"));
    public static final Item APOTH_SPLITS = new ItemEnchantableBook(Items.ANVIL,"apotheosis:splitting",EnchantmentType.BREAKABLE, 11184810,  Blocks.ANVIL, true ).setRegistryName(new ResourceLocation(MODID, "book_apothsplitting"));
    public static final Item APOTH_FOOTY = new ItemEnchantableBook(Items.DIAMOND_BOOTS,"apotheosis:stable_footing",EnchantmentType.ARMOR_FEET, 5592405,  Items.GOLDEN_BOOTS, true ).setRegistryName(new ResourceLocation(MODID, "book_apothstablefooting"));
    public static final Item APOTH_SINNER = new ItemEnchantableBook(Items.DIAMOND_HOE,"apotheosis:tempting",EnchantmentType.DIGGER, 16755455,  Items.WHEAT, true ).setRegistryName(new ResourceLocation(MODID, "book_apothtempting"));
    public static final Item APOTH_NEVERENDING = new ItemEnchantableBook(Items.BOW,"apotheosis:true_infinity",EnchantmentType.BOW, 16777215,  Blocks.END_STONE, true ).setRegistryName(new ResourceLocation(MODID, "book_apothtrueinfinity"));

    public static EnchantmentType STRADDLEBOARD = EnchantmentType.create("straddleboard", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item ALEXSMOBS_BRETURN = new ItemEnchantableBook(Items.BOOK,"alexsmobs:board_return",STRADDLEBOARD, 21845,  Items.ENDER_EYE, true ).setRegistryName(new ResourceLocation(MODID, "book_alexsboardreturn"));
    public static final Item ALEXSMOBS_LAVAWAX = new ItemEnchantableBook(Items.BOOK,"alexsmobs:lavawax",STRADDLEBOARD, 16733440,  Items.HONEYCOMB, true ).setRegistryName(new ResourceLocation(MODID, "book_alexslavawax"));
    public static final Item ALEXSMOBS_SERPENT = new ItemEnchantableBook(Items.BOOK,"alexsmobs:serpentfriend",STRADDLEBOARD, 16755455,  Items.INK_SAC, true ).setRegistryName(new ResourceLocation(MODID, "book_alexsserpentfriend"));
    public static final Item ALEXSMOBS_SJUMP = new ItemEnchantableBook(Items.BOOK,"alexsmobs:straddle_jump",STRADDLEBOARD, 11206485,  Items.RABBIT_FOOT, true ).setRegistryName(new ResourceLocation(MODID, "book_alexsstraddlejump"));

    public static final Item ARSN_BOOST = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ars_nouveau:mana_boost",EnchantmentType.ARMOR, 11141290,  Items.BLAZE_ROD, true ).setRegistryName(new ResourceLocation(MODID, "book_arsnmanaboost"));
    public static final Item ARSN_REGEN = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ars_nouveau:mana_regen",EnchantmentType.ARMOR, 11141375,  Items.GHAST_TEAR, true ).setRegistryName(new ResourceLocation(MODID, "book_arsnmanaregen"));
    public static final Item ARSN_REACTIVE = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ars_nouveau:reactive",EnchantmentType.WEARABLE, 11184810,  Items.GUNPOWDER, false ).setRegistryName(new ResourceLocation(MODID, "book_arsnreactive"));

    public static final Item ASTRAL_VISION = new ItemEnchantableBook(Items.DIAMOND_HELMET,"astralsorcery:night_vision",EnchantmentType.ARMOR_HEAD, 11141375,  Items.GOLDEN_CARROT, false ).setRegistryName(new ResourceLocation(MODID, "book_astralnightvision"));
    public static final Item ASTRAL_SCORCH = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"astralsorcery:scorching_heat",EnchantmentType.DIGGER, 16733440, Blocks.BLAST_FURNACE, true ).setRegistryName(new ResourceLocation(MODID, "book_astralscorchingheat"));

    public static final Item BETTERENDFORGE_VEIL = new ItemEnchantableBook(Items.DIAMOND_HELMET,"betterendforge:end_veil",EnchantmentType.ARMOR_HEAD, 11141290,  Blocks.CHORUS_FLOWER, true ).setRegistryName(new ResourceLocation(MODID, "book_befendveil"));

    //https://github.com/KingLemming/1.16
    //Would have to add actual code compat to do this one
    //public static final Item COFHCORE_HOLDING = new ItemEnchantableBook(Items.BOOK,"cofh_core:holding",EnchantmentType.VANISHABLE, 16755370,  Items.SHULKER_SHELL, true ).setRegistryName(new ResourceLocation(MODID, "book_cofhcoreholding"));

    public static EnchantmentType ENCHANTABLE = EnchantmentType.create("ENCHANTABLE", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static EnchantmentType HOE = EnchantmentType.create("HOE", (item -> item instanceof HoeItem));
    public static EnchantmentType PICKAXE_OR_SHOVEL = EnchantmentType.create("PICKAXE_OR_SHOVEL", (item -> item instanceof PickaxeItem || item instanceof ShovelItem));
    public static EnchantmentType SWORD_OR_AXE = EnchantmentType.create("SWORD_OR_AXE", (item -> item instanceof SwordItem || item instanceof AxeItem));
    public static EnchantmentType SWORD_OR_AXE_OR_CROSSBOW = EnchantmentType.create("SWORD_OR_AXE_OR_CROSSBOW", (item -> item instanceof SwordItem || item instanceof AxeItem || item instanceof CrossbowItem));
    public static final Item ENSORCELL_AIRAFF = new ItemEnchantableBook(Items.DIAMOND_HELMET,"ensorcellation:air_affinity",EnchantmentType.ARMOR_HEAD, 16777215,  Items.FEATHER, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationairaffinity"));
    public static final Item ENSORCELL_ANGLER = new ItemEnchantableBook(Items.FISHING_ROD,"ensorcellation:angler",EnchantmentType.FISHING_ROD, 22015,  Items.TROPICAL_FISH, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationangler"));
    public static final Item ENSORCELL_BULWARK = new ItemEnchantableBook(Items.SHIELD,"ensorcellation:bulwark",EnchantmentType.BREAKABLE, 11162880,  Items.SHIELD, false ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationbulwark"));
    public static final Item ENSORCELL_CAVAL = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:cavalier",SWORD_OR_AXE, 16755370,  Items.LEATHER_HORSE_ARMOR, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationcavalier"));
    public static final Item ENSORCELL_CFOOL = new ItemEnchantableBook(Items.DIAMOND_HELMET,"ensorcellation:curse_fool",EnchantmentType.ARMOR_HEAD, 11141120,  Items.GOLDEN_SWORD, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationcursefool"));
    public static final Item ENSORCELL_CMERCY = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:curse_mercy",SWORD_OR_AXE, 16755370,  Items.GLISTERING_MELON_SLICE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationcursemercy"));
    public static final Item ENSORCELL_DAMENDER = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:damage_ender",SWORD_OR_AXE, 11141290,  Items.ENDER_EYE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationdamageender"));
    public static final Item ENSORCELL_DAMILLAG = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:damage_illager",SWORD_OR_AXE, 11162965,  Items.IRON_AXE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationdamageillager"));
    public static final Item ENSORCELL_DAMVILLA = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:damage_villager",SWORD_OR_AXE_OR_CROSSBOW, 65280,  Items.EMERALD, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationdamagevillager"));
    //Cant get to work for some reason :shrug:
    public static final Item ENSORCELL_DISPLACE = new ItemEnchantableBook(Items.SHIELD,"ensorcellation:displacement",EnchantmentType.ARMOR_CHEST, 11162880,  Items.BUCKET, false ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationdisplacement"));
    public static final Item ENSORCELL_EXCAV = new ItemEnchantableBook(Items.DIAMOND_PICKAXE,"ensorcellation:excavating",PICKAXE_OR_SHOVEL, 16755200,  Items.IRON_PICKAXE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationexcavating"));
    public static final Item ENSORCELL_XPBOOST = new ItemEnchantableBook(Items.DIAMOND_HELMET,"ensorcellation:exp_boost",EnchantmentType.ARMOR_HEAD, 65280,  Items.EXPERIENCE_BOTTLE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationexpboost"));
    public static final Item ENSORCELL_FIREREBU = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ensorcellation:fire_rebuke",EnchantmentType.ARMOR_CHEST, 16733440,  Items.FIRE_CHARGE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationfirerebuke"));
    public static final Item ENSORCELL_FROSTASP = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:frost_aspect",SWORD_OR_AXE, 43775,  Items.SNOWBALL, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationfrostaspect"));
    public static final Item ENSORCELL_FURROW = new ItemEnchantableBook(Items.DIAMOND_HOE,"ensorcellation:furrowing",HOE, 11162880,  Blocks.GRASS_BLOCK, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationfurrowing"));
    public static final Item ENSORCELL_GOURMAND = new ItemEnchantableBook(Items.DIAMOND_HELMET,"ensorcellation:gourmand",EnchantmentType.ARMOR_HEAD, 16776960,  Items.RABBIT_STEW, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationgourmand"));
    public static final Item ENSORCELL_HUNTER = new ItemEnchantableBook(Items.BOW,"ensorcellation:hunter",EnchantmentType.BOW, 16733525,  Items.ARROW, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationhunter"));
    public static final Item ENSORCELL_INSTIG = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:instigating",SWORD_OR_AXE, 16733525,  Items.ENDER_PEARL, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationinstigating"));
    public static final Item ENSORCELL_LEECH = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:leech",SWORD_OR_AXE, 5635925,  Items.SPIDER_EYE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationleech"));
    public static final Item ENSORCELL_MAGEDGE = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:magic_edge",SWORD_OR_AXE, 11141290,  Items.GLOWSTONE_DUST, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationmagicedge"));
    public static final Item ENSORCELL_MAGPROT = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ensorcellation:magic_protection",EnchantmentType.ARMOR, 11141375,  Items.GLASS_BOTTLE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationmagicprotection"));
    public static final Item ENSORCELL_PHALANX = new ItemEnchantableBook(Items.SHIELD,"ensorcellation:phalanx",EnchantmentType.BREAKABLE, 11162880,  Items.WHITE_BANNER, false ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationphalanx"));
    public static final Item ENSORCELL_PILF = new ItemEnchantableBook(Items.FISHING_ROD,"ensorcellation:pilfering",EnchantmentType.FISHING_ROD, 16776960,  Items.DIAMOND, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationpilfering"));
    public static final Item ENSORCELL_QUICKD = new ItemEnchantableBook(Items.BOW,"ensorcellation:quick_draw",EnchantmentType.BOW, 16755200,  Items.BOW, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationquickdraw"));
    public static final Item ENSORCELL_REACH = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ensorcellation:reach",EnchantmentType.ARMOR_CHEST, 11206485,  Blocks.PISTON, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationreach"));
    public static final Item ENSORCELL_SOULB = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ensorcellation:soulbound",ENCHANTABLE, 16777215,  Items.NETHER_STAR, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationsoulbound"));
    public static final Item ENSORCELL_TILL = new ItemEnchantableBook(Items.DIAMOND_HOE,"ensorcellation:tilling",HOE, 11162880,  Items.GOLDEN_HOE, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationtilling"));
    public static final Item ENSORCELL_TRUESHOT = new ItemEnchantableBook(Items.BOW,"ensorcellation:trueshot",EnchantmentType.BOW, 16755370,  Blocks.TARGET, false ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationtrueshot"));
    public static final Item ENSORCELL_VITALITY = new ItemEnchantableBook(Items.DIAMOND_CHESTPLATE,"ensorcellation:vitality",EnchantmentType.ARMOR_CHEST, 16711765,  Items.POTION, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationvitality"));
    public static final Item ENSORCELL_VOLLEY = new ItemEnchantableBook(Items.BOW,"ensorcellation:volley",EnchantmentType.BOW, 16755370,  Items.CROSSBOW, false ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationvolley"));
    public static final Item ENSORCELL_VORPAL = new ItemEnchantableBook(Items.DIAMOND_SWORD,"ensorcellation:vorpal",SWORD_OR_AXE, 16733525,  Items.GOLDEN_SWORD, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationvorpal"));
    public static final Item ENSORCELL_WEEDS = new ItemEnchantableBook(Items.DIAMOND_HOE,"ensorcellation:weeding",HOE, 11162880,  Items.SHEARS, true ).setRegistryName(new ResourceLocation(MODID, "book_ensorcellationweeding"));

    public static EnchantmentType KNIFE = EnchantmentType.create("knife", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item FARMERS_BSTAB = new ItemEnchantableBook(Items.BOOK,"farmersdelight:backstabbing",KNIFE, 16733525,  Items.IRON_SWORD, true ).setRegistryName(new ResourceLocation(MODID, "book_farmersbackstabbing"));

    public static EnchantmentType GWR_GUN = EnchantmentType.create("GWR_GUN", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item GUNS_BULL = new ItemEnchantableBook(Items.BOOK,"gunswithoutroses:bullseye",GWR_GUN, 16755200,  Blocks.TARGET, true ).setRegistryName(new ResourceLocation(MODID, "book_gunsbullseye"));
    public static final Item GUNS_IMPACT = new ItemEnchantableBook(Items.BOOK,"gunswithoutroses:impact",GWR_GUN, 16733525,  Blocks.TNT, true ).setRegistryName(new ResourceLocation(MODID, "book_gunsimpact"));
    public static final Item GUNS_PRESRV = new ItemEnchantableBook(Items.BOOK,"gunswithoutroses:preserving",GWR_GUN, 16777215,  Blocks.ICE, true ).setRegistryName(new ResourceLocation(MODID, "book_gunspreserving"));
    public static final Item GUNS_SLEIGHT = new ItemEnchantableBook(Items.BOOK,"gunswithoutroses:sleight_of_hand",GWR_GUN, 16776960, Items.EMERALD, true ).setRegistryName(new ResourceLocation(MODID, "book_gunssleightofhand"));

    public static final Item MVL_REPAIR = new ItemEnchantableBook(Items.BOW,"morevanillalib:repairing_luck",EnchantmentType.BREAKABLE, 16777215,  Items.RABBIT_FOOT, true ).setRegistryName(new ResourceLocation(MODID, "book_mvlrepairingluck"));

    public static EnchantmentType HAMMA = EnchantmentType.create("mythicbotany_mjoellnir", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item MYTHICB_HAMMA = new ItemEnchantableBook(Items.BOOK,"mythicbotany:hammer_mobility",HAMMA, 16776960,  Items.NETHERITE_PICKAXE, true ).setRegistryName(new ResourceLocation(MODID, "book_mythicbhammermobility"));

    public static final Item NATURES_MEND = new ItemEnchantableBook(Items.BOW,"naturesaura:aura_mending",EnchantmentType.BREAKABLE, 5635925,  Blocks.OAK_LEAVES, true ).setRegistryName(new ResourceLocation(MODID, "book_naturesauramending"));

    public static EnchantmentType PEDESTALS = EnchantmentType.create("pedestalupgrade", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item PEDESTALS_ADVANCED = new ItemEnchantableBook(Items.BOOK,"pedestals:upgradeadvanced",PEDESTALS, 16776960,  Items.NETHER_STAR, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradeadvanced"));
    public static final Item PEDESTALS_AREA = new ItemEnchantableBook(Items.BOOK,"pedestals:upgradearea",PEDESTALS, 16776960,  Items.IRON_INGOT, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradearea"));
    public static final Item PEDESTALS_CAP = new ItemEnchantableBook(Items.BOOK,"pedestals:upgradecapacity",PEDESTALS, 16776960,  Items.EMERALD, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradecapacity"));
    public static final Item PEDESTALS_MAGNET = new ItemEnchantableBook(Items.BOOK,"pedestals:upgrademagnet",PEDESTALS, 16776960,  Blocks.HOPPER, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgrademagnet"));
    public static final Item PEDESTALS_RANGE = new ItemEnchantableBook(Items.BOOK,"pedestals:upgraderange",PEDESTALS, 16776960,  Items.GOLD_INGOT, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgraderange"));
    public static final Item PEDESTALS_SPEED = new ItemEnchantableBook(Items.BOOK,"pedestals:upgradespeed",PEDESTALS, 16776960,  Items.DIAMOND, false ).setRegistryName(new ResourceLocation(MODID, "book_pedestalsupgradespeed"));

    public static EnchantmentType TARANGE = EnchantmentType.create("travel_anchors_teleportable", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static EnchantmentType TATELE = EnchantmentType.create("travel_anchors_teleportable_no_staff", (item -> item.getItemEnchantability(new ItemStack(item)) > 0));
    public static final Item TRAVELA_RANGE = new ItemEnchantableBook(Items.BOOK,"travel_anchors:range",TARANGE, 16755370,  Items.POPPED_CHORUS_FRUIT, false ).setRegistryName(new ResourceLocation(MODID, "book_travelarange"));
    public static final Item TRAVELA_TELE = new ItemEnchantableBook(Items.DIAMOND_SWORD,"travel_anchors:teleportation",TATELE, 16755370,  Items.ENDER_PEARL, true ).setRegistryName(new ResourceLocation(MODID, "book_travelateleportation"));

    @SubscribeEvent
    public static void onItemRegistryReady(RegistryEvent.Register<Item> event)
    {
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

        if(ModList.get().isLoaded("apotheosis")) {
            event.getRegistry().register(APOTH_BANE);
            event.getRegistry().register(APOTH_CRAZY);
            event.getRegistry().register(APOTH_CAPTURE);
            event.getRegistry().register(APOTH_CRESCENDO);
            event.getRegistry().register(APOTH_MINER);
            event.getRegistry().register(APOTH_HELLINFUSION);
            event.getRegistry().register(APOTH_COLDPOKEY);
            event.getRegistry().register(APOTH_KNOWITALL);
            event.getRegistry().register(APOTH_VAMPIRE);
            event.getRegistry().register(APOTH_WITCHY);
            event.getRegistry().register(APOTH_DRYAD);
            event.getRegistry().register(APOTH_BYEBYE);
            event.getRegistry().register(APOTH_BOING);
            event.getRegistry().register(APOTH_CHING);
            event.getRegistry().register(APOTH_SCAV);
            event.getRegistry().register(APOTH_SEAINFUSION);
            event.getRegistry().register(APOTH_CABAL);
            event.getRegistry().register(APOTH_SPLITS);
            event.getRegistry().register(APOTH_FOOTY);
            event.getRegistry().register(APOTH_SINNER);
            event.getRegistry().register(APOTH_NEVERENDING);
        }

        if(ModList.get().isLoaded("alexsmobs")) {
            event.getRegistry().register(ALEXSMOBS_BRETURN);
            event.getRegistry().register(ALEXSMOBS_LAVAWAX);
            event.getRegistry().register(ALEXSMOBS_SERPENT);
            event.getRegistry().register(ALEXSMOBS_SJUMP);
        }

        if(ModList.get().isLoaded("ars_nouveau")) {
            event.getRegistry().register(ARSN_BOOST);
            event.getRegistry().register(ARSN_REGEN);
            event.getRegistry().register(ARSN_REACTIVE);
        }

        if(ModList.get().isLoaded("astralsorcery")) {
            event.getRegistry().register(ASTRAL_VISION);
            event.getRegistry().register(ASTRAL_SCORCH);
        }

        if(ModList.get().isLoaded("betterendforge")) {
            event.getRegistry().register(BETTERENDFORGE_VEIL);
        }

        //if(ModList.get().isLoaded("cofh_core")) {
        //    event.getRegistry().register(COFHCORE_HOLDING);
        //}

        if(ModList.get().isLoaded("ensorcellation")) {
            event.getRegistry().register(ENSORCELL_AIRAFF);
            event.getRegistry().register(ENSORCELL_ANGLER);
            event.getRegistry().register(ENSORCELL_BULWARK);
            event.getRegistry().register(ENSORCELL_CAVAL);
            event.getRegistry().register(ENSORCELL_CFOOL);
            event.getRegistry().register(ENSORCELL_CMERCY);
            event.getRegistry().register(ENSORCELL_DAMENDER);
            event.getRegistry().register(ENSORCELL_DAMILLAG);
            event.getRegistry().register(ENSORCELL_DAMVILLA);
            event.getRegistry().register(ENSORCELL_DISPLACE);
            event.getRegistry().register(ENSORCELL_EXCAV);
            event.getRegistry().register(ENSORCELL_XPBOOST);
            event.getRegistry().register(ENSORCELL_FIREREBU);
            event.getRegistry().register(ENSORCELL_FROSTASP);
            event.getRegistry().register(ENSORCELL_FURROW);
            event.getRegistry().register(ENSORCELL_GOURMAND);
            event.getRegistry().register(ENSORCELL_HUNTER);
            event.getRegistry().register(ENSORCELL_INSTIG);
            event.getRegistry().register(ENSORCELL_LEECH);
            event.getRegistry().register(ENSORCELL_MAGEDGE);
            event.getRegistry().register(ENSORCELL_MAGPROT);
            event.getRegistry().register(ENSORCELL_PHALANX);
            event.getRegistry().register(ENSORCELL_PILF);
            event.getRegistry().register(ENSORCELL_QUICKD);
            event.getRegistry().register(ENSORCELL_REACH);
            event.getRegistry().register(ENSORCELL_SOULB);
            event.getRegistry().register(ENSORCELL_TILL);
            event.getRegistry().register(ENSORCELL_TRUESHOT);
            event.getRegistry().register(ENSORCELL_VITALITY);
            event.getRegistry().register(ENSORCELL_VOLLEY);
            event.getRegistry().register(ENSORCELL_VORPAL);
            event.getRegistry().register(ENSORCELL_WEEDS);
        }

        if(ModList.get().isLoaded("farmersdelight")) {
            event.getRegistry().register(FARMERS_BSTAB);
        }

        if(ModList.get().isLoaded("gunswithoutroses")) {
            event.getRegistry().register(GUNS_BULL);
            event.getRegistry().register(GUNS_IMPACT);
            event.getRegistry().register(GUNS_PRESRV);
            event.getRegistry().register(GUNS_SLEIGHT);
        }

        if(ModList.get().isLoaded("morevanillalib")) {
            event.getRegistry().register(MVL_REPAIR);
        }

        if(ModList.get().isLoaded("mythicbotany")) {
            event.getRegistry().register(MYTHICB_HAMMA);
        }

        if(ModList.get().isLoaded("naturesaura")) {
            event.getRegistry().register(NATURES_MEND);
        }

        if(ModList.get().isLoaded("pedestals")) {
            event.getRegistry().register(PEDESTALS_ADVANCED);
            event.getRegistry().register(PEDESTALS_AREA);
            event.getRegistry().register(PEDESTALS_CAP);
            event.getRegistry().register(PEDESTALS_MAGNET);
            event.getRegistry().register(PEDESTALS_RANGE);
            event.getRegistry().register(PEDESTALS_SPEED);
        }

        if(ModList.get().isLoaded("travel_anchors")) {
            event.getRegistry().register(TRAVELA_RANGE);
            event.getRegistry().register(TRAVELA_TELE);
        }
    }

    public static void handleItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PROT);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FIRE_PROT);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},BLAST_PROT);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PROJ_PROT);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},BREATHE);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},WATER_WORKER);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},THORNY);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FEATHER);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},WATER_JESUS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FROSTY_JESUS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SOUL_JESUS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SHARP);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},DEAD_SHARP);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SPIDER_SHARP);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FIRE_SHARP);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SWEEP_SHARP);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PUSHY);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},LOOTER);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},POWER_ARROW);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PUSHY_ARROW);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FIREY_ARROW);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},UNLIM_ARROW);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},MULTI_CROSS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},QUICK_CROSS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},STABBY_CROSS);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},LOYAL_FORK);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},STABBY_FORK);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},WATERY_FORK);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},LIT_FORK);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FAST_DIG);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SILK_DIG);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FORT_DIG);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},LUCKY_PHISH);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},SPEEDY_PHISH);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},UNBREAK);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FIXERUPPER);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},VANISH_CURSE);
        event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},BOUND_CURSE);

        if(ModList.get().isLoaded("apotheosis")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_BANE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_CRAZY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_CAPTURE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_CRESCENDO);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_MINER);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_HELLINFUSION);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_COLDPOKEY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_KNOWITALL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_VAMPIRE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_WITCHY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_DRYAD);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_BYEBYE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_BOING);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_CHING);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_SCAV);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_SEAINFUSION);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_CABAL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_SPLITS);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_FOOTY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_SINNER);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},APOTH_NEVERENDING);
        }

        if(ModList.get().isLoaded("alexsmobs")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ALEXSMOBS_BRETURN);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ALEXSMOBS_LAVAWAX);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ALEXSMOBS_SERPENT);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ALEXSMOBS_SJUMP);
        }

        if(ModList.get().isLoaded("ars_nouveau")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ARSN_BOOST);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ARSN_REGEN);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ARSN_REACTIVE);
        }

        if(ModList.get().isLoaded("astralsorcery")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ASTRAL_VISION);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ASTRAL_SCORCH);
        }

        if(ModList.get().isLoaded("betterendforge")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},BETTERENDFORGE_VEIL);
        }

        //if(ModList.get().isLoaded("cofh_core")) {
        //    event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},COFHCORE_HOLDING);
        //}

        if(ModList.get().isLoaded("ensorcellation")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_AIRAFF);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_ANGLER);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_BULWARK);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_CAVAL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_CFOOL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_CMERCY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_DAMENDER);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_DAMILLAG);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_DAMVILLA);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_DISPLACE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_EXCAV);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_XPBOOST);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_FIREREBU);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_FROSTASP);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_FURROW);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_GOURMAND);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_HUNTER);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_INSTIG);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_LEECH);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_MAGEDGE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_MAGPROT);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_PHALANX);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_PILF);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_QUICKD);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_REACH);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_SOULB);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_TILL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_TRUESHOT);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_VITALITY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_VOLLEY);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_VORPAL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},ENSORCELL_WEEDS);
        }

        if(ModList.get().isLoaded("farmersdelight")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},FARMERS_BSTAB);
        }

        if(ModList.get().isLoaded("gunswithoutroses")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},GUNS_BULL);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},GUNS_IMPACT);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},GUNS_PRESRV);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},GUNS_SLEIGHT);
        }

        if(ModList.get().isLoaded("morevanillalib")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},MVL_REPAIR);
        }

        if(ModList.get().isLoaded("mythicbotany")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},MYTHICB_HAMMA);
        }

        if(ModList.get().isLoaded("naturesaura")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},NATURES_MEND);
        }

        if(ModList.get().isLoaded("pedestals")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_ADVANCED);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_AREA);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_CAP);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_MAGNET);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_RANGE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},PEDESTALS_SPEED);
        }

        if(ModList.get().isLoaded("travel_anchors")) {
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},TRAVELA_RANGE);
            event.getItemColors().register((itemstack, tintIndex) -> {if (tintIndex == 1){return getColor(itemstack);} else {return -1;}},TRAVELA_TELE);
        }
    }
}
