package constructionCraft.block;
import constructionCraft.ConstructionCraft;
import constructionCraft.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupport extends Block {
		
	public BlockSupport(int id) {
		super(id, Material.rock);
	    setCreativeTab(ConstructionCraft.tools);
	    setUnlocalizedName("" + Items.lastNum++);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	       
	@SideOnly(Side.CLIENT)
	private Icon normal1;
	@SideOnly(Side.CLIENT)
	private Icon normal2;
	@SideOnly(Side.CLIENT)
	private Icon topCorner1;
	@SideOnly(Side.CLIENT)
	private Icon topCorner2;
	@SideOnly(Side.CLIENT)
	private Icon topCorner3;
	@SideOnly(Side.CLIENT)
	private Icon topCorner4;
	       
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		normal1 = icon.registerIcon(ConstructionCraft.modid + ":supportNormal1");
		normal2 = icon.registerIcon(ConstructionCraft.modid + ":supportNormal2");
		topCorner1 = icon.registerIcon(ConstructionCraft.modid + ":supportTopCorner1");
		topCorner2 = icon.registerIcon(ConstructionCraft.modid + ":supportTopCorner2");
		topCorner3 = icon.registerIcon(ConstructionCraft.modid + ":supportTopCorner3");
		topCorner4 = icon.registerIcon(ConstructionCraft.modid + ":supportTopCorner4");
	}
	   
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int side) {
		setTextureName(ConstructionCraft.modid + ":supportNormal");
		if(side == 1) {
			if(Minecraft.getMinecraft().theWorld.getBlockId(x - 1, y, z) == Blocks.metalSupport.blockID && Minecraft.getMinecraft().theWorld.getBlockId(x, y, z - 1) == Blocks.metalSupport.blockID) {
				return topCorner1;
			}
			if(Minecraft.getMinecraft().theWorld.getBlockId(x + 1, y, z) == Blocks.metalSupport.blockID && Minecraft.getMinecraft().theWorld.getBlockId(x, y, z + 1) == Blocks.metalSupport.blockID) {
				return topCorner2;
			}
			if(Minecraft.getMinecraft().theWorld.getBlockId(x - 1, y, z) == Blocks.metalSupport.blockID && Minecraft.getMinecraft().theWorld.getBlockId(x, y, z + 1) == Blocks.metalSupport.blockID) {
				return topCorner3;
			}
			if(Minecraft.getMinecraft().theWorld.getBlockId(x + 1, y, z) == Blocks.metalSupport.blockID && Minecraft.getMinecraft().theWorld.getBlockId(x, y, z - 1) == Blocks.metalSupport.blockID) {
				return topCorner4;
			}
			if(Minecraft.getMinecraft().theWorld.getBlockId(x + 1, y, z) == Blocks.metalSupport.blockID) {
				return normal2;
			}
			if(Minecraft.getMinecraft().theWorld.getBlockId(x - 1, y, z) == Blocks.metalSupport.blockID) {
				return normal2;
			}
			else {
				return normal1;
			}
		}
		else {
			return normal1;
		}
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return normal1;
	}
}