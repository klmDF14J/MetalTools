package constructionCraft;

import java.io.File;

import constructionCraft.item.Items;

import net.minecraftforge.common.Configuration;

public class Config {
	
	/**
	 * Item ID's
	 */
	public static int[] hammers = new int[100];
	public static int handle, concreteBucket;
	
	/**
	 * Block ID's
	 */
	public static int concrete, metalSupport, blacksmithTable, concreteFluidBlock;
	
	public static Configuration config;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		config.load();
		
		for(int i = 0; i < Items.hammers.size(); i++) {
			String key = Items.hammers.get(i).getName() + "Hammer";
			hammers[i] = config.getItem(key, 16000 + i).getInt() - 256;
		}
		
		handle = config.getItem("Tool Handle", 8000).getInt() - 256;
		concreteBucket = config.getItem("Concrete Bucket", 8001).getInt() - 256;
		
		concrete = config.getBlock("Concrete Block", 3000).getInt();
		metalSupport = config.getBlock("Metal Framework", 3001).getInt();
		blacksmithTable = config.getBlock("Blacksmith Table", 3002).getInt();
		concreteFluidBlock = config.getBlock("Concrete Fluid", 3003).getInt();
		
		config.save();
	}
}
