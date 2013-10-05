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
    public static int handle, concreteBucket, concreteMixerItem;

    /**
     * Block ID's
     */
    public static int concrete, reinforcedGlass, blacksmithTable, concreteFluidBlock, whiteStoneSet, blackStoneSet, ironPlate, wormhole, concreteMixer, concreteMixerOn;

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
        concreteBucket = config.getItem("Concrete Bucket", 8001).getInt() - 256;
        //concreteMixerItem = config.getItem("Concrete Mixer Item", 8002).getInt() - 256;
        
        concrete = config.getBlock("Concrete Block", 3000).getInt();
        reinforcedGlass = config.getBlock("Reinforced Glass", 3001).getInt();
        blacksmithTable = config.getBlock("Blacksmith Table", 3002).getInt();
        concreteFluidBlock = config.getBlock("Concrete Fluid", 3003).getInt();
        whiteStoneSet = config.getBlock("White Stone Set", 3004).getInt();
        blackStoneSet = config.getBlock("Black Stone Set", 3005).getInt();
        ironPlate = config.getBlock("Iron Plate", 3006).getInt();
        wormhole = config.getBlock("Wormhole", 3007).getInt();
        concreteMixer = config.getBlock("Concrete Mixer", 3008).getInt();
        concreteMixerOn = config.getBlock("Concrete Mixer On", 3009).getInt();
        
        config.save();
    }
}
