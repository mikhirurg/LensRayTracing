package io.github.mikhirurg.lensraytracing;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLTrianglePyramid extends GLObject {

    private static final double[] glPyramid = {
            -1, -1, 1,
            0, 1, 0,
            1, -1, 1,

            1, -1, 1,
            0, 1, 0,
            1, -1, -1,

            1, -1, -1,
            0, 1, 0,
            -1, -1, -1,

            -1, -1, -1,
            0, 1, 0,
            -1, -1, 1,

            -1, -1, 1,
            1, -1, -1,
            -1, -1, -1,

            -1, -1, 1,
            1, -1, 1,
            1, -1, -1
    };

    private static final double[] vertices = {
            -1, -1, 1,
            0, 1, 0,

            0, 1, 0,
            1, -1, 1,

            -1, -1, 1,
            1, -1, 1,

            1, -1, 1,
            1, -1, -1,

            0, 1, 0,
            1, -1, -1,

            -1, -1, -1,
            0, 1, 0,

            -1, -1, -1,
            -1, -1, 1,

            -1, -1, -1,
            1, -1, -1
    };

    public GLTrianglePyramid(Vector3D shift, Vector3D scale, Color color) {
        super(glPyramid, vertices, null, shift, scale, color, GL2.GL_TRIANGLES);
    }

    public GLTrianglePyramid() {
        super(glPyramid, vertices, null, GL2.GL_TRIANGLES);
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {
        super.draw(gl2, glut);
    }
}
