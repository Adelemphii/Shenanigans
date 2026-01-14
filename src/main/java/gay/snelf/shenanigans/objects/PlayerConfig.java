package gay.snelf.shenanigans.objects;

import java.util.UUID;

public class PlayerConfig {

    private Channel channel;
    private final UUID playerUuid;

    private String nickname;
    private Integer nicknameColor;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getNicknameColor() {
        return nicknameColor;
    }

    public void setNicknameColor(Integer nicknameColor) {
        this.nicknameColor = nicknameColor;
    }

}
