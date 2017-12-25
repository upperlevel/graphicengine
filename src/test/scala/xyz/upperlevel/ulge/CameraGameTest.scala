package xyz.upperlevel.ulge

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.{GL_COLOR_BUFFER_BIT, GL_DEPTH_BUFFER_BIT, glClear, glClearColor}
import xyz.upperlevel.event.{EventHandler, Listener}
import xyz.upperlevel.ulge.opengl.DataType
import xyz.upperlevel.ulge.opengl.buffer.{DrawMode, Vbo, VboDataUsage, VertexLinker}
import xyz.upperlevel.ulge.opengl.shader._
import xyz.upperlevel.ulge.util.math.{OrthographicCamera, PerspectiveCamera}
import xyz.upperlevel.ulge.window.event.key.Key
import xyz.upperlevel.ulge.window.event.{CursorMoveEvent, MouseScrollEvent}
import xyz.upperlevel.ulge.window.{Glfw, Window}

object CameraGameTest extends Listener {
  var window: Window = _

  val vertexShaderSrc: String =
    "#version 330" +
      "\nin vec2 position;" +
      "\nin vec3 color;" +
      "\nuniform mat4 camera;" +
      "\nout vec3 Color;" +
      "\nvoid main() {" +
      "\ngl_Position = camera * vec4(position, 0.0, 1.0);" +
      "\nColor = color;" +
      "\n}"

  val fragmentShaderSrc: String = "" +
    "#version 330" +
    "\nin vec3 Color;" +
    "\nvoid main() {" +
    "\ngl_FragColor = vec4(Color, 1.0);" +
    "\n}"

  val vertexBuffer: Array[Float] = Array[Float](
    0f, 0f, 1f, 0f, 0f,
    0f, 1f, 0f, 1f, 0f,
    1f, 1f, 0f, 0f, 1f,

    0f, 0f, 1f, 0f, 0f,
    1f, 1f, 0f, 0f, 1f,
    1f, 0f, 1f, 1f, 0f
  )

  val camera = new PerspectiveCamera()
  camera.setPosition(0, 0, 1f)
  camera.setRotation(0f, 0f)

  var lastCursorX = 0.0
  var lastCursorY = 0.0

  val RotationSensitivity = 0.1f
  val Sensitivity = 0.01f

  @EventHandler
  def onCursorMove(e: CursorMoveEvent): Unit = {
    val mx = e.getX - lastCursorX
    val my = e.getY - lastCursorY

    camera.rotate((Math toRadians mx).toFloat * RotationSensitivity, (Math toRadians my).toFloat * RotationSensitivity)

    lastCursorX = e.getX
    lastCursorY = e.getY
  }

  def testKeyChanges(): Unit = {
    if (window.testKey(Key.W)) {
      camera.move(camera.getForwardDirection, Sensitivity)
    }
    if (window.testKey(Key.S)) {
      camera.move(camera.getForwardDirection, -Sensitivity)
    }
    if (window.testKey(Key.D)) {
      camera.move(camera.getRightDirection, Sensitivity)
    }
    if (window.testKey(Key.A)) {
      camera.move(camera.getRightDirection, -Sensitivity)
    }
    if (window.testKey(Key.SPACE)) {
      camera.move(camera.getUpDirection, Sensitivity)
    }
    if (window.testKey(Key.LEFT_SHIFT)) {
      camera.move(camera.getUpDirection, -Sensitivity)
    }
    if (window.testKey(Key.ESCAPE)) {
      window.close()
    }
  }

  @EventHandler
  def onMouseScroll(e: MouseScrollEvent): Unit = {
    camera.setFov(camera.getFov - Math.toRadians(e.getY).toFloat)
  }

  def main(args: Array[String]): Unit = {
    window = Glfw.createWindow(1000, 500, "Camera Test", false)

    // Sets cursor onInit known position
    window.setCursorPosition(window.getWidth / 2f, window.getHeight / 2f)
    lastCursorX = window.getWidth / 2f
    lastCursorY = window.getHeight / 2f

    window.contextualize()
    window.show()

    camera.setAspectRatio(1000f / 500f)

    window.getEventManager.register(this)
    window.setCursorEnabled(false)

    val program = new Program()

    var shader = new Shader(ShaderType.VERTEX)
    shader.linkSource(vertexShaderSrc)
    var stat = shader.compileSource()
    println("Vertex compile status: " + stat.getLog)
    program.attach(shader)

    shader = new Shader(ShaderType.FRAGMENT)
    shader.linkSource(fragmentShaderSrc)
    stat = shader.compileSource()
    println("Fragment compile status: " + stat.getLog)
    program.attach(shader)

    program.link()
    program.use()

    val camUni = program.getUniform("camera")

    val vbo = new Vbo()
    vbo.loadData(vertexBuffer, VboDataUsage.STATIC_DRAW)

    vbo.bind()

    val v = new VertexLinker()
    v.attrib(program.getAttribLocation("position"), 2, DataType.FLOAT, false, 0)
    v.attrib(program.getAttribLocation("color"), 3, DataType.FLOAT, false, 2 * DataType.FLOAT.getByteCount)
    v.setup()

    while (!window.isClosed) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      glClearColor(0f, 0f, 0f, 1f)

      camUni.matrix4(camera.getCameraMatrix.get(BufferUtils.createFloatBuffer(16)))

      vbo.draw(DrawMode.TRIANGLES, 0, 6)

      testKeyChanges()
      window.update()
    }

    program.destroy()
    vbo.destroy()
    window.destroy()
  }
}
