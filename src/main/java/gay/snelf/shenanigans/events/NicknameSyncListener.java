package gay.snelf.shenanigans.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.objects.Nickname;

import java.awt.*;

public class NicknameSyncListener {

    public static void onPlayerReady(PlayerReadyEvent event) {
        PlayerRef playerRef = event.getPlayer().getPlayerRef(); // This is deprecated but I'm too tired to care :3 also shenanigans, aka not serious plugin
        Ref<EntityStore> ref = playerRef.getReference();
        if(ref == null) return;
        Store<EntityStore> store = ref.getStore();

        Nickname nickname = store.getComponent(ref, Nickname.getComponentType());
        if(nickname == null) {
            nickname = new Nickname(playerRef.getUsername(), Color.WHITE.getRGB());
        }
        Shenanigans.getINSTANCE().syncNicknameSnapshot(playerRef.getUuid(), nickname);
    }
}
