package gay.snelf.plugin.objects;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
