package cc.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.ConstructionCraft;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWormhole extends Block {

	public BlockWormhole(int par1) {
		super(par1, Material.rock);
		setCreativeTab(ConstructionCraft.blocks);
		setBlockBounds(0, 0, 0, 2, 0.025F, 2);
		setTextureName(ConstructionCraft.modid + ":wormhole");
		setLightValue(5);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iba, int x, int y, int z) {
		setBlockBounds(0, 0, 0, 2, 0.025F, 2);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0, 0, 0, 1, 0.025F, 1);
	}

	private static final int radius = 40;

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity) {

		if (!world.isRemote) {
			EntityPlayerMP player = null;

			if (entity instanceof EntityPlayer) {
				player = (EntityPlayerMP) entity;
			}

			int x2 = x + world.rand.nextInt(radius) - (radius / 2);
			int z2 = z + world.rand.nextInt(radius) - (radius / 2);

			if (player != null) {
				player.mountEntity((Entity) null);
				player.setPositionAndUpdate(x2, y, z2);
			}

			else {
				entity.mountEntity((Entity) null);
				entity.setPosition(x2, y, z2);
			}
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		world.spawnParticle("portal", x + (rand.nextFloat() + rand.nextFloat()), y + 0.05F, z + (rand.nextFloat() + rand.nextFloat()), 0, 0, 0);
	}

}
