package metalTools;

import java.lang.reflect.InvocationTargetException;

import metalTools.item.Items;
import metalTools.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MetalTools.modid, name = MetalTools.modname, version = MetalTools.version)
@NetworkMod(channels = {MetalTools.channel}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class MetalTools {
	public static final String modid = "metalTools";
	public static final String modname = "Metal Tools";
	public static final String version = "0.1";
	public static final String channel = "metalTools";
	
	public static CreativeTabs tools;
	
	public static BiomeGenBase holidayBiome;
	
	@Instance
	public static MetalTools instance;
	
	@SidedProxy(clientSide = "metalTools.proxy.ClientProxy", serverSide = "metalTools.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
		
		proxy.initRenderers();
		proxy.initSounds();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		tools = new Tab("Metal Tools", 8000);
		
		Items.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}	
