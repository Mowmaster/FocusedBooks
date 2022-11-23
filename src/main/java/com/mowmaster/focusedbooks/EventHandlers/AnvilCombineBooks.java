package com.mowmaster.focusedbooks.EventHandlers;

import com.mowmaster.focusedbooks.Items.BaseEnchantableBook;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class AnvilCombineBooks
{
    @SubscribeEvent()
    public static void ApplyBooks(AnvilUpdateEvent event)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        if (!event.getLeft().isEmpty()) {
            ItemStack itemstack = event.getLeft();
            ItemStack itemstack1 = itemstack.copy();
            if (!event.getRight().isEmpty()) {
                ItemStack itemstack2 = event.getRight();
                if(itemstack2.getItem() instanceof BaseEnchantableBook enchantableBook)
                {
                    boolean flag = itemstack2.getItem() instanceof BaseEnchantableBook && !EnchantedBookItem.getEnchantments(itemstack2).isEmpty();

                    Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack1);
                    Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(itemstack2);
                    boolean flag2 = false;
                    boolean flag3 = false;

                    for(Enchantment enchantment1 : map1.keySet()) {
                        if (enchantment1 != null) {
                            int i2 = map.getOrDefault(enchantment1, 0);
                            int j2 = map1.get(enchantment1);
                            j2 = i2 == j2 ? j2 + 1 : Math.max(j2, i2);
                            boolean flag1 = enchantment1.canEnchant(itemstack);
                            if (event.getPlayer().getAbilities().instabuild || itemstack.getItem() instanceof BaseEnchantableBook) {
                                flag1 = true;
                            }

                            for(Enchantment enchantment : map.keySet()) {
                                boolean incompatableCombining = (enchantableBook.allowInCompatibleCombining(itemstack2))?(enchantment1.isCompatibleWith(enchantment)):(!enchantment1.isCompatibleWith(enchantment));
                                if (enchantment != enchantment1 && incompatableCombining) {
                                    flag1 = false;
                                    ++i;
                                }
                            }

                            if (!flag1) {
                                flag3 = true;
                            } else {
                                flag2 = true;

                                if(!enchantableBook.allowOverCombining(itemstack2))
                                {
                                    if (j2 > enchantment1.getMaxLevel()) {
                                        j2 = enchantment1.getMaxLevel();
                                    }
                                }
                                else
                                {
                                    if(enchantment1.getMaxLevel() != enchantableBook.maxEnchantLevel(itemstack2))
                                    {
                                        if (j2 > enchantableBook.maxEnchantLevel(itemstack2)) {
                                            j2 = enchantableBook.maxEnchantLevel(itemstack2);
                                        }
                                    }
                                }

                                map.put(enchantment1, j2);
                                int k3 = 0;
                                switch (enchantment1.getRarity()) {
                                    case COMMON:
                                        k3 = 1;
                                        break;
                                    case UNCOMMON:
                                        k3 = 2;
                                        break;
                                    case RARE:
                                        k3 = 4;
                                        break;
                                    case VERY_RARE:
                                        k3 = 8;
                                }

                                if (flag) {
                                    k3 = Math.max(1, k3 / 2);
                                }

                                i += k3 * j2;
                                if (itemstack.getCount() > 1) {
                                    i = 40;
                                }
                            }
                        }
                    }

                    event.setCost(j + i);

                    if (k == i && k > 0 && event.getCost() >= 40) {
                        event.setCost(39);
                    }

                    if (event.getCost() >= 40 && !event.getPlayer().getAbilities().instabuild) {
                        itemstack1 = ItemStack.EMPTY;
                        event.setOutput(itemstack1);
                    }

                    if (flag3 && !flag2) {
                        event.setOutput(ItemStack.EMPTY);
                        event.setCost(0);
                        return;
                    }

                    if (!itemstack1.isEmpty() && ((itemstack1.getItem() instanceof BaseEnchantableBook)?(itemstack1.getCount() == 1):(true)) && itemstack2.isEnchanted() && itemstack2.getCount() == 1 ) {
                        int k2 = itemstack1.getBaseRepairCost();
                        if (!itemstack2.isEmpty() && k2 < itemstack2.getBaseRepairCost()) {
                            k2 = itemstack2.getBaseRepairCost();
                        }

                        if (k != i || k == 0) {
                            k2 = calculateIncreasedRepairCost(k2);
                        }

                        if(event.getName() != "")
                        {
                            itemstack1.setHoverName(Component.literal(event.getName()));
                        }

                        itemstack1.setRepairCost(k2);
                        EnchantmentHelper.setEnchantments(map, itemstack1);
                        event.setOutput(itemstack1);
                    }
                }
            }
        }
    }

    public static int calculateIncreasedRepairCost(int p_39026_) {
        return p_39026_ * 2 + 1;
    }
}
