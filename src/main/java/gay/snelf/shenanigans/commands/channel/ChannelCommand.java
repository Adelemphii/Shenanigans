package gay.snelf.shenanigans.commands.channel;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.objects.Channel;
import gay.snelf.shenanigans.objects.PlayerConfig;
import gay.snelf.shenanigans.util.ChannelUtil;
import gay.snelf.shenanigans.util.SHArgTypes;

import javax.annotation.Nonnull;
import java.awt.*;

public class ChannelCommand extends AbstractPlayerCommand {

    private final Shenanigans plugin;

    private final RequiredArg<Channel> channelArg = this.withRequiredArg("channel", "Sets the chat channel to focus on.", SHArgTypes.CHANNEL);

    public ChannelCommand(Shenanigans plugin) {
        super("channel", "Sets the player's focused chat channel.");
        this.setPermissionGroup(GameMode.Adventure);

        this.plugin = plugin;
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Channel channel = channelArg.get(commandContext);
        PlayerConfig config = plugin.getPlayerConfig(playerRef.getUuid());

        config.setChannel(channel);
        plugin.addPlayerConfig(config);

        ChannelUtil.updateChannel(ref, store, channel);

        playerRef.sendMessage(Message.raw("Your chat channel has been set to " + channel.getShortName() + " (" + channel.getRange() + " blocks away)").color(Color.GREEN));
    }
}
