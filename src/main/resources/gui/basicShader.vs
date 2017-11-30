#version 410 core

in vec2 position;

uniform vec4 bounds;
uniform float depth = 0f;

out vec2 texCoord;

void main() {
    // (coords + size * position) * 2 - 1
    // in 0, 0 only coords (minX, minY), in 1, 1 coords + size (maxX, maxY)...
    gl_Position = vec4((bounds.xy + bounds.zw * position) * vec2(2) - vec2(1), depth, 1.0);

    texCoord = position;
}