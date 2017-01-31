#version 330 core

in vec3 position;
in vec3 normal;
in vec2 texCoords;

uniform mat4 camera;
uniform mat4 model;

out vec2 TexCoords;
out vec3 Normal;
out vec3 FragmentPosition;

void main()
{
    gl_Position = camera * (vec4(position, 1.0) * model);
    TexCoords = texCoords;
    Normal = normalize(mat3(transpose(inverse(model))) * normal);
    FragmentPosition = vec3(model * vec4(position, 1.0));
}