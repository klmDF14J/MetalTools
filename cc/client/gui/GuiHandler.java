package cc.client.gui;

import cc.ConstructionCraft;
import cc.block.tileentity.TileEntityConcreteMixer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
    public GuiHandler()
    {
        NetworkRegistry.instance().registerGuiHandler(ConstructionCraft.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch(ID) {
    		case 0 : return new ContainerBlacksmithTable(player.inventory, world);
    		case 1 : {
    			TileEntity te = world.getBlockTileEntity(x, y, z);
    			if(te != null && te instanceof TileEntityConcreteMixer) {
    				return new ContainerConcreteMixer(player.inventory, (TileEntityConcreteMixer) te);
    			}
    		}
    			
    		default: return null;
    	}
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch(ID) {
			case 0 : return new GuiBlacksmithTable(player.inventory, world, x, y, z);
			case 1 : {
				TileEntity te = world.getBlockTileEntity(x, y, z);
    			if(te != null && te instanceof TileEntityConcreteMixer) {
    				return new GuiConcreteMixer(player.inventory, (TileEntityConcreteMixer) te);
    			}
			}
			default: return null;
    	}
    }
}
