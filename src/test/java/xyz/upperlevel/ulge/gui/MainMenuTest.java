package xyz.upperlevel.ulge.gui;

import xyz.upperlevel.ulge.gui.impl.Button;
import xyz.upperlevel.ulge.gui.impl.Pane;
import xyz.upperlevel.ulge.gui.impl.containers.MultipleContainer;
import xyz.upperlevel.ulge.gui.impl.containers.SingletonContainer;
import xyz.upperlevel.ulge.gui.impl.containers.UnionContainer;
import xyz.upperlevel.ulge.gui.impl.text.TextBox;
import xyz.upperlevel.ulge.opengl.texture.Texture2d;
import xyz.upperlevel.ulge.opengl.texture.TextureFormat;
import xyz.upperlevel.ulge.opengl.texture.TextureParameters;
import xyz.upperlevel.ulge.opengl.texture.loader.ImageContent;
import xyz.upperlevel.ulge.simple.SimpleGame;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.ulge.text.TextPiece;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.action.Action;
import xyz.upperlevel.ulge.window.event.button.MouseButton;

import java.util.HashMap;
import java.util.function.Function;

import static xyz.upperlevel.ulge.gui.GuiBackground.color;

public class MainMenuTest extends SimpleGame {
    public static final GuiBackground
            BTN_REST    = color(Color.web("#00897B")),
            BTN_HOVER   = color(Color.web("#26A69A")),
            BTN_CLICKED = color(Color.web("#FF9800"));

    public static Function<Pane, GuiBackground> BKG_GENERATOR = (pane) -> {
        if(pane.isClick())
            return BTN_CLICKED;
        if(pane.isHover())
            return BTN_HOVER;
        return BTN_REST;
    };


    private Gui gui;

    private SimpleGuiWrapper wrapper;


    @Override
    public void config() {
        vsync(false);
    }

    @Override
    public void init() {
        simpleAlpha();

        Texture2d bkg;
        {
            ImageContent c = ImageContent.fromResource("gui/main_menu/bkg.jpg", getClass());
            bkg = new Texture2d().loadImage(TextureFormat.RGBA, c);
            TextureParameters.getDefault().setup();
        }


        gui = new UnionContainer(
                new Pane(GuiBackground.texture(bkg)),
                new SingletonContainer(
                        new TextBox()
                                .size(0.3f)
                                .text(SuperText.of(TextPiece.of("Your Game!", Color.RED))),
                        new Bounds(0f, 0.1f, 1f, 0.3f)
                ),
                new SingletonContainer(
                        new MultipleContainer(
                            new HashMap<Bounds, Gui>() {{
                                put(
                                        new Bounds(0f, 0.05f, 1f, 0.25f),
                                        new Button(BKG_GENERATOR)
                                                .setText(SuperText.of("Singleplayer", Color.BLACK))
                                                .setSize(0.2f)
                                                .setOnClick(() -> onSinglePlayer())
                                );
                                put(
                                        new Bounds(0f, 0.3f, 1f, 0.5f),
                                        new Button(BKG_GENERATOR)
                                                .setText(SuperText.of("Multiplayer", Color.BLACK))
                                                .setSize(0.2f)
                                                .setOnClick(() -> onMultiPlayer())
                                );
                                put(
                                        new Bounds(0f, 0.55f, 1f, 0.75f),
                                        new Button(BKG_GENERATOR)
                                                .setText(SuperText.of("Options", Color.BLACK))
                                                .setSize(0.2f)
                                                .setOnClick(() -> onOptions())
                                );
                                put(
                                        new Bounds(0f, 0.8f, 1f, 1f),
                                        new Button(BKG_GENERATOR)
                                                .setText(SuperText.of("Quit game", Color.BLACK))
                                                .setSize(0.2f)
                                                .setOnClick(() -> onQuit())
                                );
                            }}
                        ),
                        new Bounds(0.1f, 0.3f, 0.9f, 0.9f)
                )

        );

        wrapper = new SimpleGuiWrapper(gui);

        gui.init(DefaultGuiRenderer.$);
    }

    private void onSinglePlayer() {
        System.out.println("Going SinglePlayer!");
    }

    private void onMultiPlayer() {
        System.out.println("Going MultiPlayer!");
    }

    public void onOptions() {
        System.out.println("Viewing options!");
    }

    public void onQuit() {
        System.out.println("Quitting...!");
        stop();
    }



    @Override
    public void postDraw() {
        DefaultGuiRenderer.$.getProgram().bind();
        gui.draw(DefaultGuiRenderer.$);

        wrapper.move(cursorPos());
    }

    @Override
    public void mouseChange(MouseButton button, Action action) {
        if(button == MouseButton.LEFT) {
            if(action == Action.PRESS)
                wrapper.clickBegin(cursorPos());
            else
                wrapper.clickEnd(cursorPos());
        }
    }

    public static void main(String... args) {
        new MainMenuTest().launch();
    }
}
