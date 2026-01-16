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

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ChatListener {

    public static void onPlayerChat(PlayerChatEvent event) {
        PlayerRef sender = event.getSender();
        PlayerConfig config = Shenanigans.getINSTANCE().getPlayerConfig(sender.getUuid());
        Channel channel = config.getChannel() != null ? config.getChannel() : Channel.GLOBAL;

        String content = event.getContent().toUpperCase();
        channel = parseChannelFromMessage(content, channel);
        if(shouldSetChannel(content)) {
            config.setChannel(channel);
            Shenanigans.getINSTANCE().addPlayerConfig(config);
            sender.sendMessage(Message.raw("Your chat channel has been set to " + channel.getShortName()
                    + " (" + channel.getRange() + " blocks away)").color(Color.GREEN));
            event.setCancelled(true);
            return;
        }
        int range = channel != null ? channel.getRange() : Channel.GLOBAL.getRange();

        NicknameSnapshot nickname = Shenanigans.getINSTANCE().getNicknameSnapshot(sender.getUuid(), sender.getUsername());

        event.setFormatter(getFormatter(channel, nickname));

        List<PlayerRef> targets = getPlayersInRange(sender, range);
        if(targets.isEmpty()) {
            sender.sendMessage(Message.raw("No players in range to receive your message!").color(Color.RED));
        }
        event.setTargets(getPlayersInRange(sender, range));
    }

    private static boolean shouldSetChannel(String message) {
        if(!message.startsWith("#")) {
            return false;
        }

        for(Channel channel : Channel.values()) {
            if(message.equalsIgnoreCase("#" + channel.getShortName())) {
                return true;
            }
        }
        return false;
    }

    private static Channel parseChannelFromMessage(String message, Channel defaultChannel) {
        if(!message.startsWith("#")) {
            return defaultChannel;
        }

        for(Channel channel : Channel.values()) {
            if(message.startsWith("#" + channel.getShortName())) {
                return channel;
            }
        }
        return defaultChannel;
    }

    // TODO: MessageFormatter or something..
    private static String stripChannelPrefix(String message, Channel channel) {
        String prefix = "#" + channel.getShortName().toLowerCase();
        if(message.toLowerCase().startsWith(prefix)) {
            return message.substring(prefix.length()).trim();
        }
        return message;
    }

    private static PlayerChatEvent.Formatter getFormatter(@Nonnull Channel channel, NicknameSnapshot nickname) {
        String channelName = channel.getShortName();
        Color channelColor = channel.getColor();

        int nickColor = nickname.color();
        return (_, message) -> {
            message = stripChannelPrefix(message, channel);
            return Message.join(
                    Message.raw("[%s] ".replace("%s", channelName)).color(channelColor),
                    Message.raw(nickname.text()).color(new Color(nickColor, true)),
                    Message.raw(": " + message).color(Color.WHITE)
            );
        };
    }

    private static List<PlayerRef> getPlayersInRange(PlayerRef sender, int range) {
        if(range == -1) {
            return Universe.get().getPlayers();
        }

        UUID worldUuid = sender.getWorldUuid();
        if(worldUuid == null) return List.of();
        World world = Universe.get().getWorld(worldUuid);
        if(world == null) return List.of();

        double rangeSquared = range * range;
        return world.getPlayerRefs().stream()
                .filter(playerRef -> {
                    Vector3d senderPos = sender.getTransform().getPosition();
                    Vector3d playerPos = playerRef.getTransform().getPosition();

                    double distance = senderPos.distanceSquaredTo(playerPos);
                    return distance <= rangeSquared;
                }).toList();
    }
}