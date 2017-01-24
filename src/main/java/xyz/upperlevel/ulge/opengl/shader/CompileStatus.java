package xyz.upperlevel.ulge.opengl.shader;

import java.util.Objects;

public class CompileStatus {

    public static final CompileStatus
            OK = new CompileStatus("OK"),
            ERROR = new CompileStatus("ERROR");

    public final String log;

    public CompileStatus(String log) {
        Objects.requireNonNull(log, "Compile status log cannot be null");
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public boolean isOk() {
        return this.equals(OK);
    }
}