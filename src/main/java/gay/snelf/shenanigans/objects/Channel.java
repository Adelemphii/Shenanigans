package gay.snelf.shenanigans.objects;

import java.awt.*;

public enum Channel {
    GLOBAL(-1, "GLOBAL", Color.GRAY),
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
