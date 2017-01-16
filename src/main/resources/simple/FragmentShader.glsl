#version 330 core

in vec2 texCoord;

out vec4 color;

uniform sampler2D tex;
uniform vec4 col;

void main()
{
    color = texture(tex, texCoord) * col;
}
