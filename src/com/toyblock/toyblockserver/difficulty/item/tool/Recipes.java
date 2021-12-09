package com.toyblock.toyblockserver.difficulty.item.tool;

import com.toyblock.toyblockserver.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;

import java.util.ArrayList;

public class Recipes {
    public static ShapedRecipe getRecipe_WoodenSword() {
        MakeSword sword = new MakeSword();
        sword.setWoodenSword();
        ItemStack item = sword.getSword(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Wooden_Sword");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape(" W "," W "," S ");

        recipe.setIngredient('W', Material.OAK_PLANKS);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_StoneSword() {
        MakeSword sword = new MakeSword();
        sword.setStoneSword();
        ItemStack item = sword.getSword(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Stone_Sword");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape(" W "," W "," S ");

        recipe.setIngredient('W', Material.COBBLESTONE);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_IronSword() {
        MakeSword sword = new MakeSword();
        sword.setIronSword();
        ItemStack item = sword.getSword(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Iron_Sword");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape(" W "," W "," S ");

        recipe.setIngredient('W', Material.IRON_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_GoldenSword() {
        MakeSword sword = new MakeSword();
        sword.setGoldenSword();
        ItemStack item = sword.getSword(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Golden_Sword");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape(" W "," W "," S ");

        recipe.setIngredient('W', Material.GOLD_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_DiamondSword() {
        MakeSword sword = new MakeSword();
        sword.setDiamondSword();
        ItemStack item = sword.getSword(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Diamond_Sword");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape(" W "," W "," S ");

        recipe.setIngredient('W', Material.DIAMOND);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_WoodenPickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setWoodenPickaxe();
        ItemStack item = make.getPickaxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Wooden_Pickaxe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW"," S "," S ");

        recipe.setIngredient('W', Material.OAK_PLANKS);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_StonePickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setStonePickaxe();
        ItemStack item = make.getPickaxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Stone_Pickaxe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW"," S "," S ");

        recipe.setIngredient('W', Material.COBBLESTONE);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_GoldenPickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setGoldenPickaxe();
        ItemStack item = make.getPickaxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Golden_Pickaxe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW"," S "," S ");

        recipe.setIngredient('W', Material.GOLD_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_IronPickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setIronPickaxe();
        ItemStack item = make.getPickaxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Iron_Pickaxe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW"," S "," S ");

        recipe.setIngredient('W', Material.IRON_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_DiamondPickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setDiamondPickaxe();
        ItemStack item = make.getPickaxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Diamond_Pickaxe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW"," S "," S ");

        recipe.setIngredient('W', Material.DIAMOND);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_WoodenAxe() {
        MakeAxe make = new MakeAxe();
        make.setWoodenAxe();
        ItemStack item = make.getAxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Wooden_Axe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW","WS "," S ");

        recipe.setIngredient('W', Material.OAK_PLANKS);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_StoneAxe() {
        MakeAxe make = new MakeAxe();
        make.setStoneAxe();
        ItemStack item = make.getAxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Stone_Axe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW","WS "," S ");

        recipe.setIngredient('W', Material.COBBLESTONE);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_GoldenAxe() {
        MakeAxe make = new MakeAxe();
        make.setGoldenAxe();
        ItemStack item = make.getAxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Golden_Axe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW","WS "," S ");

        recipe.setIngredient('W', Material.GOLD_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_DiamondAxe() {
        MakeAxe make = new MakeAxe();
        make.setDiamondAxe();
        ItemStack item = make.getAxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Diamond_Axe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW","WS "," S ");

        recipe.setIngredient('W', Material.DIAMOND);
        recipe.setIngredient('S',Material.STICK);

        return recipe;

    }
    public static ShapedRecipe getRecipe_IronAxe() {
        MakeAxe make = new MakeAxe();
        make.setWoodenAxe();
        ItemStack item = make.getAxe(1);

        NamespacedKey Key = new NamespacedKey(Main.getPlugin(Main.class),"Iron_Axe");

        ShapedRecipe recipe = new ShapedRecipe(Key,item);

        recipe.shape("WWW","WS "," S ");

        recipe.setIngredient('W', Material.IRON_INGOT);
        recipe.setIngredient('S',Material.STICK);

        return recipe;
    }

    public static SmithingRecipe getSmithingRecipe_WoodenSword() {
        MakeSword make = new MakeSword();
        make.setWoodenSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"WoodenSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.WOODEN_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.FLINT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_StoneSword() {
        MakeSword make = new MakeSword();
        make.setStoneSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"StoneSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.STONE_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.FLINT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_GoldSword() {
        MakeSword make = new MakeSword();
        make.setGoldenSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"GoldSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.GOLD_INGOT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_IronSword() {
        MakeSword make = new MakeSword();
        make.setIronSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"IronSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.IRON_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.IRON_INGOT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_DiamondSword() {
        MakeSword make = new MakeSword();
        make.setDiamondSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"DiamondSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.DIAMOND);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_NetheriteSword() {
        MakeSword make = new MakeSword();
        make.setNetheriteSword();
        ItemStack makeItem = make.UpgradeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"NetheriteSwordUpgrade");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.DIAMOND);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_UpNetheriteSword() {
        MakeSword make = new MakeSword();
        make.setNetheriteSword();
        ItemStack makeItem = make.ChangeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"NetheriteSword");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_UpNetheritePickaxe() {
        MakePickaxe make = new MakePickaxe();
        make.setNetheritePickaxe();
        ItemStack makeItem = make.ChangeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"NetheritePickaxe");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }
    public static SmithingRecipe getSmithingRecipe_UpNetheriteAxe() {
        MakeAxe make = new MakeAxe();
        make.setNetheriteAxe();
        ItemStack makeItem = make.ChangeGUI();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class),"NetheriteAxe");
        RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD);
        RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT);
        SmithingRecipe recipe = new SmithingRecipe(key,makeItem,choice1,choice2,false);
        return recipe;
    }


    public static void  putRecipe() {
        Bukkit.addRecipe( getRecipe_WoodenSword() );
        Bukkit.addRecipe( getRecipe_StoneSword() );
        Bukkit.addRecipe( getRecipe_GoldenSword() );
        Bukkit.addRecipe( getRecipe_IronSword() );
        Bukkit.addRecipe( getRecipe_DiamondSword() );

        Bukkit.addRecipe( getRecipe_WoodenPickaxe() );
        Bukkit.addRecipe( getRecipe_StonePickaxe() );
        Bukkit.addRecipe( getRecipe_GoldenPickaxe() );
        Bukkit.addRecipe( getRecipe_IronPickaxe() );
        Bukkit.addRecipe( getRecipe_DiamondPickaxe() );

        Bukkit.addRecipe( getRecipe_WoodenAxe() );
        Bukkit.addRecipe( getRecipe_StoneAxe() );
        Bukkit.addRecipe( getRecipe_GoldenAxe() );
        Bukkit.addRecipe( getRecipe_IronAxe() );
        Bukkit.addRecipe( getRecipe_DiamondAxe() );

        Bukkit.addRecipe( getSmithingRecipe_WoodenSword() );
        Bukkit.addRecipe( getSmithingRecipe_StoneSword() );
        Bukkit.addRecipe( getSmithingRecipe_GoldSword() );
        Bukkit.addRecipe( getSmithingRecipe_IronSword() );
        Bukkit.addRecipe( getSmithingRecipe_DiamondSword() );
        Bukkit.addRecipe( getSmithingRecipe_NetheriteSword() );

        Bukkit.addRecipe( getSmithingRecipe_UpNetheriteSword() );
        Bukkit.addRecipe( getSmithingRecipe_UpNetheritePickaxe() );
        Bukkit.addRecipe( getSmithingRecipe_UpNetheriteAxe() );


    }



}
