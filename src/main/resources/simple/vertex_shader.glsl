#version 410 core

layout (location = 0) in vec2 position;
layout (location = 1) in vec2 itexCoord;

out vec2 texCoord;

uniform mat4 projection;
uniform mat4 transform;

void main()
{
    gl_Position = projection * transform * vec4(position, 0.0, 1.0);
    texCoord = itexCoord;
}