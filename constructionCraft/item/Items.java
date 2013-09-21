package constructionCraft.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import constructionCraft.Config;
import constructionCraft.ConstructionCraft;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static ArrayList<Hammer> hammers;
	
	public static Item handle, concreteBucket;
	
	public static int lastNum;
	public static int lastHammerID;
	
	private static void create() {
		for(int i = 0; i < hammers.size(); i++) {
			hammers.get(i).createItem();
		}
		
		handle = new ItemHandle(Config.handle);
		concreteBucket = new ItemConcreteBucket(Config.concreteBucket);
	}

	private static void name() {
		for(int i = 0; i < hammers.size(); i++) {
			LanguageRegistry.addName(hammers.get(i).getItem(), hammers.get(i).getFullName());
		}
		
		String[] handles = {"Sturdy", "Reinforced", "Encrusted"};
		for(int i = 0; i < 3; i++) {
			LanguageRegistry.addName(new ItemStack(handle, 1, i), handles[i] + " Handle");
		}
		
		LanguageRegistry.addName(concreteBucket, "Concrete Bucket");
	}
	
	
	private static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(handle, 1, 0), new Object[] {
			"LSL",
			"LSL", 'L', Item.leather, 'S', Item.stick
		});
		GameRegistry.addRecipe(new ItemStack(handle, 1, 1), new Object[] {
			"LSL",
			"LSL", 'L', Item.ingotIron, 'S', Item.stick
		});
		GameRegistry.addRecipe(new ItemStack(handle, 1, 2), new Object[] {
			"DSE",
			"LSL", 'L', Item.ingotGold, 'S', Item.stick, 'D', Item.diamond, 'E', Item.emerald
		});
	}
	
	private static Item createBasicItem(Class c, int id) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> ct = c.getConstructor(int.class);
		return (Item) ct.newInstance(id);
	}
	
	private static void addName(Item i, String name) {
		LanguageRegistry.addName(i, name);
	}
	
	public static void init() {
		create();
		name();
		addRecipes();
	}

	public static void createToolTypes() {
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
