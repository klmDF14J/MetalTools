package cc.block;

import java.util.ArrayList;
import java.util.List;

import cc.ConstructionCraft;
import cc.item.Items;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockColouredStoneSet extends Block
{
    private String colour;

    public static final int numTypes = 3;

    public BlockColouredStoneSet(int id, String colour)
    {
        super(id, Material.rock);
        setCreativeTab(ConstructionCraft.blocks);
        this.colour = colour;
    }

    @SideOnly(Side.CLIENT)
    private Icon stone;
    @SideOnly(Side.CLIENT)
    private Icon brick;
    @SideOnly(Side.CLIENT)
    private Icon smooth;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        stone = icon.registerIcon(ConstructionCraft.modid + ":" + colour + "Stone");
        brick = icon.registerIcon(ConstructionCraft.modid + ":" + colour + "Brick");
        smooth = icon.registerIcon(ConstructionCraft.modid + ":" + colour + "Smooth");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            return stone;
        }

        if (meta == 1)
        {
            return brick;
        }

        if (meta == 2)
        {
            return smooth;
        }

        return stone;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < numTypes; i++)
        {
            list.add(new ItemStack(id, 1, i));
        }
    }
    
    @Override
    public int damageDropped(int dmg) {
    	return dmg;
    }
}
