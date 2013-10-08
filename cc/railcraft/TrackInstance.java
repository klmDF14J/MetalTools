package cc.railcraft;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cc.block.Blocks;
import cc.block.tileentity.TileEntityConcreteMixer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import mods.railcraft.api.tracks.ITrackInstance;
import mods.railcraft.api.tracks.TrackSpec;

public class TrackInstance implements ITrackInstance {

	@Override
	public World getWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		
	}

	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		
	}

	@Override
	public TrackSpec getTrackSpec() {
		return new TrackSpec((short)60, "Heavy Duty Track", new TrackIconProvider(), TrackInstance.class);
	}

	@Override
	public int getBasicRailMetadata(EntityMinecart cart) {
		return 0;
	}

	@Override
	public void onMinecartPass(EntityMinecart cart) {
		
	}

	@Override
	public Icon getIcon() {
		return Blocks.cementTrackIcon;
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public void updateEntity() {
		
	}

	@Override
	public boolean blockActivated(EntityPlayer player) {
		return false;
	}

	@Override
	public void onBlockPlaced() {
		
	}

	@Override
	public void onBlockRemoved() {
		
	}

	@Override
	public void onBlockPlacedBy(EntityLivingBase entity) {
		
	}

	@Override
	public void onNeighborBlockChange(int id) {
		
	}

	@Override
	public void setTile(TileEntity tile) {
	
	}

	@Override
	public TileEntity getTile() {
		return new TileEntityConcreteMixer();
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public float getHardness() {
		return 0;
	}

	@Override
	public float getExplosionResistance(double srcX, double srcY, double srcZ, Entity exploder) {
		return 0;
	}

	@Override
	public boolean isFlexibleRail() {
		return true;
	}

	@Override
	public boolean canMakeSlopes() {
		return true;
	}

	@Override
	public float getRailMaxSpeed(EntityMinecart cart) {
		return 5;
	}

}
