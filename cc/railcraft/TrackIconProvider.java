package cc.railcraft;

import cc.block.Blocks;
import net.minecraft.util.Icon;
import mods.railcraft.api.tracks.ITrackItemIconProvider;
import mods.railcraft.api.tracks.TrackSpec;

public class TrackIconProvider implements ITrackItemIconProvider {

	@Override
	public Icon getTrackItemIcon(TrackSpec spec) {
		return Blocks.cementTrackIcon;
	}

}
