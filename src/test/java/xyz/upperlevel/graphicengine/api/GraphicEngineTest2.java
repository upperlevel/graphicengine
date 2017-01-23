package xyz.upperlevel.graphicengine.api;

import org.lwjgl.opengl.GL11;
import xyz.upperlevel.graphicengine.api.opengl.model.Model;
import xyz.upperlevel.graphicengine.api.opengl.model.Vertices;
import xyz.upperlevel.graphicengine.api.opengl.shader.CompileStatus;
import xyz.upperlevel.graphicengine.api.opengl.shader.Program;
import xyz.upperlevel.graphicengine.api.opengl.shader.Shader;
import xyz.upperlevel.graphicengine.api.opengl.shader.ShaderType;
import xyz.upperlevel.graphicengine.api.util.math.Camera;
import xyz.upperlevel.graphicengine.api.window.GLFW;
import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.key.Key;

import java.io.File;
import java.io.IOException;

public class GraphicEngineTest2 {

    private static Window w;
    private static Camera camera = new Camera();

    public static void main(String[] args) {
        w = GLFW.createWindow(500, 500, "Ciao", false);
        w.contextualize();
        w.show();

        // SHADERS
        CompileStatus err;

        Shader vs = new Shader(ShaderType.VERTEX);
        try {
            vs.linkSource(new File("C:/users/lorenzo/desktop/SIGNOR MUCI DON'T TOUCH!/sgnMucino_vrt.glsl"));
        } catch (IOException e) {
            throw new IllegalStateException("failed to load vertex shader", e);
        }
        err = vs.compileSource();
        System.out.println("Vertex compiled: " + err.log);

        Shader fs = new Shader(ShaderType.FRAGMENT);
        try {
            fs.linkSource(new File("C:/users/lorenzo/desktop/SIGNOR MUCI DON'T TOUCH!/sgnMucino_frg.glsl"));
        } catch (IOException e) {
            throw new IllegalStateException("failed to load fragment shader", e);
        }
        err = fs.compileSource();
        System.out.println("Fragment compiled: " + err.log);

        Program prg = new Program()
                .attach(vs)
                .attach(fs)
                .link();
        prg.bind();

        // MODEL
        Vertices v = new Vertices()
                .add(-0.5f, 0.5f, 1f, 0f, 0f)
                .add(-0.5f, -0.5f, 0f, 1f, 0f)
                .add(0.5f, -0.5f, 0f, 0f, 1f)

                .add(0.5f, -0.5f, 0f, 0f, 1f)
                .add(0.5f, 0.5f, 0f, 1f, 1f)
                .add(-0.5f, 0.5f, 1f, 0f, 0f);

        Model m = new Model((mode, model) -> GL11.glDrawArrays(mode.id, 0, v.size()));
        m.loadData(v);
        m.setDefiner(
                v.definer()//The default type is double
                        .attrib(0, 2)
                        .attrib(1, 3)
                        .build());

        // LOOP
        while (!w.isClosed()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            GL11.glClearColor(0f, 0f, 0f, 0f);

            processInputs();

            m.draw();
            w.update();
        }

        // DESTROY
        m.destroy();
        prg.destroy();
        w.destroy();
        GLFW.destroy();
    }

    private static void processInputs() {
        if (w.getKey(Key.W)) {
            System.out.println("W pressed!");
            camera.setPosition(camera.getPosition().add(camera.getForward()));
        }
    }
}
