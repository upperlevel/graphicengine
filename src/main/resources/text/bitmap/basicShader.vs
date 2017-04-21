#version 410 core

layout (location = 0) in vec2 position;

out vec2 texCoord;

uniform mat4 projection;


void main()
{
    gl_Position = projection * vec4(position, 0.0f, 1.0);
    texCoord = position.xy;
}