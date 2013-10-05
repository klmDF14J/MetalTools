package cc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;


import cc.block.Blocks;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;



import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
        EntityPlayer entityPlayer = (EntityPlayer)player;
        byte packetId = reader.readByte();

        if (packetId == 0)
        {
            //System.out.println("Server has recieved concrete world update packet. World name is: " + entityPlayer.worldObj);
            int x = reader.readInt();
            int y = reader.readInt();
            int z = reader.readInt();
            entityPlayer.worldObj.setBlock(x, y, z, Blocks.concrete.blockID);
        }
    }

    public static void sendConcreteHardenPacket(int x, int y, int z)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        try
        {
            dataStream.writeByte(0);
            dataStream.writeInt(x);
            dataStream.writeInt(y);
            dataStream.writeInt(z);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(ConstructionCraft.channel, byteStream.toByteArray()));
    }
}
