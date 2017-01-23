package xyz.upperlevel.graphicengine.api;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import xyz.upperlevel.graphicengine.api.opengl.model.Model;
import xyz.upperlevel.graphicengine.api.opengl.model.VertexDefiner;
import xyz.upperlevel.graphicengine.api.opengl.model.Vertices;
import xyz.upperlevel.graphicengine.api.opengl.shader.*;
import xyz.upperlevel.graphicengine.api.opengl.texture.Texture;
import xyz.upperlevel.graphicengine.api.opengl.texture.loader.TextureContent;
import xyz.upperlevel.graphicengine.api.opengl.texture.loader.TextureLoaderManager;
import xyz.upperlevel.graphicengine.api.util.math.Camera;
import xyz.upperlevel.graphicengine.api.window.GLFW;
import xyz.upperlevel.graphicengine.api.window.Window;
import xyz.upperlevel.graphicengine.api.window.event.CursorMoveEvent;
import xyz.upperlevel.graphicengine.api.window.event.MouseScrollEvent;
import xyz.upperlevel.graphicengine.api.window.event.WindowEventHandler;

import java.io.*;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static xyz.upperlevel.graphicengine.api.opengl.NumberType.DOUBLE;

public class GraphicEngineTest1 {

    private static final String VERTEX_SRC =
            "#version 330 core\n" +
                    "in vec3 pos;" +
                    "in vec3 normal;" +

                    "out vec3 FragmentPosition;" +
                    "out vec3 Normal;" +

                    "uniform mat4 model;" +
                    "uniform mat4 camera;" +

                    "void main()" +
                    "{" +
                    "FragmentPosition = vec3(model * vec4(pos, 1.0));" +
                    "Normal = mat3(transpose(inverse(model))) * normal;" +
                    "gl_Position = camera * model * vec4(pos, 1.0);" +
                    "}";

    private static final String FRAGMENT_SRC =
            "#version 330 core\n" +
                    "in vec3 FragmentPosition;" +
                    "in vec3 Normal;" +
                    "" +
                    "uniform vec3 viewPosition;" +
                    "uniform vec3 lightPosition;" +
                    "uniform vec3 lightColor;" +
                    "uniform vec3 color;" +
                    "" +
                    "void main()" +
                    "{" +
                    // ambient
                    "float ambientStrength = 0.1f;" +
                    "vec3 ambient = ambientStrength * lightColor;" +
                    // diffuse
                    "vec3 norm = normalize(Normal);" +
                    "vec3 lightDirection = normalize(lightPosition-FragmentPosition);" +
                    "float difference = max(dot(norm, lightDirection), 0.0);" +
                    "vec3 diffuse = (difference * lightColor);" +
                    // specular
                    "float specStrength = 0.5f;" +
                    "vec3 viewDirection = normalize(viewPosition - FragmentPosition);" +
                    "vec3 reflectDirection = reflect(-lightDirection, norm);" +
                    "float spec = pow(max(dot(viewDirection, reflectDirection), 0.0), 256);" +
                    "vec3 specular = specStrength * spec * lightColor;" +
                    // result
                    "vec3 result = (specular + diffuse + ambient) * color;" +
                    "gl_FragColor = vec4(result, 1f);" +
                    "}";

    public static final int WIDTH = 500;
    public static final int HEIGHT = 425;

    public static float SENSITIVITY = 0.01f;

    private static float camera_yaw = 0;
    private static float camera_pitch = 0;

    private static int camera_fov = 90;

    private static final Camera camera = new Camera();

    public static String loadSource(InputStream stream) {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(stream));
        String output = "";
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output += line + "\n";
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot read stream");
        }
        return output;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        float sensitivity = 0.01f;
        try {
            sensitivity = Float.parseFloat(args[0]);
        } catch (Exception ignored) {
        }
        SENSITIVITY = sensitivity;

        //--------------------------------
        // defines window
        Window win = GLFW.createWindow(WIDTH, HEIGHT, "ciao", false);
        // scroll
        WindowEventHandler<MouseScrollEvent> scroll = GLFW.events().MOUSE_SCROLL.inst();
        scroll.register((window, x, y) -> {
            camera_fov -= y;
            camera.setFov(Math.toRadians(camera_fov));
        });

        win.registerEventHandler(scroll);
        win.disableCursor();
        win.setCursorPosition(0, 0);
        // mouse move
        WindowEventHandler<CursorMoveEvent> cursorMove = GLFW.events().CURSOR_MOVE.inst();
        cursorMove.register(new CursorMoveEvent() {
            private double last_x = 0, last_y = 0;

            @Override
            public void onCall(Window window, double x, double y) {
                double mov_x = last_x - x;
                double mov_y = y - last_y;

                camera_yaw += -mov_x;
                camera_pitch += mov_y;
                if (camera_pitch > 90)
                    camera_pitch = 90;
                else if (camera_pitch < -90)
                    camera_pitch = -90;

                camera.setRotation(Math.toRadians(camera_yaw), Math.toRadians(camera_pitch), 0);

                last_x = x;
                last_y = y;
            }
        });
        win.registerEventHandler(cursorMove);
        win.contextualize();
        win.show();

        win.setVSync(false);
        // starts
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_ALPHA);
        // defines shader(s)

        CompileStatus cs;

        System.out.println("VERTEX");
        Shader vtx = new Shader(ShaderType.VERTEX);
        try {
            vtx.linkSource(new File("C:/Users/Lorenzo/Desktop/SIGNOR MUCI DON'T TOUCH!/textureBasicVertexShader.glsl"));
        } catch (IOException e) {
            throw new IllegalStateException("failed to load vertex shader", e);
        }
        cs = vtx.compileSource();
        System.out.println(cs.log);

        System.out.println("FRAGMENT");
        Shader frag = new Shader(ShaderType.FRAGMENT);
        try {
            frag.linkSource(new File("C:/Users/Lorenzo/Desktop/SIGNOR MUCI DON'T TOUCH!/textureBasicFragmentShader.glsl"));
        } catch (IOException e) {
            throw new IllegalStateException("failed to load fragment shader", e);
        }
        cs = frag.compileSource();
        System.out.println(cs.log);

        // defines the program, attaches the shaders to him and enables it
        Program program = new Program();
        program.attach(vtx);
        program.attach(frag);
        program.link();
        Uniformer uniformer = program.bind();
        GL30.glBindFragDataLocation(program.id, 0, "color");

        //--------------------------------
        // defines vertices
        Vertices vert = new Vertices(8)
                .add(-0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f)
                .add(0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f)
                .add(0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f)
                .add(0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f)
                .add(-0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f)
                .add(-0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f)

                .add(-0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f)
                .add(0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f)
                .add(0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)
                .add(0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)
                .add(-0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f)
                .add(-0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f)

                .add(-0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f)
                .add(-0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f)
                .add(-0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f)
                .add(-0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f)
                .add(-0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f)
                .add(-0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f)

                .add(0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f)
                .add(0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f)
                .add(0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f)
                .add(0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f)
                .add(0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f)
                .add(0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f)

                .add(-0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f)
                .add(0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f)
                .add(0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f)
                .add(0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f)
                .add(-0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f)
                .add(-0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f)

                .add(-0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f)
                .add(0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f)
                .add(0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f)
                .add(0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f)
                .add(-0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f)
                .add(-0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);
                       /* .add(-0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f)
                        .add(0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f)
                        .add(0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f)
                        .add(0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f)
                        .add(-0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f)
                        .add(-0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f)

                        .add(-0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f)
                        .add(0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f)
                        .add(0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f)
                        .add(0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f)
                        .add(-0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f)
                        .add(-0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f)

                        .add(-0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f)
                        .add(-0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f)
                        .add(-0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f)
                        .add(-0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f)
                        .add(-0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f)
                        .add(-0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f)

                        .add(0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f)
                        .add(0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f)
                        .add(0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f)
                        .add(0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f)
                        .add(0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f)
                        .add(0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f)

                        .add(-0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f)
                        .add(0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f)
                        .add(0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f)
                        .add(0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f)
                        .add(-0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f)
                        .add(-0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f)

                        .add(-0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f)
                        .add(0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f)
                        .add(0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f)
                        .add(0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f)
                        .add(-0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f)
                        .add(-0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f);
                        /* // z: 1
                        .add(0f, 0f, 1f, 0f, 0f, 1f)
                        .add(0f, 1f, 1f, 0f, 0f, 1f)
                        .add(1f, 0f, 1f, 0f, 0f, 1f)
                        .add(0f, 1f, 1f, 0f, 0f, 1f)
                        .add(1f, 1f, 1f, 0f, 0f, 1f)
                        .add(1f, 0f, 1f, 0f, 0f, 1f)

                        // z: 0
                        .add(0f, 0f, 0f, 0f, 0f, -1f)
                        .add(0f, 1f, 0f, 0f, 0f, -1f)
                        .add(1f, 0f, 0f, 0f, 0f, -1f)
                        .add(0f, 1f, 0f, 0f, 0f, -1f)
                        .add(1f, 1f, 0f, 0f, 0f, -1f)
                        .add(1f, 0f, 0f, 0f, 0f, -1f)

                        // x: 1
                        .add(1f, 0f, 1f, 1f, 0f, 0f)
                        .add(1f, 1f, 1f, 1f, 0f, 0f)
                        .add(1f, 1f, 0f, 1f, 0f, 0f)
                        .add(1f, 0f, 1f, 1f, 0f, 0f)
                        .add(1f, 0f, 0f, 1f, 0f, 0f)
                        .add(1f, 1f, 0f, 1f, 0f, 0f)

                        // x: 0
                        .add(0f, 0f, 1f, -1f, 0f, 0f)
                        .add(0f, 1f, 1f, -1f, 0f, 0f)
                        .add(0f, 1f, 0f, -1f, 0f, 0f)
                        .add(0f, 0f, 1f, -1f, 0f, 0f)
                        .add(0f, 0f, 0f, -1f, 0f, 0f)
                        .add(0f, 1f, 0f, -1f, 0f, 0f)

                        // y: 1
                        .add(0f, 1f, 1f, 0f, 1f, 0f)
                        .add(1f, 1f, 1f, 0f, 1f, 0f)
                        .add(1f, 1f, 0f, 0f, 1f, 0f)
                        .add(0f, 1f, 1f, 0f, 1f, 0f)
                        .add(0f, 1f, 0f, 0f, 1f, 0f)
                        .add(1f, 1f, 0f, 0f, 1f, 0f)

                        // y: 0
                        .add(0f, 0f, 1f, 0f, -1f, 1f)
                        .add(1f, 0f, 1f, 0f, -1f, 1f)
                        .add(1f, 0f, 0f, 0f, -1f, 0f)
                        .add(0f, 0f, 1f, 0f, -1f, 1f)
                        .add(0f, 0f, 0f, 0f, -1f, 0f)
                        .add(1f, 0f, 0f, 0f, -1f, 0f);*/

        VertexDefiner definer = vert.definer(DOUBLE)
                .attrib(uniformer.getAttribLocation("position"), 3)
                .attrib(uniformer.getAttribLocation("normal"), 3)
                .attrib(uniformer.getAttribLocation("texCoords"), 2)
                .build();
        TextureLoaderManager tlm = TextureLoaderManager.DEFAULT;
        TextureContent diffTexContent, specTexContent;
        diffTexContent = tlm.load(new File("C:/Users/Lorenzo/Desktop/textures/container2.png"));
        specTexContent = tlm.load(new File("C:/Users/Lorenzo/Desktop/textures/container2_specular.png"));

        Texture diffTex = new Texture();
        diffTex.setContent(diffTexContent);

        Texture specTex = new Texture();
        specTex.setContent(specTexContent);

        Model m = new Model((mode, model) -> glDrawArrays(mode.getId(), 0, model.getVerticesCount()));
        m.loadData(vert);
        m.setDefiner(definer);

        uniformer.setUniform("pointLightsCount", 1);
        uniformer.setUniform("pointLights[0].position", 1f, 1f, 0f);
        uniformer.setUniform("pointLights[0].ambient", 0.2f, 0.2f, 0.2f);
        uniformer.setUniform("pointLights[0].diffuse", 1f, 1f, 1f);
        uniformer.setUniform("pointLights[0].specular", 1f, 1f, 1f);
        uniformer.setUniform("pointLights[0].constant", 1f);
        uniformer.setUniform("pointLights[0].linear", 0.09f);
        uniformer.setUniform("pointLights[0].quadratic", 0.032f);

        uniformer.setUniform("directionLight.direction", 0.2f, 1f, 0f);
        uniformer.setUniform("directionLight.ambient", 0.2f, 0.2f, 0.2f);
        uniformer.setUniform("directionLight.diffuse", 0.35f, 0.35f, 0.35f);
        uniformer.setUniform("directionLight.specular", 1f, 1f, 1f);

        GL20.glUniform1i(uniformer.getUniLocation("material.diffuse"), 0);
        GL20.glUniform1i(uniformer.getUniLocation("material.specular"), 1);

        System.out.println("MATERIAL DIFFUSE TEXTURE UNIT: " + GL20.glGetUniformi(program.id, uniformer.getUniLocation("material.diffuse")));
        System.out.println("MATERIAL SPECULAR TEXTURE UNIT: " + GL20.glGetUniformi(program.getId(), uniformer.getUniLocation("material.specular")));

        float rot = 0f;

        int fps_counter = 0;

        long last_t = 0;
        long delta_time = 0;

        while (!win.isClosed()) {
            fps_counter++;

            delta_time += System.currentTimeMillis() - last_t;
            last_t = System.currentTimeMillis();

            if (delta_time >= 1000) {
                System.out.println("FPS: " + fps_counter);
                fps_counter = 0;
                delta_time = 0;
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            FloatBuffer bfr;
            bfr = BufferUtils.createFloatBuffer(16);
            camera.getMatrix().get(bfr);
            uniformer.setUniformMatrix("camera", bfr);

            Vector3f position = camera.getPosition();
            uniformer.setUniform("viewPosition", position.x, position.y, position.z);

            bfr = BufferUtils.createFloatBuffer(16);
            Matrix4f transformation = new Matrix4f();
            transformation.rotate((float) Math.toRadians(rot), new Vector3f(0, 1, 0));
            transformation.get(bfr);
            uniformer.setUniformMatrix("model", bfr);

            uniformer.setUniform("material.shininess", 32f);

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            diffTex.bind();

            GL13.glActiveTexture(GL13.GL_TEXTURE1);
            specTex.bind();

            m.draw();

            rot += 0.25f;

            int s;
            s = glfwGetKey(win.getId(), GLFW_KEY_W);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getForward().mul(SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_S);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getForward().mul(-SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_A);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getRight().mul(-SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_D);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getRight().mul(SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_LEFT_SHIFT);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getUp().mul(-SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_SPACE);
            if (s > 0)
                camera.setPosition(camera.getPosition().add(camera.getUp().mul(SENSITIVITY)));
            s = glfwGetKey(win.getId(), GLFW_KEY_ESCAPE);
            if (s == GLFW_PRESS) {
                win.close();
                break;
            }
            win.update();
        }
    }
}
