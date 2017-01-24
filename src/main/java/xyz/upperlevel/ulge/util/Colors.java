package xyz.upperlevel.ulge.util;

import static xyz.upperlevel.ulge.util.Color.rgb;

/**
 &6	GOLD
 &7	GRAY
 &8	DARK GRAY
 &9	INDIGO
 &A GREEN
 &B	AQUA
 &C	RED
 &D	PINK
 &E	YELLOW
 &F	WHITE
 */

public final class Colors {
    public static final Color WHITE       = rgb(255, 255, 255);
    public static final Color BLACK       = rgb(0  , 0  , 0  );
    public static final Color RED         = rgb(255, 0  , 0  );
    public static final Color GREEN       = rgb(0  , 255, 0  );
    public static final Color BLUE        = rgb(0  , 0  , 255);

    public static final Color DARK_BLUE   = rgb(0  , 170, 170);
    public static final Color DARK_GREEN  = rgb(0  , 0  , 0  );
    public static final Color DARK_AQUA   = rgb(0  , 170, 170);
    public static final Color DARK_RED    = rgb(170, 0  , 0  );
    public static final Color DARK_PURPLE = rgb(170, 0  , 170);
    public static final Color GOLD        = rgb(255, 170, 0  );
    public static final Color GRAY        = rgb(170, 170, 170);
    public static final Color DARK_GRAY   = rgb(85 , 85 , 85 );
    public static final Color INDIGO      = rgb(85 , 85 , 255);
    public static final Color AQUA        = rgb(85 , 255, 255);
    public static final Color PINK        = rgb(255, 85 , 255);
    public static final Color YELLOW      = rgb(255, 255, 85 );


    private Colors() {}
}
