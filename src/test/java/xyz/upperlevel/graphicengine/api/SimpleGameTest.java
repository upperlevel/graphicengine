package xyz.upperlevel.graphicengine.api;

import org.joml.Vector2f;
import xyz.upperlevel.graphicengine.api.simple.SimpleColor;
import xyz.upperlevel.graphicengine.api.simple.SimpleGame;
import xyz.upperlevel.graphicengine.api.simple.shapes.Circle;
import xyz.upperlevel.graphicengine.api.window.event.key.GLFWKey;

public class SimpleGameTest extends SimpleGame {
    public static final double VEL = 100;

    public Circle circle;


    @Override
    public void init() {
        circle = new Circle(
                new Vector2f(10f, 10f),
                new Vector2f(20f, 20f),
                SimpleColor.BLUE,
                20
        );
        register(circle);
    }

    @Override
    public void update(double delta) {
        if(key(GLFWKey.W))
            circle.pos.add(0.0f, (float) (VEL * delta));
        if(key(GLFWKey.S))
            circle.pos.sub(0.0f, (float) (VEL * delta));

        if(key(GLFWKey.A))
            circle.pos.sub((float) (VEL * delta), 0.0f);
        if(key(GLFWKey.D))
            circle.pos.add((float) (VEL * delta), 0.0f);
    }

    public static void main(String... args) {
        new SimpleGameTest().launch();
    }
}
