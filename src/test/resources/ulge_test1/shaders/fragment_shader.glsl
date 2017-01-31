#version 330 core

struct Material
{
    sampler2D diffuse;  // first sampler
    sampler2D specular; // second sampler
    float shininess;
};

in vec3 Normal;
in vec2 TexCoords;
in vec3 FragmentPosition;

uniform vec3 objectColor;
uniform vec3 viewPosition;

uniform Material material;

out vec4 color;

struct DirLight
{
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

uniform DirLight directionLight;

vec3 CalcDirLight(DirLight light, vec3 viewDir)
{
    // Ambient
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

    // Diffuse
    vec3 lightDir = normalize(-light.direction);
    float diffMul = max(dot(Normal, lightDir), 0);
    vec3 diffuse = light.diffuse * diffMul * vec3(texture(material.diffuse, TexCoords));

    // Specular
    vec3 reflectDir = reflect(-lightDir, Normal);
    float specMul = pow(max(dot(viewDir, reflectDir), 0), material.shininess);
    vec3 specular = light.specular * specMul * vec3(texture(material.specular, TexCoords));

    return ambient + diffuse + specular;
}

struct PointLight
{
    vec3 position;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;

    float constant;
    float linear;
    float quadratic;
};

uniform int pointLightsCount;
uniform PointLight pointLights[100];

vec3 CalcPointLight(PointLight light, vec3 viewDir)
{
   // Ambient
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

    // Diffuse
    vec3 lightDir = normalize(light.position - FragmentPosition);
    float diffMul = max(dot(Normal, lightDir), 0);
    vec3 diffuse = light.diffuse * diffMul * vec3(texture(material.diffuse, TexCoords));

    // Specular
    vec3 reflectDir = reflect(-lightDir, Normal);
    float specMul = pow(max(dot(viewDir, reflectDir), 0), material.shininess);
    vec3 specular = light.specular * specMul * vec3(texture(material.specular, TexCoords));

    // Attenuation
    float distance = length(light.position - FragmentPosition);
    float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance));

    ambient *= attenuation;
    diffuse *= attenuation;
    specular *= attenuation;

    return ambient + diffuse + specular;
}

void main()
{
    vec3 result = vec3(0);
    vec3 viewDir = normalize(viewPosition - FragmentPosition);

    // Direction Light
    result += CalcDirLight(directionLight, viewDir);

    // Point Lights
    int i;
    for (i = 0; i < pointLightsCount; i++)
        result += CalcPointLight(pointLights[i], viewDir);

	float MAX_DIST = 10f;
	float distance = length(viewPosition - FragmentPosition);
	
    gl_FragColor = vec4(result * (max(MAX_DIST - distance, 0.75f) / MAX_DIST), 1f);
}