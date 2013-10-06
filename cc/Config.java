package cc;

import java.io.File;

import cc.item.Items;




import net.minecraftforge.common.Configuration;

public class Config
{
    /**
     * Item ID's
     */
    public static int[] hammers = new int[100];
    public static int handle, cementBucket, cementMixerItem;

    /**
     * Block ID's
     */
    public static int cement, reinforcedGlass, blacksmithTable, cementFluidBlock, whiteStoneSet, blackStoneSet, ironPlate, wormhole, cementMixer, cementMixerOn;

    public static Configuration config;

    public static void init(File file)
    {
        config = new Configuration(file);
        config.load();

        for (int i = 0; i < Items.hammers.size(); i++)
        {
            String key = Items.hammers.get(i).getName() + "Hammer";
            hammers[i] = config.getItem(key, 16000 + i).getInt() - 256;
        }

        handle = config.getItem("Tool Handle", 8000).getInt() - 256;
        cementBucket = config.getItem("Concrete Bucket", 8001).getInt() - 256;
        cementMixerItem = config.getItem("Concrete Mixer Item", 8002).getInt() - 256;
        
        cement = config.getBlock("Concrete Block", 3000).getInt();
        reinforcedGlass = config.getBlock("Reinforced Glass", 3001).getInt();
        blacksmithTable = config.getBlock("Blacksmith Table", 3002).getInt();
        cementFluidBlock = config.getBlock("Concrete Fluid", 3003).getInt();
        whiteStoneSet = config.getBlock("White Stone Set", 3004).getInt();
        blackStoneSet = config.getBlock("Black Stone Set", 3005).getInt();
        ironPlate = config.getBlock("Iron Plate", 3006).getInt();
        wormhole = config.getBlock("Wormhole", 3007).getInt();
        cementMixer = config.getBlock("Cement Mixer", 3008).getInt();
        cementMixerOn = config.getBlock("Cement Mixer On", 3009).getInt();
        
        config.save();
    }
}
