package com.mowmaster.focusedbooks.data.Client;

import com.mowmaster.focusedbooks.items.ItemEnchantableBook;
import com.mowmaster.focusedbooks.references.Reference;
import net.minecraft.data.DataGenerator;
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

        builder(itemGenerated,"book_protection");
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
        builder(itemGenerated,"book_vanishingcurse");
        builder(itemGenerated,"focus_base");

        if(ModList.get().isLoaded("apotheosis"))
        {
            builder(itemGenerated,"book_apothbaneofillagers");
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
            builder(itemGenerated,"focus_apoth");
        }
    }

    private ItemModelBuilder builder(ModelFile itemGenerated,String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
