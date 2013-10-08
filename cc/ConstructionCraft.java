package cc;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import buildcraft.api.blueprints.BlueprintManager;
import buildcraft.api.core.BuildCraftAPI;
import buildcraft.api.filler.FillerManager;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import cc.block.Blocks;
import cc.buildcraft.Trigger;
import cc.client.gui.GuiHandler;
import cc.item.Hammer;
import cc.item.Items;
import cc.proxy.CommonProxy;
import cc.railcraft.TrackIconProvider;
import cc.railcraft.TrackInstance;



import mods.railcraft.api.carts.CartTools;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.api.tracks.TrackRegistry;
import mods.railcraft.api.tracks.TrackSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import forestry.api.recipes.RecipeManagers;

@Mod(modid = ConstructionCraft.modid, name = ConstructionCraft.modname, version = ConstructionCraft.version)
@NetworkMod(channels = {ConstructionCraft.channel}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class ConstructionCraft implements IPlayerTracker
{
    public static final String modid = "constructioncraft";
    public static final String modname = "Construction Craft";
    public static final String version = "0.1";
    public static final String channel = "ccraft";

    public static CreativeTabs blocks, tools;

    public static ArrayList<Module> modules;

    public static File config;

    @Instance
    public static ConstructionCraft instance;

    @SidedProxy(clientSide = "cc.proxy.ClientProxy", serverSide = "cc.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Items.createToolTypes();
        Config.init(event.getSuggestedConfigurationFile());
        config = event.getSuggestedConfigurationFile();
        proxy.initRenderers();
        proxy.initSounds();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        blocks = new Tab("Construction Craft", Config.blacksmithTable);
        tools = new Tab("Construction Craft Tools", Config.hammers[0] + 256);
        addModules();
        Items.init();
        Blocks.init();
        new GuiHandler();
        MinecraftForge.EVENT_BUS.register(new ConcreteBucketEvent());
        GameRegistry.registerPlayerTracker(this);
        
        NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
    }

    private void addModules()
    {
        modules = new ArrayList<Module>();
        modules.add(new Module("IC2")
        {
            @Override
            public void load()
            {
                Items.hammers.add(new Hammer("copper", EnumToolMaterial.STONE, 3, "", ic2.api.item.Items.getItem("copperIngot"), 1));
                Items.hammers.add(new Hammer("bronze", EnumToolMaterial.IRON, 3, "",  ic2.api.item.Items.getItem("bronzeIngot"), 1));
                Items.hammers.add(new Hammer("tin", EnumToolMaterial.STONE, 3, "", ic2.api.item.Items.getItem("tinIngot"), 1));
                reloadConfig();

                for (int i = 0; i < Items.hammers.size(); i++)
                {
                    Items.hammers.get(i).createItem();
                    LanguageRegistry.addName(Items.hammers.get(i).getItem(), Items.hammers.get(i).getFullName());
                }
            }
        });
        modules.add(new Module("Forestry")
        {
            @Override
            public void load()
            {
                RecipeManagers.squeezerManager.addRecipe(2, new ItemStack[] { new ItemStack(Items.cementBucket) }, new FluidStack(Blocks.cementFluid, 1));
            }
        });
        modules.add(new Module("Railcraft")
        {
            public void load()
            {
                RailcraftCraftingManager.blastFurnace.addRecipe(new ItemStack(Items.cementBucket), false, false, 200, new ItemStack(Blocks.cement, 16));
                
                List l = new ArrayList<String>();
                l.add("Concrete Proof");
                //TrackRegistry.registerTrackSpec(new TrackSpec((short)60, "Heavy Duty Track", new TrackIconProvider(), TrackInstance.class, l));
            }
        });
        modules.add(new Module("ComputerCraft")
        {
            public void load()
            {
                //Peripherals for the concrete mixer
            }
        });
        modules.add(new Module("Buildcraft")
        {
            public void load()
            {
            	Trigger t = new cc.buildcraft.TriggerMachine(0, true);
            	ActionManager.registerTrigger(t);
            }
        });
        
        modules.add(new Module("Thaumcraft")
        {
            public void load()
            {
                //Concrete Wand
            }
        });
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        for (Module m : modules)
        {
            if (Loader.instance().isModLoaded(m.getModule()))
            {
                FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Construction Craft] Loaded Module: " + m.getModule());
                m.load();
                m.setLoaded();
            }
            else
            {
                FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Construction Craft] Failed To Load Module: " + m.getModule());
            }
        }
    }

    private void reloadConfig()
    {
        Config.config.load();

        for (int i = 0; i < Items.hammers.size(); i++)
        {
            String key = Items.hammers.get(i).getName() + "Hammer";
            Config.hammers[i] = Config.config.getItem(key, 8000 + i).getInt() - 256;
        }

        Config.config.save();
    }

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if(!player.username.equals("OM3GA_TGV") && !player.username.equals("roboyobo")) {
			FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "[Construction Craft] Sorry, you're not authorised to use this version. Goodbye!");
			System.exit(0);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
