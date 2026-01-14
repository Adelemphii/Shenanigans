package gay.snelf.shenanigans.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.component.Nickname;

import java.awt.*;

public class NicknameSyncListener {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Ref<EntityStore> ref = event.getPlayerRef();
        Store<EntityStore> store = ref.getStore();
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        if(playerRef == null || !playerRef.isValid()) return;

        Nickname nickname = store.getComponent(ref, Nickname.getComponentType());
        if(nickname == null) {
            nickname = new Nickname(playerRef.getUsername(), Color.WHITE.getRGB());
        }
        Shenanigans.getINSTANCE().syncNicknameSnapshot(playerRef.getUuid(), nickname);
    }
}
