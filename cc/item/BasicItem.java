package cc.item;

import cc.block.BlockConcreteMixer;
import cc.block.Blocks;
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
				world.setBlock(x, y, z, Blocks.concreteMixer.blockID);
			}

			stack.damageItem(1, player);
			return true;
		}
	}
}
