package cc.item;

import cc.ConstructionCraft;
import cc.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.ItemFluidContainer;

public class ItemConcreteBucket extends ItemFluidContainer
{
    public ItemConcreteBucket(int itemID)
    {
        super(itemID);
        setCreativeTab(ConstructionCraft.tools);
        setTextureName(ConstructionCraft.modid + ":concreteBucket");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return item;
        }
        else
        {
            FillBucketEvent event = new FillBucketEvent(player, item, world, movingobjectposition);

            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return item;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                if (player.capabilities.isCreativeMode)
                {
                    return item;
                }

                if (--item.stackSize <= 0)
                {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result))
                {
                    player.dropPlayerItem(event.result);
                }

                return item;
            }

            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, x, y, z))
                {
                    return item;
                }

                if (movingobjectposition.sideHit == 0)
                {
                    --y;
                }

                if (movingobjectposition.sideHit == 1)
                {
                    ++y;
                }

                if (movingobjectposition.sideHit == 2)
                {
                    --z;
                }

                if (movingobjectposition.sideHit == 3)
                {
                    ++z;
                }

                if (movingobjectposition.sideHit == 4)
                {
                    --x;
                }

                if (movingobjectposition.sideHit == 5)
                {
                    ++x;
                }

                if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, item))
                {
                    return item;
                }

                if (this.attemptFill(world, movingobjectposition) == null && !player.capabilities.isCreativeMode)
                {
                    world.setBlock(x, y, z, Blocks.cementFluidBlock.blockID);
                    return new ItemStack(Item.bucketEmpty);
                }
            }

            return item;
        }
    }
    public static ItemStack attemptFill(World world, MovingObjectPosition p)
    {
        int id = world.getBlockId(p.blockX, p.blockY, p.blockZ);

        if (id == Blocks.cementFluidBlock.blockID)
        {
            if (world.getBlockMetadata(p.blockX, p.blockY, p.blockZ) == 0)
            {
                world.setBlock(p.blockX, p.blockY, p.blockZ, 0);
                return new ItemStack(Items.cementBucket);
            }
        }

        return null;
    }
}
