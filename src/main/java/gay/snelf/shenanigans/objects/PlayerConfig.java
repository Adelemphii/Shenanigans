package gay.snelf.shenanigans.objects;

import java.util.UUID;

public class PlayerConfig {

    private Channel channel;
    private final UUID playerUuid;

    public PlayerConfig(UUID uuid, Channel channel) {
        this.playerUuid = uuid;
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

}
