package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import java.awt.*;

public class Ray {
    private final Vector3D point;
    private final Vector3D vector;
    private final Color color;

    public Ray(Vector3D point, Vector3D vector, Color color) {
        this.point = point;
        this.vector = vector;
        this.color = color;
    }

    public void draw(GL2 gl2, double t) {
        gl2.glColor4f(
                getColor().getRed() / 255f,
                getColor().getGreen() / 255f,
                getColor().getBlue() / 255f,
                getColor().getAlpha() / 255f
        );
        gl2.glBegin(GL.GL_LINES);
        gl2.glVertex3f((float) point.getX(), (float) point.getY(), (float) point.getZ());
        gl2.glVertex3f(
                (float) (vector.getX() * t + point.getX()),
                (float) (vector.getY() * t + point.getY()),
                (float) (vector.getZ() * t + point.getZ())
        );
        gl2.glEnd();
    }

    public Ray refract(Vector3D vector, Vector3D point, Vector3D normal, double n1, double n2) {
        Vector3D newVector;

        Vector3D v1 = vector.getNormal().mul(n1);

        newVector = v1.add(
                normal.mul(
                        Vector3D.scalarMul(v1, normal) *
                                (Math.sqrt((n2 * n2 - n1 * n1) /
                                        (Math.pow(Vector3D.scalarMul(v1, normal), 2))
                                        + 1.0) - 1.0)
                )
        ).getNormal();

        return new Ray(point, newVector, color);
    }

    public Vector3D getPoint() {
        return point;
    }

    public Vector3D getVector() {
        return vector;
    }

    public Color getColor() {
        return color;
    }
}
