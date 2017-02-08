package xyz.upperlevel.ulge.opengl.texture;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

public final class TextureParameter {

    private TextureParameter() {
    }

    public static class Type {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Wrapping {
            /**
             * Translate on X axis (S for texture coordinates).
             */
            public static final Type S = new Type(GL_TEXTURE_WRAP_S);

            /**
             * Translate on Y axis (T for texture coordinates).
             */
            public static final Type T = new Type(GL_TEXTURE_WRAP_T);
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Filter {
            /**
             * Filter used when scaling the image down.
             */
            public static final Type MIN = new Type(GL_TEXTURE_MIN_FILTER);

            /**
             * Filter used when scaling the image up.
             */
            public static final Type MAG = new Type(GL_TEXTURE_MAG_FILTER);
        }

        @Getter
        private int id;

        public Type(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Type && id == ((Type) object).id;
        }
    }

    public static class Value {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Wrapping {
            /**
             * Generates a repeating path to fill remaining space.
             */
            public static final Value REPEAT = new Value(GL_REPEAT);

            /**
             * Generates repeating path to fill remaining space, mirrored when the integer
             * part of the coordinate is odd.
             */
            public static final Value MIRRORED_REPEAT = new Value(GL_MIRRORED_REPEAT);

            /**
             * Clamp coordinates between 0 and 1.
             */
            public static final Value CLAMP_TO_EDGE = new Value(GL_CLAMP_TO_EDGE);

            /**
             * The coordinates that fall outside the range will be given a
             * specified border color.
             */
            public static final Value CLAMP_TO_BORDER = new Value(GL_CLAMP_TO_BORDER);
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Filter {
            /**
             * Returns the pixel that is closest to the coordinates.
             */
            public static final Value LINEAR = new Value(GL_LINEAR);
            /**
             * Returns the weighted average of the 4 pixels surrounding the given coordinates.
             */
            public static final Value NEAREST = new Value(GL_NEAREST);
        }

        @Getter
        private int id;

        public Value(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Type && id == ((Type) object).id;
        }
    }
}
