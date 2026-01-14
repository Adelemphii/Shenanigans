package gay.snelf.shenanigans.component;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;
import gay.snelf.shenanigans.objects.Channel;
import gay.snelf.shenanigans.util.SHComponentTypes;

import javax.annotation.Nonnull;
import java.awt.*;

public class SHConfig implements Component<EntityStore> {
    @Nonnull
    public static final BuilderCodec<SHConfig> CODEC;
    @Nonnull
    private Channel focusedChannel;

    @Nonnull
    public static ComponentType<EntityStore, SHConfig> getComponentType() {
        return SHComponentTypes.CONFIG_COMPONENT_TYPE;
    }

    public SHConfig() {
        this.focusedChannel = Channel.GLOBAL;
    }

    public SHConfig(@Nonnull Channel focusedChannel) {
        this.focusedChannel = focusedChannel;
    }

    @Nonnull
    public Channel getFocusedChannel() {
        return focusedChannel;
    }

    public void setFocusedChannel(@Nonnull Channel focusedChannel) {
        this.focusedChannel = focusedChannel;
    }

    @Nonnull
    public Component<EntityStore> clone() {
        SHConfig config = new SHConfig();
        config.focusedChannel = this.focusedChannel;
        return config;
    }

    static {
        CODEC = BuilderCodec.builder(SHConfig.class, SHConfig::new)
                .append(
                        new KeyedCodec<>("Channel", Channel.CODEC),
                        SHConfig::setFocusedChannel,
                        SHConfig::getFocusedChannel
                )
                .documentation("The contents to display as the nameplate text.")
                .addValidator(Validators.nonNull())
                .add()
                .build();
    }
}
