package com.mowmaster.focusedbooks.Registry;

import com.mowmaster.focusedbooks.Items.BaseEnchantableBook;
import com.mowmaster.mowlib.MowLibUtils.MowLibColorReference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "focusedbooks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistry
{
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {

        event.register((stack, color) ->
        {if (color == 1) {
            return BaseEnchantableBook.getCoverColor(stack);
        }
        else if (color == 2) {
            return BaseEnchantableBook.getPageColor(stack);
        }
        else if (color == 3) {
            return BaseEnchantableBook.getAccentColor(stack);
        }
        else {return -1;}
        }, DeferredItemRegistry.BASEBOOK.get());
    }
}
