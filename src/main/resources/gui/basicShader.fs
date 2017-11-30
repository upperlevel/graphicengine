#version 410 core

in vec2 texCoord;

uniform sampler2D tex;
uniform vec4 color;

out vec4 result;

void main() {
    result = color * texture(tex, texCoord);
}