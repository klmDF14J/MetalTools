package metalTools;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class Config {
	
	/**
	 * Item ID's
	 */
	public static int hammerWood, hammerStone, hammerIron, hammerGold, hammerDiamond, hammerSmelting;
	
	/**
	 * Block ID's
	 */
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		hammerWood = config.getItem("Wooden Hammer", 8000).getInt() - 256;
		hammerStone = config.getItem("Stone Hammer", 8001).getInt() - 256;
		hammerIron = config.getItem("Iron Hammer", 8002).getInt() - 256;
		hammerGold = config.getItem("Gold Hammer", 8003).getInt() - 256;
		hammerDiamond = config.getItem("Diamond Hammer", 8004).getInt() - 256;
		hammerSmelting = config.getItem("Smelting Hammer", 8005).getInt() - 256;
		
		config.save();
	}
}
