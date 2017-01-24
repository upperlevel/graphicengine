package xyz.upperlevel.ulge.window.event.button;

public enum MouseButton {
    UNKNOWN,
    BUTTON_1,
    BUTTON_2,
    BUTTON_3,
    BUTTON_4,
    BUTTON_5,
    BUTTON_6,
    BUTTON_7,
    BUTTON_8,
    LAST,
    LEFT,
    RIGHT,
    MIDDLE;

    private static final MouseButton[] array = MouseButton.values();

    public final int id = ordinal();

    public static MouseButton fromId(int keyId) {
        return array[keyId];
    }

    public static int length() {
        return array.length;
    }
}
