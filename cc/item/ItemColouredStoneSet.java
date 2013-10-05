package cc.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemColouredStoneSet extends ItemBlock
{
    public ItemColouredStoneSet(int id)
    {
        super(id);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int dmg)
    {
        return dmg;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "" + stack.getItemDamage() + "Stone" + this.itemID;
    }
}
