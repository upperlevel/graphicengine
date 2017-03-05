package xyz.upperlevel.ulge.gui.impl.text;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.BaseGui;
import xyz.upperlevel.ulge.gui.Bounds;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.opengl.texture.Texture2D;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextRenderer;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.util.FontUtil;
import xyz.upperlevel.utils.event.EventManager;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

@Accessors(fluent = true)
public class TextBox extends BaseGui {

    public static TextRenderer DEFAULT_TEXT_RENDER = FontUtil.textRenderer(Font.getFont(Font.SERIF));
    public static TextRenderer.TextOrigin DEFAULT_ORIGIN = TextRenderer.TextOrigin.UPPER_LEFT;
    public static float DEFAULT_SIZE = 0.4f;
    public static boolean DEFAULT_LIMIT_TO_GUI = false;

    @Getter
    @Setter
    private SuperText text = SuperText.EMPTY;

    @Getter
    @Setter
    public TextRenderer renderer = DEFAULT_TEXT_RENDER;

    @Getter
    @Setter
    private float maxW = TextRenderer.DEF_MAX_WIDTH;

    @Getter
    @Setter
    private TextRenderer.TextOrigin origin = DEFAULT_ORIGIN;

    @Getter
    @Setter
    private float size = DEFAULT_SIZE;

    @Getter
    @Setter
    private boolean limitToGui = DEFAULT_LIMIT_TO_GUI;

    private Color background = Color.rgba(0f, 0f, 0f, 0f);

    public TextBox(Bounds bounds, EventManager eventManager) {
        super(bounds, eventManager);
    }

    public TextBox(Bounds bounds) {
        super(bounds);
    }

    public TextBox() {
        super();
    }

    @Override
    public void init(GuiRenderer r) {
        System.out.println("TextBox -> init()");
        renderer.init();
    }

    @Override
    public void render(GuiRenderer r) {

        glEnable(GL_STENCIL_TEST);

        glStencilOp(GL_ZERO, GL_ZERO, GL_REPLACE);

        glStencilFunc(GL_ALWAYS, 1, 0xFF);
        //glStencilMask(0xFF);//Already on 0xFF because of no other calls

        r.setTexture(Texture2D.NULL);
        r.setColor(background);
        r.fill();


        glStencilFunc(GL_EQUAL, 1, 0xFF);
        glStencilMask(0x00);

        Bounds abs = r.getAbsoluteBounds();

        final float maxW = limitToGui ? (abs.maxX - abs.minX) : Float.POSITIVE_INFINITY;

        //Coordinate conversion
        // GUI  -> OpenGL
        //[0;1] -> [1;-1] (y inverted)
        renderer.drawText2D(text, new Vector2f(abs.minX * 2f - 1f, 1f - abs.minY * 2f), origin, r.getDepth(), size, maxW);


        glDisable(GL_STENCIL_TEST);

    }
}
