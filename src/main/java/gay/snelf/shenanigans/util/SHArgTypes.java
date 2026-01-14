package gay.snelf.shenanigans.util;

import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType;
import gay.snelf.shenanigans.objects.Channel;

public class SHArgTypes {

//    public static final SingleArgumentType<Channel> CHANNEL = new SingleArgumentType<>("channel", "Channels for chatting.", Channel.shortNames()) {
//        @Nullable
//        @Override
//        public Channel parse(String s, ParseResult parseResult) {
//            return Arrays.stream(Channel.values())
//                    .filter(channel -> channel.getShortName().equalsIgnoreCase(s))
//                    .findFirst()
//                    .orElse(null);
//        }
//
//        public void suggest(@Nonnull CommandSender sender, @Nonnull String textAlreadyEntered, int numParametersTyped, @Nonnull SuggestionResult result) {
//            textAlreadyEntered = textAlreadyEntered.toLowerCase();
//
//            for(String shortHand : Channel.shortNames()) {
//                if (shortHand.startsWith(textAlreadyEntered)) {
//                    result.suggest(shortHand);
//                }
//            }
//        }
//    };

    public static final SingleArgumentType<Channel> CHANNEL = ArgTypes.forEnum("channel", Channel.class);
}
