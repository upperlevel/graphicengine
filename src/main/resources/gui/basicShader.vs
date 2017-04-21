#version 410 core

layout (location = 0) in vec2 position;

uniform mat4 model;
uniform float depth = 0f;

out vec2 texCoord;

void main()
{
    //TODO: include the vec(2) multiplication in the position!
    //(min + max * position) * 2 - 1
    // old = vec4((bounds.xy + bounds.zw * position) * vec2(2) - vec2(1), depth, 1.0);

    // adapt given gui coords to screen coords
    gl_Position = model * vec4((position * vec2(2) - vec2(1)) * vec2(1, -1), depth, 1);

    texCoord = position;
}