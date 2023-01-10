package ac.grim.removehealthmetadata;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public final class RemoveHealthMetadata extends JavaPlugin {
    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().readOnlyListeners(false).checkForUpdates(false).bStats(true);
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().getEventManager().registerListener(new HealthListener(), PacketListenerPriority.LOW);
        PacketEvents.getAPI().init();
    }
}
