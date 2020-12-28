package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLObject implements Drawable {
    private final double[] data;
    private final double[] vertices;
    private final double[] normals;
    private Vector3D shift;
    private Vector3D scale;
    private Color color;
    private boolean intersectable;
    private final int renderType;

    GLObject(double[] data, double[] vertices, double[] normals, Vector3D shift, Vector3D scale, Color color, int renderType) {
        this.data = data;
        this.shift = shift;
        this.scale = scale;
        this.color = color;
        this.vertices = vertices;
        this.renderType = renderType;
        this.normals = normals;
    }

    GLObject(double[] data, double[] vertices, double[] normals, int renderType) {
        this(data, vertices, normals, Vector3D.ZERO, Vector3D.ONE, Color.WHITE, renderType);
    }

    public void draw(GL2 gl2, GLUT glut) {

        gl2.glBegin(renderType);
        gl2.glColor4f(
                getColor().getRed() / 255.0f,
                getColor().getGreen() / 255.0f,
                getColor().getBlue() / 255.0f,
                getColor().getAlpha() / 255.0f
        );
        for (int i = 0; i < data.length; i += 3) {
            gl2.glNormal3f((float) normals[i], (float) normals[i + 1], (float) normals[i + 2]);
            gl2.glVertex3f(
                    (float) (data[i] * scale.getX() + shift.getX()),
                    (float) (data[i + 1] * scale.getY() + shift.getY()),
                    (float) (data[i + 2] * scale.getZ() + shift.getZ())
            );
        }
        gl2.glEnd();

        gl2.glLineWidth(1.0f);
        gl2.glBegin(GL2.GL_LINES);
        gl2.glColor4f(1, 1, 1, 1);
        for (int i = 0; i < vertices.length; i += 3) {
            double k = 1.01;
            gl2.glVertex3f(
                    (float) (vertices[i] * scale.getX() * k + shift.getX()),
                    (float) (vertices[i + 1] * scale.getY() * k + shift.getY()),
                    (float) (vertices[i + 2] * scale.getZ() * k + shift.getZ())
            );
        }
        gl2.glEnd();
    }

    public double[] getData() {
        return data;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public Vector3D getScale() {
        return scale;
    }

    public void setShift(Vector3D shift) {
        this.shift = shift;
    }

    public Vector3D getShift() {
        return shift;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near) {
        return Cortege.of(1000.0, null, null);
    }

    @Override
    public void setIntersectable(boolean intersectable) {
        this.intersectable = intersectable;
    }

    @Override
    public boolean getIntersetable() {
        return intersectable;
    }

    public boolean isIntersectable() {
        return intersectable;
    }
}
