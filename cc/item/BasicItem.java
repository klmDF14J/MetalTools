package cc.item;

import cc.block.BlockCementFluid;
import cc.block.BlockConcreteMixer;
import cc.block.Blocks;
import cc.block.tileentity.TileEntityConcreteMixer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BasicItem extends Item {
	public BasicItem(int par1) {
		super(par1);
		setUnlocalizedName("" + Items.lastNum++);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 0) {
			--y;
		}

		if (side == 1) {
			++y;
		}

		if (side == 2) {
			--z;
		}

		if (side == 3) {
			++z;
		}

		if (side == 4) {
			--x;
		}

		if (side == 5) {
			++x;
		}

		if (!player.canPlayerEdit(x, y, z, side, stack)) {
			return false;
		} else {
			if (world.isAirBlock(x, y, z)) {
				world.setBlock(x, y, z, Blocks.cementMixer.blockID);
				
				 if (!world.isRemote)
			        {
			            int l = world.getBlockId(x, y, z - 1);
			            int i1 = world.getBlockId(x, y, z + 1);
			            int j1 = world.getBlockId(x - 1, y, z);
			            int k1 = world.getBlockId(x + 1, y, z);
			            byte b0 = 3;

			            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			            {
			                b0 = 3;
			            }

			            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			            {
			                b0 = 2;
			            }

			            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			            {
			                b0 = 5;
			            }

			            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			            {
			                b0 = 4;
			            }

			            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
			        }
			}

			stack.damageItem(1, player);
			return true;
		}
	}
}
