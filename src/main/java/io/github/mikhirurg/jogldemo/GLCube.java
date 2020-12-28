package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLCube extends GLObject {

    private static final double[] glCube = {
            -1, 1, 1,
            1, 1, 1,
            1, -1, 1,
            -1, -1, 1,

            1, 1, 1,
            1, 1, -1,
            1, -1, -1,
            1, -1, 1,

            1, 1, -1,
            -1, 1, -1,
            -1, -1, -1,
            1, -1, -1,

            -1, 1, -1,
            -1, 1, 1,
            -1, -1, 1,
            -1, -1, -1,

            -1, 1, -1,
            1, 1, -1,
            1, 1, 1,
            -1, 1, 1,

            -1, -1, 1,
            1, -1, 1,
            1, -1, -1,
            -1, -1, -1
    };

    private static final double[] glNormals = {
            0, 0, 1,
            0, 0, 1,
            0, 0, 1,
            0, 0, 1,

            1, 0, 0,
            1, 0, 0,
            1, 0, 0,
            1, 0, 0,

            0, 0, -1,
            0, 0, -1,
            0, 0, -1,
            0, 0, -1,

            -1, 0, 0,
            -1, 0, 0,
            -1, 0, 0,
            -1, 0, 0,

            0, 1, 0,
            0, 1, 0,
            0, 1, 0,
            0, 1, 0,

            0, -1, 0,
            0, -1, 0,
            0, -1, 0,
            0, -1, 0,
    };

    private static final double[] vertices = {
            -1, 1, 1,
            1, 1, 1,

            -1, 1, 1,
            -1, -1, 1,

            -1, -1, 1,
            1, -1, 1,

            1, 1, 1,
            1, -1, 1,

            -1, 1, 1,
            -1, 1, -1,

            1, 1, 1,
            1, 1, -1,

            1, -1, 1,
            1, -1, -1,

            -1, -1, 1,
            -1, -1, -1,

            -1, 1, -1,
            1, 1, -1,

            1, 1, -1,
            1, -1, -1,

            1, -1, -1,
            -1, -1, -1,

            -1, -1, -1,
            -1, 1, -1
    };

    public GLCube(Vector3D shift, Vector3D scale, Color color) {
        super(glCube, vertices, glNormals, shift, scale, color, GL2.GL_QUADS);
    }

    public GLCube() {
        super(glCube, vertices, glNormals, GL2.GL_QUADS);
    }

    @Override
    public Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near) {

        if (!getIntersetable()) {
            double t = 1000;
            return Cortege.of(t, null, null);
        }

        Vector3D intersectPoint;
        Vector3D normalVector;
        double t;

        Vector3D point = ray.getPoint();
        Vector3D vector = ray.getVector();

        if (near) {
            t = (1 * getScale().getZ() + getShift().getZ() - point.getZ()) / vector.getZ();
        } else {
            t = (-1 * getScale().getZ() + getShift().getZ() - point.getZ()) / vector.getZ();
        }

        intersectPoint = Vector3D.of(
                vector.getX() * t + point.getX(),
                vector.getY() * t + point.getY(),
                vector.getZ() * t + point.getZ()
        );

        if (intersectPoint.getY() > 1 * getScale().getY() + getShift().getY() ||
                intersectPoint.getY() < -1 * getScale().getY() + getShift().getY() ||
                intersectPoint.getX() > 1 * getScale().getX() + getShift().getX() ||
                intersectPoint.getX() < -1 * getScale().getX() + getShift().getX()) {
            t = 1000;
            return Cortege.of(t, null, null);
        }

        if (near) {
            normalVector = Vector3D.of(0, 0, 1).getNormal();
        } else {
            normalVector = Vector3D.of(0, 0, -1).getNormal();
        }

        return Cortege.of(t, intersectPoint, normalVector);
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {
        gl2.glBegin(GL2.GL_QUADS);
        super.draw(gl2, glut);
    }
}
