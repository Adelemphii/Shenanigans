package gay.snelf.shenanigans.events;

import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.objects.Channel;
import gay.snelf.shenanigans.objects.NicknameSnapshot;
import gay.snelf.shenanigans.objects.PlayerConfig;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ChatListener {

    public static void onPlayerChat(PlayerChatEvent event) {
        PlayerRef sender = event.getSender();
        PlayerConfig config = Shenanigans.getINSTANCE().getPlayerConfig(sender.getUuid());
        Channel channel = config.getChannel() != null ? config.getChannel() : Channel.GLOBAL;

        NicknameSnapshot nickname = Shenanigans.getINSTANCE().getNicknameSnapshot(sender.getUuid(), sender.getUsername());

        String channelName = channel != null ? channel.getShortName() : Channel.GLOBAL.name();
        Color channelColor = channel != null ? channel.getColor() : Channel.GLOBAL.getColor();
        int range = channel != null ? channel.getRange() : Channel.GLOBAL.getRange();

        int nickColor = nickname.color();
        String name = nickname.text().isEmpty() ? sender.getUsername() : nickname.text();

        event.setFormatter((_, message) -> Message.join(
                Message.raw("[%s] ".replace("%s", channelName)).color(channelColor),
                Message.raw(name).color(new Color(nickColor, true)),
                Message.raw(": " + message).color(Color.WHITE)
        ));

        event.setTargets(getPlayersInRange(sender, range));
    }

    private static List<PlayerRef> getPlayersInRange(PlayerRef sender, int range) {
        if(range == -1) {
            return Universe.get().getPlayers();
        }

        UUID worldUuid = sender.getWorldUuid();
        if(worldUuid == null) return List.of();
        World world = Universe.get().getWorld(worldUuid);
        if(world == null) return List.of();

        return world.getPlayerRefs().stream()
                .filter(playerRef -> {
                    Vector3d senderPos = sender.getTransform().getPosition();
                    Vector3d playerPos = playerRef.getTransform().getPosition();

                    double distance = senderPos.distanceTo(playerPos);
                    return distance <= range;
                }).toList();
    }
}