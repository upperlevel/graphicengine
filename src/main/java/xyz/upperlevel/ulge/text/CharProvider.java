package xyz.upperlevel.ulge.text;

import lombok.Getter;
import lombok.NonNull;
import xyz.upperlevel.ulge.opengl.texture.Texture2dArray;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static org.lwjgl.opengl.GL11.GL_RGBA8;

public class CharProvider {

    @Getter
    private Font font;

    @Getter
    private Font boldFont;

    @Getter
    private boolean antialiasing = false;

    // parameters built up on build
    @Getter
    private CharData
            charset[]     = new CharData[128],
            boldCharset[] = new CharData[128];

    private Texture2dArray
            charsetCompiled     = new Texture2dArray(),
            boldCharsetCompiled = new Texture2dArray();

    public CharProvider(@NonNull Font font) {
        setFont(font);
    }

    public CharData getChar(char character) {
       // CharData c = bold ? boldCharset[character] : charset[character];
        //return c == null ? CharData.NULL : c;
        return null;
    }

    public void setFont(@NonNull Font font) {
        if (!this.font.equals(font)) {
            // initializes bold font and normal font
            if (font.isBold()) {
                font = new Font(
                        font.getName(),
                        PLAIN,
                        font.getSize()
                );
                boldFont = font;
            } else {
                this.font = font;
                boldFont = new Font(
                        font.getName(),
                        BOLD,
                        font.getSize()
                );
            }
            build();
        }
    }

    public void setAntialiasing(boolean antialiasing) {
        if (this.antialiasing != antialiasing) {
            this.antialiasing = antialiasing;
            build();
        }
    }

    private FontMetrics retrieveMetrics(boolean bold) {
        // creates a temporary image to get current font metrics
        BufferedImage temp = new BufferedImage(1, 1, TYPE_INT_ARGB);
        Graphics2D g = temp.createGraphics();
        if (antialiasing)
            g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setFont(bold ? boldFont : font);
        return g.getFontMetrics();
    }

    private CharData retrieveChar(char character, boolean bold) {
        FontMetrics m = retrieveMetrics(bold);

        // gets character size
        int w = m.charWidth(character);
        int h = m.getHeight();

        // initializes char image
        BufferedImage image = new BufferedImage(w, h, GL_RGBA8);
        Graphics2D g = image.createGraphics();
        if (antialiasing)
            g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setFont(bold ? boldFont : font);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(character), 0, m.getAscent());

        return new CharData(
                character,
                m.charWidth(character),
                m.getHeight(),
                image
        );
    }

    @Getter
    private int maxWidth, maxHeight; // size of the bigger character drawn

    public void build(boolean bold) {
        maxWidth  = 0;
        maxHeight = 0;
        for (char c = 0; c < 128; c++) {
            CharData cr = retrieveChar(c, bold);
            int cw      = cr.getWidth();
            int ch      = cr.getHeight();
            // gets max character size to set the max size for texture array
            if (cw >= maxWidth)
                maxWidth = cw;
            if (ch >= maxHeight)
                maxHeight = ch;
            // registers the char retrieved
            if (bold)
                boldCharset[c] = cr;
            else
                charset[c] = cr;
        }
        // now we can initialize the texture array
        Texture2dArray target = (bold ? boldCharsetCompiled : charsetCompiled);
        target.allocate(1, GL_RGBA8, maxWidth, maxHeight, 128);
        for (char c = 0; c < 128; c++)
            target.load(c, new ImageContent(charset[c].getImage()));
    }

    public void build() {
        // builds both fonts
        build(true);
        build(false);
    }
}
