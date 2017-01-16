package xyz.upperlevel.graphicengine.api.opengl.shader;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompileStatus {

    public static final CompileStatus
            OK = new CompileStatus("OK"),
            ERROR = new CompileStatus("ERROR");

    @Getter @NonNull public final String log;

    public boolean isOk() {
        return this == OK;
    }
}