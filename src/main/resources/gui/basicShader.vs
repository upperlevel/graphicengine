#version 410 core

in vec2 texCoord;

out vec4 color;

uniform sampler2D tex;
uniform vec4 col;
uniform vec2 size = vec2(1);

void main()
{
    color = col * texture(tex, texCoord * size);
}
