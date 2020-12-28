package io.github.mikhirurg.jogldemo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;

public class GLUTObject implements Drawable {
    private Vector3D shift;
    private Vector3D scale;
    private Color color;
    private boolean intersectable;

    GLUTObject(Vector3D shift, Vector3D scale, Color color) {
        this.shift = shift;
        this.scale = scale;
        this.color = color;
    }

    @Override
    public void draw(GL2 gl2, GLUT glut) {

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
    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    @Override
    public void setShift(Vector3D shift) {
        this.shift = shift;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Cortege<Double, Vector3D, Vector3D> intersect(Ray ray, boolean near) {
        return null;
    }

    @Override
    public void setIntersectable(boolean intersectable) {
        this.intersectable = intersectable;
    }

    @Override
    public boolean getIntersetable() {
        return intersectable;
    }
}
