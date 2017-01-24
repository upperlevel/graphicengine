package xyz.upperlevel.ulge.util.math;

import java.nio.ByteBuffer;

public interface Vec {

    int size();

    int byteSize();

    ByteBuffer buffer();
}
