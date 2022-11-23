package com.mowmaster.focusedbooks.Registry;

import com.mowmaster.focusedbooks.Items.BookFocused;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static com.mowmaster.focusedbooks.focusedbooks.MODID;

public class ItemModelProperties
{
    public static void FocusedBooksItemVariants(Item item)
    {
        ItemProperties.register(item, new ResourceLocation(MODID + ":cover_variant"),(p_174625_, p_174626_, p_174627_, p_174628_) -> {
            return BookFocused.getRenderBookCover(p_174625_);});
    }
}
