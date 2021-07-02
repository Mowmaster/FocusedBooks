package com.mowmaster.focusedbooks.data.Client;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.items.ItemFocus;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class RecipeProviderGen extends ForgeRecipeProvider {
    public RecipeProviderGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public String getName() {
        return "Focused Books - Recipes";
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.PROT);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FIRE_PROT);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FEATHER);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.BLAST_PROT);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.PROJ_PROT);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.BREATHE);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.WATER_WORKER);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.THORNY);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.WATER_JESUS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FROSTY_JESUS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SOUL_JESUS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SHARP);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.DEAD_SHARP);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SPIDER_SHARP);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FIRE_SHARP);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SWEEP_SHARP);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.PUSHY);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.LOOTER);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FAST_DIG);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SILK_DIG);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FORT_DIG);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.UNBREAK);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.POWER_ARROW);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.PUSHY_ARROW);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FIREY_ARROW);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.UNLIM_ARROW);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.LUCKY_PHISH);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.SPEEDY_PHISH);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.LOYAL_FORK);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.STABBY_FORK);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.WATERY_FORK);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.LIT_FORK);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.MULTI_CROSS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.QUICK_CROSS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.STABBY_CROSS);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.FIXERUPPER);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.BOUND_CURSE);
        makeSimpleRecipe(consumer, ":vanilla",ItemEnchantableBook.VANISH_CURSE);

        //if(ModList.get().isLoaded("apotheosis"))
        //{
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_BANE);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_CRAZY);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_CAPTURE);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_CRESCENDO);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_MINER);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_HELLINFUSION);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_COLDPOKEY);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_KNOWITALL);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_VAMPIRE);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_WITCHY);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_DRYAD);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_BYEBYE);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_BOING);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_CHING);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_SCAV);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_SEAINFUSION);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_CABAL);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_SPLITS);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_FOOTY);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_SINNER);
        makeSimpleModRecipeRecipe(consumer,"apotheosis",ItemFocus.FOCUS_APOTH, ItemEnchantableBook.APOTH_NEVERENDING);
        //}

        //if(ModList.get().isLoaded("alexsmobs")) {
        makeSimpleModRecipeRecipe(consumer,"alexsmobs",ItemFocus.FOCUS_ALEXS, ItemEnchantableBook.ALEXSMOBS_BRETURN);
        makeSimpleModRecipeRecipe(consumer,"alexsmobs",ItemFocus.FOCUS_ALEXS, ItemEnchantableBook.ALEXSMOBS_LAVAWAX);
        makeSimpleModRecipeRecipe(consumer,"alexsmobs",ItemFocus.FOCUS_ALEXS, ItemEnchantableBook.ALEXSMOBS_SERPENT);
        makeSimpleModRecipeRecipe(consumer,"alexsmobs",ItemFocus.FOCUS_ALEXS, ItemEnchantableBook.ALEXSMOBS_SJUMP);
        //}

        //if(ModList.get().isLoaded("ars_nouveau")) {
        makeSimpleModRecipeRecipe(consumer,"ars_nouveau",ItemFocus.FOCUS_ARSN, ItemEnchantableBook.ARSN_BOOST);
        makeSimpleModRecipeRecipe(consumer,"ars_nouveau",ItemFocus.FOCUS_ARSN, ItemEnchantableBook.ARSN_REGEN);
        //makeSimpleModRecipeRecipe(consumer,"ars_nouveau",ItemFocus.FOCUS_ARSN, ItemEnchantableBook.ARSN_REACTIVE);
        //}

        //if(ModList.get().isLoaded("astralsorcery")) {
        //makeSimpleModRecipeRecipe(consumer,"astralsorcery",ItemFocus.FOCUS_ASTRAL, ItemEnchantableBook.ASTRAL_VISION);
        makeSimpleModRecipeRecipe(consumer,"astralsorcery",ItemFocus.FOCUS_ASTRAL, ItemEnchantableBook.ASTRAL_SCORCH);
        //}

        //if(ModList.get().isLoaded("betterendforge")) {
        makeSimpleModRecipeRecipe(consumer,"betterendforge",ItemFocus.FOCUS_BEF, ItemEnchantableBook.BETTERENDFORGE_VEIL);
        //}

        //if(ModList.get().isLoaded("cofh_core")) {
        //makeSimpleModRecipeRecipe(consumer,"cofh_core",ItemFocus.FOCUS_COFH, ItemEnchantableBook.COFHCORE_HOLDING);
        //}

        //if(ModList.get().isLoaded("ensorcellation")) {
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_AIRAFF);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_ANGLER);
        //makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_BULWARK);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_CAVAL);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_CFOOL);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_CMERCY);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_DAMENDER);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_DAMILLAG);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_DAMVILLA);
        //makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_DISPLACE);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_EXCAV);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_XPBOOST);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_FIREREBU);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_FROSTASP);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_FURROW);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_GOURMAND);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_HUNTER);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_INSTIG);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_LEECH);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_MAGEDGE);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_MAGPROT);
        //makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_PHALANX);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_PILF);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_QUICKD);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_REACH);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_SOULB);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_TILL);
        //makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_TRUESHOT);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_VITALITY);
        //makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_VOLLEY);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_VORPAL);
        makeSimpleModRecipeRecipe(consumer,"ensorcellation",ItemFocus.FOCUS_ENSORC, ItemEnchantableBook.ENSORCELL_WEEDS);
        //}

        //if(ModList.get().isLoaded("farmersdelight")) {
        makeSimpleModRecipeRecipe(consumer,"farmersdelight",ItemFocus.FOCUS_FARMD, ItemEnchantableBook.FARMERS_BSTAB);
        //}

        //if(ModList.get().isLoaded("gunswithoutroses")) {
        makeSimpleModRecipeRecipe(consumer,"gunswithoutroses",ItemFocus.FOCUS_GWOR, ItemEnchantableBook.GUNS_BULL);
        makeSimpleModRecipeRecipe(consumer,"gunswithoutroses",ItemFocus.FOCUS_GWOR, ItemEnchantableBook.GUNS_IMPACT);
        makeSimpleModRecipeRecipe(consumer,"gunswithoutroses",ItemFocus.FOCUS_GWOR, ItemEnchantableBook.GUNS_PRESRV);
        makeSimpleModRecipeRecipe(consumer,"gunswithoutroses",ItemFocus.FOCUS_GWOR, ItemEnchantableBook.GUNS_SLEIGHT);
        //}

        //if(ModList.get().isLoaded("morevanillalib")) {
        makeSimpleModRecipeRecipe(consumer,"morevanillalib",ItemFocus.FOCUS_MVL, ItemEnchantableBook.MVL_REPAIR);
        //}

        //if(ModList.get().isLoaded("mythicbotany")) {
        makeSimpleModRecipeRecipe(consumer,"mythicbotany",ItemFocus.FOCUS_MYTHICB, ItemEnchantableBook.MYTHICB_HAMMA);
        //}

        //if(ModList.get().isLoaded("naturesaura")) {
        makeSimpleModRecipeRecipe(consumer,"naturesaura",ItemFocus.FOCUS_NATURESA, ItemEnchantableBook.NATURES_MEND);
        //}

        //if(ModList.get().isLoaded("pedestals")) {
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_ADVANCED);
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_AREA);
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_CAP);
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_MAGNET);
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_RANGE);
        //makeSimpleModRecipeRecipe(consumer,"pedestals",ItemFocus.FOCUS_PEDS, ItemEnchantableBook.PEDESTALS_SPEED);
        //}

        //if(ModList.get().isLoaded("travel_anchors")) {
        //makeSimpleModRecipeRecipe(consumer,"travel_anchors",ItemFocus.FOCUS_TRAVELA, ItemEnchantableBook.TRAVELA_RANGE);
        makeSimpleModRecipeRecipe(consumer,"travel_anchors",ItemFocus.FOCUS_TRAVELA, ItemEnchantableBook.TRAVELA_TELE);
        //}

        ShapedRecipeBuilder.shaped(ItemFocus.FOCUS_BASE)
                .group(Reference.MODID)
                .pattern("GGG")
                .pattern("GEG")
                .pattern("GGG")
                .define('G',Items.GLASS)
                .define('E',Items.ENDER_EYE)
                .unlockedBy("has_item",has(Items.ENDER_EYE))
                .save(consumer);
    }

    public void makeSimpleRecipe(Consumer<IFinishedRecipe> consumer,String groupName, Item item)
    {
        ShapelessRecipeBuilder.shapeless(item)
                .group(Reference.MODID + groupName)
                .requires(Items.BOOK)
                .requires(ItemFocus.FOCUS_BASE)
                .requires(((ItemEnchantableBook)item).getGetIngredient())
                .unlockedBy("has_item",has(ItemFocus.FOCUS_BASE))
                .save(consumer);
    }

    public void makeSimpleModRecipeRecipe(Consumer<IFinishedRecipe> consumer,String modid, Item focus, Item item)
    {
        /*
        * Someday i may try to make this work, but till then, will use custom item
        JsonObject itemName = new JsonObject();
        itemName.add("item",new JsonPrimitive("apotheosis:tome_armor"));
        .requires(Ingredient.fromJson(itemName))*/

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(modid))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(item)
                                .group(Reference.MODID + ":" +  modid)
                                .requires(Items.BOOK)
                                .requires(focus)
                                .requires(((ItemEnchantableBook)item).getGetIngredient())
                                .unlockedBy("has_item",has(focus))
                        ::save
                )
                .build(consumer, new ResourceLocation("focusedbooks", item.getRegistryName().getPath()));
    }

}
