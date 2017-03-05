package xyz.upperlevel.ulge.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import xyz.upperlevel.ulge.opengl.buffer.BufferUtil;
import xyz.upperlevel.ulge.opengl.shader.Program;
import xyz.upperlevel.ulge.opengl.shader.ShaderType;
import xyz.upperlevel.ulge.opengl.shader.ShaderUtil;
import xyz.upperlevel.ulge.opengl.shader.Uniformer;
import xyz.upperlevel.ulge.window.Glfw;
import xyz.upperlevel.ulge.window.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class BaseOpenGLTest {

    public static void main(String[] args) {
        Window win = Glfw.createWindow(500, 500, "base opengl test", false);
        win.centerPosition();
        win.contextualize();
        win.show();
        win.setVSync(false);

        // creates base standardProgram
        Program program = new Program();
        ClassLoader res = BaseOpenGLTest.class.getClassLoader();
        program.attach(ShaderUtil.load(ShaderType.VERTEX, res.getResourceAsStream("base/vertex_shader.glsl")));
        program.attach(ShaderUtil.load(ShaderType.FRAGMENT, res.getResourceAsStream("base/fragment_shader.glsl")));
        // links standardProgram
        program.link();
        Uniformer uniformer = program.bind();
        GL30.glBindFragDataLocation(program.getId(), 0,"display");

        // sets up shape
        float[] vert = {
                -1f, -1f,//0
                1f, -1f,//1
                1f, 1f,//2

                -1f, -1f,//0
                1f, 1f,//2
                -1f, 1f//3
        };
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtil.createBuffer(vert), GL_STATIC_DRAW);

        int posLoc = uniformer.getAttribLocation("position");
        glEnableVertexAttribArray(posLoc);
        glVertexAttribPointer(posLoc, 2, GL11.GL_FLOAT, false, 2 * Float.BYTES, 0);

        while (!win.isClosed()) {
            glClear(GL11.GL_COLOR_BUFFER_BIT);
            glClearColor(0f, 0f, 0f, 0f);

            glDrawArrays(GL11.GL_TRIANGLES, 0, vert.length / 2);

            win.update();
        }
    }
}
