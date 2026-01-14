package gay.snelf.shenanigans;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.modules.entity.EntityModule;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.commands.channel.ChannelCommand;
import gay.snelf.shenanigans.commands.nickname.NicknameCommand;
import gay.snelf.shenanigans.events.ChatListener;
import gay.snelf.shenanigans.events.NicknameSyncListener;
import gay.snelf.shenanigans.objects.Channel;
import gay.snelf.shenanigans.objects.Nickname;
import gay.snelf.shenanigans.objects.NicknameSnapshot;
import gay.snelf.shenanigans.objects.PlayerConfig;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class Shenanigans extends JavaPlugin {

    private static Shenanigans INSTANCE;

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Map<UUID, PlayerConfig> playerConfigs = new ConcurrentHashMap<>();
    public static final ComponentType<EntityStore, Nickname> NICKNAME_COMPONENT_TYPE = EntityModule.get().getEntityStoreRegistry()
            .registerComponent(Nickname.class, "Nickname", Nickname.CODEC);
    private final ConcurrentMap<UUID, NicknameSnapshot> nicknameCache = new ConcurrentHashMap<>();

    public Shenanigans(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());

        INSTANCE = this;
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.getCommandRegistry().registerCommand(new NicknameCommand(this));
        this.getCommandRegistry().registerCommand(new ChannelCommand(this));

        this.getEventRegistry().registerGlobal(PlayerChatEvent.class, ChatListener::onPlayerChat);
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, NicknameSyncListener::onPlayerReady);
    }

    public PlayerConfig getPlayerConfig(UUID uuid) {
        return playerConfigs.getOrDefault(uuid, new PlayerConfig(uuid, Channel.GLOBAL));
    }

    public void addPlayerConfig(PlayerConfig config) {
        playerConfigs.put(config.getPlayerUuid(), config);
    }

    @Nonnull
    public NicknameSnapshot getNicknameSnapshot(UUID playerUuid, String fallbackName) {
        return nicknameCache.getOrDefault(
                playerUuid,
                new NicknameSnapshot(fallbackName, java.awt.Color.WHITE.getRGB())
        );
    }

    public void clearNicknameSnapshot(UUID playerUuid) {
        nicknameCache.remove(playerUuid);
    }

    /**
     * MUST be called from the entity's WorldThread.
     */
    public void syncNicknameSnapshot(@Nonnull UUID playerUuid, @Nonnull Nickname nickname) {
        nicknameCache.put(playerUuid, new NicknameSnapshot(nickname.getText(), nickname.getColor()));
    }

    public static Shenanigans getINSTANCE() {
        return INSTANCE;
    }
}