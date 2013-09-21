package constructionCraft.item;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import constructionCraft.Config;
import constructionCraft.ConstructionCraft;

public class Hammer {
	
	private int id, radius;
	private String name, suffix;
	private EnumToolMaterial material;
	
	private boolean isCreated = false;
	private Item hammer;
	private Object item;
	private int handle;
	
	public Hammer(String name, EnumToolMaterial material, int radius, String suffix, Object item, int handle) {
		this.id = Items.lastHammerID++;
		this.name = name;
		this.material = material;
		this.radius = radius;
		this.suffix = suffix;
		this.item = item;
		this.handle = handle;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	public String getFullName() {
		String firstLetter = getName().substring(0, 1);
		firstLetter = firstLetter.toUpperCase();
		String key = getName().substring(1);
		return firstLetter + key + getSuffix() + " Hammer";
	}
	
	public EnumToolMaterial getMaterial() {
		return material;
	}
	
	public boolean isCreated() {
		return isCreated;
	}
	
	public void createItem() {
		if(!isCreated()) {
			isCreated = true;
			
			hammer = new ItemHammer(Config.hammers[id], 1.0F, getMaterial(), radius, getName()).setTextureName(ConstructionCraft.modid + ":" + getName() + "Hammer");
		}
	}
	
	public Item getItem() {
		if(isCreated()) {
			return hammer;
		}
		return Item.itemsList[0];
	}
	
	public Object getCraftingIngredient() {
		return item;
	}
	
	public int getHandle() {
		return handle;
	}
}
