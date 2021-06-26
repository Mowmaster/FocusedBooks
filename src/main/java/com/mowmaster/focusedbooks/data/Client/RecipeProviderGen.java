package com.mowmaster.focusedbooks.data.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.items.ItemFocus;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

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

        if(ModList.get().isLoaded("apotheosis"))
        {
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_BANE);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_CRAZY);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_CAPTURE);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_CRESCENDO);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_MINER);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_HELLINFUSION);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_COLDPOKEY);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_KNOWITALL);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_VAMPIRE);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_WITCHY);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_DRYAD);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_BYEBYE);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_BOING);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_CHING);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_SCAV);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_SEAINFUSION);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_CABAL);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_SPLITS);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_FOOTY);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_SINNER);
        makeSimpleApothRecipe(consumer, ":apotheosis", ItemEnchantableBook.APOTH_NEVERENDING);
        }

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

    public void makeSimpleApothRecipe(Consumer<IFinishedRecipe> consumer, String groupName, Item item)
    {
        ShapelessRecipeBuilder.shapeless(item)
                .group(Reference.MODID + groupName)
                .requires(Items.BOOK)
                .requires(ItemFocus.FOCUS_APOTH)
                .requires(((ItemEnchantableBook)item).getGetIngredient())
                .unlockedBy("has_item",has(ItemFocus.FOCUS_APOTH))
                .save(consumer);
    }

}
