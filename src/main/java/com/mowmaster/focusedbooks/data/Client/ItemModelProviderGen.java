package com.mowmaster.focusedbooks.data.Client;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;

public class ItemModelProviderGen extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProviderGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //withExistingParent("blockname",modLoc("blocklocation"))

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile customBook = new ModelFile.ExistingModelFile(new ResourceLocation(Reference.MODID + ":item/bookguide"),existingFileHelper);

        /*builder(itemGenerated,"book_protection");
        builder(itemGenerated,"book_fireprotection");
        builder(itemGenerated,"book_featherfall");
        builder(itemGenerated,"book_blastprotection");
        builder(itemGenerated,"book_projectileprotection");
        builder(itemGenerated,"book_respiration");
        builder(itemGenerated,"book_aquaaffinity");
        builder(itemGenerated,"book_thorns");
        builder(itemGenerated,"book_depthstrider");
        builder(itemGenerated,"book_frostwalker");
        builder(itemGenerated,"book_soulspeed");
        builder(itemGenerated,"book_sharpness");
        builder(itemGenerated,"book_smite");
        builder(itemGenerated,"book_baneofawfulturds");
        builder(itemGenerated,"book_fireaspect");
        builder(itemGenerated,"book_sweeping");
        builder(itemGenerated,"book_knockback");
        builder(itemGenerated,"book_looting");
        builder(itemGenerated,"book_efficiency");
        builder(itemGenerated,"book_silktouch");
        builder(itemGenerated,"book_fortune");
        builder(itemGenerated,"book_unbreaking");
        builder(itemGenerated,"book_power");
        builder(itemGenerated,"book_punch");
        builder(itemGenerated,"book_flame");
        builder(itemGenerated,"book_infinity");
        builder(itemGenerated,"book_luckofthesea");
        builder(itemGenerated,"book_lure");
        builder(itemGenerated,"book_loyality");
        builder(itemGenerated,"book_impaling");
        builder(itemGenerated,"book_riptide");
        builder(itemGenerated,"book_channeling");
        builder(itemGenerated,"book_multishot");
        builder(itemGenerated,"book_quickcharge");
        builder(itemGenerated,"book_piercing");
        builder(itemGenerated,"book_mending");
        builder(itemGenerated,"book_bindingcurse");
        builder(itemGenerated,"book_vanishingcurse");*/


        //if(ModList.get().isLoaded("apotheosis"))
        //{
            /*builder(itemGenerated,"book_apothbaneofillagers");
            builder(itemGenerated,"book_apothberserk");
            builder(itemGenerated,"book_apothcapturing");
            builder(itemGenerated,"book_apothcrescendo");
            builder(itemGenerated,"book_apothdepthminer");
            builder(itemGenerated,"book_apothhellinfusion");
            builder(itemGenerated,"book_apothicythorns");
            builder(itemGenerated,"book_apothknowledge");
            builder(itemGenerated,"book_apothlifemending");
            builder(itemGenerated,"book_apothmagicprotection");
            builder(itemGenerated,"book_apothnaturesblessing");
            builder(itemGenerated,"book_apothobliteration");
            builder(itemGenerated,"book_apothrebounding");
            builder(itemGenerated,"book_apothreflective");
            builder(itemGenerated,"book_apothscavenger");
            builder(itemGenerated,"book_apothseainfusion");
            builder(itemGenerated,"book_apothshieldbash");
            builder(itemGenerated,"book_apothsplitting");
            builder(itemGenerated,"book_apothstablefooting");
            builder(itemGenerated,"book_apothtempting");
            builder(itemGenerated,"book_apothtrueinfinity");
            */
        //}

        //if(ModList.get().isLoaded("apotheosis")) {
        builderModified(customBook,ItemEnchantableBook.APOTH_BANE);
        builderModified(customBook,ItemEnchantableBook.APOTH_CRAZY);
        builderModified(customBook,ItemEnchantableBook.APOTH_CAPTURE);
        builderModified(customBook,ItemEnchantableBook.APOTH_CRESCENDO);
        builderModified(customBook,ItemEnchantableBook.APOTH_MINER);
        builderModified(customBook,ItemEnchantableBook.APOTH_HELLINFUSION);
        builderModified(customBook,ItemEnchantableBook.APOTH_COLDPOKEY);
        builderModified(customBook,ItemEnchantableBook.APOTH_KNOWITALL);
        builderModified(customBook,ItemEnchantableBook.APOTH_VAMPIRE);
        builderModified(customBook,ItemEnchantableBook.APOTH_WITCHY);
        builderModified(customBook,ItemEnchantableBook.APOTH_DRYAD);
        builderModified(customBook,ItemEnchantableBook.APOTH_BYEBYE);
        builderModified(customBook,ItemEnchantableBook.APOTH_BOING);
        builderModified(customBook,ItemEnchantableBook.APOTH_CHING);
        builderModified(customBook,ItemEnchantableBook.APOTH_SCAV);
        builderModified(customBook,ItemEnchantableBook.APOTH_SEAINFUSION);
        builderModified(customBook,ItemEnchantableBook.APOTH_CABAL);
        builderModified(customBook,ItemEnchantableBook.APOTH_SPLITS);
        builderModified(customBook,ItemEnchantableBook.APOTH_FOOTY);
        builderModified(customBook,ItemEnchantableBook.APOTH_SINNER);
        builderModified(customBook,ItemEnchantableBook.APOTH_NEVERENDING);
        builder(itemGenerated,"focus_apoth");
        //}

        builder(itemGenerated,"focus_base");
        builderModified(customBook,ItemEnchantableBook.PROT);
        builderModified(customBook,ItemEnchantableBook.FIRE_PROT);
        builderModified(customBook,ItemEnchantableBook.BLAST_PROT);
        builderModified(customBook,ItemEnchantableBook.PROJ_PROT);
        builderModified(customBook,ItemEnchantableBook.BREATHE);
        builderModified(customBook,ItemEnchantableBook.WATER_WORKER);
        builderModified(customBook,ItemEnchantableBook.THORNY);
        builderModified(customBook,ItemEnchantableBook.FEATHER);
        builderModified(customBook,ItemEnchantableBook.WATER_JESUS);
        builderModified(customBook,ItemEnchantableBook.FROSTY_JESUS);
        builderModified(customBook,ItemEnchantableBook.SOUL_JESUS);
        builderModified(customBook,ItemEnchantableBook.SHARP);
        builderModified(customBook,ItemEnchantableBook.DEAD_SHARP);
        builderModified(customBook,ItemEnchantableBook.SPIDER_SHARP);
        builderModified(customBook,ItemEnchantableBook.FIRE_SHARP);
        builderModified(customBook,ItemEnchantableBook.SWEEP_SHARP);
        builderModified(customBook,ItemEnchantableBook.PUSHY);
        builderModified(customBook,ItemEnchantableBook.LOOTER);
        builderModified(customBook,ItemEnchantableBook.POWER_ARROW);
        builderModified(customBook,ItemEnchantableBook.PUSHY_ARROW);
        builderModified(customBook,ItemEnchantableBook.FIREY_ARROW);
        builderModified(customBook,ItemEnchantableBook.UNLIM_ARROW);
        builderModified(customBook,ItemEnchantableBook.MULTI_CROSS);
        builderModified(customBook,ItemEnchantableBook.QUICK_CROSS);
        builderModified(customBook,ItemEnchantableBook.STABBY_CROSS);
        builderModified(customBook,ItemEnchantableBook.LOYAL_FORK);
        builderModified(customBook,ItemEnchantableBook.STABBY_FORK);
        builderModified(customBook,ItemEnchantableBook.WATERY_FORK);
        builderModified(customBook,ItemEnchantableBook.LIT_FORK);
        builderModified(customBook,ItemEnchantableBook.FAST_DIG);
        builderModified(customBook,ItemEnchantableBook.SILK_DIG);
        builderModified(customBook,ItemEnchantableBook.FORT_DIG);
        builderModified(customBook,ItemEnchantableBook.LUCKY_PHISH);
        builderModified(customBook,ItemEnchantableBook.SPEEDY_PHISH);
        builderModified(customBook,ItemEnchantableBook.UNBREAK);
        builderModified(customBook,ItemEnchantableBook.FIXERUPPER);
        builderModified(customBook,ItemEnchantableBook.VANISH_CURSE);
        builderModified(customBook,ItemEnchantableBook.BOUND_CURSE);
    }

    private ItemModelBuilder builder(ModelFile itemGenerated,String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder builderModified(ModelFile itemGenerated, Item item) {
        String path = ((ItemEnchantableBook)item).getRegistryName().getPath();
        return getBuilder(path).parent(itemGenerated).texture("bookguide", "item/bookguide_default")
                .texture("bookguidetop", "item/" + getBookTop(((ItemEnchantableBook)item).getEnchantType()))
                .texture("bookguideaccent", "item/bookguideaccent_default");
    }

    private String getBookTop(EnchantmentType type)
    {
        String returner = "bookguidetop_default";
        switch (type)
        {
            case ARMOR: returner = "bookguidetop_armor";
            break;
            case ARMOR_HEAD: returner = "bookguidetop_helm";
            break;
            case ARMOR_CHEST: returner = "bookguidetop_chest";
            break;
            case ARMOR_LEGS: returner = "bookguidetop_legs";
            break;
            case ARMOR_FEET: returner = "bookguidetop_feet";
            break;
            case WEAPON: returner = "bookguidetop_weapon";
            break;
            case DIGGER: returner = "bookguidetop_digger";
            break;
            case FISHING_ROD: returner = "bookguidetop_fish";
            break;
            case TRIDENT: returner = "bookguidetop_trident";
            break;
            case BREAKABLE: returner = "bookguidetop_breakable";
            break;
            case BOW: returner = "bookguidetop_bow";
            break;
            case WEARABLE: returner = "bookguidetop_wearable";
            break;
            case CROSSBOW: returner = "bookguidetop_crossbow";
            break;
            case VANISHABLE: returner = "bookguidetop_default";
            break;
        }

        return returner;
    }
}
