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
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.plugin.Shenanigans;
import gay.snelf.plugin.objects.PlayerConfig;
import gay.snelf.plugin.util.NicknameUtility;

import javax.annotation.Nonnull;
import java.awt.*;

public class NicknameCommand extends AbstractPlayerCommand {

    private final Shenanigans plugin;

    private final RequiredArg<String> nicknameArg = this.withRequiredArg("nickname", "server.commands.argtype.string.desc", ArgTypes.STRING);

    public NicknameCommand(Shenanigans plugin) {
        super("nick", "Sets your nickname.");
        this.requirePermission(HytalePermissions.fromCommand("nickname.self"));
        this.addSubCommand(new ClearNicknameCommand(plugin));
        this.addSubCommand(new SetNicknameColorCommand(plugin));

        this.plugin = plugin;
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        PlayerConfig config = plugin.getPlayerConfig(playerRef.getUuid());
        String nickname = nicknameArg.get(commandContext);

        config.setNickname(nickname);
        plugin.addPlayerConfig(config);

        playerRef.sendMessage(Message.raw("Your nickname has been set to " + nickname + ".").color(Color.GREEN));

        NicknameUtility.updateNameplate(ref, store, nickname);
    }
}
