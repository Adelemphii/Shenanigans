package gay.snelf.shenanigans.commands.nickname;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.permissions.HytalePermissions;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.objects.Nickname;
import gay.snelf.shenanigans.util.NicknameUtility;

import javax.annotation.Nonnull;
import java.awt.*;

public class ClearNicknameCommand extends AbstractPlayerCommand {

    public ClearNicknameCommand() {
        super("clear", "Clears your nickname.");
        this.requirePermission(HytalePermissions.fromCommand("nickname.self"));

    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Nickname nickname = NicknameUtility.updateNicknameComponent(ref, store, playerRef.getUsername(), Color.WHITE.getRGB());
        Shenanigans.getINSTANCE().syncNicknameSnapshot(playerRef.getUuid(), nickname);

        playerRef.sendMessage(Message.raw("Your nickname has been cleared.").color(Color.GREEN));

        NicknameUtility.updateNameplate(ref, store, playerRef.getUsername());
    }
}
