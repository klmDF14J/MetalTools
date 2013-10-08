/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package cc.buildcraft;

import cc.block.Blocks;
import cc.block.tileentity.TileEntityConcreteMixer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.gates.ITriggerParameter;

public class TriggerMachine extends Trigger {

	boolean active;

	public TriggerMachine(int legacyId, boolean active) {
		super(legacyId, "Cement Mixer");

		this.active = active;
	}

	@Override
	public String getDescription() {
		if (active)
			return "Work Scheduled";
		else
			return "Work Done";
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
		if (tile instanceof TileEntityConcreteMixer) {
			TileEntityConcreteMixer machine = (TileEntityConcreteMixer) tile;

			if (active)
				return machine.getWorldObj().getBlockId(machine.xCoord, machine.yCoord, machine.zCoord) == Blocks.cementMixerOn.blockID;
			else
				return machine.getWorldObj().getBlockId(machine.xCoord, machine.yCoord, machine.zCoord) == Blocks.cementMixer.blockID;
		}

		return false;
	}

	@Override
	public int getIconIndex() {
		if (active)
			return 0;
		else
			return 1;
	}
}
