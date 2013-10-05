package cc;

import cc.block.Blocks;
import cc.item.ItemConcreteBucket;
import cc.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ConcreteBucketEvent
{
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void FillBucket(FillBucketEvent event)
    {
        ItemStack result = ItemConcreteBucket.attemptFill(event.world, event.target);

        if (result != null)
        {
            event.result = result;
            event.setResult(Result.ALLOW);
        }
    }
}
