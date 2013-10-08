package cc.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import cc.Config;
import cc.ConstructionCraft;
import cc.block.tileentity.TileEntityConcreteMixer;
import cc.item.ItemColouredStoneSet;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks
{
    public static Fluid cementFluid;
    public static Block cement, reinforcedGlass, blacksmithTable, cementFluidBlock, whiteStone, blackStone, ironPlate, wormhole, cementMixer, cementMixerOn;

    public static String[] types = {"Stone", "Brick", "Smooth Brick"};
	public static Icon cementTrackIcon;

    private static void create()
    {
        cementFluid = new CementFluid();
        cementFluidBlock = new BlockCementFluid(Config.cementFluidBlock);
        cement = new BasicBlock(Config.cement).setCreativeTab(ConstructionCraft.blocks).setTextureName(ConstructionCraft.modid + ":concrete").setHardness(2F);
        reinforcedGlass = new BlockMetalSupport(Config.reinforcedGlass).setCreativeTab(ConstructionCraft.blocks).setHardness(2F);
        blacksmithTable = new BlockBlacksmithTable(Config.blacksmithTable).setHardness(2F);
        whiteStone = new BlockColouredStoneSet(Config.whiteStoneSet, "white").setHardness(2F);
        blackStone = new BlockColouredStoneSet(Config.blackStoneSet, "black").setHardness(2F);
        ironPlate = new BlockMetalPlate(Config.ironPlate).setCreativeTab(ConstructionCraft.blocks).setHardness(2F);
        wormhole = new BlockWormhole(Config.wormhole).setHardness(2F);
        cementMixer = new BlockConcreteMixer(Config.cementMixer, false).setHardness(2F);
        cementMixerOn = new BlockConcreteMixer(Config.cementMixerOn, true).setHardness(2F);
    }

    private static void register()
    {
        GameRegistry.registerBlock(cement, "concrete");
        GameRegistry.registerBlock(reinforcedGlass, "reinforcedGlass");
        GameRegistry.registerBlock(blacksmithTable, "blacksmithTable");
        GameRegistry.registerBlock(cementFluidBlock, "concreteFluidBlock");
        GameRegistry.registerBlock(whiteStone, ItemColouredStoneSet.class, "whiteStoneSet");
        GameRegistry.registerBlock(blackStone, ItemColouredStoneSet.class, "blackStoneSet");
        GameRegistry.registerBlock(ironPlate, "ironPlate");
        GameRegistry.registerBlock(wormhole, "wormhole");
        GameRegistry.registerBlock(cementMixer, "concreteMixer");
        GameRegistry.registerBlock(cementMixerOn, "concreteMixerOn");
        
        GameRegistry.registerTileEntity(TileEntityConcreteMixer.class, "tileEntityConcreteMixer");
    }

    private static void name()
    {
        LanguageRegistry.addName(cement, "Cement Block");
        LanguageRegistry.addName(reinforcedGlass, "Reinforced Glass");
        LanguageRegistry.addName(blacksmithTable, "Blacksmith Table");
        LanguageRegistry.addName(cementFluidBlock, "Liquid Cement");
        LanguageRegistry.addName(ironPlate, "Iron Plate");
        LanguageRegistry.addName(wormhole, "Wormhole");
        LanguageRegistry.addName(cementMixer, "Cement Mixer");
        LanguageRegistry.addName(cementMixerOn, "Cement Mixer Active");

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
