package gay.snelf.shenanigans.objects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import gay.snelf.shenanigans.Shenanigans;

import javax.annotation.Nonnull;
import java.awt.*;

public class Nickname implements Component<EntityStore> {
    @Nonnull
    public static final BuilderCodec<Nickname> CODEC;
    @Nonnull
    private String text = "";
    private int color = Color.WHITE.getRGB();

    @Nonnull
    public static ComponentType<EntityStore, Nickname> getComponentType() {
        return Shenanigans.NICKNAME_COMPONENT_TYPE;
    }

    public Nickname() {
    }

    public Nickname(@Nonnull String text, int color) {
        this.text = text;
        this.color = color;
    }

    @Nonnull
    public String getText() {
        return this.text;
    }

    public void setText(@Nonnull String text) {
        if(!this.text.equals(text)) {
            this.text = text;
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Nonnull
    public Component<EntityStore> clone() {
        Nickname nickname = new Nickname();
        nickname.text = this.text;
        nickname.color = this.color;
        return nickname;
    }

    static {
        CODEC = BuilderCodec.builder(Nickname.class, Nickname::new)
                .append(
                        new KeyedCodec<>("Text", Codec.STRING),
                        (nickname, s) -> nickname.text = s,
                        nickname -> nickname.text
                )
                .documentation("The contents to display as the nameplate text.")
                .addValidator(Validators.nonNull())
                .add()
                .append(
                        new KeyedCodec<>("Color", Codec.INTEGER),
                        (nickname, i) -> nickname.color = i,
                        nickname -> nickname.color
                )
                .documentation("The color of the nickname text.")
                .add()
                .build();
    }
}
