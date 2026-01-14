package gay.snelf.shenanigans.util;

import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType;
import gay.snelf.shenanigans.objects.Channel;

public class SHArgTypes {
    public static final SingleArgumentType<Channel> CHANNEL = ArgTypes.forEnum("channel", Channel.class);
}
