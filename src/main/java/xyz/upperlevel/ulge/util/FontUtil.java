package xyz.upperlevel.ulge.util;

import xyz.upperlevel.ulge.opengl.texture.Texture;
import xyz.upperlevel.ulge.opengl.texture.TextureParameter;
import xyz.upperlevel.ulge.opengl.texture.TextureParameters;
import xyz.upperlevel.ulge.opengl.texture.loader.TextureContent;
import xyz.upperlevel.ulge.text.impl.truetype.BitmapTextRenderer;
import xyz.upperlevel.ulge.text.impl.truetype.CharData;
import xyz.upperlevel.ulge.text.impl.truetype.CharDataManager;
import xyz.upperlevel.ulge.text.impl.truetype.chardata.StandardCharDataManager;

import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public final class FontUtil {
    public static final char[] NO_CHARS = new char[0];

    public static final TextureParameters DEF_TEX_PARAMS = new TextureParameters(Arrays.asList(
            new TextureParameter(TextureParameter.Type.Wrapping.S, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE),
                        new TextureParameter(TextureParameter.Type.Wrapping.T, TextureParameter.Value.Wrapping.CLAMP_TO_EDGE),
                        new TextureParameter(TextureParameter.Type.Filter.MIN, TextureParameter.Value.Filter.NEAREST),
                        new TextureParameter(TextureParameter.Type.Filter.MAG, TextureParameter.Value.Filter.NEAREST)
                ));

    public static BufferedImage drawFont(Font font, char c, boolean antiAliasing) {
        // Create a temporary image to extract the character's size
        BufferedImage tempfontImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) tempfontImage.getGraphics();
        if (antiAliasing) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();
        int charwidth = fontMetrics.charWidth(c);

        if (charwidth <= 0)
            charwidth = 1;

        int charheight = fontMetrics.getHeight();
        if (charheight <= 0)
            charheight = font.getSize();

        // Create another image holding the character we are creating
        BufferedImage fontImage = new BufferedImage(charwidth, charheight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gt = (Graphics2D) fontImage.getGraphics();
        if (antiAliasing) {
            gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        gt.setFont(font);

        gt.setColor(Color.WHITE);
        gt.drawString(String.valueOf(c), 0, fontMetrics.getAscent());

        return fontImage;
    }

    public static BitmapTextRenderer textRenderer(Font font, boolean antiAliasing, CharDataManager chars, TextureParameters parameters) {
        BufferedImage[] images = new BufferedImage[256 + chars.size()];

        final int spaceX = 2, spaceY = 2;

        final int width = 512;
        int height = 0;

        {
            int currentWidth = 0;
            int maxLineH = 0;

            int i = 0;

            for (char c : chars) {
                images[i] = drawFont(font, c, antiAliasing);

                final int h = images[i].getHeight();

                final int w = images[i].getWidth();

                if (w > width)
                    throw new IllegalArgumentException("Unsupported font rw: " + w);

                if (currentWidth == 0) {
                    maxLineH = h;
                    currentWidth = w;
                } else {
                    if (w + currentWidth > width) {
                        height += height == 0 ? maxLineH :  maxLineH + spaceY;
                        maxLineH = h;
                        currentWidth = w;
                    } else {
                        if (h > maxLineH)
                            maxLineH = h;
                        currentWidth += spaceX + w;
                    }
                }
                i++;
            }
            height += maxLineH;
        }

        BufferedImage image;

        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();

            int currentX = 0;
            int currentY = 0;
            int maxLineY = 0;

            int i = 0;

            for (char c : chars) {
                BufferedImage img = images[i];

                final int w = img.getWidth();
                final int h = img.getHeight();

                if(currentX == 0) {
                    maxLineY = h;
                } else {
                    if(currentX + w > width) {
                        currentY += maxLineY + spaceY;
                        maxLineY = 0;
                        currentX = 0;
                    } else {
                        currentX += spaceX;
                        if(h > maxLineY)
                            maxLineY = h;
                    }
                }

                CharData data = new CharData(currentX, currentY, w, h);
                chars.set(c, data);

                g.drawImage(img, currentX, currentY, null);

                currentX += w;
                i++;
            }
        }

        Texture tex = new Texture().setContent(new TextureContent(image, false), parameters);

        return new BitmapTextRenderer(tex, chars);
    }

    public static BitmapTextRenderer textRenderer(Font font) {
        return textRenderer(font, false, new StandardCharDataManager(), DEF_TEX_PARAMS);
    }

    public static BitmapTextRenderer textRenderer(Font font, boolean antialiasing) {
        return textRenderer(font, antialiasing, new StandardCharDataManager(), DEF_TEX_PARAMS);
    }

    public static BitmapTextRenderer textRenderer(Font font, boolean antialiasing, CharDataManager chars) {
        return textRenderer(font, antialiasing, chars, DEF_TEX_PARAMS);
    }



    private FontUtil(){}
}
