package cc.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import cc.Config;
import cc.ConstructionCraft;




import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items
{
    public static ArrayList<Hammer> hammers;

    public static Item handle, cementBucket, cementMixerItem;

    public static int lastNum;
    public static int lastHammerID;

    private static void create()
    {
        for (int i = 0; i < hammers.size(); i++)
        {
            hammers.get(i).createItem();
        }

        
        handle = new ItemHandle(Config.handle);
        cementBucket = new ItemConcreteBucket(Config.cementBucket);
        cementMixerItem = new BasicItem(Config.cementMixerItem).setTextureName(ConstructionCraft.modid + ":concreteMixer").setCreativeTab(ConstructionCraft.tools);
    }

    private static void name()
    {
        for (int i = 0; i < hammers.size(); i++)
        {
            LanguageRegistry.addName(hammers.get(i).getItem(), hammers.get(i).getFullName());
        }

        String[] handles = {"Sturdy", "Reinforced", "Encrusted"};

        for (int i = 0; i < 3; i++)
        {
            LanguageRegistry.addName(new ItemStack(handle, 1, i), handles[i] + " Handle");
        }

        LanguageRegistry.addName(cementBucket, "Cement Bucket");
        LanguageRegistry.addName(cementMixerItem, "Cement Mixer");
    }

    private static void addRecipes()
    {
    	GameRegistry.addRecipe(new ItemStack(cementMixerItem), new Object[] {
    		"XYX", "CAC", "ZBZ", 'X', new ItemStack(Block.cloth, 1, 4), 'Y', new ItemStack(Block.cloth, 1, 15), 'Z', new ItemStack(Block.cloth, 1, 1), 'A', Block.dispenser, 'B', Item.redstone, 'C', Block.pistonBase
    	});
    }

    private static Item createBasicItem(Class c, int id) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Constructor<?> ct = c.getConstructor(int.class);
        return (Item) ct.newInstance(id);
    }

    private static void addName(Item i, String name)
    {
        LanguageRegistry.addName(i, name);
    }

    public static void init()
    {
        create();
        name();
        addRecipes();
    }

    public static void createToolTypes()
    {
        hammers = new ArrayList<Hammer>();
        EnumToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("obsidian", EnumToolMaterial.EMERALD.getHarvestLevel(), 3000, 10F, 3F, 10);
        hammers.add(new Hammer("wood", EnumToolMaterial.WOOD, 1, "en", Block.planks, 0));
        hammers.add(new Hammer("stone", EnumToolMaterial.STONE, 3, "", Block.cobblestone, 0));
        hammers.add(new Hammer("iron", EnumToolMaterial.IRON, 5, "", Item.ingotIron, 1));
        hammers.add(new Hammer("gold", EnumToolMaterial.GOLD, 9, "", Item.ingotGold, 2));
        hammers.add(new Hammer("diamond", EnumToolMaterial.EMERALD, 7, "", Item.diamond, 1));
        hammers.add(new Hammer("smelting", EnumToolMaterial.EMERALD, 7, "", Item.diamond, 1));
        hammers.add(new Hammer("obsidian", OBSIDIAN, 9, "", Block.obsidian, 1));
    }
}
