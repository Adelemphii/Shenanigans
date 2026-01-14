package gay.snelf.plugin.commands.nickname;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.permissions.HytalePermissions;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.plugin.Shenanigans;
import gay.snelf.plugin.objects.PlayerConfig;

import javax.annotation.Nonnull;
import java.awt.*;

public class SetNicknameColorCommand extends AbstractPlayerCommand {
    private final Shenanigans plugin;
    private final RequiredArg<Integer> colorArg = this.withRequiredArg("color", "server.commands.argtype.string.desc", ArgTypes.COLOR);

    public SetNicknameColorCommand(Shenanigans plugin) {
        super("color", "Sets your nickname color.");
        this.requirePermission(HytalePermissions.fromCommand("nickname.self"));

        this.plugin = plugin;
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        PlayerConfig config = plugin.getPlayerConfig(playerRef.getUuid());
        Integer color = colorArg.get(commandContext);
        config.setNicknameColor(color);
        plugin.addPlayerConfig(config);

        playerRef.sendMessage(Message.raw("Your nickname color has been set.").color(Color.GREEN));
    }
}
