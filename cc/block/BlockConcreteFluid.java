package cc.block;

import java.util.Random;

import cc.ConstructionCraft;
import cc.PacketHandler;
import cc.item.Items;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockConcreteFluid extends BlockFluidClassic
{
    public BlockConcreteFluid(int id)
    {
        super(id, Blocks.concreteFluid, Material.water);
        Blocks.concreteFluid.setBlockID(id);
        setTickRandomly(true);
        setUnlocalizedName("" + Items.lastNum++);
    }

    @SideOnly(Side.CLIENT)
    private Icon concreteFluid;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        concreteFluid = icon.registerIcon(ConstructionCraft.modid
                                          + ":concreteFluid");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return concreteFluid;
    }

    int numTicks;

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random par5Random)
    {
        if (numTicks >= 40)
        {
            PacketHandler.sendConcreteHardenPacket(x, y, z);
            numTicks = 0;
        }

        numTicks++;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int quantaRemaining = quantaPerBlock - world.getBlockMetadata(x, y, z);
        int expQuanta = -101;

        // check adjacent block levels if non-source
        if (quantaRemaining < quantaPerBlock)
        {
            int y2 = y - densityDir;

            if (world.getBlockId(x, y2, z) == blockID
                    || world.getBlockId(x - 1, y2, z) == blockID
                    || world.getBlockId(x + 1, y2, z) == blockID
                    || world.getBlockId(x, y2, z - 1) == blockID
                    || world.getBlockId(x, y2, z + 1) == blockID)
            {
                expQuanta = quantaPerBlock - 1;
            }
            else
            {
                int maxQuanta = -100;
                maxQuanta = getLargerQuanta(world, x - 1, y, z, maxQuanta);
                maxQuanta = getLargerQuanta(world, x + 1, y, z, maxQuanta);
                maxQuanta = getLargerQuanta(world, x, y, z - 1, maxQuanta);
                maxQuanta = getLargerQuanta(world, x, y, z + 1, maxQuanta);
                expQuanta = maxQuanta - 1;
            }

            // decay calculation
            if (expQuanta != quantaRemaining)
            {
                quantaRemaining = expQuanta;

                if (expQuanta <= 0)
                {
                    world.setBlockToAir(x, y, z);
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y, z, quantaPerBlock
                                                     - expQuanta, 3);
                    world.scheduleBlockUpdate(x, y, z, blockID, tickRate);
                    world.notifyBlocksOfNeighborChange(x, y, z, blockID);
                }
            }
        }
        // This is a "source" block, set meta to zero, and send a server only
        // update
        else if (quantaRemaining >= quantaPerBlock)
        {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        // Flow vertically if possible
        if (canDisplace(world, x, y + densityDir, z))
        {
            flowIntoBlock(world, x, y + densityDir, z, 1);
            return;
        }

        // Flow outward if possible
        int flowMeta = quantaPerBlock - quantaRemaining + 1;

        if (flowMeta >= quantaPerBlock)
        {
            return;
        }

        if (isSourceBlock(world, x, y, z)
                || !isFlowingVertically(world, x, y, z))
        {
            if (world.getBlockId(x, y - densityDir, z) == blockID)
            {
                flowMeta = 1;
            }

            boolean flowTo[] = getOptimalFlowDirections(world, x, y, z);

            if (flowTo[0])
            {
                flowIntoBlock(world, x - 1, y, z, flowMeta);
            }

            if (flowTo[1])
            {
                flowIntoBlock(world, x + 1, y, z, flowMeta);
            }

            if (flowTo[2])
            {
                flowIntoBlock(world, x, y, z - 1, flowMeta);
            }

            if (flowTo[3])
            {
                flowIntoBlock(world, x, y, z + 1, flowMeta);
            }
        }
    }
}