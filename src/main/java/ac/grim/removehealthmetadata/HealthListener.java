package ac.grim.removehealthmetadata;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;

public class HealthListener implements PacketListener {
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_METADATA) {
            WrapperPlayServerEntityMetadata metadata = new WrapperPlayServerEntityMetadata(event);

            int id;

            if (PacketEvents.getAPI().getServerManager().getVersion().isOlderThanOrEquals(ServerVersion.V_1_13_2)) {
                id = 7;
            } else if (PacketEvents.getAPI().getServerManager().getVersion().isOlderThanOrEquals(ServerVersion.V_1_16_5)) {
                id = 8;
            } else {
                id = 9;
            }

            metadata.getEntityMetadata().removeIf(data -> data.getIndex() == id);
        }
    }
}