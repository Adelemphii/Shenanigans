package gay.snelf.plugin.commands.nickname;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.permissions.HytalePermissions;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.plugin.Shenanigans;
import gay.snelf.plugin.objects.PlayerConfig;
import gay.snelf.plugin.util.NicknameUtility;

import javax.annotation.Nonnull;
import java.awt.*;

public class ClearNicknameCommand extends AbstractPlayerCommand {
    private final Shenanigans plugin;

    public ClearNicknameCommand(Shenanigans plugin) {
        super("clear", "Clears your nickname.");
        this.requirePermission(HytalePermissions.fromCommand("nickname.self"));

        this.plugin = plugin;
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        PlayerConfig config = plugin.getPlayerConfig(playerRef.getUuid());
        config.setNickname(null);
        config.setNicknameColor(null);
        plugin.addPlayerConfig(config);

        playerRef.sendMessage(Message.raw("Your nickname has been cleared.").color(Color.GREEN));

        NicknameUtility.updateNameplate(ref, store, playerRef.getUsername());
    }
}
