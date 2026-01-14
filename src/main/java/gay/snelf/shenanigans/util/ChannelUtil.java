package gay.snelf.shenanigans.util;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.component.SHConfig;
import gay.snelf.shenanigans.objects.Channel;

public class ChannelUtil {

    public static SHConfig getConfigComponent(Ref<EntityStore> ref, Store<EntityStore> store) {
        SHConfig config = store.getComponent(ref, SHConfig.getComponentType());
        if(config == null) {
            config = new SHConfig();
            store.addComponent(ref, SHConfig.getComponentType(), config);
        }
        return config;
    }

    public static void updateChannel(Ref<EntityStore> ref, Store<EntityStore> store, Channel channel) {
        SHConfig config = getConfigComponent(ref, store);
        config.setFocusedChannel(channel);
        store.replaceComponent(ref, SHConfig.getComponentType(), config);
    }
}
