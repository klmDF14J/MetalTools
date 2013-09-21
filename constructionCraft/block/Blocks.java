package constructionCraft.block;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import constructionCraft.Config;
import constructionCraft.ConstructionCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	
	public static Fluid concreteFluid;
	public static Block concrete, metalSupport, blacksmithTable, concreteFluidBlock;
	
	private static void create() {
		concreteFluid = new ConcreteFluid();
		concreteFluidBlock = new BlockConcreteFluid(Config.concreteFluidBlock);
		concrete = new BasicBlock(Config.concrete).setCreativeTab(ConstructionCraft.tools).setTextureName(ConstructionCraft.modid + ":concrete");
		metalSupport = new BlockSupport(Config.metalSupport).setCreativeTab(ConstructionCraft.tools).setTextureName(ConstructionCraft.modid + ":supportNormal");
		blacksmithTable = new BlockBlacksmithTable(Config.blacksmithTable);
	}
	
	private static void register() {
		GameRegistry.registerBlock(concrete, "concrete");
		GameRegistry.registerBlock(metalSupport, "metalSupport");
		GameRegistry.registerBlock(blacksmithTable, "blacksmithTable");
		GameRegistry.registerBlock(concreteFluidBlock, "concreteFluidBlock");
	}
	
	private static void name() {
		LanguageRegistry.addName(concrete, "Concrete Block");
		LanguageRegistry.addName(metalSupport, "Metal Support");
		LanguageRegistry.addName(blacksmithTable, "Blacksmith Table");
		LanguageRegistry.addName(concreteFluidBlock, "Liquid Concrete");
	}
	
	public static void init() {
		create();
		register();
		name();
	}
}
