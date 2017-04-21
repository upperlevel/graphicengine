#version 410 core

struct Char {
    float x, y;
    float w, h;
};

in vec2 texCoord;

out vec4 color;

uniform sampler2D tex;
uniform vec4 col;
uniform Char ch;

void main()
{
    color = texture(tex, vec2(
        ch.x + ch.w * texCoord.x,
        ch.y + ch.h * (1.0f - texCoord.y)
    )) * col;
}
