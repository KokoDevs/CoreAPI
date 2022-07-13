package me.kokodevs.coreapi.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemsUtils {
    public ItemsUtils(){}
    public static void removeItems(ItemStack material, Player p, int amount) {
        for (int i = 0; i < 36; i++) {
            ItemStack item = p.getInventory().getItem(i);

            if (item == null) continue;

            if (item.getItemMeta().getDisplayName().equals(material.getItemMeta().getDisplayName())) {
                if (item.getAmount() >= amount) {
                    item.setAmount(item.getAmount() - amount);
                    p.getInventory().setItem(i, item);
                    break;
                } else {
                    amount -= item.getAmount();
                    p.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }
        }
    }
    public static void removeItems(ItemStack material, Player p) {
        removeItems(material, p, 1);
    }

    public static ItemStack createItem(Material mat, int amount, Boolean glowing, Boolean unbreakable, String name, List<String> lore){
        ItemStack item = new ItemStack(mat);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        if(unbreakable) {
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        if(glowing){
            item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createItem(Material mat, int amount, Boolean glowing, String name, List<String> lore){
        return createItem(mat, amount, glowing, false, name, lore);
    }
    public static ItemStack createItem(Material mat, int amount, String name, List<String> lore){
        return createItem(mat, amount, false, false, name, lore);
    }
    public static ItemStack createItem(Material mat, String name, List<String> lore){
        return createItem(mat, 1, false, false, name, lore);
    }

    public static List<String> createLore(String lines){
        List<String> loreFix;
        if(!lines.equals("")){
            String[] lore = lines.split("%nl%");
            loreFix = Arrays.asList(lore);
        }else{
            loreFix = new ArrayList<>();
        }
        return loreFix;
    }
    public static ItemStack setLore(ItemStack item, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack SetDisplayName(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getHead(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Custom head");
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }
}
