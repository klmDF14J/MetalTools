package metalTools;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Tab extends CreativeTabs {

	private String name; 
	private int index;
	
	public Tab(String s, int id) {
		super(getNextID(), s);
		
		name = s;
		index = id;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return index;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return name;
	}

}
