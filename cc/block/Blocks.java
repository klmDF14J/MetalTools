package cc.block;

import cc.Config;
import cc.ConstructionCraft;
import cc.block.tileentity.TileEntityConcreteMixer;
import cc.item.ItemColouredStoneSet;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks
{
    public static Fluid concreteFluid;
    public static Block concrete, reinforcedGlass, blacksmithTable, concreteFluidBlock, whiteStone, blackStone, ironPlate, wormhole, concreteMixer, concreteMixerOn;

    public static String[] types = {"Stone", "Brick", "Smooth Brick"};

    private static void create()
    {
        concreteFluid = new ConcreteFluid();
        concreteFluidBlock = new BlockConcreteFluid(Config.concreteFluidBlock);
        concrete = new BasicBlock(Config.concrete).setCreativeTab(ConstructionCraft.blocks).setTextureName(ConstructionCraft.modid + ":concrete");
        reinforcedGlass = new BlockMetalSupport(Config.reinforcedGlass).setCreativeTab(ConstructionCraft.blocks);
        blacksmithTable = new BlockBlacksmithTable(Config.blacksmithTable);
        whiteStone = new BlockColouredStoneSet(Config.whiteStoneSet, "white");
        blackStone = new BlockColouredStoneSet(Config.blackStoneSet, "black");
        ironPlate = new BlockMetalPlate(Config.ironPlate).setCreativeTab(ConstructionCraft.blocks);
        wormhole = new BlockWormhole(Config.wormhole);
        concreteMixer = new BlockConcreteMixer(Config.concreteMixer, false);
        concreteMixerOn = new BlockConcreteMixer(Config.concreteMixerOn, true);
    }

    private static void register()
    {
        GameRegistry.registerBlock(concrete, "concrete");
        GameRegistry.registerBlock(reinforcedGlass, "reinforcedGlass");
        GameRegistry.registerBlock(blacksmithTable, "blacksmithTable");
        GameRegistry.registerBlock(concreteFluidBlock, "concreteFluidBlock");
        GameRegistry.registerBlock(whiteStone, ItemColouredStoneSet.class, "whiteStoneSet");
        GameRegistry.registerBlock(blackStone, ItemColouredStoneSet.class, "blackStoneSet");
        GameRegistry.registerBlock(ironPlate, "ironPlate");
        GameRegistry.registerBlock(wormhole, "wormhole");
        GameRegistry.registerBlock(concreteMixer, "concreteMixer");
        GameRegistry.registerBlock(concreteMixerOn, "concreteMixerOn");
        
        GameRegistry.registerTileEntity(TileEntityConcreteMixer.class, "tileEntityConcreteMixer");
    }

    private static void name()
    {
        LanguageRegistry.addName(concrete, "Concrete Block");
        LanguageRegistry.addName(reinforcedGlass, "Reinforced Glass");
        LanguageRegistry.addName(blacksmithTable, "Blacksmith Table");
        LanguageRegistry.addName(concreteFluidBlock, "Liquid Concrete");
        LanguageRegistry.addName(ironPlate, "Iron Plate");
        LanguageRegistry.addName(wormhole, "Wormhole");
        LanguageRegistry.addName(concreteMixer, "Concrete Mixer");
        LanguageRegistry.addName(concreteMixerOn, "Concrete Mixer Active");

        for (int i = 0; i < BlockColouredStoneSet.numTypes; i++)
        {
            LanguageRegistry.addName(new ItemStack(whiteStone, 1, i), "White " + types[i]);
            LanguageRegistry.addName(new ItemStack(blackStone, 1, i), "Black " + types[i]);
        }
    }
    
    private static void addRecipes() {
    	GameRegistry.addRecipe(new ItemStack(blacksmithTable, 1), new Object[] {
    		"XXX", "Y Y", 'X', Block.blockIron, 'Y', Block.fenceIron
    	});
    }

    public static void init()
    {
        create();
        register();
        name();
        addRecipes();
    }
}
