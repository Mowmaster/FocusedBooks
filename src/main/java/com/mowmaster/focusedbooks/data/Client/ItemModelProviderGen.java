package com.mowmaster.focusedbooks.data.Client;

import com.google.common.collect.Maps;
import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;

import java.util.HashMap;
import java.util.Map;

import static com.mowmaster.focusedbooks.items.ItemEnchantableBook.*;
import static net.minecraft.enchantment.EnchantmentType.*;

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

        //if(ModList.get().isLoaded("alexsmobs")) {
            builderModified(customBook,ItemEnchantableBook.ALEXSMOBS_BRETURN);
            builderModified(customBook,ItemEnchantableBook.ALEXSMOBS_LAVAWAX);
            builderModified(customBook,ItemEnchantableBook.ALEXSMOBS_SERPENT);
            builderModified(customBook,ItemEnchantableBook.ALEXSMOBS_SJUMP);
        builder(itemGenerated,"focus_alexs");
        //}

        //if(ModList.get().isLoaded("ars_nouveau")) {
            builderModified(customBook,ItemEnchantableBook.ARSN_BOOST);
            builderModified(customBook,ItemEnchantableBook.ARSN_REGEN);
            builderModified(customBook,ItemEnchantableBook.ARSN_REACTIVE);
        builder(itemGenerated,"focus_arsn");
        //}

        //if(ModList.get().isLoaded("astralsorcery")) {
            builderModified(customBook,ItemEnchantableBook.ASTRAL_VISION);
            builderModified(customBook,ItemEnchantableBook.ASTRAL_SCORCH);
        builder(itemGenerated,"focus_astral");
        //}

        //if(ModList.get().isLoaded("betterendforge")) {
            builderModified(customBook,ItemEnchantableBook.BETTERENDFORGE_VEIL);
        builder(itemGenerated,"focus_bef");
        //}

        //if(ModList.get().isLoaded("cofh_core")) {
        //builderModified(customBook,ItemEnchantableBook.COFHCORE_HOLDING);
        //builder(itemGenerated,"focus_cofhcore");
        //}

        //if(ModList.get().isLoaded("ensorcellation")) {
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_AIRAFF);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_ANGLER);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_BULWARK);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_CAVAL);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_CFOOL);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_CMERCY);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_DAMENDER);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_DAMILLAG);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_DAMVILLA);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_DISPLACE);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_EXCAV);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_XPBOOST);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_FIREREBU);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_FROSTASP);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_FURROW);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_GOURMAND);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_HUNTER);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_INSTIG);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_LEECH);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_MAGEDGE);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_MAGPROT);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_PHALANX);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_PILF);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_QUICKD);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_REACH);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_SOULB);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_TILL);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_TRUESHOT);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_VITALITY);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_VOLLEY);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_VORPAL);
            builderModified(customBook,ItemEnchantableBook.ENSORCELL_WEEDS);
        builder(itemGenerated,"focus_ensorcellation");
        //}

        //if(ModList.get().isLoaded("farmersdelight")) {
            builderModified(customBook,ItemEnchantableBook.FARMERS_BSTAB);
        builder(itemGenerated,"focus_farmersd");
        //}

        //if(ModList.get().isLoaded("gunswithoutroses")) {
            builderModified(customBook,ItemEnchantableBook.GUNS_BULL);
            builderModified(customBook,ItemEnchantableBook.GUNS_IMPACT);
            builderModified(customBook,ItemEnchantableBook.GUNS_PRESRV);
            builderModified(customBook,ItemEnchantableBook.GUNS_SLEIGHT);
        builder(itemGenerated,"focus_gunsnroses");
        //}

        //if(ModList.get().isLoaded("morevanillalib")) {
            builderModified(customBook,ItemEnchantableBook.MVL_REPAIR);
        builder(itemGenerated,"focus_mvl");
        //}

        //if(ModList.get().isLoaded("mythicbotany")) {
            builderModified(customBook,ItemEnchantableBook.MYTHICB_HAMMA);
        builder(itemGenerated,"focus_mythicb");
        //}

        //if(ModList.get().isLoaded("naturesaura")) {
        builderModified(customBook,ItemEnchantableBook.NATURES_MEND);
        builder(itemGenerated,"focus_naturesa");
        //}

        //if(ModList.get().isLoaded("pedestals")) {
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_ADVANCED);
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_AREA);
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_CAP);
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_MAGNET);
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_RANGE);
            builderModified(customBook,ItemEnchantableBook.PEDESTALS_SPEED);
        builder(itemGenerated,"focus_pedestals");
        //}

        //if(ModList.get().isLoaded("travel_anchors")) {
            builderModified(customBook,ItemEnchantableBook.TRAVELA_RANGE);
            builderModified(customBook,ItemEnchantableBook.TRAVELA_TELE);
        builder(itemGenerated,"focus_travela");
        //}
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
        Map<EnchantmentType,String> myMap = new HashMap<EnchantmentType,String>() {{
            put(ARMOR, "bookguidetop_armor");
            put(ARMOR_HEAD, "bookguidetop_helm");
            put(ARMOR_CHEST, "bookguidetop_chest");
            put(ARMOR_LEGS, "bookguidetop_legs");
            put(ARMOR_FEET, "bookguidetop_feet");
            put(WEAPON, "bookguidetop_weapon");
            put(DIGGER, "bookguidetop_digger");
            put(FISHING_ROD, "bookguidetop_fish");
            put(TRIDENT, "bookguidetop_trident");
            put(BREAKABLE, "bookguidetop_breakable");
            put(BOW, "bookguidetop_bow");
            put(WEARABLE, "bookguidetop_wearable");
            put(CROSSBOW, "bookguidetop_crossbow");
            put(VANISHABLE, "bookguidetop_default");
            put(STRADDLEBOARD, "bookguidetop_straddleboard");
            put(ENCHANTABLE, "bookguidetop_default");
            put(HOE, "bookguidetop_hoe");
            put(PICKAXE_OR_SHOVEL, "bookguidetop_digger");
            put(SWORD_OR_AXE, "bookguidetop_weapon");
            put(SWORD_OR_AXE_OR_CROSSBOW, "bookguidetop_weapon");
            put(KNIFE, "bookguidetop_knife");
            put(GWR_GUN, "bookguidetop_gun");
            put(HAMMA, "bookguidetop_hammer");
            put(PEDESTALS, "bookguidetop_pedestals");
            put(TATELE, "bookguidetop_anchor");
        }};

        if(myMap.containsKey(type))return myMap.get(type);
        else return returner;
    }
}
