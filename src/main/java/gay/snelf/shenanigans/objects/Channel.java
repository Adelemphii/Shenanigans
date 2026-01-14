package gay.snelf.shenanigans.objects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.ExtraInfo;
import com.hypixel.hytale.codec.schema.SchemaContext;
import com.hypixel.hytale.codec.schema.config.Schema;
import com.hypixel.hytale.codec.schema.config.StringSchema;
import io.netty.handler.codec.CodecException;
import org.bson.BsonString;
import org.bson.BsonValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;

public enum Channel {
    GLOBAL(-1, "OOC", Color.GRAY),
    WHISPER(2, "WHISPER", Color.BLUE),
    QUIET(8, "QUIET", Color.CYAN),
    NORMAL(24, "NORMAL", Color.GREEN),
    SHOUT(48, "SHOUT", Color.RED);

    private final int range;
    private final String shortName;
    private final Color color;

    Channel(int range, String shortName, Color color) {
        this.range = range;
        this.shortName = shortName;
        this.color = color;
    }

    public int getRange() {
        return range;
    }

    public String getShortName() {
        return shortName;
    }

    public Color getColor() {
        return color;
    }

    public static final Codec<Channel> CODEC = new Codec<>() {
        @Nullable
        @Override
        public Channel decode(BsonValue bsonValue, ExtraInfo extraInfo) {
            try {
                return Channel.valueOf(bsonValue.asString().getValue());
            } catch(IllegalArgumentException e) {
                throw new CodecException("Invalid Channel value: " + bsonValue.asString().getValue(), e);
            }
        }

        @Override
        public BsonValue encode(Channel channel, ExtraInfo extraInfo) {
            return new BsonString(channel.name());
        }

        @Nonnull
        @Override
        public Schema toSchema(@Nonnull SchemaContext schemaContext) {
            StringSchema schema = new StringSchema();
            schema.setEnum(Arrays.stream(Channel.class.getEnumConstants()).map(Enum::name).toArray(String[]::new));
            schema.setDescription("Chat channel");
            return schema;
        }
    };
}
