package xyz.upperlevel.graphicengine.api;

import org.lwjgl.opengl.GL11;
import xyz.upperlevel.graphicengine.api.opengl.model.Model;
import xyz.upperlevel.graphicengine.api.opengl.model.Vertices;
import xyz.upperlevel.graphicengine.api.opengl.shader.CompileStatus;
import xyz.upperlevel.graphicengine.api.opengl.shader.Program;
import xyz.upperlevel.graphicengine.api.opengl.shader.Shader;
import xyz.upperlevel.graphicengine.api.opengl.shader.ShaderType;
import xyz.upperlevel.graphicengine.api.opengl.shader.loader.SimpleShaderSourceLoader;
import xyz.upperlevel.graphicengine.api.util.math.Camera;
import xyz.upperlevel.graphicengine.api.window.GLFW;
import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.action.GLFWAction;
import xyz.upperlevel.graphicengine.api.window.event.key.GLFWKey;

import java.io.File;

public class SignorMucino {

    private static Window w;
    private static Camera camera = new Camera();

    public static void main(String[] args) {
        w = GLFW.createWindow(500, 500, "Ciao");
        w.contextualize();
        w.show();

        // SHADER
        String v_src = SimpleShaderSourceLoader.INSTANCE.load(new File("C:/users/lorenzo/desktop/SIGNOR MUCI DON'T TOUCH!/sgnMucino_vrt.glsl"));
        String f_src = SimpleShaderSourceLoader.INSTANCE.load(new File("C:/users/lorenzo/desktop/SIGNOR MUCI DON'T TOUCH!/sgnMucino_frg.glsl"));

        CompileStatus err;

        Shader vs = new Shader(ShaderType.VERTEX);
        vs.linkSource(v_src);
        err = vs.compileSource();
        System.out.println("Vertex compiled: " + err.log);

        Shader fs = new Shader(ShaderType.FRAGMENT);
        vs.linkSource(f_src);
        err = fs.compileSource();
        System.out.println("Fragment compiled: " + err.log);

        Program prg = new Program();
        prg.attach(vs);
        prg.attach(fs);
        prg.link();
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
        m.setDefiner(v.definer()
                .attrib(0, 2, 0)
                .attrib(1, 3, 2)
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
        if (w.getKeyState(GLFWKey.W).equals(GLFWAction.PRESS) || w.getKeyState(GLFWKey.W).equals(GLFWAction.REPEAT)) {
            System.out.println("W pressed/repeated!");
            camera.setPosition(camera.getPosition().add(camera.getForward()));
        }
    }
}
