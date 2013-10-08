package cc.block;

import cc.Config;
import cc.ConstructionCraft;
import cc.item.Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BasicBlock extends Block
{
    public BasicBlock(int par1)
    {
        super(par1, Material.rock);
        setUnlocalizedName("" + Items.lastNum++);
    }
}
