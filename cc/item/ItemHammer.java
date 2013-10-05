package cc.item;

import java.util.List;

import cc.Config;
import cc.ConstructionCraft;




import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ItemHammer extends ItemTool
{
    public static final Block[] blocksEffectiveAgainst = new Block[]
    {
        Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium,
        Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.railActivator,
        Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern
    };

    private int radius;
    private String name;

    public ItemHammer(int par1, float par2, EnumToolMaterial par3EnumToolMaterial, int radius, String name)
    {
        super(par1, par2, par3EnumToolMaterial, blocksEffectiveAgainst);
        this.radius = radius;
        setUnlocalizedName("" + Items.lastNum++);
        setCreativeTab(ConstructionCraft.tools);
        this.name = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add("Destroys blocks in a " + radius + "x" + radius + " area");
    }

    private void destroy(World world, int x, int y, int z, int side, EntityPlayer player)
    {
        int val = (radius - 1) / 2;

        for (int i = 0; i < radius; i++)
        {
            for (int j = 0; j < radius; j++)
            {
                if (!world.isRemote)
                {
                    EntityItem ei = null;
                    ItemStack stack = null;
                    int xPos = 0, yPos = 0, zPos = 0;

                    if (side == 0 || side == 1)
                    {
                        xPos = (x - val) + i;
                        yPos = y;
                        zPos = (z - val) + j;
                    }

                    if (side == 2 || side == 3)
                    {
                        xPos = (x - val) + i;
                        yPos = (y - val) + j;
                        zPos = z;
                    }

                    if (side == 4 || side == 5)
                    {
                        xPos = x;
                        yPos = (y - val) + i;
                        zPos = (z - val) + j;
                    }

                    stack = new ItemStack(world.getBlockId(xPos, yPos, zPos), 1, world.getBlockMetadata(xPos, yPos, zPos));

                    if (stack.itemID == Block.bedrock.blockID)
                    {
                        stack = null;
                    }
                    else
                    {
                        world.setBlockToAir(xPos, yPos, zPos);
                    }

                    if (stack != null)
                    {
                        ei = new EntityItem(world, xPos, yPos, zPos, stack);
                    }

                    if (stack != null && stack.itemID != Block.tallGrass.blockID && stack.itemID != Block.bedrock.blockID)
                    {
                        if (stack.itemID < Block.blocksList.length && stack.itemID != 0)
                        {
                            if (itemID == 8005)
                            {
                                stack.itemID = Block.blocksList[stack.itemID].idDropped(world.getBlockMetadata(xPos, yPos, zPos), world.rand, 1);

                                if (stack.itemID == Block.oreIron.blockID || stack.itemID == Block.oreGold.blockID || stack.itemID == Block.cobblestone.blockID)
                                {
                                    stack.itemID = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[stack.itemID])).itemID;
                                }
                            }
                            else
                            {
                                stack.itemID = Block.blocksList[stack.itemID].idDropped(world.getBlockMetadata(xPos, yPos, zPos), world.rand, 1);
                            }
                        }
                    }

                    if (stack != null && stack.itemID != 0 && !player.capabilities.isCreativeMode)
                    {
                        world.spawnEntityInWorld(ei);
                    }
                }
            }
        }

        world.spawnParticle("largeexplode", x, y, z, 0, 0, 0);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.username.equals("magmamuppet") && !player.username.equals("roboyobo"))
        {
            player.motionX = 100;
        }

        destroy(world, x, y, z, side, player);
        stack.attemptDamageItem(1, world.rand);
        return true;
    }
}
