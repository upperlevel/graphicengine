package xyz.upperlevel.ulge.gui.impl;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.gui.GuiBackground;
import xyz.upperlevel.ulge.gui.GuiRenderer;
import xyz.upperlevel.ulge.gui.events.GuiClickEvent;
import xyz.upperlevel.ulge.gui.impl.text.TextBox;
import xyz.upperlevel.ulge.text.SuperText;
import xyz.upperlevel.utils.event.EventManager;

import java.util.function.Function;
import java.util.function.Supplier;

public class Button extends Pane {
    protected TextBox text = new TextBox();
    protected Runnable run = null;


    public Button(EventManager manager, Supplier<GuiBackground> bkg) {
        super(manager, bkg);
    }

    public Button(Supplier<GuiBackground> bkg) {
        super(bkg);
    }

    public Button(Function<Pane, GuiBackground> bkg) {
        super(bkg);
    }

    public Button(GuiBackground bkg){
        super(bkg);
    }

    public Button() {
        super();
    }


    public Button setText(SuperText text) {
        this.text.text(text);
        return this;
    }

    public Button setSize(float size) {
        this.text.size(size);
        return this;
    }

    public Button setOnClick(Runnable run) {
        this.run = run;
        return this;
    }

    @Override
    public boolean onClickBegin(Vector2f position) {
        if(getEventManager().call(new GuiClickEvent(this, position, GuiClickEvent.Type.BEGIN))) {
            if(run != null)
                run.run();
            return true;
        } else return false;
    }

    @Override
    public void draw(GuiRenderer r) {
        super.draw(r);
        text.draw(r);
    }
}
