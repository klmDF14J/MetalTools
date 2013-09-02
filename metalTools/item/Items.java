package metalTools.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import metalTools.Config;
import metalTools.MetalTools;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	
	public static Item hammerWood, hammerStone, hammerIron, hammerGold, hammerDiamond, hammerSmelting;
	public static int lastNum;
	
	private static void create() {
		hammerWood = new ItemHammer(Config.hammerWood, EnumToolMaterial.WOOD.getDamageVsEntity(), EnumToolMaterial.WOOD, 1).func_111206_d("metal:woodHammer");
		hammerStone = new ItemHammer(Config.hammerStone, EnumToolMaterial.STONE.getDamageVsEntity(), EnumToolMaterial.STONE, 3).func_111206_d("metal:stoneHammer");
		hammerIron = new ItemHammer(Config.hammerIron, EnumToolMaterial.IRON.getDamageVsEntity(), EnumToolMaterial.IRON, 5).func_111206_d("metal:ironHammer");
		hammerGold = new ItemHammer(Config.hammerGold, EnumToolMaterial.GOLD.getDamageVsEntity(), EnumToolMaterial.GOLD, 7).func_111206_d("metal:goldHammer");
		hammerDiamond = new ItemHammer(Config.hammerDiamond, EnumToolMaterial.EMERALD.getDamageVsEntity(), EnumToolMaterial.EMERALD, 9).func_111206_d("metal:diamondHammer");
		hammerSmelting = new ItemHammer(Config.hammerSmelting, EnumToolMaterial.EMERALD.getDamageVsEntity(), EnumToolMaterial.EMERALD, 9).func_111206_d("metal:smeltingHammer");
	}
	
	private static void name() {
		addName(hammerWood, "Wooden Hammer");
		addName(hammerStone, "Stone Hammer");
		addName(hammerIron, "Iron Hammer");
		addName(hammerGold, "Gold Hammer");
		addName(hammerDiamond, "Diamond Hammer");
		addName(hammerSmelting, "Smelting Hammer");
	}
	
	private static void setTabs() {
		
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
	}
	
	
}
