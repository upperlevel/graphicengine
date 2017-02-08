package xyz.upperlevel.ulge.opengl.buffer;

import lombok.Getter;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS;

public enum FBOStatus {

    COMPLETE(GL_FRAMEBUFFER_COMPLETE),
    UNDEFINED(GL_FRAMEBUFFER_UNDEFINED),
    INCOMPLETE_ATTACHMENT(GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT),
    INCOMPLETE_MISSING_ATTACHMENT(GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT),
    INCOMPLETE_DRAW_BUFFER(GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER),
    ATTACHEMNT_OBJECT_TYPE(GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE),
    INCOMPLETE_READ_BUFFER(GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER),
    UNSUPPORTED(GL_FRAMEBUFFER_UNSUPPORTED),
    INCOMPLETE_MULTISAMPLE(GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE),
    INCOMPLETE_LAYER_TARGETS(GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS);

    @Getter
    private int id;

    FBOStatus(int id) {
        this.id = id;
    }

    public static FBOStatus getStatus(int id) {
        for (FBOStatus status : values() )
            if (status.getId() == id)
                return status;
        return null;
    }
}
