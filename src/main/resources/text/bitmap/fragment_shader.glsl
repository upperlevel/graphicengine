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

uniform int draw = 1;

void main()
{
    if(draw == 0) {
        color = texture(tex, vec2(
            ch.x + ch.w * texCoord.x,
            ch.y + ch.h * (1.0f - texCoord.y)
        )) * col;
    } else {
        color = vec4(1.0, 1.0, 1.0, 1.0);
    }
}
