package cc.block;
import cc.ConstructionCraft;
import cc.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlacksmithTable extends Block
{
    public BlockBlacksmithTable(int id)
    {
        super(id, Material.rock);
        setCreativeTab(ConstructionCraft.blocks);
        setUnlocalizedName("" + Items.lastNum++);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    private Icon side;
    @SideOnly(Side.CLIENT)
    private Icon bottom;
    @SideOnly(Side.CLIENT)
    private Icon topNormal;
    @SideOnly(Side.CLIENT)
    private Icon top1;
    @SideOnly(Side.CLIENT)
    private Icon top2;
    @SideOnly(Side.CLIENT)
    private Icon top3;
    @SideOnly(Side.CLIENT)
    private Icon top4;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        side = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableSide");
        bottom = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableBottom");
        topNormal = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableTopNormal");
        top1 = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableTop1");
        top2 = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableTop2");
        top3 = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableTop3");
        top4 = icon.registerIcon(ConstructionCraft.modid + ":blacksmithTableTop4");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int side)
    {
        setTextureName(ConstructionCraft.modid + ":supportNormal");

        if (side == 1)
        {
            World world = Minecraft.getMinecraft().theWorld;

            if (world.getBlockId(x + 1, y, z) == Blocks.blacksmithTable.blockID)
            {
                return top1;
            }

            if (world.getBlockId(x - 1, y, z) == Blocks.blacksmithTable.blockID)
            {
                return top2;
            }

            if (world.getBlockId(x, y, z + 1) == Blocks.blacksmithTable.blockID)
            {
                return top4;
            }

            if (world.getBlockId(x, y, z - 1) == Blocks.blacksmithTable.blockID)
            {
                return top3;
            }

            return topNormal;
        }
        else if (side == 0)
        {
            return bottom;
        }

        return this.side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return side == 0 ? bottom : side == 1 ? topNormal : this.side;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (world.getBlockId(x - 1, y, z) == Blocks.blacksmithTable.blockID && world.getBlockId(x + 1, y, z) != Blocks.blacksmithTable.blockID)
            {
                FMLNetworkHandler.openGui(player, ConstructionCraft.instance, 0, world, x, y, z);
            }

            if (world.getBlockId(x + 1, y, z) == Blocks.blacksmithTable.blockID && world.getBlockId(x - 1, y, z) != Blocks.blacksmithTable.blockID)
            {
                FMLNetworkHandler.openGui(player, ConstructionCraft.instance, 0, world, x, y, z);
            }

            if (world.getBlockId(x, y, z - 1) == Blocks.blacksmithTable.blockID && world.getBlockId(x, y, z + 1) != Blocks.blacksmithTable.blockID)
            {
                FMLNetworkHandler.openGui(player, ConstructionCraft.instance, 0, world, x, y, z);
            }

            if (world.getBlockId(x, y, z + 1) == Blocks.blacksmithTable.blockID && world.getBlockId(x, y, z - 1) != Blocks.blacksmithTable.blockID)
            {
                FMLNetworkHandler.openGui(player, ConstructionCraft.instance, 0, world, x, y, z);
            }
        }

        return true;
    }
}