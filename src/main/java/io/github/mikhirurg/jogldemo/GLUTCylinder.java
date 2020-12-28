package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLUTCylinder extends GLUTObject {

    private Vector3D shift;
    private Vector3D scale;
    private Color color;
    private final double radius;
    private final double height;

    public GLUTCylinder(Vector3D shift, Vector3D scale, Color color, double radius, double height) {
        super(shift, scale, color);
        this.shift = shift;
        this.scale = scale;
        this.color = color;
        this.radius = radius;
        this.height = height;
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {
        super.draw(gl2, glut);
        gl2.glRotatef(90f, 1.0f, 0f, 0);
        gl2.glTranslatef(
                (float) getShift().getX(),
                (float) getShift().getY(),
                (float) (getShift().getZ() - height / 2.0)
        );

        gl2.glColor4f(
                getColor().getRed() / 255f,
                getColor().getGreen() / 255f,
                getColor().getBlue() / 255f,
                getColor().getAlpha() / 255f
        );
        glut.glutSolidCylinder(radius, height, 20, 20);

        gl2.glTranslatef(
                (float) -getShift().getX(),
                (float) -getShift().getY(),
                (float) -(getShift().getZ() - height / 2.0)
        );
        gl2.glRotatef(-90f, 1.0f, 0f, 0);
    }

    @Override
    public Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near) {
        Vector3D intersectPoint;
        Vector3D normalVector;

        Vector3D point = ray.getPoint();
        Vector3D vector = ray.getVector();

        double D = Math.pow(2 * vector.getX() * point.getX() + 2 * vector.getZ() * point.getZ(), 2) -
                4 * (vector.getX() * vector.getX() + vector.getZ() * vector.getZ()) *
                        (point.getX() * point.getX() + point.getZ() * point.getZ() - radius * radius);

        if (D < 0) {
            double t = 1000;
            return Cortege.of(t, null, null);
        }

        double t = (-(2 * vector.getX() * point.getX() + 2 * vector.getZ() * point.getZ()) + Math.sqrt(D)) /
                (2 * (vector.getX() * vector.getX() + vector.getZ() * vector.getZ()));

        if (near) {
            t = Math.min(t, (-(2 * vector.getX() * point.getX() + 2 * vector.getZ() * point.getZ()) - Math.sqrt(D)) /
                    (2 * (vector.getX() * vector.getX() + vector.getZ() * vector.getZ())));
        } else {
            t = Math.max(t, (-(2 * vector.getX() * point.getX() + 2 * vector.getZ() * point.getZ()) - Math.sqrt(D)) /
                    (2 * (vector.getX() * vector.getX() + vector.getZ() * vector.getZ())));
        }

        intersectPoint = Vector3D.of(
                vector.getX() * t + point.getX(),
                vector.getY() * t + point.getY(),
                vector.getZ() * t + point.getZ()
        );

        if (intersectPoint.getY() > height / 2.0 || intersectPoint.getY() < -height / 2.0) {
            t = 1000;
            return Cortege.of(t, null, null);
        }

        double B = Math.sqrt((4 * vector.getX() * vector.getX()) + (4 * vector.getZ() * vector.getZ()));

        normalVector = Vector3D.of(
                2 * intersectPoint.getX() / B,
                0,
                2 * intersectPoint.getZ() / B

        ).getNormal();

        return Cortege.of(t, intersectPoint, normalVector);
    }

    @Override
    public void setShift(Vector3D shift) {
        this.shift = shift;
    }

    @Override
    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Vector3D getShift() {
        return shift;
    }

    @Override
    public Vector3D getScale() {
        return scale;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
