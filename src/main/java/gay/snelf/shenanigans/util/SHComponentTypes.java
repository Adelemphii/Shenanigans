package gay.snelf.shenanigans.util;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.component.Nickname;
import gay.snelf.shenanigans.component.SHConfig;

public class SHComponentTypes {
    public static final ComponentType<EntityStore, Nickname> NICKNAME_COMPONENT_TYPE = Shenanigans.getINSTANCE().getEntityStoreRegistry()
            .registerComponent(Nickname.class, Nickname::new);
    public static final ComponentType<EntityStore, SHConfig> CONFIG_COMPONENT_TYPE = Shenanigans.getINSTANCE().getEntityStoreRegistry()
            .registerComponent(SHConfig.class, SHConfig::new);
}
