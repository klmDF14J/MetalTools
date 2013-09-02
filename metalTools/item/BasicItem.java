package metalTools.item;

import net.minecraft.item.Item;

public class BasicItem extends Item {

	public BasicItem(int par1) {
		super(par1);
		
		setUnlocalizedName("" + Items.lastNum++);
	}

}
