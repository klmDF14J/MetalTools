package cc.item;

import java.util.List;

import cc.ConstructionCraft;




import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHandle extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemHandle(int id)
    {
        super(id);
        setCreativeTab(ConstructionCraft.tools);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return "handle " + itemstack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        icons = new Icon[3];
        String[] names = {"sturdy", "reinforced", "encrusted"};

        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = register.registerIcon(ConstructionCraft.modid + ":" + names[i] + "Handle");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg)
    {
        return icons[dmg];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 3; i++)
        {
            ItemStack stack = new ItemStack(id, 1, i);
            list.add(stack);
        }
    }
}
