package xyz.upperlevel.ulge.simple;

import org.joml.Vector2f;
import xyz.upperlevel.ulge.simple.shapes.Circle;
import xyz.upperlevel.ulge.util.Color;
import xyz.upperlevel.ulge.window.event.key.Key;

public class SimpleGameTest extends SimpleGame {
    public static final double VEL = 300;

    public Circle circle;


    @Override
    public void onInit() {
        circle = new Circle(
                new Vector2f(10f, 10f),
                new Vector2f(20f, 20f),
                Color.BLUE,
                20
        );
        register(circle);
    }

    @Override
    public void onUpdate(double delta) {
        if (testKey(Key.W))
            circle.pos.add(0.0f, (float) (VEL * delta));
        if (testKey(Key.S))
            circle.pos.sub(0.0f, (float) (VEL * delta));

        if (testKey(Key.A))
            circle.pos.sub((float) (VEL * delta), 0.0f);
        if (testKey(Key.D))
            circle.pos.add((float) (VEL * delta), 0.0f);

        if (circle.pos.x > getWidth())
            circle.pos.x = 0;
        if (circle.pos.x < 0)
            circle.pos.x = getWidth();

        if (circle.pos.y > getHeight())
            circle.pos.y = 0;
        if (circle.pos.y < 0)
            circle.pos.y = getHeight();
    }

    public static void main(String... args) {
        new SimpleGameTest().launch();
    }
}
