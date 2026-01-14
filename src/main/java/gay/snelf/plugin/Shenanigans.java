package gay.snelf.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import gay.snelf.plugin.commands.channel.ChannelCommand;
import gay.snelf.plugin.commands.nickname.NicknameCommand;
import gay.snelf.plugin.events.ChatListener;
import gay.snelf.plugin.objects.Channel;
import gay.snelf.plugin.objects.PlayerConfig;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class Shenanigans extends JavaPlugin {

    private static Shenanigans INSTANCE;

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private Map<UUID, PlayerConfig> playerConfigs = new ConcurrentHashMap<>();

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
    }

    public Map<UUID, PlayerConfig> getPlayerConfigs() {
        return playerConfigs;
    }

    public PlayerConfig getPlayerConfig(UUID uuid) {
        return playerConfigs.getOrDefault(uuid, new PlayerConfig(uuid, Channel.GLOBAL));
    }

    public void addPlayerConfig(PlayerConfig config) {
        playerConfigs.put(config.getPlayerUuid(), config);
    }

    public void removePlayerConfig(UUID uuid) {
        playerConfigs.remove(uuid);
    }

    public static Shenanigans getINSTANCE() {
        return INSTANCE;
    }
}