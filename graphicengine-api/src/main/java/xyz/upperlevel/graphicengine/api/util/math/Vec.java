package xyz.upperlevel.graphicengine.api.util.math;

import java.nio.ByteBuffer;

public interface Vec {

    int size();

    int byteSize();

    ByteBuffer buffer();
}
