package codechicken.multipart.minecraft;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class McMultipartSPH implements IServerPacketHandler
{
    public static Object channel = MinecraftMultipartMod.instance;

    @Override
    public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer netHandler) {
        switch (packet.getType()) {
            case 1:
                EventHandler.place(sender, packet.readBoolean() ?
                        EnumHand.MAIN_HAND : EnumHand.OFF_HAND, sender.worldObj);
                break;
        }
    }

    public static void spawnBurnoutSmoke(World world, int x, int y, int z) {
        new PacketCustom(channel, 1).writeCoord(x, y, z).sendToChunk(world, x >> 4, z >> 4);
    }
}
